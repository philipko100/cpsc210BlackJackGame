package placeholder;

import game.BlackJ;

import game.Game;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import players.Dealer;
import players.Player;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class BlackJTest {

    Player player;
    Dealer dealer;
    BlackJ game;
    int dealerMoney;
    int userChips;

    @BeforeEach
    void beforeEach(){
        dealerMoney = 10000;
        userChips = 10000;

         dealer = new Dealer(dealerMoney);
         player = new Player(userChips);
         game = new BlackJ();
    }

    @Test
    void testBet() {
        player.bet(1000);
        assertEquals(1000, player.getBet());

        dealer.bet(1000);
        assertEquals(1000, dealer.getBet());
    }

    @Test
    void testDealtBegCards() {
        assertFalse(player.getCardSum() > 0);
        player.dealtBegCards(game);
        assertTrue(player.getCardSum() > 0);

        assertFalse(dealer.getCardSum() > 0);
        dealer.dealtBegCards(game);
        assertTrue(dealer.getCardSum() > 0);
    }

    @Test
    void testAddCards() {
        player.addCard(5);
        assertEquals(5, player.getCardSum());

        dealer.addCard(5);
        assertEquals(5, dealer.getCardSum());
    }

    @Test
    void testDealtCard() {
        player.dealtCard(game);
        assertTrue(player.getCardSum() > 0);

        dealer.dealtCard(game);
        assertTrue(dealer.getCardSum() > 0);
    }

    @Test
    void testAddOrMinusChips() {
        player.addOrMinusChips(500);
        assertEquals(userChips+500, player.getChips());
        player.addOrMinusChips(-500);
        assertEquals(userChips, player.getChips());

        dealer.addOrMinusChips(500);
        assertEquals(dealerMoney+500, dealer.getChips());
        dealer.addOrMinusChips(-500);
        assertEquals(dealerMoney, dealer.getChips());
    }



    @Test
    void testReset() {
        player.bet(1000);
        player.dealtCard(game);
        dealer.dealtCard(game);
        player.reset(dealer, game);
        assertEquals(0, player.getBet());
        assertEquals(0, player.getCardSum());
        assertEquals(0, dealer.getCardSum());
        assertEquals(0, game.getTotalBet());
    }

    @Test
    void testArrangeWinnings() {
        game.addBet(1000);
        dealer.bet(500);
        player.bet(1000);
        String result = "player wins";
        player.arrangeWinnings(result, game, dealer);
        assertEquals(userChips + 1000 * 2, player.getChips());
        assertEquals(dealerMoney - 500, dealer.getChips());

        game.addBet(1000);
        dealer.bet(500);
        player.bet(1000);
        result = "dealer wins";
        player.arrangeWinnings(result, game, dealer);
        assertEquals(dealerMoney - 500  + 1000 * 2, dealer.getChips());
        assertEquals(userChips + 1000 * 2 - 1000, player.getChips());

    }

    @Test
    void testHostPlay() {
        dealer.hostPlay(game);
        assertTrue(dealer.getCardSum() >= 17);
    }
}
