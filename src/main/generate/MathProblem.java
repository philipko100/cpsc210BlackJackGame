package generate;


public class MathProblem extends Problem {

    // EFFECTS: constructs a problem with "10 + 10 = "
    public MathProblem() {
        super("10 + 10 = ");
    }

    // overloading
    // EFFECTS: constructs a puzzle with the given puzzle string
    public MathProblem(String problem) {
        super(problem);
    }


    // EFFECTS: return whether user answer is correct
    @Override
    public boolean isCorrect(String answer) {
        return false;
    }
}
