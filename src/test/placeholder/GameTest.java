package placeholder;

import game.BlackJ;

import game.Game;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import players.Dealer;
import players.Player;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class GameTest {

    Player player;
    Dealer dealer;
    BlackJ game;
    Game g;
    int dealerMoney;
    int userChips;

    @BeforeEach
    void beforeEach() {
        dealerMoney = 10000;
        userChips = 10000;

        dealer = new Dealer(dealerMoney);
        player = new Player(userChips);
        game = new BlackJ();
        g = new BlackJ();
    }

    @Test
    void testUsedAndCheckAndReset() {
        assertEquals(5, game.used(5));
        assertEquals(5, game.used(5));
        assertEquals(5, game.used(5));
        assertEquals(5, game.used(5));
        assertTrue(game.check(5));
        assertFalse(game.check(1));

        int[] usedTimes = game.getUsedTimes();
        assertEquals(4, usedTimes[5-1]);
        ArrayList<Integer> usedCards = game.getUsedCards();
        assertTrue(usedCards.contains(5));

        game.reset();
        assertFalse(game.check(5));
    }

    @Test
    void testChooseWinner() {
        assertTrue(game.chooseWinner(19,19).equals("draw"));
        assertTrue(game.chooseWinner(21,21).equals("draw"));
        assertTrue(game.chooseWinner(15,19).equals("dealer wins"));
        assertTrue(game.chooseWinner(22,10).equals("dealer wins"));
        assertTrue(game.chooseWinner(19,15).equals("player wins"));
        assertTrue(game.chooseWinner(19,22).equals("player wins"));
    }
}