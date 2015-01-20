package com.github.anthony408.examples;

/**
 * Which data types are suported in Java?
 */
public class JavaSupportedDataTypes {
   /*
    Byte : 8-bit signed two’s complement integer. It has a minimum value of -128 and a maximum value of 127 (inclusive)
    Short : 16-bit signed two’s complement integer. It has a minimum value of -32,768 and a maximum value of 32,767 (inclusive).
    Int : 32-bit signed two’s complement integer. It has a minimum value of -2,147,483,648 and a maximum value of 2,147,483,647 (inclusive)
    Long : 64-bit signed two’s complement integer. It has a minimum value of -9,223,372,036,854,775,808 and a maximum value of 9,223,372,036,854,775,807 (inclusive)
    Float
    Double
   */
    public static void main(String[] args) {
        byte b = 32;
        short s = 32727;
        int i = -2147483648;
        long l = new Long(Long.MAX_VALUE); // 9223372036854775807
        System.out.println(String.valueOf(l));    
    }
    

}
