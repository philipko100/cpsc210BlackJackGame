package ui;

import exceptions.NotRealBetException;
import exceptions.NotRealException;
import game.BlackJ;
import network.ReadWebPageEx;
import players.Player;
import players.Dealer;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {
    String city;

    public String getCity() {
        Scanner sc = new Scanner(System.in);
        if (city == "") {
            city = sc.nextLine();
            return city;
        } else {
            return city;
        }
    }

    // EFFECTS: it states the puzzle for the user
    public int userChips(String line) {
        System.out.println("Hi there! I will be your dealer for Blackjack. "
                + "You start with " + line + " chips.");
        return Integer.parseInt(line);
    }

    // EFFECTS: gets user choice on whether the user wants to continue playing
    public boolean hitOrStop() {
        Scanner sc = new Scanner(System.in);
        System.out.println("If you would like to hit, type anything. Type stop to stop.");
        String input = sc.nextLine();
        return !input.equals("stop");
    }

    //MODIFIES: this
    //EFFECTS: communicate with user to store user's bet
    public void intakeBet(Player player) {
        Scanner sc = new Scanner(System.in);
        System.out.println("How much would you like to bet this round?");
        String input = sc.nextLine();
        int bet = Integer.parseInt(input);
        try {
            player.bet(bet);
        } catch (NotRealBetException e) {
            System.out.println("Invalid bet");
        }
    }

    public boolean continuePlaying(Player player, String result) throws IOException {
        Scanner sc = new Scanner(System.in);
        System.out.println(result + ": Your new net worth: " + player.getChips()
                + ". Would you like to keep playing? Type stop to stop and anything to keep going.");
        ReadWebPageEx r = new ReadWebPageEx();
        System.out.println("The weather outside is " + r.getApi());
        if (sc.nextLine().equals("stop")) {
            System.out.println("Ok, the game has stopped for now, but you can return to it at any time.");
            return false;
        } else {
            return true;
        }
    }

    public static void main(String[] args) throws IOException {

        List<String> lines = Files.readAllLines(Paths.get("save.txt"));
        int dealerMoney = 10000;
        String line = "";
        for (String oneLine : lines) {
            line = oneLine;
        }

        try {
            Main main = new Main();
            Dealer dealer = new Dealer(dealerMoney);
            Player player = new Player(main.userChips(line));
            BlackJ game = new BlackJ();

            dealer.bet(500);
            game.addBet(dealer.getBet());
            player.play(game, dealer, line);
        } catch (NotRealException | IOException e) {
            System.out.println("There was a algorithmic error. Please Contact engineers.");
        }

    }

    public static ArrayList<String> splitOnSpace(String line) {
        String[] splits = line.split(" ");
        return new ArrayList<>(Arrays.asList(splits));
    }
}
