/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.ConnectionString;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoCollection;
import javax.swing.JTextField;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;
import org.bson.types.ObjectId;

/**
 * @author Kalkanis Kiriakos, Emmanouil Kritikakis
 */
public class ConnectWithDatabase {

    private String time;
    private String username;
    private String level;
    private MongoCollection collection;

    public ConnectWithDatabase(String time, String username, String level) {
        this.time = time;
        this.username = username;
        this.level = level;
        connection();
        insertToDB();
    }

    public MongoCollection getCollection() {
        return collection;
    }

    public ConnectWithDatabase() {
        connection();
    }

    private void connection() {
        String uri = "mongodb+srv://REPLACE_WITH_USERNAME:REPLACE_WITH_PASSWORD@cluster-xbyif.mongodb.net/test?retryWrites=true&w=majority";
        MongoClientURI clientURI = new MongoClientURI(uri);
        MongoClient mongoClient = new MongoClient(clientURI);
        MongoDatabase mongoDatabase = mongoClient.getDatabase("Mahjong_Solitaire");
        collection = mongoDatabase.getCollection("Player");

        System.out.println("Database connected");
        //access to collection

    }

    private void insertToDB() {
        //create new document
        Document document = new Document("username", username);
        document.append("level", level);
        document.append("time", time + " sec");

        // insert the document
        collection.insertOne(document);
    }
}
