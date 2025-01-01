/* *****************************************************************************
 *  Name:              Robert Minkler
 *  Coursera User ID:  1ed726f5c48df1d367a6fd1d8cf5bc11
 *  Last modified:     December 23, 2024
 **************************************************************************** */

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdRandom;

public class RandomWord {
    public static void main(String[] args) {

        String champion = "";
        int i = 1;

        while (!StdIn.isEmpty()) {
            String nextLine = StdIn.readString();

            if (StdRandom.bernoulli(1.0 / i)) {
                champion = nextLine;
            }

            i++;
        }

        System.out.println(champion);
    }
}
