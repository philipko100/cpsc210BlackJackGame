package placeholder;

import generate.Puzzle;
import generate.Problem;
import generate.MathProblem;
import generate.Puzzle;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PuzzleTest {
    private Puzzle puzzle;
    private Problem problem;
    private MathProblem mathproblem;
    private String rightAns;
    private String wrongAns;


    @BeforeEach
    void beforeEach(){
        puzzle =  new Puzzle("2 4 6 8 10 ...");
        rightAns = "12";
        wrongAns = "20";
        mathproblem = new MathProblem();
    }


    @Test
    void testIsCorrect() {
        assertTrue(puzzle.isCorrect(rightAns));
        assertFalse(puzzle.isCorrect(wrongAns));
    }

    @Test
    void testGenerate() {
        assertEquals("2 4 6 8 10 ...", puzzle.getProblem());
    }

    @Test
    void testMathAddMinus() {
        assertEquals(20+5, mathproblem.add(5));
        assertEquals(20-5, mathproblem.minus(5));
    }

    @Test
    void testMathProblem() {
        assertEquals("10 + 10 = ", mathproblem.getProblem());
        assertFalse(mathproblem.isCorrect("2"));
    }
}
