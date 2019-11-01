package game;

import exceptions.NotRealCardException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public abstract class Game {
    private HashSet<Integer> usedCards = new HashSet<Integer>();
    private Map<Integer,Integer> usedTimes = new HashMap<>();

    public abstract void addBet(int bet);

    public int used(int used) {
        usedCards.add(used);
        usedTimes.put(used,usedTimes.get(used) + 1);
        return used;
    }

    public boolean check(int card) throws NotRealCardException {
        return usedTimes.get(card) == 4;
    }

}
