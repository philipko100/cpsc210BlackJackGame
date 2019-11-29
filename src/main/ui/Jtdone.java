package ui;

import java.util.ArrayList;

public class Jtdone implements Draw {
    int num = 0;
    ArrayList<Integer> cards = new ArrayList<Integer>();

    public void setCards(ArrayList<Integer> list) {
        num = 1;
    }

    public ArrayList<Integer> getCards() {
        return new ArrayList<Integer>();
    }

    public boolean changeUser() {
        return false;
    }

    public boolean getUser() {
        return true;
    }

    public void callRepaint() {
        num = 10;
    }
}
