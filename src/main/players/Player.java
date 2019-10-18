package players;

import exceptions.CardSumException;
import exceptions.NotRealBetException;
import exceptions.NotRealChipOutcome;
import exceptions.NotRealMoneyException;
import game.BlackJ;
import generate.Generate;
import ui.Main;

import java.util.ArrayList;
import java.util.Scanner;

public class Player implements Person, Play {
    private int chips = 0;
    public int bet = 0;
    private ArrayList<Integer> cards = new ArrayList<Integer>();
    public int cardSum = 0;

    // MODIFIES: this
    // EFFECTS: initialize person with their net worth
    public Player(int chips) {
        this.chips = chips;
    }

    // EFFECTS: returns the amount of money they currently have
    public int getChips() {
        return chips;
    }

    //MODIFIES: this
    //REQUIRES: bet is less than net worth and larger than 0
    //EFFECTS: get and store how much this person bets in the current game
    public void bet(int bet) throws NotRealBetException {
        if (bet > chips || bet < 0) {
            throw new NotRealBetException();
        }
        if (bet == 0) {
            this.bet = 0;
        }
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
    // EFFECTS: the person receives another card from the game
    public int dealtCard(BlackJ game) {
        Generate gen = new Generate();
        int newCard = gen.deal(game);
        addCard(newCard);
        return game.used(newCard);
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
    // EFFECTS: allows player to play the game until player dies
    public void play(BlackJ game, Dealer dealer) {
        Main main = new Main();
        Scanner sc = new Scanner(System.in);
        while (this.getChips() != 0) {
            main.intakeBet(this);
            game.addBet(this.getBet());
            if (this.getBet() != 0) {
                int[] begCards = this.dealtBegCards(game);
                System.out.println("Your cards are " + begCards[0] + " " + begCards[1]);
                dealer.dealtBegCards(game);
                this.hit(game);
                dealer.hostPlay(game);
            }
            String result = getWinner(game, dealer);
            arrangeWinnings(result, game, dealer);
            if (!main.continuePlaying(this, result)) {
                break;
            }
        }
    }

    //EFFECTS: gets the winner of the game, if error returns ERROR
    public String getWinner(BlackJ game, Dealer dealer) {
        try {
            return game.chooseWinner(this.getCardSum(), dealer.getCardSum());
        } catch (CardSumException e) {
            return "ERROR";
        }
    }

    //MODIFIES: this and Dealer.cardSum
    //EFFECTS: resets all bets and cardSums
    public void reset(Dealer dealer, BlackJ game) throws NotRealBetException {
        bet(0);
        this.cardSum = 0;
        dealer.resetSum();
        game.reset();
    }

    //MODIFIES: this
    //EFFECTS: changes the net worth of each player based on who won
    public void arrangeWinnings(String result, BlackJ game, Dealer dealer) {
        try {
            if (result.equals("player wins")) {
                this.addOrMinusChips(game.getTotalBet());
                dealer.addOrMinusChips(-dealer.getBet());
            } else if (result.equals("dealer wins")) {
                this.addOrMinusChips(-this.getBet());
                dealer.addOrMinusChips(game.getTotalBet());
            }
            reset(dealer, game);
        } catch (NotRealMoneyException e) {
            if (result.equals("player wins")) {
                System.out.println("You did so well that you bankrupted the dealer");
            } else if (result.equals("dealer wins")) {
                chips = 0;
                System.out.println("You have lost all your money");
            }
        }
    }

    private void hit(BlackJ game) {
        Main main = new Main();
        while (this.getCardSum() <= 21) {
            System.out.println("Would you like another card?");
            if (main.hitOrStop()) {
                System.out.println("Your new card was: " + this.dealtCard(game));
            } else {
                break;
            }
        }
    }
}
