/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import Board.BoardFrame;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

/**
 * @author Kalkanis Kiriakos, Emmanouil Kritikakis
 */
public class LevelActionListener implements ActionListener {
    private JTextField nameField;
    private JComboBox cb;   
    private ArrayList<ImageIcon>  tilesLast = new ArrayList<>(); 
    private ArrayList<ImageIcon>  tilesTop = new ArrayList<>(); 
    private ArrayList<ImageIcon>  tilesMid = new ArrayList<>();
    private ArrayList<ImageIcon>  tilesBot = new ArrayList<>();   
    private MainFrame mainFrame;
    private JFrame boardFrame;
    private JLabel labelLevel;
    private BoardFrame easyFrame;
    private final MainFrame mFrame;
    public LevelActionListener(JComboBox cb, MainFrame mainFrame,JTextField nameField,MainFrame mFrame) {
        this.cb = cb;
        this.mainFrame = mainFrame;
        this.nameField=nameField;
        this.mFrame=mFrame;
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        labelLevel = new JLabel();
        labelLevel.setFont(new Font("Serif", Font.PLAIN, 14));
        labelLevel.setHorizontalAlignment(SwingConstants.CENTER);
        if (cb.getSelectedItem() == "EASY") {
            labelLevel.setText("EASY");         
        } else if (cb.getSelectedItem() == "MEDIUM") {
            labelLevel.setText("MEDIUM");
        } else {
            labelLevel.setText("HARD");
        }

        if (ae.getSource() == cb) {
            System.out.println("You chose " + cb.getSelectedItem());
            mainFrame.setVisible(false);
        }
        try {
            initBoardFrame();
        } catch (IOException ex) {
            Logger.getLogger(LevelActionListener.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    

    private void initBoardFrame() throws IOException {
        labelLevel.setBounds(0, 0, 1350, 600);
        if (cb.getSelectedItem() == "EASY") {
            easyFrame = new BoardFrame(mFrame,"EASY");
        } else if (cb.getSelectedItem() == "MEDIUM") {
            easyFrame = new BoardFrame(mFrame,"MEDIUM");
        } else if (cb.getSelectedItem() == "HARD") {
                    easyFrame = new BoardFrame(mFrame,"HARD");
        }
    }
}