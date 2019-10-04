package generate;


public class Problem {
    protected String problem;

    // EFFECTS: constructs a puzzle with the given puzzle string
    public Problem(String problem) {
        this.problem = problem;
    }

    // EFFECTS: returns the puzzle
    public String getProblem() {
        return this.problem;
    }

    // EFFECTS: return whether user answer is correct
    public boolean isCorrect(String answer) {
        return true;
    }
}
