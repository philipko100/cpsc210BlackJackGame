package players;

import game.BlackJ;

public interface Play {

    // MODIFIES: this
    // EFFECTS: allows player to play the game until player dies
    public void play(BlackJ game, Dealer dealer);
}
