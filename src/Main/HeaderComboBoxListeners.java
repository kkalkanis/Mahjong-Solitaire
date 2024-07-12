/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;


import Board.History;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import static java.lang.System.exit;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;

/**
 * @author Kalkanis Kiriakos, Emmanouil Kritikakis
 */
public class HeaderComboBoxListeners implements ActionListener {
    private JComboBox gameCombox;
    private MainFrame mainFrame;
    private JComboBox optionsC;
    private  History history;
    private int flag=0;
    private JFrame helpFrame;
   
    public HeaderComboBoxListeners(JComboBox gameCombox,MainFrame mainFrame,JComboBox optionsC){
        this.gameCombox=gameCombox;
        this.mainFrame=mainFrame;
        this.optionsC=optionsC;
    }
    @Override
    public void actionPerformed(ActionEvent ae) {
     if(optionsC.getSelectedIndex()==1){
            if(flag==0){
                history = new History();
                flag =1;}
                                        }
     else if(optionsC.getSelectedIndex()==2){     
            if(flag==1){
                  history.dispose();
                  flag=0;
            }                       
        } 
    }
}
