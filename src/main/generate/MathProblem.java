package generate;


public class MathProblem implements Problem, Math {

    private String problem;

    // EFFECTS: constructs a problem with "10 + 10 = "
    public MathProblem() {
        problem = "10 + 10 = ";
    }

    // overloading
    // EFFECTS: constructs a puzzle with the given puzzle string
    public MathProblem(String problem) {
        this.problem = problem;
    }

    @Override
    // EFFECTS: returns the puzzle
    public String getProblem() {
        return problem;
    }

    // EFFECTS: return whether user answer is correct
    @Override
    public boolean isCorrect(String answer) {
        return false;
    }


    @Override
    public int add(int num) {
        return 20 + num;
    }

    @Override
    public int minus(int num) {
        return 20 - num;
    }

}
