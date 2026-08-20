import java.util.Objects;

class LibraryMember {
    private String memberId;
    private String name;
    private String email;

    LibraryMember(String memberId, String name, String email) {
        this.memberId = memberId;
        this.name = name;
        this.email = email;
    }

    @Override
    public String toString() {
        return "LibraryMember{id='" + memberId
                + "', name='" + name
                + "', email='" + email + "'}";
    }

    @Override
    public boolean equals(Object other) {

        if (this == other) {
            return true;
        }

        if (!(other instanceof LibraryMember)) {
            return false;
        }

        LibraryMember member = (LibraryMember) other;

        return Objects.equals(memberId, member.memberId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(memberId);
    }
}

public class MemberEqualityPractice {

    public static void main(String[] args) {

        LibraryMember member1 =
                new LibraryMember(
                        "M001",
                        "Amy",
                        "amy@gmail.com");

        LibraryMember member2 =
                new LibraryMember(
                        "M001",
                        "Amy Chen",
                        "amychen@gmail.com");

        LibraryMember member3 =
                new LibraryMember(
                        "M002",
                        "Ben",
                        "ben@gmail.com");

        System.out.println(member1);
        System.out.println(member2);
        System.out.println(member3);

        System.out.println("\n== 比較：");
        System.out.println("member1 == member2："
                + (member1 == member2));

        System.out.println("\nequals() 比較：");
        System.out.println("member1.equals(member2)："
                + member1.equals(member2));
        System.out.println("member1.equals(member3)："
                + member1.equals(member3));
        System.out.println("\nnull 比較：");
        System.out.println("member1.equals(null)："
                + member1.equals(null));
    }
}