/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Board;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
/**
 * @author Kalkanis Kiriakos, Emmanouil Kritikakis
 */
public class ShuffleListener implements ActionListener {

    private JPanel lastPanel;
    private JPanel topPanel;
    private JPanel midPanel;
    private JPanel bottomPanel;
    private ArrayList<Icon> shuffleList = new ArrayList<Icon>();
    private JButton shuffleButton;
    private int clicked = 0;
    private int rows,cols;
    private String level;
    public ShuffleListener(JPanel lastPanel, JPanel topPanel, JPanel midPanel, JPanel bottomPanel, JButton shuffleButton,String level) {
        this.lastPanel = lastPanel;
        this.topPanel = topPanel;
        this.midPanel = midPanel;
        this.bottomPanel = bottomPanel;
        this.shuffleButton = shuffleButton;
        this.level= level;
        initSize();
    }

    @Override
    public void actionPerformed(ActionEvent ae) {

        int i = 0;
        clicked++;
        for (int j = 1; j < rows*cols-1; j++) {
            if (lastPanel.getComponent(j).isVisible()) {
                shuffleList.add(((JButton) lastPanel.getComponent(j)).getIcon());

            }
            if (topPanel.getComponent(j).isVisible()) {
                shuffleList.add(((JButton) topPanel.getComponent(j)).getIcon());

            }
            if (midPanel.getComponent(j).isVisible()) {
                shuffleList.add(((JButton) midPanel.getComponent(j)).getIcon());

            }
            if (bottomPanel.getComponent(j).isVisible()) {
                shuffleList.add(((JButton) bottomPanel.getComponent(j)).getIcon());

            }
        }
        Collections.shuffle(shuffleList);

        for (int j = 1; j < rows*cols-1; j++) {
            if (lastPanel.getComponent(j).isVisible()) {

                ((JButton) lastPanel.getComponent(j)).setIcon(shuffleList.get(i++));

            }
            if (topPanel.getComponent(j).isVisible()) {
                ((JButton) topPanel.getComponent(j)).setIcon(shuffleList.get(i++));
            }
            if (midPanel.getComponent(j).isVisible()) {
                ((JButton) midPanel.getComponent(j)).setIcon(shuffleList.get(i++));
            }
            if (bottomPanel.getComponent(j).isVisible()) {
                ((JButton) bottomPanel.getComponent(j)).setIcon(shuffleList.get(i++));
            }
        }

        shuffleList.clear();
        if (clicked == 3) {
            shuffleButton.setEnabled(false);
        }

    }

    public int getClicked() {
        return clicked;
    }

    private void initSize() {
        if("EASY".equals(level)){
            rows=6;
            cols=10;
        }
        else if("MEDIUM".equals(level)){
                rows=8;
                cols=12;
        }
         else if("HARD".equals(level)){
            this.rows=7;
            this.cols=17;
        }
    }

}
