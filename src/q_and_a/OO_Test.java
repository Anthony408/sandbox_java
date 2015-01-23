package q_and_a;

import org.junit.Test;

import java.util.ArrayList;

import static junit.framework.Assert.*;

/*
    Example TDD used for illustrating OOP and Java.
    This shows how to use JUNIT, same could be done using TestNG.
    
    TestNG allows input via .xml files, and generators based on input / dynamic data (see Session Tests in COTA)
 */
public class OO_Test {

    @Test
    public void TestAnimal(){
        // Top level Animal, test it
        OO_Animal a1 = new OO_Animal("Fred", 38);
        assertEquals(a1.getName(), "Fred");
        assertEquals(a1.getWeightInPounds(), 38);
        assertEquals(a1.getSound(), "Grrrrr");
    }

    @Test
    public void TestAnimalBooleanConstructor(){
        // test the animal with a boolean constructor, testing if its namedLarry or not.
        OO_Animal a2 = new OO_Animal(true);
        assertEquals(a2.getName(), "Larry");
        assertEquals(a2.getWeightInPounds(), 10);
        assertEquals(a2.getSound(), "SQWAAAWK");
    }
    
    @Test
    public void TestDog(){
        // Example of inheritance (Dog) is a child of Animal
        OO_Dog OODog1 = new OO_Dog("Rufus", 150); // big dog.
        assertEquals(OODog1.getName(), "Dog Tag Reads: Rufus"); // overridden getName.
        assertEquals(OODog1.getSound(), "Woof"); // overridden getSound
        assertEquals(OODog1.speak(), "... MAKING A DOG SPECIFIC NOISE ... Woof");
    }
    
    @Test
    public void TestCat(){
        // Example Cat, assigned with "ANIMAL" but when speaks, it knows its a cat.
        OO_Animal cat1 = new OO_Cat("Oscar", 83);
        assertEquals(cat1.getName(), "Cat Tag Reads: Oscar"); // overridden getName.
        assertEquals(cat1.getWeightInPounds(), 83);
        assertEquals(cat1.getSound(), "Meow"); // overridden getSound
    }

    @Test
    public void TestCatBooleanConstructor(){
        // Example Cat, assigned with "ANIMAL" but when speaks, it knows its a cat.
        OO_Animal cat1 = new OO_Cat(false);
        assertEquals(cat1.getName(), "Cat Tag Reads: George"); // overridden getName.
        assertEquals(cat1.getWeightInPounds(), 135);
        assertEquals(cat1.getSound(), "Meow"); // overridden getSound
    }
    
    public void showPolyMorphismAndCollections(){
        // create a bunch animals, and see if they know how to speak
        ArrayList<OO_Animal> OOAnimals = new ArrayList<>();
        OO_Animal dog1 = new OO_Dog("Rufus", 100);
        OO_Animal dog2 = new OO_Dog("Spike", 50);
        OO_Dog OODog3 = new OO_Dog("Spot", 25);
        OO_Cat OOCat1 = new OO_Cat("Tinkles", 10);
        OO_Cat OOCat2 = new OO_Cat("Fluffy", 22);
        OO_Animal gorilla1 = new OO_Animal("Koko", 200);
        
        // put all the animals in a collection
        OOAnimals.add(dog1);
        OOAnimals.add(dog2);
        OOAnimals.add(OODog3);
        OOAnimals.add(OOCat1);
        OOAnimals.add(OOCat2);
        OOAnimals.add(gorilla1);
        
        for (OO_Animal OOAnimal : OOAnimals) {
            System.out.println("Hello my name is " + OOAnimal.getName());
            System.out.println("The sound I make is " + OOAnimal.getSound());
            System.out.println("And I weigh :" + OOAnimal.getWeightInPounds() +" LBS");
        }
        /*
        ------------------------------------------------------
        -- OUTPUT (didnt put into a test, just show works)
        ------------------------------------------------------
            Hello my name is Dog Tag Reads: Rufus
            The sound I make is Woof
            And I weigh :100 LBS
            Hello my name is Dog Tag Reads: Spike
            The sound I make is Woof
            And I weigh :50 LBS
            Hello my name is Dog Tag Reads: Spot
            The sound I make is Woof
            And I weigh :25 LBS
            Hello my name is Cat Tag Reads: Tinkles
            The sound I make is Meow
            And I weigh :10 LBS
            Hello my name is Cat Tag Reads: Fluffy
            The sound I make is Meow
            And I weigh :22 LBS
            Hello my name is Koko
            The sound I make is Grrrrr
            And I weigh :200 LBS
         */
    }

    public static void main(String[] args) {
        /*
         Quick example of how each object knows what it is, and how we can group together collections of objects
         based on their parent type, and then call the inherited methods on them, they know how to appropriately 
         respond.
         
         (example, dogs bark, cats meow, generic Animals growl)
         */
        OO_Test me = new OO_Test();
        me.showPolyMorphismAndCollections(); // see method above illustrating output.
        
    }
}
