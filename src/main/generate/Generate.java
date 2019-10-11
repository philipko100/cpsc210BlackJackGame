package generate;

import game.BlackJ;

import java.util.ArrayList;
import java.util.Random;

public class Generate {

    //EFFECTS: generate a random card if it has not been used 4 times already
    public int deal(BlackJ game) {
        Random random = new Random();
        int newCard = random.nextInt(13) + 1;
        while (game.check(newCard)) {
            newCard = random.nextInt(13) + 1;
        }
        return newCard;
    }
}
