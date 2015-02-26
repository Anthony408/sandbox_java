package q_and_a.example_OO_polymorphism;

/*
  Example OOP:
  
            ANIMAL
              |
              |
              V
             Cat
   
 */
public class OO_Cat extends OO_Animal {

//    Cat(){} // see not allowed, because Animal() is not available.  This could be put into an interface as well.
    public OO_Cat(String name, int weight){
        super(name, weight); // call the super to set all the parent/inherited fields.
        setSound("Meow"); // this objects sound is not "Grrr", its Woof
    }
    
    public OO_Cat(boolean isNamedLarry){
        super(isNamedLarry); // call the super's, but if a cat is named larry, its not squawking
        if(isNamedLarry){
            setWeightInPounds(35); // Cats named larry are always 35 lbs.
            setSound("Purrr"); // Larry the cat purr's instead of squawking like the default Animal.
        } else {
            setSound("Meow"); // this objects sound is not "Grrr", its Woof
        }

    }

    // alter the super's behavior in some way and do some kind of unique behavior.
    @Override
    public String getName() {return "Cat Tag Reads: "+ super.getName();}

    // example override for cat's speak.
    @Override
    public String speak(){
        return "... MAKING A CAT SPECIFIC NOISE ... " + getSound();
    };

}
