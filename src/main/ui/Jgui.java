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

public class Jgui extends JComponent implements GUI {
    public JFrame frame;
    JPanel bottomPanel;
    JPanel firstMiddle;
    JPanel secondMiddle;
    JButton torontoBtn;
    JButton nyBtn;
    JButton laBtn;
    JButton vancouverBtn;
    JButton hitButton;
    JButton standButton;
    JButton stopButton;
    JButton continueButton;
    JTextArea textArea;
    public Player player;
    public ReadWebPageEx rwebCon;
    JTextField betInput;
    JLabel betLabel;
    JButton betButton;
    public ArrayList<Integer> cards;
    JPanel secondTop;
    JPanel thirdTop;
    Jdraws jdraws;
    public Jdraws dealerdraws;
    int numOfParts = 6;

    public static final int FRAME_WIDTH = 1400;
    public static final int FRAME_HEIGHT = 1000;
    public static final int CARD_WIDTH = 100;
    public static final int CARD_HEIGHT = 100;

    public Jgui(Jdraws jdraws, Jdraws dealerdraws) {
        this.jdraws = jdraws;
        this.dealerdraws = dealerdraws;
        initializeJ();
    }

    //MODIFIES: this
    //EFECTS: changes card list to given
    public void setCards(ArrayList<Integer> cards) {
        this.cards = cards;
        jdraws.setCards(cards);
    }

    public ArrayList<Integer> getCards() {
        return cards;
    }


    //EFFECTS: changes rwebCon to given
    public void setR(ReadWebPageEx r) {
        this.rwebCon = r;
    }

    //EFFECTS: changes the numofParts variable to given
    public int setNumParts(int num) {
        numOfParts = num;
        return numOfParts;
    }

    public int returnNumParts() {
        return numOfParts;
    }

    //EFFECTS: set Jpanels to gui
    public void drawsToTops() {
        secondTop = jdraws;
        thirdTop = dealerdraws;
    }

    // MODIFIES this
    //EFFECTS: initializes Jframe and adds all jpanels
    public void initializeJ() {
        frame = new JFrame("My First GUI");
        frame = setFrame(frame);
        textArea = new JTextArea();
        JScrollPane scrollPane = new JScrollPane(textArea);
        DefaultCaret caret = (DefaultCaret)textArea.getCaret();
        caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
        textArea.setEditable(false);
        frame.add(scrollPane);
        drawsToTops();
        frame.add(secondTop);

        frame.add(thirdTop);
        firstMiddle = initializeFirst(new JPanel());
        secondMiddle = initializeSecond(new JPanel());
        frame.add(firstMiddle);
        frame.add(secondMiddle);
        bottomPanel = setBottom(new JPanel());
        frame.add(bottomPanel); //second panel
        frame.setVisible(true);
    }


    //MODIFIES this
    //EFFECTS: initialize betting jpanel
    public JPanel initializeFirst(JPanel panel) {
        betLabel = new JLabel("Bet Amount: ");
        betInput = new JTextField(16);
        betButton = createBetButton(new JButton("Bet"));
        panel.add(betLabel);
        panel.add(betInput);
        panel.add(betButton);
        return panel;
    }

    JButton createBetButton(JButton button) {
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    player.bet(Integer.parseInt(betInput.getText()));
                } catch (NotRealBetException ex) {
                    printWords("That was not a real bet. Please input another bet.");
                }
            }
        });
        return button;
    }

    JPanel initializeSecond(JPanel panel) {
        torontoBtn = createTorontoButton(new JButton("Toronto"));
        vancouverBtn = createVancouverButton(new JButton("Vancouver"));
        nyBtn = createNewYorkButton(new JButton("New York"));
        laBtn = createLAButton(new JButton("LA"));
        panel.add(torontoBtn);
        panel.add(vancouverBtn);
        panel.add(nyBtn);
        panel.add(laBtn);
        return panel;
    }

    JButton createTorontoButton(JButton button) {
        button.setPreferredSize(new Dimension(FRAME_WIDTH / 4 - 50, FRAME_HEIGHT / numOfParts - 50));
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                rwebCon.city = "Toronto";
                rwebCon.noCity = false;
                System.out.println("Toronto");
            }
        });
        return button;
    }

    JButton createVancouverButton(JButton button) {
        button.setPreferredSize(new Dimension(FRAME_WIDTH / 4 - 50, FRAME_HEIGHT / numOfParts - 50));
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                rwebCon.city = "Vancouver";
                rwebCon.noCity = false;
                System.out.println("Vancouver");
            }
        });
        return button;
    }

    JButton createNewYorkButton(JButton button) {
        button.setPreferredSize(new Dimension(FRAME_WIDTH / 4 - 50, FRAME_HEIGHT / numOfParts - 50));
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                rwebCon.city = "New York";
                rwebCon.noCity = false;
                System.out.println("New York");
            }
        });
        return button;
    }

    JButton createLAButton(JButton button) {
        button.setPreferredSize(new Dimension(FRAME_WIDTH / 4 - 50, FRAME_HEIGHT / numOfParts - 50));
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                rwebCon.city = "Los Angeles";
                rwebCon.noCity = false;
                System.out.println("Los Angeles");
            }
        });
        return button;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    JFrame setFrame(JFrame frame) {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(FRAME_WIDTH,FRAME_HEIGHT);
        frame.setLayout(new GridLayout(numOfParts,1));
        return frame;
    }

    JPanel setBottom(JPanel panel) {
        hitButton = createHitButton(new JButton("Hit"));
        standButton = createStandButton(new JButton("Stand"));
        stopButton = createStopButton(new JButton("Stop Game"));
        continueButton = createContinueButton(new JButton("Continue Game"));
        panel.add(hitButton);
        panel.add(standButton);
        panel.add(continueButton);
        panel.add(stopButton);
        return panel;
    }

    //EFFECTS: print the words in the text area
    public void printWords(String string) {
        textArea.append(string + "\n");
    }

    JButton createHitButton(JButton button) {
        button.setPreferredSize(new Dimension(FRAME_WIDTH / 4 - 50, FRAME_HEIGHT / 4 - 50));
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                player.hit = true;
                System.out.println("hit");
            }
        });
        return button;
    }

    JButton createStandButton(JButton button) {
        button.setPreferredSize(new Dimension(FRAME_WIDTH / 4 - 50, FRAME_HEIGHT / 4 - 50));
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                player.stand = true;
                System.out.println("stand");
            }
        });
        return button;
    }

    JButton createStopButton(JButton button) {
        button.setPreferredSize(new Dimension(FRAME_WIDTH / 4 - 50, FRAME_HEIGHT / 4 - 50));
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                player.stop = true;
                player.continueB = true;
                System.out.println("stop");
            }
        });
        return button;
    }

    JButton createContinueButton(JButton button) {
        button.setPreferredSize(new Dimension(FRAME_WIDTH / 4 - 50, FRAME_HEIGHT / 4 - 50));
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                player.continueB = true;
                player.stop = false;
                System.out.println("Continue");
            }
        });
        return button;
    }
}
