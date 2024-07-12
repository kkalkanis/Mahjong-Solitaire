/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Board;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;
import javax.swing.Timer;

/**
 * @author Kalkanis Kiriakos, Emmanouil Kritikakis
 */

public class MyTimer extends Thread{
    private int count;
    private static int flag;
    private  JLabel timeLabel;
    private static int  disappear;

    public void setDisappear(int disappear) {
        MyTimer.disappear = disappear;
    }
    
    public MyTimer(JLabel timeLabel,int disappear) {
        this.timeLabel = timeLabel;
        MyTimer.disappear=disappear;
        
    }

   @Override
    public void run()
    {
        while(disappear!=144)
        {
            try {
                MyTimer.sleep(1000);
            } catch (InterruptedException ex) {
                Logger.getLogger(MyTimer.class.getName()).log(Level.SEVERE, null, ex);
            }       
        count++;
        timeLabel.setText(""+count);
        }  
    }

}
