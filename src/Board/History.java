/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Board;

import Main.ConnectWithDatabase;
import com.mongodb.client.MongoCursor;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.WindowConstants;
import org.bson.Document;
/**
 * @author Kalkanis Kiriakos, Emmanouil Kritikakis
 */
public class History extends JFrame {

    private static JTextArea area;
    private String jsonString;
    private  String[] couple;
    private  String[] id;
    private  String[] name;
    private  String[] difficulty;
    private  String[] time;
    private  String[] clearname;
    private  String[] cleardifficulty;
    private  String[] cleartime;
    private  String[] finalValues;
    private  ArrayList<String> names = new ArrayList<>();
    private  ArrayList<String> difficulties = new ArrayList<>();
    private  ArrayList<String> times = new ArrayList<>();
    private  MongoCursor<Document> cursor;

    public History() {
        initFrame();
        initTextArea();

    }    

    private void initFrame() {
        setTitle("History");
        setBounds(370, 170, 600, 400);
        setVisible(true);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
    }

    private void initTextArea() {

        try {
            area = new JTextArea();
            this.add(area);
            ConnectWithDatabase connect = new ConnectWithDatabase();
            cursor = connect.getCollection().find().iterator();
        }
        catch (Exception e){
            System.out.println("There is no history yet");
        }
        try {
            while (cursor.hasNext()) {
                jsonString = cursor.next().toJson();
                couple = jsonString.split(",");
                name = couple[1].split(":");
                difficulty = couple[2].split(":");
                time = couple[3].split(":");
                clearname = name[1].split("\"");
                cleardifficulty = difficulty[1].split("\"");
                cleartime = time[1].split("\"");
                names.add(clearname[1]);
                difficulties.add(cleardifficulty[1]);
                times.add(cleartime[1]);
            }
            cursor.close();
        }
        catch(Exception e) {
            System.out.println("Something went wrong with db con ");
        }
        area.append("NAME" + "\t" + "LEVEL" + "\t" + "TIME" + "\n\n");
        for (int i = 0; i < names.size(); i++) {
            area.append(names.get(i) + "\t" + difficulties.get(i) + "\t" + times.get(i) + "\t" + "\n");

        }
        
    }

}
