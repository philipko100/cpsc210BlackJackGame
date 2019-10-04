package generate;

import checking.Check;

public class Puzzle extends Problem {

    // EFFECTS: constructs a puzzle with the given puzzle string
    public Puzzle(String puzzle) {
        super(puzzle);
    }

    // EFFECTS: return whether user answer is correct
    @Override
    public boolean isCorrect(String answer) {
        Check c = new Check();
        return c.check(problem, answer);
    }
}
