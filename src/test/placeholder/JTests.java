package placeholder;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ui.Jdraws;
import ui.Jgui;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

public class JTests {
    Jdraws jdraws;
    Jdraws dealerdraws;
    Jgui jgui;

    @BeforeEach
    void beforeEach() {
        jdraws = new Jdraws();
        dealerdraws = new Jdraws();
        jgui = new Jgui(jdraws, dealerdraws);
    }

    @Test
    void testSetCards() {
        ArrayList<Integer> cards = new ArrayList<>();
        jdraws.setCards(cards);
        assertEquals(cards, jdraws.getCards());
        assertFalse(cards == dealerdraws.getCards());
        jgui.setCards(cards);
        assertEquals(cards, jgui.getCards());
        assertEquals(cards, jdraws.getCards());
        assertEquals(cards, dealerdraws.getCards());
    }

    @Test
    void changeUser() {
        assertFalse(jdraws.getUser());
        assertFalse(dealerdraws.getUser());
        assertTrue(jdraws.changeUser());
        assertTrue(jdraws.getUser());
    }
}
