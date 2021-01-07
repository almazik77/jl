package ru.itis.mongo.hateoas.driver;

import com.mongodb.client.*;
import org.bson.Document;

import java.util.regex.Pattern;

import static com.mongodb.client.model.Projections.*;

public class Main {
    public static void main(String[] args) {
        MongoClient client = MongoClients.create();
        MongoDatabase database = client.getDatabase("new_project");
        MongoCollection<Document> collection = database.getCollection("doctors");
        Document doctor = new Document();
        doctor.put("first_name", "doctorCreated");
      //  collection.insertOne(doctor);

        //collection.deleteOne(doctor);


        Pattern regex = Pattern.compile("doctor", Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CASE);

        Document searchQuery = new Document();
        searchQuery.append("first_name", regex);

        FindIterable<Document> resultDocuments = collection.find(searchQuery)
                .projection(fields(include("first_name", "last_name", "office", "specialty"), excludeId()));

        for (Document document : resultDocuments) {
            System.out.println(document.toJson());
        }
    }
}
