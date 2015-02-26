package com.github.anthony408.examples.searching_sorting;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertTrue;

public class ReverseTest {

    private final ReverseNumericalOrder comparator = new ReverseNumericalOrder();

    /*

     */
    @Test
    public void testNeg() {
        assertTrue(comparator.compare(10, 4) < 0);
    }

    @Test
    public void testEq() {
        assertTrue(comparator.compare(2, 2) == 0);
    }

    @Test
    public void testPos() {
        assertTrue(comparator.compare(4, 10) > 0);
    }

    @Test
    public void testListReversed() {

        List<Integer> ints = Arrays.asList(13, 3, 82, 22, 10, 99, 18);
        List<Integer> expected_ints = Arrays.asList(99, 82, 22, 18, 13, 10, 3);
//        System.out.println("ints:"+ints);
        Collections.sort(ints, new ReverseNumericalOrder()); // ints:[99, 82, 22, 18, 13, 10, 3]
//        System.out.println("ints:"+ints);
        assertEquals(ints, expected_ints);
    }
}
