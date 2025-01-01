import java.util.Arrays;

public class QuickUnion {

    private final int[] id;

    public QuickUnion(int length) {
        id = new int[length];

        for (int i = 0; i < id.length; i++) {
            id[i] = i;
        }
    }

    public void union(int p, int q) {
        id[getRoot(p)] = getRoot(q);
    }

    public boolean connected(int p, int q) {
        return getRoot(p) == getRoot(q);
    }

    public int getRoot(int i) {
        while (i != id[i]) {
            i = id[i];
        }
        return i;
    }

    public int[] getId() {
        return id;
    }

    @Override
    public String toString() {
        return Arrays.toString(getId());
    }
}
