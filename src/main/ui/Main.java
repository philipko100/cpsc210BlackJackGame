package ui;

import game.BlackJ;
import players.Player;
import players.Dealer;

import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.util.Scanner;

public class Main {

    // EFFECTS: it states the puzzle for the user
    public int userChips() {
        System.out.println("Hi there! I will be your dealer for Blackjack. "
                + "Please enter a number of the value of chips you have brought.");
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        return Integer.parseInt(input);
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
        player.bet(bet);
    }

    public boolean continuePlaying(Player player, String result) {
        Scanner sc = new Scanner(System.in);
        System.out.println(result + ": Your new net worth: " + player.getChips()
                + ". Would you like to keep playing? Type stop to stop and anything to keep going.");
        if (sc.nextLine().equals("stop")) {
            return false;
        } else {
            return true;
        }
    }

    public static void main(String[] args) throws FileNotFoundException, UnsupportedEncodingException {

        int dealerMoney = 10000;

        Main main = new Main();
        Dealer dealer = new Dealer(dealerMoney);
        Player player = new Player(main.userChips());
        BlackJ game = new BlackJ();

        dealer.bet(500);
        game.addBet(dealer.getBet());
        player.play(game, dealer);

    }
}
