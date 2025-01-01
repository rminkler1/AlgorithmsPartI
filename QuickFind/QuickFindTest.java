package QuickFind;

import java.util.Arrays;

public class QuickFindTest {
    public static void main(String[] args) {
        QuickFind testObject = new QuickFind(10);

        System.out.println(testObject);

        testObject.union(0, 5);
        testObject.union(5, 6);
        testObject.union(1, 2);
        testObject.union(2, 7);
        testObject.union(3, 4);
        testObject.union(8, 3);
        testObject.union(4, 9);


        System.out.println(testObject);

        System.out.println(Arrays.toString(testObject.find(9)));

        System.out.println(testObject.connected(3, 9));
    }
}
