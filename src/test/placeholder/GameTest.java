package placeholder;

import exceptions.CardSumException;
import exceptions.NotRealCardException;
import game.BlackJ;

import game.Times;
import game.Game;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import players.Dealer;
import players.Player;

import java.util.HashSet;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

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
        try {
            assertEquals(5, game.used(5));
            assertEquals(5, game.used(5));
            assertEquals(5, game.used(5));
            assertEquals(5, game.used(5));
            assertTrue(game.check(5));
            assertFalse(game.check(1));

            Map<Integer, Times> usedTimes = game.getUsedTimes();
            assertEquals(4, usedTimes.get(5).number);
            HashSet<Integer> usedCards = game.getUsedCards();
            assertTrue(usedCards.contains(5));

            game.reset();
            assertFalse(game.check(5));
        } catch (NotRealCardException e) {
            fail();
        }
        boolean catchEx = false;
        try {
            game.check(15);
        } catch (NotRealCardException e) {
            catchEx = true;
        }
        assertTrue(catchEx);
        catchEx = false;
        try {
            game.check(-5);
        } catch (NotRealCardException e) {
            catchEx = true;
        }
        assertTrue(catchEx);

    }

    @Test
    void testChooseWinner() {
        try {
            assertTrue(game.chooseWinner(19, 19).equals("draw"));
            assertTrue(game.chooseWinner(21, 21).equals("draw"));
            assertTrue(game.chooseWinner(15, 19).equals("dealer wins"));
            assertTrue(game.chooseWinner(22, 10).equals("dealer wins"));
            assertTrue(game.chooseWinner(19, 15).equals("player wins"));
            assertTrue(game.chooseWinner(19, 22).equals("player wins"));
        } catch (CardSumException e) {
            fail();
        }
        boolean catchEx = false;
        try {
            game.chooseWinner(-1, 19);
        } catch (CardSumException e) {
            catchEx = true;
        }
        assertTrue(catchEx);
        catchEx = false;
        try {
            game.chooseWinner(20, -1);
        } catch (CardSumException e) {
            catchEx = true;
        }
        assertTrue(catchEx);
        catchEx = false;
        try {
            game.chooseWinner(-1, -2).equals("draw");
        } catch (CardSumException e) {
            catchEx = true;
        }
        assertTrue(catchEx);
    }
    @Test
    void testGetWinner() {

            player.cardSum = 19;
            dealer.cardSum = 19;
            assertTrue(player.getWinner(game, dealer).equals("draw"));
            player.cardSum = 21;
            dealer.cardSum = 21;
            assertTrue(player.getWinner(game, dealer).equals("draw"));
            player.cardSum = 15;
            dealer.cardSum = 19;
            assertTrue(player.getWinner(game, dealer).equals("dealer wins"));
            player.cardSum = 22;
            dealer.cardSum = 10;
            assertTrue(player.getWinner(game, dealer).equals("dealer wins"));
            player.cardSum = 19;
            dealer.cardSum = 15;
            assertTrue(player.getWinner(game, dealer).equals("player wins"));
            player.cardSum = 19;
            dealer.cardSum = 22;
            assertTrue(player.getWinner(game, dealer).equals("player wins"));
        player.cardSum = -1;
        dealer.cardSum = 19;
        assertTrue(player.getWinner(game, dealer).equals("ERROR"));
        player.cardSum = 21;
        dealer.cardSum = -1;
        assertTrue(player.getWinner(game, dealer).equals("ERROR"));
        player.cardSum = -21;
        dealer.cardSum = -1;
        assertTrue(player.getWinner(game, dealer).equals("ERROR"));

    }
}