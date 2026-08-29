import java.util.ArrayList;
import java.util.List;

public class MemberBstIndex {

    static class Member {
        final int memberId;
        final String name;
        String email;

        Member(int memberId, String name, String email) {
            this.memberId = memberId;
            this.name = name;
            this.email = email;
        }

        @Override
        public String toString() {
            return memberId + " " + name + " email=" + email;
        }
    }

    static class Node {
        Member data;
        Node left;
        Node right;

        Node(Member data) {
            this.data = data;
        }
    }

    static class MemberBst {
        private Node root;

        boolean add(Member member) {
            if (member == null) {
                return false;
            }

            if (member.email == null ||
                member.email.isBlank()) {
                return false;
            }

            if (root == null) {
                root = new Node(member);
                return true;
            }

            Node current = root;

            while (true) {

                if (member.memberId == current.data.memberId) {
                    return false;
                }

                if (member.memberId < current.data.memberId) {

                    if (current.left == null) {
                        current.left = new Node(member);
                        return true;
                    }

                    current = current.left;

                } else {

                    if (current.right == null) {
                        current.right = new Node(member);
                        return true;
                    }

                    current = current.right;
                }
            }
        }

        Member find(int memberId) {
            Node current = root;

            while (current != null) {

                if (memberId == current.data.memberId) {
                    return current.data;
                }

                if (memberId < current.data.memberId) {
                    current = current.left;
                } else {
                    current = current.right;
                }
            }

            return null;
        }

        boolean updateEmail(int memberId, String email) {

            if (email == null || email.isBlank()) {
                return false;
            }

            Member member = find(memberId);

            if (member == null) {
                return false;
            }

            member.email = email;

            return true;
        }

        boolean remove(int memberId) {

            if (find(memberId) == null) {
                return false;
            }

            root = remove(root, memberId);

            return true;
        }

        private Node remove(Node node, int memberId) {

            if (memberId < node.data.memberId) {

                node.left = remove(
                        node.left,
                        memberId
                );

            } else if (memberId > node.data.memberId) {

                node.right = remove(
                        node.right,
                        memberId
                );

            } else {

                if (node.left == null) {
                    return node.right;
                }

                if (node.right == null) {
                    return node.left;
                }

                Node successor = minimum(node.right);

                node.data = successor.data;

                node.right = remove(
                        node.right,
                        successor.data.memberId
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

        List<Member> inorder() {

            List<Member> result = new ArrayList<>();

            inorder(root, result);

            return result;
        }

        private void inorder(
                Node node,
                List<Member> result) {

            if (node == null) {
                return;
            }

            inorder(node.left, result);

            result.add(node.data);

            inorder(node.right, result);
        }
    }

    public static void main(String[] args) {

        MemberBst index = new MemberBst();

        System.out.println(
                "add=" +
                index.add(
                        new Member(
                                300,
                                "Mina",
                                "mina@example.com"
                        )
                )
        );

        System.out.println(
                "add=" +
                index.add(
                        new Member(
                                100,
                                "Leo",
                                "leo@example.com"
                        )
                )
        );

        System.out.println(
                "add=" +
                index.add(
                        new Member(
                                500,
                                "Nora",
                                "nora@example.com"
                        )
                )
        );

        System.out.println(
                "add=" +
                index.add(
                        new Member(
                                200,
                                "Ivy",
                                "ivy@example.com"
                        )
                )
        );

        System.out.println(
                "duplicate=" +
                index.add(
                        new Member(
                                100,
                                "Other",
                                "other@example.com"
                        )
                )
        );

        System.out.println(
                "blankEmail=" +
                index.add(
                        new Member(
                                400,
                                "Jack",
                                "   "
                        )
                )
        );

        System.out.println(
                "find=" +
                index.find(200)
        );

        System.out.println(
                "updateEmail=" +
                index.updateEmail(
                        200,
                        "ivy2026@example.com"
                )
        );

        System.out.println(
                "missingUpdate=" +
                index.updateEmail(
                        999,
                        "test@example.com"
                )
        );

        System.out.println(
                "remove=" +
                index.remove(300)
        );

        System.out.println(
                "missingRemove=" +
                index.remove(999)
        );

        System.out.println("--- Inorder Report ---");

        for (Member member : index.inorder()) {
            System.out.println(member);
        }
    }
}