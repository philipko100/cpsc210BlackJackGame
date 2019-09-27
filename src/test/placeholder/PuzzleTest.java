package placeholder;

import generate.Puzzle;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PuzzleTest {
    private Puzzle puzzle;
    private String rightAns;
    private String wrongAns;


    @BeforeEach
    void beforeEach(){
        puzzle =  new Puzzle("2 4 6 8 10 ...");
        rightAns = "12";
        wrongAns = "20";
    }


    @Test
    void testIsCorrect() {
        assertTrue(puzzle.isCorrect(rightAns));
        assertFalse(puzzle.isCorrect(wrongAns));
    }
}
