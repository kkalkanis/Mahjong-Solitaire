/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Board;

import MVC.ViewWinner;
import MVC.WinnerControler;
import MVC.WinnerPanel;
import Main.ConnectWithDatabase;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.Timer;
import javax.swing.border.LineBorder;
/**
 * @author Kalkanis Kiriakos, Emmanouil Kritikakis
 */
public class TileListener implements ActionListener {

    private String timeToFinish = "", level;
    private JButton tile;
    private static JButton previousTile;
    private static int clicked;
    private JPanel lastPanel = new JPanel();
    private JPanel topPanel = new JPanel();
    private JPanel midPanel = new JPanel();
    private JPanel bottomPanel = new JPanel();
    private static int disappear;
    private JFrame easyFrame;
    private JLayeredPane layeredPane;
    private JButton shuffleButton;
    private ArrayList<Icon> remaining = new ArrayList<Icon>();
    private ShuffleListener shuffleListener;
    private JLabel timeLabel;
    private static boolean counterFlag;
    private ConnectWithDatabase database;
    private MyTimer timer;
    private String nameField;
    private int rows, cols;
    private WinnerPanel model;
    private ViewWinner view;
    private CheckTiles c;

    public TileListener(JButton tile, JPanel lastPanel, JPanel topPanel, JPanel midPanel, JPanel bottomPanel, BoardFrame easyFrame, JLayeredPane layeredPane, JButton shuffleButton, ShuffleListener shuffleListener, JLabel timeLabel, String nameField, String level) {
        this.tile = tile;
        this.lastPanel = lastPanel;
        this.topPanel = topPanel;
        this.midPanel = midPanel;
        this.bottomPanel = bottomPanel;
        this.easyFrame = easyFrame;
        this.layeredPane = layeredPane;
        this.shuffleButton = shuffleButton;
        this.shuffleListener = shuffleListener;
        this.timeLabel = timeLabel;
        this.nameField = nameField;
        this.level = level;
        initSize();
    }

    public static void setDisappear(int disappear) {
        TileListener.disappear = disappear;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        timer = new MyTimer(timeLabel, disappear);
        if (counterFlag == false) {
            timer.start();
        }
        counterFlag = true;
        if (clicked == 0) {
            previousTile = tile;
            previousTile.setBorder(new LineBorder(Color.RED));
            lastPanel.revalidate();
            lastPanel.repaint();
        }
        clicked++;
        if (clicked == 2) {
            tile.setBorder(new LineBorder(Color.RED));
            if (previousTile.getIcon().toString().equals(tile.getIcon().toString()) && tile != previousTile) {
                System.out.println(tile);
                tile.setVisible(false);
                previousTile.setVisible(false);
                disappear += 2;
                System.out.println(tile.isVisible());
            }
            //for special yellow cards
            System.out.println(tile.getIcon().toString());
            System.out.println(previousTile.getIcon().toString());
            if ((tile.getIcon().toString().contains("tiles/lit_" + 33 + ".png")|| tile.getIcon().toString().contains(("tiles/lit_" + 34 + ".png")) || tile.getIcon().toString().contains(("tiles/lit_" + 35 + ".png")) || tile.getIcon().toString().contains(("tiles/lit_" + 36 + ".png"))) && (previousTile.getIcon().toString().contains(("tiles/lit_" + 33 + ".png")) || previousTile.getIcon().toString().contains(("tiles/lit_" + 34 + ".png")) || previousTile.getIcon().toString().contains(("tiles/lit_" + 35 + ".png")) || previousTile.getIcon().toString().contains(("tiles/lit_" + 36 + ".png"))) && tile != previousTile) {
                System.out.println("found yellows");
                tile.setVisible(false);
                previousTile.setVisible(false);
                disappear += 2;
            }
            //for special black cards
            if ((tile.getIcon().toString().contains(("tiles/lit_" + 38 + ".png")) || tile.getIcon().toString().contains(("tiles/lit_" + 39 + ".png")) || tile.getIcon().toString().contains(("tiles/lit_" + 40 + ".png")) || tile.getIcon().toString().contains(("tiles/lit_" + 41 + ".png"))) && (previousTile.getIcon().toString().contains(("tiles/lit_" + 38 + ".png")) || previousTile.getIcon().toString().contains(("tiles/lit_" + 39 + ".png")) || previousTile.getIcon().toString().contains(("tiles/lit_" + 40 + ".png")) || previousTile.getIcon().toString().contains(("tiles/lit_" + 41 + ".png"))) && tile != previousTile) {
                tile.setVisible(false);
                previousTile.setVisible(false);
                disappear += 2;
            }
            if (disappear == 144) {

                timeToFinish = timeLabel.getText();

                timer.setDisappear(disappear);
                easyFrame.remove(timeLabel);
                easyFrame.remove(layeredPane);
                easyFrame.remove(shuffleButton);
                easyFrame.revalidate();
                easyFrame.repaint();
                model = retrieveWinnerPanel();
                view = new ViewWinner();
                WinnerControler controller = new WinnerControler(view, model);
                controller.updateView();
                database = new ConnectWithDatabase(timeToFinish, nameField, level);
            }

            tile.setBorder(new LineBorder(Color.WHITE));
            previousTile.setBorder(new LineBorder(Color.WHITE));
            check_Tiles();
            lastPanel.revalidate();
            lastPanel.repaint();
            clicked = 0;
            previousTile = null;

        }

        availableMoves();
        timer.setDisappear(disappear);
    }

