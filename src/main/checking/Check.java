package checking;

public class Check {
    public String[] puzzles = {"1 2 3 4 5 ...", "2 4 6 8 10 ...", "3 5 7 9 11 ..."};

    public boolean check(String puzzle, String answer) {
        for (int i = 0; i < puzzles.length; i++) {
            if (puzzles[i].equals(puzzle)) {
                if (i == 0 && answer.equals("6") || i == 1 && answer.equals("12") || i == 2 && answer.equals("13")) {
                    return true;
                }
            }
        }
        return false;
    }
}
