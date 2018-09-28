/*
 * This program will return the best combination of thievery to get the most value.
 */
package thief;

/**
 *
 * @author 55schmidtl14
 */

import java.util.Random;

public class Thief 
{

    public static void main(String[] args) 
    {
        //Main things here.
        
        Storage MarALago = new Storage();
        randPerm rand = new randPerm(MarALago.totalNumItems);
        BanditWagon maxWagon = new BanditWagon();
        int maxValue = 0;
        int presentValue = 0;
        
        
        for(int k = 0; k < 1000; k++)
        {
            rand.randomize();
            BanditWagon Dastardly = new BanditWagon();
            presentValue = 0;
            for(int i = 0; i < rand.perm.length; i++)
            {
                int itemNumber = rand.perm[i];
                if(Dastardly.itemFits(MarALago.item[itemNumber]))
                {
                    Dastardly.addItem(MarALago.item[itemNumber]);
                    presentValue += MarALago.item[itemNumber].value;
                }
            }
            if(presentValue > maxValue)
            {
                maxValue = presentValue;
                Dastardly.displayBooty();
            }
        }

        
        
    }
    
}
class Stuff {

    static final String[] NAMES = {"Silver Spoon", "Bobblehead", "Hairgel", 
        "Toaster Oven", "Turtlewax", "Bill Clinton Costume", 
        "Fake Mona Lisa", "Elvis", "A shrubbery", "2015 Federal Tax Forms",
        "Sticky notes", "Tardis","Hulk Hogan Cookie Jar", "1984 Boom Box", 
        "Ernie's rubber ducky","The Rosie O'Donnell Collection"};
    static final double[] WEIGHTS = {1, 2, 3, 4, 4, 2, 3, 5, 1, 3, 2, 4, 5, 2, 4, 2};
    static final double[] VOLS = {4, 2, 3, 2, 1, 2, 4, 3, 5, 3, 4, 5, 3, 3, 3, 7};
    static final double[] VALS = {1, 6, 8, 3, 5, 1, 7, 2, 8, 4, 6, 9, 4, 5, 8, 10};
    static final int TOTAL = NAMES.length;

}
class Treasure {

    String name;
    double weight;
    double volume;
    double value;
    Boolean inStorage;

    public String toString() {
        String out = "Weight: " + weight
                + " Volume: " + volume + " Value: " + value + " " + name;

        return out;
    }
}
class Storage {

    final int totalNumItems = Stuff.TOTAL;
    Treasure[] item = new Treasure[Stuff.TOTAL];
    Boolean isEmpty = false;

    Storage() {
        for (int i = 0; i < totalNumItems; i++) {
            item[i] = new Treasure();
            item[i].name = Stuff.NAMES[i];
            item[i].weight = Stuff.WEIGHTS[i];
            item[i].volume = Stuff.VOLS[i];
            item[i].value = Stuff.VALS[i];
        }
    }

}

class BanditWagon {

    double MAXWEIGHT = 12;
    double MAXVOLUME = 12;
    double currentWeight = 0;
    double currentVolume = 0;
    double currentValue = 0;
    Treasure[] item = new Treasure[Stuff.TOTAL];
    int numItems = 0;

    Boolean itemFits(Treasure item) {
        Boolean out = true;
        if (item.weight + this.currentWeight > MAXWEIGHT) {
            out = false;
        }
        if (item.volume + this.currentVolume > MAXVOLUME) {
            out = false;
        }
        return out;
    }

    void addItem(Treasure item) {
        this.item[numItems] = item;
        numItems++;
        //item.inStorage = false;
        currentWeight += item.weight;
        currentVolume += item.volume;
        currentValue += item.value;
    }

    void displayBooty() {

        for (int i = 0; i < this.numItems; i++) {
            System.out.println(item[i].toString());
        }

    }

}
class randPerm {

    Random randGen = new Random();

    int[] perm;

    randPerm(int n) {
        perm = new int[n];
        initPerm();
    }

    void initPerm() {
        for (int i = 0; i < perm.length; i++) {
            perm[i] = i;
        }
    }

    void randomize() {
        int temp;
        int ranIndex;
        for (int i = 0; i < perm.length; i++) {
            ranIndex = randGen.nextInt(perm.length);
            temp = perm[i];
            perm[i] = perm[ranIndex];
            perm[ranIndex] = temp;
        }

    }

}