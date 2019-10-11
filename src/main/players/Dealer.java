package players;

import game.BlackJ;
import generate.Generate;

import java.util.ArrayList; // import the ArrayList class

public class Dealer implements Person, Host {
    private int chips = 0;
    private int bet = 0;
    private ArrayList<Integer> cards = new ArrayList<Integer>();
    private int cardSum = 0;
    private boolean isUnder21 = true;

    // MODIFIES: this
    // EFFECTS: initialize person with their net worth
    public Dealer(int chips) {
        this.chips = chips;
    }

    // EFFECTS: returns the amount of money they currently have
    public int getChips() {
        return chips;
    }

    //MODIFIES: this
    //REQUIRES: bet is less than net worth and not negative
    //EFFECTS: get and store how much this person bets in the current game
    public void bet(int bet) {
        this.bet += bet;
    }

    // EFFECTS: returns the amount they bet on the current game
    public int getBet() {
        return bet;
    }

    // MODIFIES: this
    // EFFECTS: person get the two initial cards to start the game
    public int[] dealtBegCards(BlackJ game) {
        Generate gen = new Generate();
        int[] beg = new int[2];
        beg[0] = game.used(gen.deal(game));
        beg[1] = game.used(gen.deal(game));
        addCard(beg[0]);
        addCard(beg[1]);
        return beg;
    }

    // MODIFIES: this
    // EFFECTS: adds the new card number to the total sum of the person's cards
    public void addCard(int card) {
        cards.add(card);
        cardSum += card;
    }

    // EFFECTS: returns the sum of card numbers
    public int getCardSum() {
        return cardSum;
    }

    // MODIFIES: this
    // EFFECTS: add the amount of money to its net worth
    public void addOrMinusChips(int chips) {
        this.chips += chips;
    }

    // MODIFIES: this
    // EFFECTS: the person receives another card from the game
    public int dealtCard(BlackJ game) {
        Generate gen = new Generate();
        int newCard = gen.deal(game);
        addCard(newCard);
        return game.used(newCard);
    }

    //MODIFIES: this
    //EFFECTS: reset cardSum
    public void resetSum() {
        cardSum = 0;
    }

    // MODIFIES: this
    // EFFECTS: randomly plays the host or dealer of the game
    public void hostPlay(BlackJ game) {
        while (this.getCardSum() < 17) {
            this.dealtCard(game);
        }
    }
}
