package problems.hackerrank;

/*

https://www.hackerrank.com/challenges/cavity-map

Read in An array.
Mark all the "cavity" indicated by they are the following:
a.  not on the edge
b.  bigger than all the adjacent numbers

Example @ 1,1 there is "9"
Adjacencies to check (i,j)

Check in x,y format all combinations;

# ABOVE
x-1, j-1
x, j-1
x+1, j-1

# SAME
x-1, j
x, j
x+1, j

# BELOW
x-1, j+1
x, j+1
x+1, j+1


Sample Input
------------
4
1112
3912
1892
1234

Sample Output
-------------
1112
1X12
18X2
1234
**************************************************/

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class CavityMap {

    static String[][] xyArray; // holds all the numbers.
    static String[][] cavityMapArray; // holds the numbers, but with X's where "cavities" are.

    public static void main(String[] args) {
        xyArray = parseInput();
        // copy 2d array.
        cavityMapArray = cloneArray(xyArray);

        int endEdge = xyArray.length - 2;
        int startEdge = 1;

        for(int x=startEdge; x <= endEdge; x++ ){
            for(int y=startEdge; y <= endEdge; y++){
                checkCavityChar(x, y);
            }
        }
        printArray(cavityMapArray);
    }

    private static void printArray(String[][] cavityMapArray) {
        for ( int x = 0; x < xyArray.length; x++){
            for ( int y = 0; y < cavityMapArray.length; y++){
                System.out.print(cavityMapArray[x][y]);
            }
            System.out.print("\n");
        }
    }

    private static void checkCavityChar(int x, int y) {
        ArrayList<Integer> list = new ArrayList<>();
        
        // the below for loop gets all 8 adjacencies  
//        for( int xx = x-1; xx <= x+1; xx++){
//            for(int yy = y-1; yy <= y+1; yy++){
//                list.add(Integer.parseInt(xyArray[xx][yy]));
//            }
//        }

        int above = Integer.parseInt(xyArray[x][y - 1]);
        int left = Integer.parseInt(xyArray[x - 1][y]);
        int right = Integer.parseInt(xyArray[x + 1][y]);
        int below = Integer.parseInt(xyArray[x][y+1]);
        list.add(above);
        list.add(left);
        list.add(right);
        list.add(below);
        
        int max = Collections.max(list); // find the max of all these other elements.
        int cur = Integer.parseInt(xyArray[x][y]);
        if(cur > max) {
            cavityMapArray[x][y] = "X"; // overwrite.
        }
    }

    public static String[][] parseInput(){
        Scanner in = new Scanner(System.in);
        int n = Integer.parseInt(in.nextLine());
        String[][] array2d = new String[n][n];
        String currentLine;
        // System.out.print("N="+n);
        // for each line populate the array
        for(int x=0;x<n;x++){ // y cordinates start at 0, and increase.
            currentLine = in.nextLine().trim();
            //System.out.println("currentLine="+currentLine);
            String[] currentLineCharArray = currentLine.split("(?!^)");
            for(int y=0; y<currentLineCharArray.length; y++){
                array2d[x][y] = currentLineCharArray[y];
            }
        }
        return array2d;
    }

    public static String[][] cloneArray(String[][] src) {
        int length = src.length;
        String[][] target = new String[length][src[0].length];
        for (int i = 0; i < length; i++) {
            System.arraycopy(src[i], 0, target[i], 0, src[i].length);
        }
        return target;
    }
}
