package placeholder;

import exceptions.NotRealBetException;
import exceptions.NotRealCardException;
import game.BlackJ;
import game.Game;
import generate.Generate;
import generate.Randomize;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import players.Player;
import ui.*;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class GeneratorTest {

    Generate gen;
    Randomize ran;
    BlackJ game;
    Player player;

    @BeforeEach
    void beforeEach() throws NotRealBetException {
        player = new Player(1000, new Main(), new Jtgraph(), new Jtdone(), true);
        gen = player.getGen();
        game = player.getGame();
        ran = new Randomize();
    }

    @Test
    void testDealMethods() {
        try {
            assertTrue(0 < ran.deal());
            assertTrue(ran.deal() < 99);
            assertTrue(ran.randomInt != 0);

            assertTrue(0 < gen.deal());
            assertTrue(gen.deal() < 14);

            assertEquals(5, game.used(5));
            assertEquals(5, game.used(5));
            assertEquals(5, game.used(5));
            assertEquals(5, game.used(5));
            assertTrue(game.check(5));
            assertTrue(gen.deal() != 5);
        } catch (NotRealCardException e) {
            fail();
        }
    }
}
