package generate;

import checking.Check;

public class Puzzle {
    private String puzzle;

    // EFFECTS: constructs a puzzle with the given puzzle string
    public Puzzle(String puzzle) {
        this.puzzle = puzzle;
    }

    // EFFECTS: returns the puzzle
    public String getPuzzle() {
        return this.puzzle;
    }

    // EFFECTS: return whether user answer is correct
    public boolean isCorrect(String answer) {
        Check c = new Check();
        return c.check(puzzle, answer);
    }
}
