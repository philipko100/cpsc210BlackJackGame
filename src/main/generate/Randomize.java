package generate;

import java.util.Random;

public class Randomize {
    public int randomInt = 0;

    //MODIFIES: this
    //EFFECTS: stores and returns a random number from 1 to 100
    public int deal() {
        Random random = new Random();
        return randomInt = random.nextInt(100) + 1;
    }
}
