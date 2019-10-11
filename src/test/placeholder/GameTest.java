package placeholder;

import game.BlackJ;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import players.Dealer;
import players.Player;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class GameTest {

    Player player;
    Dealer dealer;
    BlackJ game;
    int dealerMoney;
    int userChips;

    @BeforeEach
    void beforeEach() {
        dealerMoney = 10000;
        userChips = 10000;

        dealer = new Dealer(dealerMoney);
        player = new Player(userChips);
        game = new BlackJ();
    }

    @Test
    void testUsedAndCheckAndReset() {
        assertEquals(5, game.used(5));
        assertEquals(5, game.used(5));
        assertEquals(5, game.used(5));
        assertEquals(5, game.used(5));
        assertTrue(game.check(5));
        assertFalse(game.check(1));
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