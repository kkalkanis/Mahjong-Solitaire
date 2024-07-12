/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MVC;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * einai to panel pou emfanizetai otan o paikths kerdizei 
 * @author Kalkanis Kiriakos, Emmanouil Kritikakis
 */
public class WinnerPanel extends JPanel {
    private ImageIcon icon;
    private JLabel thumb;

    public JLabel getThumb() {
        return thumb;
    }
    public void setThumb(JLabel thumb) {
        this.thumb = thumb;
    }
    public void setIcon(ImageIcon icon) {
        this.icon = icon;
    }
     public ImageIcon getIcon() {
        return icon;
    }
    public WinnerPanel(){
        setBackground();
    }

    private void setBackground() {
        icon = new ImageIcon(getClass().getResource("/images/winer.png")); 
        thumb = new JLabel();
        thumb.setIcon(icon);
        add(thumb);
    }

    
}
