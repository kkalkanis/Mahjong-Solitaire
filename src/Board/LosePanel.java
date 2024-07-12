/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Board;

import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
/**
 * @author Kalkanis Kiriakos, Emmanouil Kritikakis
 */
public class LosePanel extends JPanel {
    
    public LosePanel(){
        setBackground();
    }

    private void setBackground() {
        ImageIcon icon = new ImageIcon(getClass().getResource("/images/loser.jpg")); 
        JLabel thumb = new JLabel();
        thumb.setIcon(icon);
        add(thumb);
    }

    
}
