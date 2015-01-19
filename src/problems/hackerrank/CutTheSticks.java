package problems.hackerrank;

/*
https://www.hackerrank.com/challenges/cut-the-sticks

You are given N sticks, where each stick is of positive integral length. A cut operation is performed on the sticks
such that all of them are reduced by the length of the smallest stick.

Suppose we have 6 sticks of length
5 4 4 2 2 8

then in one cut operation we make a cut of length 2 from each of the 6 sticks. 
For next cut operation 4 sticks are left (of non-zero length), whose length are
3 2 2 6

Above step is repeated till no sticks are left.
Given length of N sticks, print the number of sticks that are cut in subsequent cut operations.

Input Format
------------
The first line contains a single integer N.
The next line contains N integers: a0, a1,...aN-1 separated by space, where ai represents the length of ith stick.

Output Format
-------------
For each operation, print the number of sticks that are cut in separate line.

Constraints
------------------
1 <= N <= 1000
1 <= ai <= 1000

Sample Input #00
------------------
6
5 4 4 2 2 8
Sample Output #00
------------------
6
4
2
1
Sample Input #01
------------------
8
1 2 3 4 3 3 2 1
Sample Output #01
------------------
8
6
4
1


 */


import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CutTheSticks {


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

    public static void main(String[] args) {
        int[] arr = parseInput(); // parse System.in input for this problem
//        int[] arr_data_input_1 = new int[] {1,2,3,4,3,3,2,1};
        cutTheSticks(arr);
    }

    private static void cutTheSticks(int[] arr) {
        // given a set of sticks, how many cuts can be made from the smallest initial stick found
        List<Integer> sticksLeft = new ArrayList<>();
        
        for (int stick : arr) {
            sticksLeft.add(stick); // add to list of all sticks left.
        }
        
        while (sticksLeft.size() > 0){
            System.out.println(sticksLeft.size());
            int minStickSize = findMinimumStick(sticksLeft);
            List<Integer> nextSticksLeft = new ArrayList<>();
            Integer nextStick;
            for (Integer stickSize : sticksLeft) {
                nextStick = stickSize - minStickSize;
                if (nextStick > 0){
                    nextSticksLeft.add(nextStick);
                }
            }    
            sticksLeft = nextSticksLeft;
        }
    }
    
    static private int findMinimumStick(List<Integer> sticks){
        int minStick = Integer.MAX_VALUE;
        for (int i : sticks) {
            if ( i < minStick){
                minStick = i;
            }
        }
        return minStick;
    }
}


