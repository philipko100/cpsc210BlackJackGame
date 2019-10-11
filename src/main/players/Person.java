package players;

import game.BlackJ;

public interface Person {

    // EFFECTS: returns the amount of money they currently have
    public int getChips();

    //MODIFIES: this
    //EFFECTS: get and store how much this person bets in the current game
    public void bet(int bet);

    // EFFECTS: returns the amount they bet on the current game
    public int getBet();

    // MODIFIES: this
    // EFFECTS: person get the two initial cards to start the game
    public int[] dealtBegCards(BlackJ game);

    // EFFECTS: returns the sum of card numbers
    public int getCardSum();

    // MODIFIES: this
    // EFFECTS: the person receives another card from the game
    public int dealtCard(BlackJ game);

}
