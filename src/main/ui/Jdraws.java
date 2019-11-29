package ui;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;

public class Jdraws extends JPanel implements Draw {
    ArrayList<Integer> cards = new ArrayList<>();
    public static final int CARD_WIDTH = 50;
    public static final int CARD_HEIGHT = 80;
    public static final int FRAME_WIDTH = 100;
    public static final int FRAME_HEIGHT = 110;
    public boolean user = false;


    public void setCards(ArrayList<Integer> cards) {
        this.cards = cards;
    }

    public ArrayList<Integer> getCards() {
        return cards;
    }

    public boolean changeUser() {
        user = !user;
        return user;
    }

    public boolean getUser() {
        return user;
    }

    public void callRepaint() {
        repaint();
    }


    @Override
    public void paintComponent(Graphics g) { // paint() method
        super.paintComponent(g);
        System.out.println("printcom is called");
        g.setColor(Color.black);
        if (user) {
            g.drawString("Your cards:", 10,20);
        } else {
            g.drawString("Dealer's cards:", 10,20);
        }
        //g.drawRect(10 + cards.size() * 50,10,20,20); //works
        int i = 0;
        for (Integer card : cards) { //only works on first time
            int cardVal = card.intValue();
            System.out.println("i: " + i + ". cards:  " + cardVal);
            g.drawRect(50 + ((CARD_WIDTH + 20) * i), FRAME_WIDTH / 4, CARD_WIDTH, CARD_HEIGHT);
            g.drawString("" + cardVal, (50 + ((CARD_WIDTH + 20) * i) + (CARD_WIDTH / 2)) - 5,
                    (FRAME_WIDTH / 4) + (CARD_HEIGHT / 2));
            i++;
        }
    }
}
