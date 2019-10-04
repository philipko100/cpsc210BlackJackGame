package generate;


public interface Problem {

    // EFFECTS: returns the puzzle
    public String getProblem();

    // EFFECTS: return whether user answer is correct
    public boolean isCorrect(String answer);
}
