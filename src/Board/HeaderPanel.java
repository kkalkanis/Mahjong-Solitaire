/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Board;

import Main.MainFrame;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
/**
 * @author Kalkanis Kiriakos, Emmanouil Kritikakis
 */
public class HeaderPanel extends JPanel {

    private JComboBox gameC, optionsC, helpC;
    private String level;
    private BoardFrame easyFrame;
    private MainFrame mFrame;
    private int flag = 0;
    private History history;
    
    private JFrame helpFrame;
    public HeaderPanel(BoardFrame easyFrame, MainFrame mFrame, String level) {
        this.mFrame = mFrame;
        this.easyFrame = easyFrame;
        this.level = level;
        initHeader();
    }

    private void initHeader() {
        setLayout(new GridLayout(1, 3, 1, 1));
        //init of Game Combo Box
        String game[] = {"GAME", "NEW GAME", "RESTART", "EXIT"};
        gameC = new JComboBox(game);
        gameC.setBounds(0, 0, 150, 20);
        gameC.setSelectedIndex(0);
        add(gameC);
        //init of Options ComboBox
        String options[] = {"OPTIONS", "SHOW HISORY", "HIDE HISTORY"};
        optionsC = new JComboBox(options);
        optionsC.setBounds(0, 0, 150, 20);
        optionsC.setSelectedIndex(0);
        add(optionsC);

        //init of HelpComboBox
        String help[] = {"HELP", "ABOUT"};
        helpC = new JComboBox(help);
        helpC.setBounds(0, 0, 150, 20);
        helpC.setSelectedIndex(0);
        add(helpC);
        // init some settings of headerPanel 
        setBounds(0, 0, 300, 30);
        setOpaque(false);

        gameC.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                if (gameC.getSelectedItem() == "NEW GAME") {
                    easyFrame.dispose();
                    mFrame.setVisible(true);
                }
                if (gameC.getSelectedItem() == "RESTART") {
                    easyFrame.dispose();
                    try {
                        BoardFrame easyFrame1 = new BoardFrame(mFrame, level);

                    } catch (IOException ex) {
                        Logger.getLogger(HeaderPanel.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } else if (gameC.getSelectedItem() == "EXIT") {
                    mFrame.dispatchEvent(new WindowEvent(mFrame, WindowEvent.WINDOW_CLOSING));
                }
            }

        });
        optionsC.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (optionsC.getSelectedIndex() == 1) {
                    if (flag == 0) {
                        history = new History();
                        flag = 1;
                    }
                } else if (optionsC.getSelectedIndex() == 2) {
                    if (flag == 1) {
                        history.dispose();
                        flag = 0;
                    }
                }
            }

        });
        helpC.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                if (helpC.getSelectedIndex() == 0) {
                     helpFrame=new JFrame();

            JOptionPane.showMessageDialog(helpFrame,"Ο πίνακας Shanghai MahJong αποτελείται από 144 πλακάκια, κάθε πλακίδιο έχει διαφορετικές εικόνες, συνήθως αριθμούς, "+"\n"+ "εποχές και λουλούδια. Τα πλακίδια είναι τοποθετημένα σε ένα πλέγμα, το οποίο είναι συνήθως υψηλότερο κοντά στο κέντρο "+"\n"+"(εξαρτάται από την επιλεγμένη διάταξη). Η διάταξη της πλακέτας δημιουργείται τυχαία από τον υπολογιστή. Η διάταξη "+"\n"+"μπορεί να διαφέρει σε μοτίβο, μέγεθος και ύψος. Υπάρχουν εκατοντάδες διαφορετικές διατάξεις. Ορισμένες από τις "+"\n"+"υλοποιήσεις έχουν επίσης χρονικό όριο 5-15 λεπτών για την ολοκλήρωση του πίνακα."+"\n"+ "●	Ο στόχος είναι να διαγράψετε τον πίνακα αφαιρώντας όλα τα αντίστοιχα ζεύγη που ταιριάζουν από τη διάταξη. Ένα "+"\n"+"έγκυρο ζεύγος αποτελείται από δύο πλακίδια που είναι και τα δύο «ελεύθερα» και «ίδια» (ή του ίδιου τύπου). "+"\n"+"●	Σε περισσότερες λεπτομέρειες, μπορείτε να αφαιρέσετε ένα ζευγάρι πλακιδίων εάν ισχύει η ακόλουθη συνθήκη: "+"\n"+"●	Τα πλακίδια είναι πανομοιότυπα (π.χ. 4 και 4, Δυτικά και Δυτικά κ.λπ.)"+"\n"+"●	Όλες οι εποχές και τα λουλούδια μπορούν να ταιριάζουν μεταξύ τους, δεν πρέπει να είναι πανομοιότυπες. "+"\n"+"●	Κάθε πλακίδιο του ζεύγους πρέπει να συμμορφώνεται με τους ακόλουθους κανόνες: "+"\n"+"●	Κανένα άλλο πλακίδιο δεν βρίσκεται πάνω ή το καλύπτει μερικώς "+"\n"+"●	Κανένα άλλο πλακίδιο δεν βρίσκεται αριστερά ή δεξιά του "+"\n"+"●	Το κουμπί ανασχηματισμού μπορεί να χρησιμοποιηθεί όταν δεν υπάρχουν κινήσεις. Επιτρέπεται το πολύ 5 "+"\n"+"ανακατατάξεις ανά παιχνίδι."+"\n"+ "●	Μπορεί να βγει ένα ζευγάρι κάθε γύρο "+"\n"+"●	Κάθε πλακίδιο εμφανίζεται τέσσερις φορές σε ένα τυπικό παιχνίδι πασιέντζας mahjong. Σε ορισμένες εφαρμογές, τα "+ "\n"+"πλακίδια Flowers and Seasons θα εμφανίζονται μόνο μία φορά κατά τη διάρκεια ενός παιχνιδιού. "+"\n"+ "●	Επειδή κάθε πλακίδιο εμφανίζεται τέσσερις φορές, μπορεί να ταιριάζει με τα λάθος ζευγάρια, με αποτέλεσμα αργότερα "+"\n"+"να βρεθείτε χωρίς άλλα αντίστοιχα ελεύθερα πλακίδια. Ορισμένα παιχνίδια σάς επιτρέπουν να αναιρέσετε τις κινήσεις "+"\n"+"σας, οπότε μπορείτε να δοκιμάσετε και να μαντέψετε ξανά ποιο ζεύγος είναι το σωστό, αλλά κάποια εφαρμογή θα "+"\n"+"τερματίσει το παιχνίδι, επειδή γίνεται απλώς άλυτο."
        ); 
        }
                else if(helpC.getSelectedIndex()==1){
                    helpFrame=new JFrame();
                    JOptionPane.showMessageDialog(helpFrame,"Advanced Software Engineering"+"\n"+"Term Project"+"\n"+"Author1: Kritikakis Emanouel"+"\n"+"Author2: "+"Kalkanis Kiriakos");
            
                }
            }
            
        });
        
        
    }

}
