package game;

import java.util.ArrayList;

public abstract class Game {
    private ArrayList<Integer> usedCards = new ArrayList<>();
    private int[] usedTimes = new int[13];

    public abstract void addBet(int bet);

    public int used(int used) {
        usedCards.add(used);
        usedTimes[used - 1] += 1;
        return used;
    }

    public boolean check(int card) {
        return usedTimes[card] == 1;
    }

}
