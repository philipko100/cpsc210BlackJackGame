package generate;

import java.util.ArrayList;
import java.util.Random;

public class Generate {

    // EFFECTS: return a randomized puzzle
    public String randomize() {
        Random random = new Random();
        ArrayList<String> puzzles = new ArrayList<String>();

        puzzles.add("1 2 3 4 5 ...");
        return puzzles.get(random.nextInt(3));
    }
}
