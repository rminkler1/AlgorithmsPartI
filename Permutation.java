/* *****************************************************************************
 *  Name:              Robert Minkler
 *  Coursera User ID:  xxxxxx
 *  Last modified:     January 2, 2025
 **************************************************************************** */

import edu.princeton.cs.algs4.StdIn;

public class Permutation {
    public static void main(String[] args) {

        // Scanner reader = new Scanner(System.in);

        int k = Integer.parseInt(args[0]);

        RandomizedQueue<String> strings = new RandomizedQueue<>();

        for (int i = 0; i < k; i++) {
            strings.enqueue(StdIn.readString());
        }
        
        for (String str : strings) {
            System.out.println(str);
        }
    }
}
