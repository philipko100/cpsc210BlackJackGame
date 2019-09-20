package Generate;

import Checking.Check;

public class Puzzle {
    private String puzzle;

    public Puzzle(String puzzle){
        this.puzzle = puzzle;
    }

    public String getPuzzle(){
        return this.puzzle;
    }

    public boolean isCorrect(String answer) {
        Check C = new Check();
        return C.check(puzzle, answer);
    }
}
