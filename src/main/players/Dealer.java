package players;

import exceptions.NotRealBetException;
import exceptions.NotRealChipOutcome;
import game.BlackJ;
import generate.Generate;
import ui.Jdraws;
import ui.Jgui;

import java.io.IOException;
import java.util.ArrayList; // import the ArrayList class

//leaf

public class Dealer extends Person implements Host {
    private int chips = 0;
    public int bet = 0;
    private ArrayList<Integer> cards = new ArrayList<Integer>();
    public int cardSum;
    private boolean isUnder21 = true;
    BlackJ game;
    Player player;
    Generate gen;
    Jdraws dealerdraws;

    // MODIFIES: this
    // EFFECTS: initialize person with their net worth
    public Dealer(int chips, Player player, Jgui jgui) {
        this.chips = chips;
        this.player = player;
        game = player.game;
        gen = player.getGen();
        cardSum = 0;
        dealerdraws = jgui.dealerdraws;
    }

    // EFFECTS: returns the amount of money they currently have
    public int getChips() {
        return chips;
    }

    //MODIFIES: this
    //REQUIRES: bet is less than net worth and not negative
    //EFFECTS: get and store how much this person bets in the current game
    public void bet(int bet) throws NotRealBetException {
        if (bet > chips || bet < 0) {
            throw new NotRealBetException();
        }
        this.bet += bet;
    }

    // EFFECTS: returns the amount they bet on the current game
    public int getBet() {
        return bet;
    }

    // MODIFIES: this
    // EFFECTS: person get the two initial cards to start the game
    public int[] dealtBegCards() {
        Generate gen = new Generate(game);
        int[] beg = new int[2];
        beg[0] = game.used(gen.deal());
        beg[1] = game.used(gen.deal());
        addCard(beg[0]);
        addCard(beg[1]);
        dealerdraws.setCards(cards);
        dealerdraws.callRepaint();
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
    public void addOrMinusChips(int chips) throws NotRealChipOutcome {
        if (this.chips + chips < 0) {
            throw new NotRealChipOutcome();
        }
        this.chips += chips;
    }

    // MODIFIES: this
    // EFFECTS: the person receives another card from the game
    public int dealtCard() {
        int newCard = gen.deal();
        addCard(newCard);
        dealerdraws.setCards(cards);
        dealerdraws.callRepaint();
        return game.used(newCard);
    }

    //MODIFIES: this
    //EFFECTS: reset cardSum
    public void resetSum() {
        cardSum = 0;
        cards = new ArrayList<Integer>();
        dealerdraws.setCards(cards);
        dealerdraws.callRepaint();
    }

    // MODIFIES: this
    // EFFECTS: randomly plays the host or dealer of the game
    public void play() throws IOException, NotRealBetException {
        hostPlay();
    }

    // MODIFIES: this
    // EFFECTS: randomly plays the host or dealer of the game
    public void hostPlay() {
        if (this.getCardSum() < 17) {
            this.dealtCard();
        }
    }
}
