package game;

import exceptions.CardSumException;
import exceptions.NotRealCardException;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class BlackJ extends Game {
    private int totalBet = 0;
    private HashSet<Integer> usedCards = new HashSet<Integer>();
    private Map<Integer, Times> usedTimes = new HashMap<>();

    //MODIFIES: this
    //EFFECTS: initialize the game by declaring that none of the cards are used yet
    public BlackJ() {
        for (int i = 1; i <= 13; i++) {
            usedTimes.put(i,new Times(0));
        }
    }


    //EFFECTS: return used card list
    public HashSet<Integer> getUsedCards() {
        return usedCards;
    }

    //EFFECTS: return array of the used cards
    public Map<Integer, Times> getUsedTimes() {
        return usedTimes;
    }

    // MODIFIES: this
    // EFFECTS: adds the double the amount of a player's bet to the table
    @Override
    public void addBet(int bet) {
        totalBet += bet * 2;
    }

    @Override
    public int used(int used) {
        usedCards.add(used);
        usedTimes.put(used,new Times(usedTimes.get(used).number + 1));
        return used;
    }

    //REQUIRES: card is less than or equal to 13 or not negative
    // EFFECTS: return whether the card has been used 4 times
    @Override
    public boolean check(int card) throws NotRealCardException {
        if (card > 13 || card < 0) {
            throw new NotRealCardException();
        }
        return usedTimes.get(card).number == 4;
    }

    // EFFECTS: returns the amount of bets on the table
    public int getTotalBet() {
        return totalBet;
    }

    public void reset() {
        usedTimes = new HashMap<>();
        usedCards = new HashSet<Integer>();
        for (int i = 1; i <= 13; i++) {
            usedTimes.put(i,new Times(0));
        }
        totalBet = 0;
    }

    // REQUIRES: requires that both player number and dealer number are above or equal to 0
    // EFFECTS: chooses winner based on who is closer to 21 while below or equal to 21
    public String chooseWinner(int player, int dealer) throws CardSumException {
        int counterplay = 21 - player;
        int counterdeal = 21 - dealer;
        if (player < 0 || dealer < 0) {
            System.out.println("There is an error in the way"
                    + "cardsum is being kept. "
                    + "Contact engineers immediately.");
            throw new CardSumException();
        }
        if (player > 21 && dealer > 21 || player == dealer) {
            return "draw";
        } else if (counterdeal < counterplay && dealer <= 21 || player > 21) {
            return "dealer wins";
        } else {
            return "player wins";
        }
    }
}
