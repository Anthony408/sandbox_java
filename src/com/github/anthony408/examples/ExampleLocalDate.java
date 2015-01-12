package com.github.anthony408.examples;

import java.time.LocalDate;

/**
 * Example of how to use Java8 Date object (LocalDate)
 */
public class ExampleLocalDate {

    private static void PrintLocalDateExamples() {
        // Java 8 Example Local Date object. 
        LocalDate localDateNow = LocalDate.now();
        System.out.println(String.format("Local Date Now: %s", localDateNow));

        // Java 8 Example LocalDate object initialized with calendar dates
        LocalDate localDateSpecific = LocalDate.of(2014, 3, 20);
        System.out.println(String.format("Local Date March 20, 2014: %s", localDateSpecific));
        
        // Java 8 Example LocalDate instantiation with respect to another Date Modified (Addition)
        int numDays = 30;
        LocalDate dueDate = localDateSpecific.plusDays(numDays);
        System.out.println(String.format("Local Date (March 20, 2014) + (%s DAYS): %s", numDays, dueDate));
    }

    public static void main(String[] args) {
        PrintLocalDateExamples();
    }
}
