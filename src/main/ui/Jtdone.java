package ui;

import java.util.ArrayList;

public class Jtdone implements Draw {
    int num = 0;
    ArrayList<Integer> cards = new ArrayList<Integer>();

    //EFECTS: changes number to one
    public void setCards(ArrayList<Integer> list) {
        num = 1;
    }

    public ArrayList<Integer> getCards() {
        return new ArrayList<Integer>();
    }

    //EFFECTS: changes users to oppposite
    public boolean changeUser() {
        return false;
    }

    public boolean getUser() {
        return true;
    }

    //EFFECTS: changes number to 10
    public void callRepaint() {
        num = 10;
    }
}
