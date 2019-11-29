package generate;

import exceptions.NotRealCardException;
import game.BlackJ;

import java.util.ArrayList;
import java.util.Random;

public class Generate extends Randomize {

    BlackJ game;

    public Generate(BlackJ game) {
        this.game = game;
    }

    //EFFECTS: returns a random card number
    public int deal(int rand) {
        Random random = new Random();
        return random.nextInt(9) + 1;
    }

    //EFFECTS: generate a random card if it has not been used 4 times already
    public int deal() {
        Random random = new Random();
        int newCard = random.nextInt(9) + 1;
        try {
            while (game.check(newCard)) {
                newCard = random.nextInt(9) + 1;
            }
        } catch (NotRealCardException e) {
            System.out.println("There is an error in the random card generator algorithm. "
                    + "Please check with the engineers");
        } finally {
            return newCard;
        }
    }
}
