package q_and_a;


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class ExampleErrorHandling {

    // example run time error (crashes program)
    public static void exampleRunTimeError(){
        System.out.println(3/0);  // Not caught, will cause run time error and crash.
    }

    // example catching error with Try
    public static void exampleCaughtErrorWithTry(){
        try{
            System.out.println(3/0);
        } catch(ArithmeticException e){
            System.out.println("You cant do that!");
            System.out.println("Error: "+e.getMessage());
        }
    }

    // example of how to catch with throws.
    public static void exampleCaughtErrorWithThrows() throws ArithmeticException{
        System.out.println(3/0); // handled by throws.
    }
    
    // example of how to use throw. (catches with throw)
    public static void exampleThrow() throws ArithmeticException {

        try{
            throw new ArithmeticException("foo");
        } catch(ArithmeticException e){
            System.out.println("You cant do that!");
            System.out.println("Error: "+e.getMessage());
        }
    }

    
    public static void exampleUserInputError(){
        Scanner userInput = new Scanner(System.in); // scan user input
        System.out.print("How Old Are You? (enter non-digit to throw exception) :");
        try{
            int age = userInput.nextInt();
            System.out.println("User Has Entered the following integer: " + age);
        } catch (InputMismatchException e) {
            System.out.println("You didnt enter a valid number?");
            System.out.println("Error: "+ e.getMessage());
        }
        
    }
    
    public static void exampleFileGetError(){
        FileInputStream fi = null;
        try{
            // FIleInputStream needs "FileNotFoundException", IDE warns.
            fi = new FileInputStream("SomeFileThatDoesNotExist237342.txt");
        } catch (FileNotFoundException e) {
            System.out.println("Sorry, can not find the file");
        } catch (Exception e){
            System.out.println("Some unknown error occured");
        } finally {
            System.out.printf("This runs regardless if there was an error or not (CLEANUP)");
        }
    }

    private static void exampleIgnoreError() {
        try{
            throw new ArithmeticException();
        } catch (ArithmeticException e) {}  // this is how you ignore an error.
    }
    
    public static void main(String[] args) throws InterruptedException {
        
        exampleCaughtErrorWithTry();
        /*
        ------------------------------------------
        -- OUTPUT: exampleCaughtErrorWithTry() 
        ------------------------------------------
            You cant do that!
            Error: / by zero
         */
        
        try{
            exampleCaughtErrorWithThrows();    
        } catch (ArithmeticException e){
            System.out.println("Error Caught: " + e.getMessage());
        }
        /*
        ------------------------------------------
        -- OUTPUT: exampleCaughtErrorWithThrows();
        ------------------------------------------
            Error Caught: / by zero
         */
        
        exampleThrow();
        /*
        ------------------------------------------
        -- OUTPUT:  exampleThrow();
        ------------------------------------------
            Error: foo
         */

        exampleUserInputError();
        /*
        ------------------------------------------
        -- OUTPUT:  exampleUserInputError();
        ------------------------------------------
            How Old Are You? (enter non-digit to throw exception) : abc
            You didnt enter a valid number?
         */
     
        exampleFileGetError(); 
        /*
            Sorry, can not find the file
            This runs regardless if there was an error or not (CLEANUP)
         */
        
        exampleIgnoreError(); // nothing prints.

        
        // added sleep time so error message not mixed up with the caught messages in console.
        System.out.println("\n\n-- SPACE BEFORE RUN TIME ERROR --\n\n");
        Thread.sleep(3);
        exampleRunTimeError();
        /*
        ------------------------------------------
        -- OUTPUT:  exampleRunTimeError();
        ------------------------------------------
            Exception in thread "main" java.lang.ArithmeticException: / by zero
            at q_and_a.ExampleErrorHandling.exampleRunTimeError(ExampleErrorHandling.java:8)
            at q_and_a.ExampleErrorHandling.main(ExampleErrorHandling.java:47)
            at sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
            at sun.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:62)
            at sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
            at java.lang.reflect.Method.invoke(Method.java:483)
            at ... rt.execution.application.AppMain.main(AppMain.java:134)

         */
    }

}
