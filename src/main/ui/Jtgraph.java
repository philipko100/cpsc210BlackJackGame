package ui;

import exceptions.NotRealBetException;
import network.ReadWebPageEx;
import players.Player;

import javax.swing.*;
import javax.swing.text.DefaultCaret;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class Jtgraph implements GUI {
    int num = 0;
    Player player;
    ArrayList<Integer> cards = new ArrayList<Integer>();

    public void setCards(ArrayList<Integer> cards) {
        num = 1;
    }

    public ArrayList<Integer> getCards(ArrayList<Integer> list) {
        return new ArrayList<>();
    }


    public void setR(ReadWebPageEx r) {
        num = 2;
    }

    public int setNumParts(int num) {
        return num;
    }


    public int returnNumParts() {
        return num;
    }

    public void drawsToTops() {
        num = 3;
    }

    public void initializeJ() {
        num = 5;
    }



    public void setPlayer(Player player) {
        this.player = player;
    }


    public void printWords(String string) {
        num = 10;
    }
}
