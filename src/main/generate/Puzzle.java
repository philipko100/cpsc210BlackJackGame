package generate;

import checking.Check;

public class Puzzle {
    private String puzzle;

    public Puzzle(String puzzle) {
        this.puzzle = puzzle;
    }

    public String getPuzzle() {
        return this.puzzle;
    }

    public boolean isCorrect(String answer) {
        Check c = new Check();
        return c.check(puzzle, answer);
    }
}
