package com.github.anthony408.examples;

/**
 What is the difference between STRINGBUFFER and STRING?

 String:
 -------
 String object is immutable. i.e , the value stored in the String object cannot be changed.

 StringBuffer/StringBuilder
 ---------------------------
 StringBuffer/StringBuilder objects are mutable: StringBuffer/StringBuilder objects are mutable;
 we can make changes to the value stored in the object. What this effectively means is that 
 string operations such as append would be more efficient if performed using
 StringBuffer/StringBuilder objects than String objects.
  
 */
public class StringVsStringBuffer {

    
    public static void useString(){
    /*
        When you print the contents of myString the output will be "Hello Guest”. 
        Although we made use of the same object (myString), 
        internally a new object was created in the process. That’s a performance issue.
    */
        String myString = "Hello";
        myString = myString + "Guest";
        System.out.println(myString);
    }
    
    public static void useStringBuffer(){
        /*
            When you append to stringbuffer, unlike string it doesnt need to make a new object.
         */
        String str = "Be Happy With Your Salary";
        str += "Because Increments are a myth";
        StringBuffer strbuf = new StringBuffer();
        strbuf.append(str);
        System.out.println(strbuf);
        
    }
    
}
