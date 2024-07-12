/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MVC;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

/**
 * einai o controller pou xrisimopoioume gia to mvc o o opoios pairnei ta 
 * model kai view kai allazei to model kai to emfanizei me th view 
 * @author Kalkanis Kiriakos, Emmanouil Kritikakis
 * @param1 view einai to view dhladh h klash pou xrhsimopoiei o controller gia na emfanisei to panel
 * @param2 model einai to panel pou theloume na emfanisoume otan kerdizei o paikths 
 */
public class WinnerControler {
    private ViewWinner view;
    private WinnerPanel model;

    public WinnerControler(ViewWinner view, WinnerPanel model) {
        this.view = view;
        this.model = model;
    }

    
    public Icon getModelIcon(){
        return model.getIcon();
    }
    public JLabel getModelThumb(){
        return model.getThumb();
    }
    public void setModelIcon(ImageIcon icon){
        model.setIcon(icon);
    }
    public void setModelThumb(JLabel thumb){
        model.setThumb(thumb);
    }
    
    public void updateView(){
        view.setModelVisible(model);
    }
}
