package players;

import exceptions.CardSumException;
import exceptions.NotRealBetException;
import exceptions.NotRealChipOutcome;
import exceptions.NotRealMoneyException;
import game.BlackJ;
import generate.Generate;
import network.ReadWebPageEx;
import ui.Jdraws;
import ui.Jgui;
import ui.Main;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

//composite

public class Player extends Person implements Participant {
    private int chips = 0;
    public int bet = 0;
    private ArrayList<Integer> cards = new ArrayList<Integer>();
    public int cardSum = 0;
    int dealerMoney = 10000;
    private List<Person> people = new ArrayList<Person>();
    public Dealer dealer;
    public BlackJ game = new BlackJ();
    public Generate gen;
    public Main main;
    public Jgui jgui;
    public boolean hit;
    public boolean stand;
    public boolean continueB;
    public boolean stop;
    public String city;
    public Jdraws jdraws;

    // MODIFIES: this
    // EFFECTS: initialize person with their net worth
    public Player(int chips, Main main, Jgui jgui, Jdraws jdraws) throws NotRealBetException {
        this.chips = chips;
        bet = 0;
        gen = new Generate(game);
        dealer = new Dealer(dealerMoney, this, jgui);
        dealer.bet(500);
        game.addBet(dealer.getBet());
        people.add(dealer);
        this.main = main;
        this.jgui = jgui;
        hit = false;
        stand = false;
        stop = false;
        continueB = false;
        city = "";
        this.jdraws = jdraws;
    }

    public Dealer getDealer() {
        return dealer;
    }

    public BlackJ getGame() {
        return game;
    }

    public Generate getGen() {
        return gen;
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
        putToGameBet(bet);
    }

    //MODIFIES: this
    //EFFECTS: communicate with user to store user's bet
    public void intakeBet() {
        System.out.println("How much would you like to bet this round?");
        jgui.printWords("You have $" + chips + " chips. How much would you like to bet this round?");
        while (bet == 0) {
            System.out.print("");
        }
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
        jgui.setCards(cards);
        //jdraws.setCards(cards);
        jdraws.callRepaint();
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
    public int dealtCard() {
        int newCard = gen.deal();
        addCard(newCard);
        jgui.setCards(cards);
        jdraws.callRepaint();
        System.out.println("called dealt cards: new card : ");
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
    // EFFECTS: add bet to the game object
    public void putToGameBet(int bet) {
        game.addBet(bet);
    }


    // MODIFIES: this
    // EFFECTS: allows player to play the game until player dies
    public void play() throws IOException, NotRealBetException {
        while (this.getChips() != 0) {
            intakeBet();
            if (this.getBet() != 0) {
                int[] begCards = this.dealtBegCards();
                System.out.println("Your cards are " + begCards[0] + " " + begCards[1]);
                people.get(0).dealtBegCards();
                this.hit();
            }
            String result = getWinner();
            arrangeWinnings(result);
            while (stopPlaying(result)) {
            }
            resetContinue();
        }
    }

    public void resetContinue() {
        continueB = false;
        stop = false;
    }

    public boolean stopPlaying(String result) throws IOException {
        if (stop == true) {
            stoppedGame();
        } else {
            System.out.println(result + ": Your new net worth: " + getChips());
            jgui.printWords(result + ": Your new net worth: " + getChips());
            ReadWebPageEx r = new ReadWebPageEx();
            jgui.setR(r);
            //System.out.println("The weather outside is " + r.getApi(jgui));
            jgui.printWords("The weather outside is " + r.getApi(jgui));
            System.out.println("Would you like to keep playing? Type stop to stop and anything to keep going.");
            jgui.printWords("Would you like to keep playing? Press either Continue playing or Stop playing.");
            while (!continueB) {
                System.out.print("");
            }
        }
        return stop;
    }

    public void stoppedGame() {
        jgui.printWords("You can either exit the application or continue when you wish");
        continueB = false;
        while (stop) {
            System.out.print("");
        }
        jgui.printWords("All right! We will continue playing.");
    }

    //EFFECTS: gets the winner of the game, if error returns ERROR
    public String getWinner() {
        try {
            return game.chooseWinner(this.getCardSum(), dealer.getCardSum());
        } catch (CardSumException e) {
            return "ERROR";
        }
    }

    //MODIFIES: this and Dealer.cardSum
    //EFFECTS: resets all bets and cardSums
    public void reset() throws NotRealBetException {
        bet(0);
        this.cardSum = 0;
        dealer.resetSum();
        game.reset();
        cards = new ArrayList<Integer>();
        jgui.setCards(cards);
        jdraws.callRepaint();
    }

    //MODIFIES: this
    //EFFECTS: changes the net worth of each player based on who won  //EXTRACT THIS TO INCREASE COHESION
    public void arrangeWinnings(String result) throws IOException {
        try {
            if (result.equals("player wins")) {
                this.addOrMinusChips(game.getTotalBet());
                dealer.addOrMinusChips(-dealer.getBet());
            } else if (result.equals("dealer wins")) {
                this.addOrMinusChips(-this.getBet());
                dealer.addOrMinusChips(game.getTotalBet());
            }
            saveGame();
            reset();
        } catch (NotRealMoneyException | IOException e) {
            bankRupt(result);
            saveGame();
        }
    }

    public void bankRupt(String result) { //THIS IS THE EXTRACTED ONE
        if (result.equals("player wins")) {
            cards = new ArrayList<Integer>();
            jgui.setCards(cards);
            jdraws.callRepaint();
            System.out.println("You did so well that you bankrupted the dealer");
            jgui.printWords("You did so well that you bankrupted the dealer.");

        } else if (result.equals("dealer wins")) {
            chips = 0;
            cards = new ArrayList<Integer>();
            jgui.setCards(cards);
            jdraws.callRepaint();
            System.out.println("You have lost all your money");
            jgui.printWords("You have lost all your money.");
        }
    }

    public void saveGame() throws IOException {
        PrintWriter writer = new PrintWriter("save.txt","UTF-8");
        writer.println(this.chips);
        writer.close();
    }

    private void hit() throws IOException, NotRealBetException {
        while (this.getCardSum() <= 21) {
            System.out.println("Would you like another card?");
            jgui.printWords("Would you like another card?");
            if (hitOrStop()) {
                //System.out.println("Your new card was: " + this.dealtCard());
                jgui.printWords("Your new card was: " + this.dealtCard());
                people.get(0).play();
                resetHit();
            } else {
                resetHit();
                break;
            }
        }
    }

    public void resetHit() {
        hit = false;
        stand = false;
    }

    // EFFECTS: gets user choice on whether the user wants to continue playing
    public boolean hitOrStop() {
        System.out.println("If you would like to hit, type anything. Type stop to stop.");
        jgui.printWords("If you would like to hit, click Hit! Click Stand to stand.");
        while (!hit && !stand) {
            //System.out.println("hit : " + hit + ". stand : " + stand);
            System.out.print("");
        }
        System.out.println("hit : " + hit + ". stand : " + stand);
        return !stand;
    }
}
