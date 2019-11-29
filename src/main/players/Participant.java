package players;

import exceptions.NotRealBetException;
import game.BlackJ;

import java.io.File;
import java.io.IOException;
import java.util.List;

public interface Participant {

    // MODIFIES: this
    // EFFECTS: allows player to save the game to come back to
    void saveGame() throws IOException;
}
