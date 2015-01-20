package com.github.anthony408.examples;

/**
 * Created by agaglian on 1/19/15.
 */
public class ExampleConstructorOverloading {
    String name;
    ExampleConstructorOverloading(String input) { //This is the constructor
        name = "Our Boss is also known as : " + input;
    }
    ExampleConstructorOverloading() {
        name = "Our Boss is a nice man. We don’t call him names.";
    }
    public static void main(String args[]) {
        ExampleConstructorOverloading p1 = new ExampleConstructorOverloading("Super-Man");
        ExampleConstructorOverloading p2 = new ExampleConstructorOverloading();
    }
}
