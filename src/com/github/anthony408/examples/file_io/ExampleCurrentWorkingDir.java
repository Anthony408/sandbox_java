package com.github.anthony408.examples.file_io;

public class ExampleCurrentWorkingDir {
    public static void main (String args[]) {

        String workingDir = System.getProperty("user.dir");
        System.out.println("Current working directory : " + workingDir);
    }
}
