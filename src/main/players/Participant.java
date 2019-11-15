package players;

import exceptions.NotRealBetException;
import game.BlackJ;

import java.io.File;
import java.io.IOException;
import java.util.List;

public interface Participant {

    // MODIFIES: this
    // EFFECTS: allows player to play the game until player dies
    public void play(BlackJ game, Dealer dealer, String line) throws IOException, NotRealBetException;
}
