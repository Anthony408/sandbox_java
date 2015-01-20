package com.github.anthony408.examples;


import java.util.Random;


public class ExampleOverLoading {

    private int getRandomNumber(){
        Random r = new Random();
        return r.nextInt(); 
    }
    
    private int getRandomNumber(int seed){
        Random r = new Random(seed);
        return r.nextInt();
    }

    private int getRandomNumber(int seed, int range){
        Random r = new Random(seed);
        return r.nextInt(range) + 1; // so range goes from 1-->range.
    }



    public static void main(String[] args) {
        ExampleOverLoading me = new ExampleOverLoading();
        System.out.println("Signature's 1 use:"+ me.getRandomNumber());
        System.out.println("Signature's 2 use:"+ me.getRandomNumber(1));
        System.out.println("Signature's 3 use:"+ me.getRandomNumber(123,1000));
    }
    
}

/*
OUTPUT
------
Signature's 1 use:332908814
Signature's 2 use:-1155869325
Signature's 3 use:783
 */
