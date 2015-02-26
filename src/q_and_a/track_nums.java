package q_and_a;

import java.util.*;

/**
 * Problem, go through a list of integers, and let me know which ones are repeat values:
 *      1,2,3,4,3,3,7,3,8,10
 *      
 *      Solution:
 *      Not only iterate from beginning to end, but also keep track of Freq.
 *      To do this, make a hashmap to keep track of values seen, @ repeat values index the key's 'seen' value +1
 *      Initially, if the value isnt seen, set it to 1
 */
public class track_nums {

    public static void main(String[] args) {
        List<Integer> numlist = new ArrayList<Integer>();
        Collections.addAll(numlist, 1,2,3,4,3,3,7,3,8,10);
        HashMap<Integer, Integer> trackNumbers = new HashMap<>(); // keeps track of nums found, and freq
        
        for (Integer num : numlist) {
            if ( trackNumbers.containsKey(num)) {
                int newValue = trackNumbers.get(num) + 1;
                trackNumbers.put(num, newValue);
            } else {
                trackNumbers.put(num, 1);
            }
        }

        for (Integer id: trackNumbers.keySet()){
            String key = id.toString();
            String value = trackNumbers.get(id).toString();
            System.out.println(String.format("Number(%s) Frequency(%s)",id,value));
        }
    }
}