    public static void setCounterFlag(boolean counterFlag) {
        TileListener.counterFlag = counterFlag;
    }

    public static int getDisappear() {
        return disappear;
    }

    private void check_Tiles() {
        c = new CheckTiles(lastPanel, topPanel, midPanel, bottomPanel, rows, cols);

    }

    public void availableMoves() {
        for (int j = 1; j < rows * cols - 1; j++) {
            if (lastPanel.getComponent(j).isVisible() && lastPanel.getComponent(j).isEnabled()) {
                remaining.add(((JButton) lastPanel.getComponent(j)).getIcon());

            }
            if (topPanel.getComponent(j).isVisible() && topPanel.getComponent(j).isEnabled()) {
                remaining.add(((JButton) topPanel.getComponent(j)).getIcon());

            }
            if (midPanel.getComponent(j).isVisible() && midPanel.getComponent(j).isEnabled()) {
                remaining.add(((JButton) midPanel.getComponent(j)).getIcon());

            }
            if (bottomPanel.getComponent(j).isVisible() && bottomPanel.getComponent(j).isEnabled()) {
                remaining.add(((JButton) bottomPanel.getComponent(j)).getIcon());

            }
        }

        int k = 0;
        for (int i = 0; i < remaining.size() - 1; i++) {
            for (int j = i + 1; j < remaining.size(); j++) {
                if (remaining.get(i).toString().equals(remaining.get(j).toString())) {
                    k = 1;
                    System.out.println("found 2 same cards!");
                }
                if ((remaining.get(i).toString().equals(getClass().getResource("src/tiles/lit_" + 33 + ".png")) || remaining.get(i).toString().equals(getClass().getResource("src/tiles/lit_" + 34 + ".png")) || remaining.get(i).toString().equals(getClass().getResource("src/tiles/lit_" + 35 + ".png")) || remaining.get(i).toString().equals(getClass().getResource("src/tiles/lit_" + 36 + ".png"))) && (remaining.get(j).toString().equals(getClass().getResource("src/tiles/lit_" + 33 + ".png")) || remaining.get(j).toString().equals(getClass().getResource("src/tiles/lit_" + 34 + ".png")) || remaining.get(j).toString().equals(getClass().getResource("src/tiles/lit_" + 35 + ".png")) || remaining.get(j).toString().equals(getClass().getResource("src/tiles/lit_" + 36 + ".png")))) {
                    k = 1;
                    System.out.println("found 2 special golds");
                }
                if ((remaining.get(i).toString().equals(getClass().getResource("src/tiles/lit_" + 38 + ".png")) || remaining.get(i).toString().equals(getClass().getResource("src/tiles/lit_" + 39 + ".png")) || remaining.get(i).toString().equals(getClass().getResource("src/tiles/lit_" + 40 + ".png")) || remaining.get(i).toString().equals(getClass().getResource("src/tiles/lit_" + 41 + ".png"))) && (remaining.get(j).toString().equals(getClass().getResource("src/tiles/lit_" + 38 + ".png")) || remaining.get(j).toString().equals(getClass().getResource("src/tiles/lit_" + 39 + ".png")) || remaining.get(j).toString().equals(getClass().getResource("src/tiles/lit_" + 40 + ".png")) || remaining.get(j).toString().equals(getClass().getResource("src/tiles/lit_" + 41 + ".png")))) {
                    k = 1;
                    System.out.println("found 2 black cards");
                }
            }
        }

        if (k == 0 && shuffleListener.getClicked() == 3) {
            easyFrame.revalidate();
            easyFrame.repaint();
            System.out.println("YOU LOSE !");
            easyFrame.remove(layeredPane);
            easyFrame.remove(shuffleButton);
            easyFrame.revalidate();
            easyFrame.repaint();
            LosePanel lose = new LosePanel();
            lose.setBounds(450, 200, 400, 200);
            lose.setOpaque(false);
            easyFrame.add(lose);
        }
        if (k == 0) {
            System.out.println("no more moves, make shuffle");
        }
        remaining.clear();
        easyFrame.revalidate();
        easyFrame.repaint();
    }

    private void initSize() {
        if ("EASY".equals(level)) {
            rows = 6;
            cols = 10;
        } else if ("MEDIUM".equals(level)) {
            rows = 8;
            cols = 12;
        } else if ("HARD".equals(level)) {
            this.rows = 7;
            this.cols = 17;
        }
    }

    private WinnerPanel retrieveWinnerPanel() {
        WinnerPanel win = new WinnerPanel();
        win.setBounds(450, 200, 400, 200);
        win.setOpaque(false);
        easyFrame.add(win);
        win.setVisible(false);
        return win;
    }

}
