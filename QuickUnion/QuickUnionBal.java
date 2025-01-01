package QuickUnion;

import java.util.Arrays;

public class QuickUnionBal {

    private final int[] id;
    private final int[] size;

    public QuickUnionBal(int length) {
        id = new int[length];
        size = new int[length];

        Arrays.fill(size, 1);

        for (int i = 0; i < id.length; i++) {
            id[i] = i;
        }
    }

    public void union(int p, int q) {
        int pRoot = getRoot(p);
        int qRoot = getRoot(q);

        if (size[pRoot] < size[qRoot]) {
            size[qRoot] += size[pRoot];
            id[pRoot] = qRoot;
        } else {
            size[pRoot] += size[qRoot];
            id[qRoot] = pRoot;
        }
    }

    public Boolean connected(int p, int q) {
        return getRoot(p) == getRoot(q);
    }

    public int getRoot(int i) {
        while (i != id[i]) {
            id[i] = id[id[i]];  // Path compression
            i = id[i];
        }
        return i;
    }

    public int[] getId() {
        return id;
    }

    public int find(int n) {
        int i = id.length - 1;
        while (!connected(n, i)) i--;
        return i;
    }

    @Override
    public String toString() {
        return Arrays.toString(getId());
    }
}
