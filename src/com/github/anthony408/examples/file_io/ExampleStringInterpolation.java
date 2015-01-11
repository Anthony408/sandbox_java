package com.github.anthony408.examples.file_io;

public class ExampleStringInterpolation {

    public static void main(String[] args) {
        int age = 139;
        String name = "The Tree named Splinters";

        String output = String.format("%s is %d years old.", name, age);

        System.out.println(output);
    }
}
