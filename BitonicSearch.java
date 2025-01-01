/* *****************************************************************************
 *  Name:              Robert Minkler
 *  Coursera User ID:
 *  Last modified:     December 30, 2024
 **************************************************************************** */

public class BitonicSearch {
    public static void main(String[] args) {

        // Test arrays
        int[] a = { 1, 3, 5, 7, 9, 10, 8, 6, 4, 2, 0 };
        //          0   1   2  3    4    5     6    7   8   9   10  11 12  13  14  15
        int[] b = { 1, 23, 44, 234, 566, 235, 123, 90, 80, 70, 60, 50, 40, 30, 20, 10 };
        //           0   1   2   3   4   5  6   7    8   9  10  11  12  13  14  15  16  17   18
        int[] c = { 10, 20, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 60, 70, 80, 90, 100, 50 };
        //          0   1    2
        int[] d = { 10, 50, 11 };

        int[] e = {
                1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23,
                24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44,
                45, 46, 47, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61, 62, 63, 64, 65,
                66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86,
                87, 88, 89, 90, 91, 92, 93, 94, 95, 96, 97, 98, 99, 103, 104, 105, 106, 107, 108,
                109, 110, 111, 112, 113, 114, 115, 116, 117, 118, 119, 120, 121, 122, 123, 124, 125,
                126, 127, 128, 129, 130, 131, 132, 133, 134, 135, 136, 137, 138, 139, 140, 141, 142,
                143, 144, 145, 146, 147, 148, 149, 150, 151, 152, 153, 154, 155, 156, 157, 158, 159,
                160, 161, 162, 163, 164, 165, 166, 167, 168, 169, 170, 171, 172, 173, 174, 175, 176,
                177, 178, 179, 180, 181, 182, 183, 184, 185, 186, 187, 188, 189, 190, 191, 192, 193,
                194, 195, 196, 197, 198, 199, 200, 199, 198, 197, 196, 195, 194, 193, 192, 191, 190,
                189, 188, 187, 186, 185, 184, 183, 182, 181, 180, 179, 178, 177, 176, 175, 174, 173,
                172, 171, 170, 169, 168, 167, 166, 165, 164, 163, 162, 161, 160, 159, 158, 157, 156,
                155, 154, 153, 152, 151, 150, 149, 148, 147, 146, 145, 144, 143, 142, 141, 140, 139,
                138, 137, 136, 135, 134, 133, 132, 131, 130, 129, 128, 127, 126, 125, 124, 123, 122,
                121, 120, 119, 118, 117, 116, 115, 114, 113, 112, 111, 110, 109, 108, 107, 106, 105,
                104, 103, 102, 101, 100
        };


        for (int i = 0; i <= 10; i++) {
            System.out.println(i + " in a: " + searchBitonicArray(a, i));
        }
        System.out.println("566 in b: " + searchBitonicArray(b, 566));
        System.out.println("1 in b: " + searchBitonicArray(b, 1));
        System.out.println("10 in b: " + searchBitonicArray(b, 10));
        System.out.println("44 in b: " + searchBitonicArray(b, 44));
        System.out.println("10 in c: " + searchBitonicArray(c, 10));
        for (int i = 10; i <= 110; i += 10) {
            System.out.println(i + " in c: " + searchBitonicArray(c, i));
        }
        for (int i = 0; i <= 20; i++) {
            System.out.println(i + " in c: " + searchBitonicArray(c, i));
        }

        System.out.println("10 in d: " + searchBitonicArray(d, 10));
        System.out.println("50 in d: " + searchBitonicArray(d, 50));
        System.out.println("11 in d: " + searchBitonicArray(d, 11));

        for (int i = 0; i <= 200; i++) {
            System.out.println(i + " in e: " + searchBitonicArray(e, i));
        }

        System.out.println("TESTS COMPLETE");
    }

    public static int searchBitonicArray(int[] array, int num) {

        // Define Markers to track both binary search structures l1, l2, r1, r2
        // l = left - r = right
        int l1 = 0;
        int l2 = array.length - 1;
        int r1 = 0;
        int r2 = array.length - 1;

        int pointer;

        // Jump pointer between right and left side using this boolean
        boolean alternateRL = true;


        while (l1 <= l2 || r1 <= r2) {
            // Place the pointer between the right or left markers
            if (alternateRL) {
                pointer = (l2 - l1) / 2 + l1;
            }
            else {
                pointer = (r2 - r1) / 2 + r1;
            }

            // flip sides
            alternateRL = !alternateRL;

            // Return the value once we find it
            if (array[pointer] == num) {
                return pointer;
            }

            // If Center Point found place markers on either side of the center
            if (pointer < array.length - 1 && pointer > 0
                    && array[pointer] > array[pointer + 1]
                    && array[pointer] > array[pointer - 1]) {
                if (l2 >= pointer) l2 = pointer - 1;
                if (r1 <= pointer) r1 = pointer + 1;
            }

            // If slope up found - we are on the left side of the array
            // Test for zero and test order avoids out of range errors
            else if (pointer == 0 || array[pointer] > array[pointer - 1] &&
                    array[pointer] < array[pointer + 1]) {

                if (num > array[pointer]) {
                    // place l1 if the value searched for is to the right of the pointer
                    l1 = pointer + 1;
                    // r1 must not be to the left of l1
                    if (r1 < l1) r1 = l1;
                }
                else {
                    // place l2 if the value searched for is to the left of the pointer
                    if (l2 >= pointer) l2 = pointer - 1;
                    // r1 should not be to the left of l2
                    if (r1 <= pointer) r1 = pointer + 1;

                }
            }

            else {
                // If Slope Down is found - we are on the right side of the array
                if (num < array[pointer]) {
                    // place r1 if the value searched for is to the right of the pointer
                    if (r1 <= pointer) r1 = pointer + 1;
                    if (l2 >= pointer) l2 = pointer - 1;

                }
                else {
                    // place r2 if the value searched for is to the left of the pointer
                    r2 = pointer - 1;
                    if (l2 > r2) l2 = r2;
                }
            }
        }

        // If value not found return -1
        return -1;
    }
}
