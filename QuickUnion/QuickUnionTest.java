package QuickUnion;

import java.util.Arrays;

public class QuickUnionTest {
    public static void main(String[] args) {
        QuickUnionBal quTest = new QuickUnionBal(20);

        System.out.println(Arrays.toString(quTest.getId()));


        quTest.union(1, 2);
        quTest.union(2, 6);
        quTest.union(2, 9);

        System.out.println(quTest);
        System.out.println(quTest.find(6));


    }
}
