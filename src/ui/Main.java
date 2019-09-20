package ui;

import Generate.Generate;
import Checking.Check;
import Generate.Puzzle;

import java.util.Scanner;

public class Main {

    public void statePuzzle(String puzzle){
        System.out.println("Hi there! I will give you a series of numbers and you will enter a number that you believe best fit the pattern.");
        System.out.println(puzzle);
    }

    public void stateAnswer(boolean isCorrect){
        if(isCorrect)
            System.out.println("You are correct! Well done!");
        else
            System.out.println("Unfortunately, you are incorrect.");
    }

    public static void main(String[] args) {

        Generate generator = new Generate();
        Main main = new Main();

        Puzzle puzzle = new Puzzle(generator.randomize());
        main.statePuzzle(puzzle.getPuzzle());

        Scanner myObj = new Scanner(System.in);  // Create a Scanner object
        String answer = myObj.nextLine();  // Read user input
        main.stateAnswer(puzzle.isCorrect(answer));

    }
}
