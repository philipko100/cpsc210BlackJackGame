package ui;

import network.ReadWebPageEx;
import players.Player;

import java.util.ArrayList;

public interface GUI {

    abstract  void setCards(ArrayList<Integer> cards);


    abstract  void setR(ReadWebPageEx r);

    abstract  int setNumParts(int num);


    abstract  int returnNumParts();

    abstract  void drawsToTops();

    abstract  void initializeJ();


    abstract  void setPlayer(Player player);


    abstract  void printWords(String string);
}
