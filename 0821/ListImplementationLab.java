import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class ListImplementationLab {

    static void testList(String name, List<Integer> list) {
        list.add(10);
        list.add(20);
        list.add(30);

        list.add(1, 15);

        System.out.println(name);
        System.out.println("資料=" + list);
        System.out.println("搜尋20=" + list.contains(20));
        System.out.println("20的位置=" + list.indexOf(20));

        list.remove(Integer.valueOf(20));

        System.out.println("刪除20=" + list);

        int sum = 0;

        for (int value : list) {
            sum += value;
        }

        System.out.println("總和=" + sum);
        System.out.println("大小=" + list.size());
        System.out.println();
    }

    public static void main(String[] args) {
        List<Integer> arrayList =
                new ArrayList<>();

        List<Integer> linkedList =
                new LinkedList<>();

        testList("ArrayList", arrayList);
        testList("LinkedList", linkedList);

        System.out.println(
                "ArrayList 與 LinkedList 功能結果一致");

        System.out.println(
                "ArrayList：依索引存取通常較快，尾端新增通常有效率。");

        System.out.println(
                "LinkedList：節點透過引用連接，已知位置插入或刪除可避免搬移大量元素。");

        System.out.println(
                "LinkedList 使用 get(index) 時需要逐節走訪，因此大量索引存取成本較高。");
    }
}