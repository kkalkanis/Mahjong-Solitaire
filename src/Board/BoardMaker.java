/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Board;

import java.awt.GridLayout;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;

/**
 * @author Kalkanis Kiriakos, Emmanouil Kritikakis
 */
public class BoardMaker {

    private JPanel lastPanel;
    private JPanel topPanel;
    private JPanel midPanel;
    private JPanel bottomPanel;
    private TileListener listener;
    private int panelLines;
    private int panelColumns;
    private JLayeredPane layeredPane;
    private ArrayList<ImageIcon> tiles = new ArrayList<ImageIcon>();
    private JButton shuffleButton;
    private JLabel timeLabel;
    private BoardFrame easyFrame;
    private ShuffleListener shuffleListener;
    private String nameField;
    private String level;
    private int rows, columns, c;

    public BoardMaker(JPanel lastPanel, JPanel topPanel, JPanel midPanel, JPanel bottomPanel, JLayeredPane layeredPane, JButton shuffleButton, ShuffleListener shuffleListener, JLabel timeLabel, BoardFrame easyFrame, String nameField, String level) throws IOException {
        this.lastPanel = lastPanel;
        this.topPanel = topPanel;
        this.midPanel = midPanel;
        this.bottomPanel = bottomPanel;
        this.layeredPane = layeredPane;
        this.shuffleButton = shuffleButton;
        this.shuffleListener = shuffleListener;
        this.timeLabel = timeLabel;
        this.easyFrame = easyFrame;
        this.nameField = nameField;
        this.level = level;
        this.nameField = nameField;
        selectSize();
        TileListener.setDisappear(0);
        TileListener.setCounterFlag(false);

        initTiles();
        init();
    }

    private void init() throws FileNotFoundException, IOException {
        lastPanel.setLayout(new GridLayout(rows, columns, 1, 1));
        bottomPanel.setLayout(new GridLayout(rows, columns, 1, 1));
        midPanel.setLayout(new GridLayout(rows, columns, 1, 1));
        topPanel.setLayout(new GridLayout(rows, columns, 1, 1));

        byte[] buffer = new byte[1];
        int i = 0, k = 0;

        InputStream in = getClass().getResourceAsStream("/files/" + level + "0.txt");

        assert in != null;
        BufferedReader reader = new BufferedReader(new InputStreamReader(in));

        while ((c = reader.read()) != -1) {

            if ('0' == (char) c) {
                bottomPanel.add(new JButton());
                bottomPanel.getComponent(k).setVisible(false);
                k++;
            } else if ('1' == (char) c) {

                bottomPanel.add(new JButton(tiles.get(i)));
                listener = new TileListener((JButton) bottomPanel.getComponent(k), lastPanel, topPanel, midPanel, bottomPanel, easyFrame, layeredPane, shuffleButton, shuffleListener, timeLabel, nameField, level);

                ((JButton) bottomPanel.getComponent(k)).addActionListener(listener);
                i++;
                k++;
            }
        }

        k = 0;
        in = getClass().getResourceAsStream("/files/" + level + "1.txt");
        reader = new BufferedReader(new InputStreamReader(in));

        while ((c = reader.read()) != -1) {
            if ('0' == (char) c) {
                midPanel.add(new JButton());
                midPanel.getComponent(k).setVisible(false);
                k++;
            } else if ('1' == (char) c) {

                midPanel.add(new JButton(tiles.get(i)));
                listener = new TileListener((JButton) midPanel.getComponent(k), lastPanel, topPanel, midPanel, bottomPanel, easyFrame, layeredPane, shuffleButton, shuffleListener, timeLabel, nameField, level);

                ((JButton) midPanel.getComponent(k)).addActionListener(listener);
                i++;
                k++;
            }
        }

        k = 0;
        in = getClass().getResourceAsStream("/files/" + level + "2.txt");
        reader = new BufferedReader(new InputStreamReader(in));
        while ((c = reader.read()) != -1) {
            if ('0' == (char) c) {
                topPanel.add(new JButton());
                topPanel.getComponent(k).setVisible(false);
                k++;
            } else if ('1' == (char) c) {

                topPanel.add(new JButton(tiles.get(i)));
                listener = new TileListener((JButton) topPanel.getComponent(k), lastPanel, topPanel, midPanel, bottomPanel, easyFrame, layeredPane, shuffleButton, shuffleListener, timeLabel, nameField, level);

                ((JButton) topPanel.getComponent(k)).addActionListener(listener);
                i++;
                k++;
            }
        }

        k = 0;
        in = getClass().getResourceAsStream("/files/" + level + "3.txt");
        reader = new BufferedReader(new InputStreamReader(in));
        while ((c = reader.read()) != -1) {
            if ('0' == (char) c) {
                lastPanel.add(new JButton());
                lastPanel.getComponent(k).setVisible(false);
                k++;
            } else if ('1' == (char) c) {

                lastPanel.add(new JButton(tiles.get(i)));
                listener = new TileListener((JButton) lastPanel.getComponent(k), lastPanel, topPanel, midPanel, bottomPanel, easyFrame, layeredPane, shuffleButton, shuffleListener, timeLabel, nameField, level);

                ((JButton) lastPanel.getComponent(k)).addActionListener(listener);
                i++;
                k++;
            }

        }
        for (int j = 1; j < rows * columns - 1; j++) {
            if (lastPanel.getComponent(j - 1).isVisible() && lastPanel.getComponent(j + 1).isVisible()) {
                lastPanel.getComponent(j).setEnabled(false);
            } else {
                lastPanel.getComponent(j).setEnabled(true);
            }
            if (topPanel.getComponent(j - 1).isVisible() && topPanel.getComponent(j + 1).isVisible() || lastPanel.getComponent(j).isVisible()) {
                topPanel.getComponent(j).setEnabled(false);
            } else {
                topPanel.getComponent(j).setEnabled(true);
            }
            if (midPanel.getComponent(j - 1).isVisible() && midPanel.getComponent(j + 1).isVisible() || topPanel.getComponent(j).isVisible()) {
                midPanel.getComponent(j).setEnabled(false);
            } else {
                midPanel.getComponent(j).setEnabled(true);
            }
            if (bottomPanel.getComponent(j - 1).isVisible() && bottomPanel.getComponent(j + 1).isVisible() || midPanel.getComponent(j).isVisible()) {
                bottomPanel.getComponent(j).setEnabled(false);
            } else {
                bottomPanel.getComponent(j).setEnabled(true);
            }
        }
    }

    private void initTiles() {
        for (int i = 0; i <= 41; i++) //first 16 img *2
        {
            if (i <= 32) {
                for (int j = 0; j < 4; j++) {
                    tiles.add(new ImageIcon(getClass().getResource("/tiles/lit_" + i + ".png")));
                }
            }
            if (i >= 33 && i != 37) {
                tiles.add(new ImageIcon(getClass().getResource("/tiles/lit_" + i + ".png")));

            }
            if (i == 37) {
                for (int j = 0; j < 4; j++) {
                    tiles.add(new ImageIcon(getClass().getResource("/tiles/lit_" + 37 + ".png")));
                }
            }
        }
        Collections.shuffle(tiles);

    }

    private void selectSize() {
        if ("EASY".equals(level)) {
            this.rows = 6;
            this.columns = 10;
        } else if ("MEDIUM".equals(level)) {
            this.rows = 8;
            this.columns = 12;
        } else if ("HARD".equals(level)) {
            this.rows = 7;
            this.columns = 17;
        }
    }
}
