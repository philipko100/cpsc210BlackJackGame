package placeholder;

import game.BlackJ;
import game.Game;
import generate.Generate;
import generate.Randomize;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class GeneratorTest {

    Generate gen;
    Randomize ran;
    BlackJ game;

    @BeforeEach
    void beforeEach() {
        gen = new Generate();
        ran = new Randomize();
        game = new BlackJ();
    }

    @Test
    void testDealMethods() {
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
        assertTrue(gen.deal(game) != 5);
    }
}
