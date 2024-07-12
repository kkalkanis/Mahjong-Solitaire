/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import java.io.IOException;

/**
 * @author Kalkanis Kiriakos, Emmanouil Kritikakis
 */
public interface LevelFrameCreator {
    
   void init();
   void setBackground();
   void initBoardFrame() throws IOException;
    
}
