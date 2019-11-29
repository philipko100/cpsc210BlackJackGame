package players;

import exceptions.NotRealBetException;
import game.BlackJ;

import java.io.IOException;

// The component

public abstract class Person {

    // EFFECTS: returns the amount of money they currently have
    public abstract int getChips();

    //MODIFIES: this
    //EFFECTS: get and store how much this person bets in the current game
    public abstract void bet(int bet) throws NotRealBetException;

    // EFFECTS: returns the amount they bet on the current game
    public abstract int getBet();

    // MODIFIES: this
    // EFFECTS: person get the two initial cards to start the game
    public abstract int[] dealtBegCards();

    // EFFECTS: returns the sum of card numbers
    public abstract int getCardSum();

    // MODIFIES: this
    // EFFECTS: the person receives another card from the game
    public abstract int dealtCard();

    // MODIFIES: this
    // EFFECTS: allows the player to play
    public abstract void play() throws IOException, NotRealBetException;

}
