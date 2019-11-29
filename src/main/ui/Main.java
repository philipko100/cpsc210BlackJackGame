package ui;

import exceptions.NotRealBetException;
import exceptions.NotRealException;
import network.ReadWebPageEx;
import players.Player;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;
import javax.swing.*;

public class Main {
    String city = "";
    String input = "";
    JFrame frame;
    JPanel bottomPanel;

    // EFFECTS: it states the puzzle for the user
    public int userChips(String line, Jgui jgui) {
        System.out.println("Hi there! I will be your dealer for Blackjack. "
                + "You start with " + line + " chips.");
        jgui.printWords("Hi there! I will be your dealer for Blackjack. "
                + "You start with " + line + " chips.");
        return Integer.parseInt(line);
    }



    public static void main(String[] args) throws IOException {
        Main main = new Main();
        Jdraws jdraws = new Jdraws();
        Jdraws dealerdraws = new Jdraws();
        jdraws.user = true;
        Jgui jgui = new Jgui(jdraws, dealerdraws);

        //int dealerMoney = 10000;
        String line = Files.readAllLines(Paths.get("save.txt")).get(0);

        try {

            Player player = new Player(main.userChips(line, jgui), main, jgui, jdraws, false);
            jgui.setPlayer(player);

            player.play();
        } catch (NotRealException | IOException e) {
            System.out.println("There was a algorithmic error. Please Contact engineers.");
        }

    }
}
