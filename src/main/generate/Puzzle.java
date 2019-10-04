package generate;

import checking.Check;

public class Puzzle implements Problem {

    private String problem;

    // EFFECTS: constructs a puzzle with the given puzzle string
    public Puzzle(String puzzle) {
        this.problem = puzzle;
    }

    // EFFECTS: returns the puzzle
    @Override
    public String getProblem() {
        return problem;
    }

    // EFFECTS: return whether user answer is correct
    @Override
    public boolean isCorrect(String answer) {
        Check c = new Check();
        return c.check(problem, answer);
    }
}
