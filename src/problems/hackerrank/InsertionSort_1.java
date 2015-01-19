package problems.hackerrank;
import java.util.*;

// https://www.hackerrank.com/challenges/insertionsort1

public class InsertionSort_1 {

    public static void insertIntoSorted(int[] ar) {
        /*
         Algorithm:
         Array example 2 4 6 8 3
         
         2 4 6 8 3 (pointer starts @end and end-1 and compares)
         2 4 6 8 8 * since 8 < 3, 3 must be pusehd inward, so overwrite the 3 position with the 8 (shift right)
         2 4 6 6 8 * keep comparing where to put the 3, check vs the end-2 position (6 < 3 so shift 6 right )
         2 4 4 6 8 * same concept 4 < 3, so shift the 4 right
         2 3 6 6 8 --> HERE 2 < 3 is true, so instead of shifting 2 to the right, 3 now clobbers the right position (4)
         */
            int ptr_cur = ar.length - 1;  // starts off with the ending.
            int current_value = ar[ptr_cur];
            boolean swapped;
            for (int cur_idx = ar.length - 2; cur_idx >= 0; cur_idx--) {
                int compare_value = ar[cur_idx]; // this
                if (compare_value > current_value ) {
                    // SWAP COMPARE VALUE RIGHT
                    ar[cur_idx+1] = compare_value;
                    swapped = true;
                } else {
                    // INSERTION OF CURRENT VALUE, exit for loop.
                    ar[cur_idx+1] = current_value;
                    swapped = false;
                }

                printArray(ar);
                
                // corner case, if the value was the 1rst element of the array, 
                // and swapped all the way down, when reaching the last element, 
                // insert it into the FIRST index
                if (swapped & cur_idx == 0){
                    // insert the value into the first element.
                    ar[cur_idx] = current_value;
                    printArray(ar);
                }

                if (! swapped ){
                    break;
                }
            }
        }

    /**
     * Input reader:
     * 1.  N
     * 2.  N integers
     *
     * @return Integer[] array
     */
    public static int[] parseInput(){
         /*
         Read in teh input via System.in scanner.
         Example input (System.in)
         ---------------------------------------
         5
         2 4 6 8 3
         ---------------------------------------
         */
        Scanner in = new Scanner(System.in);
        int s = in.nextInt();
        int[] ar = new int[s];
        for(int i=0;i<s;i++){
            ar[i]=in.nextInt();
        }
        return ar;
    }
    
    /* Tail starts here */
    public static void main(String[] args) {
        int[] arr = parseInput(); // parse System.in input for this problem
//        int[] arr_data_input_1 = new int[] {1,3,5,9,13,22,27,35,46,51,55,83,87,23};

//        int[] arr_data_input_2 = new int[] {2,3,4,5,6,7,8,9,10,1};
/*
            2 3 4 5 6 7 8 9 10 10
            2 3 4 5 6 7 8 9 9 10
            2 3 4 5 6 7 8 8 9 10
            2 3 4 5 6 7 7 8 9 10
            2 3 4 5 6 6 7 8 9 10
            2 3 4 5 5 6 7 8 9 10
            2 3 4 4 5 6 7 8 9 10
            2 3 3 4 5 6 7 8 9 10
            2 2 3 4 5 6 7 8 9 10
            1 2 3 4 5 6 7 8 9 10
 */
        insertIntoSorted(arr);
    }

    /**
     * Helper function to print an array of ints
     * @param ar array of ints
     */
    private static void printArray(int[] ar) {
        for(int n: ar){
            System.out.print(n+" ");
        }
        System.out.println("");
    }

}

/*

Sample Input 
-------------
5
2 4 6 8 3

Sample Output
-------------
2 4 6 8 8
2 4 6 6 8
2 4 4 6 8
2 3 4 6 8

 */
