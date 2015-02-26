package com.github.anthony408.examples;


import junit.framework.Assert;
import org.junit.Test;

import java.util.HashMap;

public class ExampleFib {

    // example 1 recursive fib


    public static int fib(int n){
        // case < 0 throw error
        if(n < 0){
            throw new IllegalArgumentException(n + " must be > 0");
        }
        if(n==0){
            return 0;
        } else if (n==1){
            return 1;
        }

        else return fib(n-1) + fib(n-2);
    }

    private static  HashMap<Integer, Integer> memoized = new HashMap<>(); // store key/values f(n) = N
    
    // memoized needs a helper and a recursive funct.
    public static int fib_m(int n) {
        int result = 0;
        
        memoized.put(0,0); // insert f(0)
        memoized.put(1,1); // insert f(1)
        
        return fib_helper(n);
    }

    public static int fib_helper(int n){
        if (n < 0){
            throw new IllegalArgumentException(n + " must be > 0");
        }
        
        if (memoized.containsKey(n)){
            return memoized.get(n);
        }
        
        // else call recursively and get f(n)
        int f_n = fib_helper(n-1) + fib_helper(n-2);

        memoized.put(n,f_n);
        
        return f_n;
        
    }

    @Test
    public void testfib(){
        int n = 3;  // 0 + 1 + 1 = 2
        int found = fib(n);
        int expected = 2;
        Assert.assertEquals(expected, found);
    }

    @Test
    public void testfib_mem(){
        //0, 1, 1, 2, 3, 5, 8, 13, 21, 34, 55, 89, 144, 233, 377, 610, 987, 1597, 2584, 4181, 6765, 10946, 17711, 28657, 46368, 75025, 121393, 196418, 317811, ...
        int n = 6;  // 0 + 1 + 1 + 2 + 3
        int found = fib_m(n);
        int expected = 8;
        Assert.assertEquals(expected, found);
    }
}
