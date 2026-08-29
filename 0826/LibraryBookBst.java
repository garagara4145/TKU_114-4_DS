import java.util.ArrayList;
import java.util.List;

public class LibraryBookBst {

    static class Book {
        final String isbn;
        final String title;
        final String author;
        boolean available;

        Book(
                String isbn,
                String title,
                String author,
                boolean available) {

            this.isbn = isbn;
            this.title = title;
            this.author = author;
            this.available = available;
        }

        @Override
        public String toString() {
            return isbn + " " + title
                    + " author=" + author
                    + " available=" + available;
        }
    }

    static class Node {
        Book data;
        Node left;
        Node right;

        Node(Book data) {
            this.data = data;
        }
    }

    static class BookBst {
        private Node root;

        boolean add(Book book) {

            if (book == null) {
                return false;
            }

            if (book.isbn == null ||
                    book.isbn.isBlank()) {
                return false;
            }

            if (root == null) {
                root = new Node(book);
                return true;
            }

            Node current = root;

            while (true) {

                int compare =
                        book.isbn.compareTo(
                                current.data.isbn
                        );

                if (compare == 0) {
                    return false;
                }

                if (compare < 0) {

                    if (current.left == null) {
                        current.left =
                                new Node(book);
                        return true;
                    }

                    current = current.left;

                } else {

                    if (current.right == null) {
                        current.right =
                                new Node(book);
                        return true;
                    }

                    current = current.right;
                }
            }
        }

        Book find(String isbn) {

            if (isbn == null) {
                return null;
            }

            Node current = root;

            while (current != null) {

                int compare =
                        isbn.compareTo(
                                current.data.isbn
                        );

                if (compare == 0) {
                    return current.data;
                }

                if (compare < 0) {
                    current = current.left;
                } else {
                    current = current.right;
                }
            }

            return null;
        }

        boolean borrow(String isbn) {

            Book book = find(isbn);

            if (book == null) {
                return false;
            }

            if (!book.available) {
                return false;
            }

            book.available = false;

            return true;
        }

        boolean returnBook(String isbn) {

            Book book = find(isbn);

            if (book == null) {
                return false;
            }

            if (book.available) {
                return false;
            }

            book.available = true;

            return true;
        }

        boolean remove(String isbn) {

            Book book = find(isbn);

            if (book == null) {
                return false;
            }

            if (!book.available) {
                return false;
            }

            root = remove(root, isbn);

            return true;
        }

        private Node remove(
                Node node,
                String isbn) {

            int compare =
                    isbn.compareTo(
                            node.data.isbn
                    );

            if (compare < 0) {

                node.left =
                        remove(
                                node.left,
                                isbn
                        );

            } else if (compare > 0) {

                node.right =
                        remove(
                                node.right,
                                isbn
                        );

            } else {

                if (node.left == null) {
                    return node.right;
                }

                if (node.right == null) {
                    return node.left;
                }

                Node successor =
                        minimum(node.right);

                node.data = successor.data;

                node.right =
                        remove(
                                node.right,
                                successor.data.isbn
                        );
            }

            return node;
        }

        private Node minimum(Node node) {

            while (node.left != null) {
                node = node.left;
            }

            return node;
        }

        List<Book> isbnRange(
                String low,
                String high) {

            List<Book> result =
                    new ArrayList<>();

            if (low == null ||
                    high == null) {
                return result;
            }

            if (low.compareTo(high) > 0) {
                return result;
            }

            isbnRange(
                    root,
                    low,
                    high,
                    result
            );

            return result;
        }

        private void isbnRange(
                Node node,
                String low,
                String high,
                List<Book> result) {

            if (node == null) {
                return;
            }

            if (low.compareTo(
                    node.data.isbn) < 0) {

                isbnRange(
                        node.left,
                        low,
                        high,
                        result
                );
            }

            if (low.compareTo(
                    node.data.isbn) <= 0
                    && node.data.isbn
                    .compareTo(high) <= 0) {

                result.add(node.data);
            }

            if (node.data.isbn
                    .compareTo(high) < 0) {

                isbnRange(
                        node.right,
                        low,
                        high,
                        result
                );
            }
        }

        List<Book> inorder() {

            List<Book> result =
                    new ArrayList<>();

            inorder(root, result);

            return result;
        }

        private void inorder(
                Node node,
                List<Book> result) {

            if (node == null) {
                return;
            }

            inorder(node.left, result);

            result.add(node.data);

            inorder(node.right, result);
        }
    }

    public static void main(String[] args) {

        BookBst library =
                new BookBst();

        System.out.println(
                "add=" +
                library.add(
                        new Book(
                                "978001",
                                "Java Basics",
                                "Alice",
                                true
                        )
                )
        );

        System.out.println(
                "add=" +
                library.add(
                        new Book(
                                "978003",
                                "Data Structures",
                                "Bob",
                                true
                        )
                )
        );

        System.out.println(
                "add=" +
                library.add(
                        new Book(
                                "978005",
                                "Database",
                                "Carol",
                                true
                        )
                )
        );

        System.out.println(
                "add=" +
                library.add(
                        new Book(
                                "978002",
                                "Algorithms",
                                "David",
                                true
                        )
                )
        );

        System.out.println(
                "duplicate=" +
                library.add(
                        new Book(
                                "978001",
                                "Other Book",
                                "Other",
                                true
                        )
                )
        );

        System.out.println(
                "find=" +
                library.find("978002")
        );

        System.out.println(
                "borrow=" +
                library.borrow("978002")
        );

        System.out.println(
                "borrowAgain=" +
                library.borrow("978002")
        );

        System.out.println(
                "removeBorrowed=" +
                library.remove("978002")
        );

        System.out.println(
                "return=" +
                library.returnBook("978002")
        );

        System.out.println(
                "remove=" +
                library.remove("978002")
        );

        System.out.println(
                "missing=" +
                library.remove("999999")
        );

        System.out.println();

        System.out.println(
                "--- ISBN Range ---"
        );

        for (Book book :
                library.isbnRange(
                        "978001",
                        "978005")) {

            System.out.println(book);
        }

        System.out.println();

        System.out.println(
                "--- Inorder Report ---"
        );

        for (Book book :
                library.inorder()) {

            System.out.println(book);
        }
    }
}