/*
 * Robert Minkler Implementation 3/8/25
 * The size array tracks the size of the tree under the root.
 * the id array points to the parent node of the tree until the root is found.
 * the root position points to itself.
 */

import java.util.Arrays;

public class WeightedQUFind {

    // store root and tree size
    private final int[] id;
    private final int[] size;


    WeightedQUFind(int n) {

        // Initialize tree and size array
        id = new int[n];
        size = new int[n];

        // Populate the tree and size array
        for (int i = 0; i < n; i++) {
            id[i] = i;
            size[i] = 1;
        }
    }

    public void union(int p, int q) {

        // Get the root of p and q
        int rootOfP = root(p);
        int rootOfQ = root(q);

        // If they are already joined, return
        if (rootOfP == rootOfQ) return;

        // Place smaller tree under larger tree
        if (size[rootOfP] < size[rootOfQ]) {
            // p under q
            id[rootOfP] = rootOfQ;
            size[rootOfQ] += size[rootOfP];  // update size
        }
        else {
            // q under p
            id[rootOfQ] = rootOfP;
            size[rootOfP] += size[rootOfQ]; // update size
        }

    }

    public boolean find(int p, int q) {
        return root(p) == root(q);
    }

    private int root(int n) {

        // Check if index == value which means it is the root.
        while (id[n] != n) {
            id[n] = id[id[n]];  // path compression to flatten the tree
            n = id[n];
        }

        // Return the root
        return n;
    }


    // getters for testing
    public String getId() {
        return Arrays.toString(id);
    }

    public String getSize() {
        return Arrays.toString(size);
    }

    public static void main(String[] args) {
        WeightedQUFind container = new WeightedQUFind(10);

        container.union(0, 1);
        container.union(1, 2);
        container.union(1, 3);
        container.union(3, 4);
        container.union(0, 4);

        System.out.println(container.getId());
        System.out.println(container.getSize());
        System.out.println();

        container.union(9, 8);
        container.union(8, 5);
        container.union(5, 4);

        System.out.println(container.find(5, 9));

        System.out.println(container.getId());
        System.out.println(container.getSize());
        System.out.println();

        System.out.println(container.find(8, 0));
        System.out.println(container.getId());
        System.out.println(container.getSize());
        System.out.println();
    }

}
