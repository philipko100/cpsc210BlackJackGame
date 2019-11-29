package ui;

import java.util.ArrayList;

public interface Draw {

    public void setCards(ArrayList<Integer> cards);

    public ArrayList<Integer> getCards();

    public boolean changeUser();

    public boolean getUser();

    public void callRepaint();
}
