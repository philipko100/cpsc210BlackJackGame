package players;

import game.BlackJ;

public interface Host {

    // MODIFIES: this
    // EFFECTS: randomly plays the host or dealer of the game
    public void hostPlay();
}
