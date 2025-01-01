package QuickFind;

// Algorithms Course
// Part 1, Module 2

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class QuickFind {

    private final int[] id;

    public QuickFind(int length) {
        id = new int[length];

        for (int i = 0; i < length; i++) {
            id[i] = i;
        }
    }

    //    Returns true if the nodes are connected to the same group
    public boolean connected(int p, int q) {
        return id[p] == id[q];
    }

    // Get all locations that are connected as a group
    public int[] find(int indexPosition) {
        List<Integer> connected = new ArrayList<>();

        for (int i = 0; i < id.length; i++) {
            if (indexPosition >= 0 && indexPosition < id.length && id[i] == id[indexPosition]) {
                connected.add(i);
            }
        }

        // Convert from ArrayList<Integer> to int[]
        int[] result = new int[connected.size()];
        int i = 0;

        for (Integer value : connected) {
            result[i++] = value;
        }
        return result;
    }

    //    Change all entries with id p to id q so they are all part of the same group
    public void union(int p, int q) {
        int pid = id[p];
        int qid = id[q];

        for (int i = 0; i < id.length; i++) {
            if (id[i] == pid) {
                id[i] = qid;
            }
        }
    }

    public int[] getId() {
        return id;
    }

    @Override
    public String toString() {
        return Arrays.toString(getId());
    }
}
