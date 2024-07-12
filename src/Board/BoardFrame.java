package Board;

import java.awt.Image;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import Main.LevelFrameCreator;
import Main.MainFrame;
import java.net.URISyntaxException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 * @author Kalkanis Kiriakos, Emmanouil Kritikakis
 */
public class BoardFrame extends JFrame implements LevelFrameCreator {
    private JButton shuffleButton;
    private JLayeredPane layeredPane;
    private JPanel lastPanel;
    private JPanel bottomPanel;
    private JPanel midPanel;
    private JPanel topPanel;
    private JLabel timeLabel;
    private JPanel rightBoardPanel;
    private JTextField nameField;
    private JTextArea nameBoard;
    private MainFrame mFrame;
    private String level;
    private int width,height,x,y;
    public BoardFrame(MainFrame mFrame,String level) throws IOException {
        this.mFrame=mFrame;
        this.level=level;
        setSize();
        init();
        setBackground();
        initBoardFrame();
    }

    @Override
    public void init() {
        setLayout(null);
        this.setBounds(0, 0, 1370, 700);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
        setResizable(false);
    }

    @Override
    public void setBackground() {
        try {
            setContentPane(new JLabel(new ImageIcon(getClass().getResource("/images/green.jpg"))));
        } catch (Exception e) {
            System.out.println(e);
        }
        // set the logo icon
        ImageIcon ImageIcon = new ImageIcon(getClass().getResource("/images/mahjong_background.png"));
        Image Image = ImageIcon.getImage();
        this.setIconImage(Image);
        
        //init headerPanel
        HeaderPanel headerPanel = new HeaderPanel(this,mFrame,level);
        add(headerPanel);
        //init rightBoardPanel
        rightBoardPanel=new JPanel();
        rightBoardPanel.setLayout(null);
        rightBoardPanel.setBounds(1034,0,340,750);
        //rightBoardPanel.setOpaque(false);
        ImageIcon rightBoardPanelIcon = new ImageIcon(getClass().getResource("/images/green.jpg"));
        ImageIcon timerIcon = new ImageIcon(getClass().getResource("/images/timer.png")); 
        JLabel rightPanelLabel = new JLabel();
        rightPanelLabel.setIcon(rightBoardPanelIcon);
        rightPanelLabel.setBounds(0, 0, 340, 750);
        rightBoardPanel.add(rightPanelLabel);
        
        add(rightBoardPanel);
        ImageIcon ic = new ImageIcon(getClass().getResource("/images/shuffle.png"));
        shuffleButton = new JButton(ic);
        shuffleButton.setContentAreaFilled(false);
        shuffleButton.setBounds(115, 100, 100, 100);
        rightPanelLabel.add(shuffleButton);
              
        timeLabel=new JLabel(timerIcon);
        timeLabel.setText("0");
        timeLabel.setHorizontalTextPosition(JLabel.CENTER);
        timeLabel.setVerticalTextPosition(JLabel.BOTTOM);
        timeLabel.setIconTextGap(-35);
        timeLabel.setOpaque(false);
        timeLabel.setBounds(70, 200, 200, 200);
        rightPanelLabel.add(timeLabel);
        
        nameBoard=new JTextArea();
        nameBoard.setEditable(false);
        nameBoard.setOpaque(false);
        nameBoard.setBounds(100, 0, 180, 40);
        nameBoard.setText("Username : "+mFrame.getNameField().getText()+"\n"+"Level : "+level);
        rightPanelLabel.add(nameBoard);
        
        
    }

    @Override
    public void initBoardFrame() throws IOException, FileNotFoundException{
        layeredPane = new JLayeredPane();
        layeredPane.setBounds(0, 0, 900, 600);

        lastPanel = new JPanel();
        bottomPanel = new JPanel();
        midPanel = new JPanel();
        topPanel = new JPanel();

     
        
        ShuffleListener shuffleListener = new ShuffleListener(lastPanel, topPanel, midPanel, bottomPanel, shuffleButton,level);
        shuffleButton.addActionListener(shuffleListener);
       
            BoardMaker boardMaker=new BoardMaker(lastPanel,topPanel,midPanel,bottomPanel,layeredPane,shuffleButton,shuffleListener,timeLabel,this,mFrame.getNameField().getText(),level);
       
            
        
        

        bottomPanel.setBounds(x, y, width, height);
        bottomPanel.setOpaque(false);
        layeredPane.add(bottomPanel, Integer.valueOf(0));

        midPanel.setBounds(x=x-10, y=y-10, width, height);
        midPanel.setOpaque(false);
        layeredPane.add(midPanel, Integer.valueOf(1));

        topPanel.setBounds(x=x-10, y=y-10, width, height);
        topPanel.setOpaque(false);
        layeredPane.add(topPanel, Integer.valueOf(2));

        lastPanel.setBounds(x=x-10, y=y-10, width, height);
        lastPanel.setOpaque(false);
        layeredPane.add(lastPanel, Integer.valueOf(3));
        
        add(layeredPane);
        revalidate();
        repaint();
    }
    private void setSize() {
        if("EASY".equals(level)){
            height=500;
            width=500;
            x=130;
            y=105;
        }
        else if("MEDIUM".equals(level)){
                height=520;
                width=600;
                x=100;
                y=80;
        }
         else if("HARD".equals(level)){
                height=500;
                width=800;
                x=0;
                y=60;
        }
    }

    
}
