package q_and_a;

/*
  Example OOP:
  
            ANIMAL
              |
              |
              V
             DOG
   
 */
public class OO_Dog extends OO_Animal {

    public OO_Dog(String name, int weight){
        super(name, weight); // call the super to set all the parent/inherited fields.
        setSound("Woof"); // this objects sound is not "Grrr", its Woof
    }

    // alter the super's behavior in some way and do some kind of unique behavior.
    @Override
    public String getName() {return "Dog Tag Reads: " + super.getName();}

    // example override for dogs speak
    @Override
    public String speak(){return "... MAKING A DOG SPECIFIC NOISE ... " + getSound();};

}
