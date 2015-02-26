package com.github.anthony408.examples.searching_sorting;

import java.util.Comparator;



public class ReverseNumericalOrder implements Comparator<Integer> {
    
    @Override
    public int compare(Integer o1, Integer o2) {
        return o2 - o1;
    }
    // equals omitted
}


/*
 override the compare for integers, normally this would return o1 - o2
 however that will yeield ascending order (biggest first).
 
 Now with this returning 1 onlhy if o2 is bigger, the bigger num
 */