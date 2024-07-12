package Main;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.UnsupportedLookAndFeelException;

/**
 * @author Kalkanis Kiriakos, Emmanouil Kritikakis
 */
public final class MainFrame extends JFrame {

    private JComboBox cb, gameC, optionsC, helpC;
    private JPanel headerPanel;
    private JTextField nameField;
    private JFrame helpFrame;
    private static MainFrame mainFrame = new MainFrame();

    String instructions = "Mahjong is a traditional Chinese game played with tiles.\n" +
            "Here's a brief introduction to the rules of Mahjong:\n" +
            "\n" +
            "Objective of the game: The goal of Mahjong is for a player to gather tiles into sets and complete a Mahjong (a specific set of tiles) before the other players.\n" +
            "\n" +
            "Basic rules:\n" +
            "1. The game: Players draw and discard tiles in turns to complete a set.\n" +
            "2. Tile sets: A set can be a triplet (three identical tiles), a sequence (three consecutive tiles of the same suit), or a pair (two identical tiles).\n" +
            "3. Mahjong: Each player must complete Mahjong, a set of 14 tiles that includes a pair and four triplets, with one tile being the 'Mahjong tile'.\n" +
            "\n" +
            "Getting started:\n" +
            "1. Tile distribution: Each player receives a set number of tiles according to the game's rules.\n" +
            "2. Gameplay sequence: Players draw and discard tiles in sequence, aiming to collect sets.\n" +
            "3. Tile actions: Key tile actions include acquiring, discarding, and reshuffling tiles to complete sets.";
    
    public JTextField getNameField() {
        return nameField;
    }

    //Get the only object available
    public static MainFrame getInstance() {
        return mainFrame;
    }

    private MainFrame() {
        setTitle("Mahjong Solitaire");
        initLookAndFeel();
        frameComponents();
        initComboPanel();
        initLevel();
        initListeners();

    }

    private void frameComponents() {
        setLayout(null);
        this.setBounds(380, 50, 600, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        // set the logo icon
        ImageIcon ImageIcon = new ImageIcon(getClass().getResource("/images/mahjong_background.png"));
        Image Image = ImageIcon.getImage();
        this.setIconImage(Image);

        //set background image
        try{
              setContentPane(new JLabel(new ImageIcon(getClass().getResource("/images/mahjong.jpg"))));
        }catch(Exception e){
            System.out.println(e);
        }
            
      
        setVisible(true);
        setResizable(false);
    }

    private void initLevel() {
        String languages[] = {"EASY", "MEDIUM", "HARD"};
        cb = new JComboBox(languages);
        cb.setBounds(220, 540, 150, 30);
        cb.setSelectedIndex(0);
        add(cb);
        revalidate();
        repaint();
    }

    private void initComboPanel() {
        //init headerPanel
        headerPanel = new JPanel();
        headerPanel.setLayout(new GridLayout(1, 3, 1, 1));

        //init of Game Combo Box
        String game[] = {"EXIT"};
        gameC = new JComboBox(game);
        gameC.setBounds(0, 0, 150, 20);
        gameC.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (gameC.getSelectedItem() == "EXIT") {
                    mainFrame.dispatchEvent(new WindowEvent(mainFrame, WindowEvent.WINDOW_CLOSING));
                }
            }

        });

        //init of Options ComboBox
        String options[] = {"OPTIONS", "SHOW HISTORY", "HIDE HISTORY"};
        optionsC = new JComboBox(options);
        optionsC.setBounds(0, 0, 150, 20);
        optionsC.setSelectedIndex(0);

        headerPanel.add(optionsC);

        //init of HelpComboBox
        String help[] = {"HELP", "ABOUT"};
        helpC = new JComboBox(help);
        helpC.setBounds(0, 0, 150, 20);
        helpC.setSelectedIndex(0);
        headerPanel.add(helpC);
        headerPanel.add(gameC);

        // init some settings of headerPanel 
        headerPanel.setBounds(0, 0, 300, 30);
        headerPanel.setOpaque(false);
        add(headerPanel);

        //init nameField
        nameField = new JTextField("    Enter your Username");
        nameField.setBounds(220, 518, 150, 25);
        nameField.setBackground(Color.LIGHT_GRAY);
        nameField.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent fe) {
                nameField.setText("");
            }

            @Override
            public void focusLost(FocusEvent fe) {
            }
        });
        Font fo = new Font("Serif", Font.ROMAN_BASELINE, 12);
        nameField.setFont(fo);
        add(nameField);
        revalidate();
        repaint();
    }

    public void initLookAndFeel() {
        try {
            for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (UnsupportedLookAndFeelException e) {
            // handle exception
        } catch (ClassNotFoundException e) {
            // handle exception
        } catch (InstantiationException e) {
            // handle exception
        } catch (IllegalAccessException e) {
            // handle exception
        }
    }

    private void initListeners() {
        HeaderComboBoxListeners header = new HeaderComboBoxListeners(gameC, this, optionsC);
        optionsC.addActionListener(header);

        helpC.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (helpC.getSelectedIndex() == 0) {
                    helpFrame = new JFrame();
                    JOptionPane.showMessageDialog(helpFrame,instructions);

                } else if (helpC.getSelectedIndex() == 1) {
                    JOptionPane.showMessageDialog(helpFrame,  "Author1: Kalkanis Kiriakos" + "\n" + "Author2: " + "Kritikakis Emanouil");
                }
            }

        });
        LevelActionListener levelHlisten = new LevelActionListener(cb, this, nameField, this);
        cb.addActionListener(levelHlisten);
    }
}
