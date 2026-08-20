class Book {
    private String id;
    private String name;
    private int price;
    private int stock;

    Book(String id, String name, int price, int stock) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.stock = stock;
    }

    int getPrice() {
        return price;
    }
    int getStock() {
        return stock;
    }
    int getStockValue() {
        return price * stock;
    }

    @Override
    public String toString() {
        return id + " " + name
                + " price=" + price
                + " stock=" + stock;
    }
}

public class BookArrayReport {

    public static void main(String[] args) {

        Book[] books = {
            new Book("01", "A", 500, 10),
            new Book("02", "B", 650, 3),
            new Book("03", "V", 450, 5),
            new Book("04", "X", 800, 2)
        };

        System.out.println("ALL：");

        for (Book book : books) {
            System.out.println(book);
        }

        int totalValue = 0;

        for (Book book : books) {
            totalValue += book.getStockValue();
        }

        System.out.println("\nALL　ｐｒｉｃｅ　：" + totalValue);

        Book highestPriceBook = books[0];

        for (Book book : books) {
            if (book.getPrice() > highestPriceBook.getPrice()) {
                highestPriceBook = book;
            }
        }

        System.out.println("\nA：");
        System.out.println(highestPriceBook);

        System.out.println("\n庫存小於或等於 3 的書：");

        for (Book book : books) {
            if (book.getStock() <= 3) {
                System.out.println(book);
            }
        }
    }
}