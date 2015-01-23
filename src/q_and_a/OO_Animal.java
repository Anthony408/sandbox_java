package q_and_a;

/**
 * for OO examples.  Top level object.
 */
public class OO_Animal {


    // example fields
    private String name;
    private int weightInPounds;
    private String sound;

    private OO_Animal(){}; // not allowed to instantiate via default constructor.
    
    // default constructor, available publicly
    public OO_Animal(String name, int weightInPounds) {
        this.setName(name);
        this.setWeightInPounds(weightInPounds);
        this.setSound("Grrrrr");
    }
    
    // illustration of Constructor overloading.
    // protected only allows items in this package (q_and_a) to use this method.
    // this method assumes everything named Larry is a 10 lb bird that sqwuaaks.
    protected OO_Animal(boolean nameMeLarry){
        if (nameMeLarry){
            this.setName("Larry");
            this.setWeightInPounds(10);
            this.setSound("SQWAAAWK");
        } else {
            setName("George"); // if their not named Larry, name them george.
            setWeightInPounds(135); // Animals not named Larry, using the boolean constructor are 135
            this.setSound("Hack.");  // Animals not named Larry, but George will be sick, they Hack.
        }
    }

    public int getWeightInPounds() {
        return weightInPounds;
    }

    public void setWeightInPounds(int weightInPounds) {
        this.weightInPounds = weightInPounds;
    }
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSound() {
        return sound;
    }

    public void setSound(String sound) {
        this.sound = sound;
    }

    public String speak(){
        return "... MAKING A GENERAL NOISE ... " + getSound();
    };
}
