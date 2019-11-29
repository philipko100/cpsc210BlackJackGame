package placeholder;

import exceptions.NotRealBetException;
import exceptions.NotRealChipOutcome;
import exceptions.NotRealException;
import game.BlackJ;

import game.Game;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import players.Dealer;
import players.Player;
import ui.Jdraws;
import ui.Jgui;
import ui.Main;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class BlackJTest {

    Player player;
    Dealer dealer;
    BlackJ game;
    int dealerMoney;
    int userChips;
    File file;

    @BeforeEach
    void beforeEach() throws NotRealBetException {
        dealerMoney = 10000;
        userChips = 10000;

        player = new Player(userChips, new Main(), new Jgui(new Jdraws(),new Jdraws()), new Jdraws());
         player.hit = true;
         player.stand = false;
        player.stop = true;
        player.continueB = true;
        dealer = player.getDealer();
         game = player.getGame();
         file = new File("/Library/Java/JavaVirtualMachines/CPSC210Labs/project6/testFiles/save.txt");
    }

    @Test
    void testBet() {
        boolean playerEx = false;
        boolean dealerEx = false;
        try {
            player.bet(1000);
            assertEquals(1000, player.getBet());

            dealer.bet(1000); //adds this to the initializing 500 bet
            assertEquals(1000 + 500, dealer.getBet());
        } catch (NotRealBetException e) {
            fail("All the bets are legit.");
        }
        try {
            player.bet(-10);
        } catch (NotRealBetException e) {
            // I should catch this exception.
            assertTrue(true);
            playerEx = true;
        }
        assertTrue(playerEx);
        try {
            dealer.bet(-10);
        } catch (NotRealBetException e) {
            // I should catch this exception.
            assertTrue(true);
            dealerEx = (true);
        }
        assertTrue(dealerEx);
    }

    @Test
    void testDealtBegCards() {
        assertFalse(player.getCardSum() > 0);
        player.dealtBegCards();
        assertTrue(player.getCardSum() > 0);

        assertFalse(dealer.getCardSum() > 0);
        dealer.dealtBegCards();
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
        player.dealtCard();
        assertTrue(player.getCardSum() > 0);

        dealer.dealtCard();
        assertTrue(dealer.getCardSum() > 0);
    }

    @Test
    void testAddOrMinusChips() {
        try {
            player.addOrMinusChips(500);
            assertEquals(userChips + 500, player.getChips());
            player.addOrMinusChips(-500);
            assertEquals(userChips, player.getChips());

            dealer.addOrMinusChips(500);
            assertEquals(dealerMoney + 500, dealer.getChips());
            dealer.addOrMinusChips(-500);
            assertEquals(dealerMoney, dealer.getChips());
        } catch (NotRealChipOutcome e) {
            fail();
        }
        try {
            player.addOrMinusChips(-userChips-100);
        } catch (NotRealChipOutcome e) {
            assertTrue(true);
        }
        try {
            dealer.addOrMinusChips(-dealerMoney-100);
        } catch (NotRealChipOutcome e) {
            assertTrue(true);
        }
    }



    @Test
    void testReset() {
        try {
            player.bet(1000);
            player.dealtCard();
            dealer.dealtCard();
            player.reset();
            assertEquals(0, player.getBet());
            assertEquals(0, player.getCardSum());
            assertEquals(0, dealer.getCardSum());
            assertEquals(0, game.getTotalBet());
        } catch (NotRealException e) {
            fail();
        }
    }

    @Test
    void testArrangeWinnings() throws IOException {
        try {
            //in addition to the initializing 500 dealer bet
            game.addBet(1000);
            dealer.bet(500);
            player.bet(1000);
            String result = "player wins";
            player.arrangeWinnings(result);
            assertEquals(15000, player.getChips());
            assertEquals(dealerMoney - 500 - 500, dealer.getChips());
            //arrangeWinnings() resets all the bets
            game.addBet(1000);
            dealer.bet(500);
            player.bet(1000);
            result = "dealer wins";
            player.arrangeWinnings(result);
            assertEquals(13000, dealer.getChips());
            assertEquals(14000, player.getChips());
        } catch (NotRealException e) {
            fail();
        } catch (IOException e) {
            fail();
        }
        int previousPMoney = player.getChips();
        int previousDMoney = dealer.getChips();
        game.addBet(1000);
        dealer.bet = (dealer.getChips() + 1);
        player.bet = (1000);
        String result = "player wins";
        player.arrangeWinnings(result);
        assertEquals(previousDMoney, dealer.getChips());
        assertEquals(previousPMoney + 1000 * 2, player.getChips());

            game.addBet(1000);
            dealer.bet = 500;
            player.bet = player.getChips() + 1;
             result = "dealer wins";
            player.arrangeWinnings(result);
            assertEquals(0, player.getChips());
            assertEquals(previousDMoney, dealer.getChips());

    }

    @Test
    void testHostPlay() {
        dealer.hostPlay();
        //assertTrue(dealer.getCardSum() >= 17);
    }

    @Test
    void testBankRupt() {
        player.bankRupt("player wins");
        assertEquals(userChips, player.getChips());
        player.bankRupt("dealer wins");
        assertEquals(0, player.getChips());
    }
}
