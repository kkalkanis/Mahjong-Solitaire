/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Board;

import javax.swing.JPanel;
/**
 * @author Kalkanis Kiriakos, Emmanouil Kritikakis
 */
public final class CheckTiles {

    private JPanel lastPanel;
    private JPanel topPanel;
    private JPanel midPanel;
    private JPanel bottomPanel;
    private int cols, rows;

    public CheckTiles(JPanel lastPanel, JPanel topPanel, JPanel midPanel, JPanel bottomPanel, int rows, int cols) {
        this.lastPanel = lastPanel;
        this.topPanel = topPanel;
        this.midPanel = midPanel;
        this.bottomPanel = bottomPanel;
        this.rows = rows;
        this.cols = cols;
        check();
    }

    public  void check() {
        for (int j = 1; j < rows * cols - 1; j++) {
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
}
