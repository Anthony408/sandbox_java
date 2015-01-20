package com.github.anthony408.examples;

/**
 * Over Riding uses Java's OOP
 * and anything that this class extends or inherits
 * it can over ride (example @toString() 
 */
public class ExampleOverRiding {

    int size = 0;
    int weight = 0;
    public static enum WeightMetric {LB, OZ} 
    WeightMetric wm = WeightMetric.LB;  // default to LB's
    
    public ExampleOverRiding(int size, int weight, String weightMetric){
        this.size = size;
        this.weight = weight;
        try {
            this.wm = WeightMetric.valueOf(weightMetric);
        } catch (IllegalArgumentException e) {
            System.out.println("ERROR:"+e);
        }

        
    }
    @Override
    public String toString() {
//        return super.toString();
        String me = String.format("\n" +
                "Size=%d" + "\n" +
                "Weight=%d(%s)" + "\n",
                this.size, this.weight, this.wm);
        
        return me;
    }

    public static void main(String[] args) {
        // example of the over riden method being used. (toString of the object)
        
        int size = 3;
        int weight = 38;
        String wm = "OZ"; // example also shows String --> Enum in constructor.
        ExampleOverRiding me = new ExampleOverRiding(size, weight, wm);
        System.out.println("HERE IS THE OBJECT!!!\n"+me);
    }
}


/*

OUTPUT (shows the overriden toString() in use)
----------------------------------------------
HERE IS THE OBJECT!!!

Size=3
Weight=38(OZ)

 */
