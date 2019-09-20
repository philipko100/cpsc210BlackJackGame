package generate;

import java.util.ArrayList;
import java.util.Random;

public class Generate {

    public String randomize() {
        Random random = new Random();
        ArrayList<String> puzzles = new ArrayList<String>();

        puzzles.add("1 2 3 4 5 ...");
        puzzles.add("2 4 6 8 10 ...");
        puzzles.add("3 5 7 9 11 ...");
        return puzzles.get(random.nextInt(3));
    }
}
