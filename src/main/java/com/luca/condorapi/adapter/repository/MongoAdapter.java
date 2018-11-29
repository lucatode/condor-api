package com.luca.condorapi.adapter.repository;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.result.DeleteResult;
import org.bson.Document;
import org.bson.types.ObjectId;

import java.util.ArrayList;
import java.util.List;

import static com.mongodb.client.model.Filters.eq;

public class MongoAdapter<BSON_ENTITY> {

    private final Adapter<BSON_ENTITY> adapter;
    private final String connectionString;
    private final String databaseName;
    private final String collectionName;

    private MongoClientURI uri;
    private MongoClient mongoClient;
    private MongoDatabase database;

    public MongoAdapter(Adapter<BSON_ENTITY> adapter, String connectionString, String databaseName, String collectionName){
        this.adapter = adapter;
        this.connectionString = connectionString;
        this.databaseName = databaseName;
        this.collectionName = collectionName;
    }

    public List<BSON_ENTITY> getAll(){
        openConnection();
        MongoCollection<Document> collection = database.getCollection(collectionName);
        MongoCursor<Document> cursor = collection.find().iterator();

        List<BSON_ENTITY> list = new ArrayList<>();

        try {
            while (cursor.hasNext()) {
                Document doc = cursor.next();
                list.add(adapter.adapt(doc));
            }
        } finally {
            cursor.close();
        }
        closeConnection();

        return list;
    }

    public BSON_ENTITY getById(String id) {
        openConnection();
        MongoCollection<Document> collection = database.getCollection(collectionName);
        BSON_ENTITY entity;
        try {
            Document doc = collection.find(eq("id", id)).first();
            entity = adapter.adapt(doc);
        }catch (Exception e){
            throw new RuntimeException();
        }finally {
            closeConnection();
        }
        return entity;
    }

    public void insertDocument(BSON_ENTITY entity){
        openConnection();
        MongoCollection<Document> collection = database.getCollection(collectionName);
        collection.insertOne(adapter.reverseAdapt(entity));
        closeConnection();
    }

    public void updateDocument(String id, BSON_ENTITY newEntity){
        openConnection();
        MongoCollection<Document> collection = database.getCollection(collectionName);
        collection.updateOne(eq("id",id), new Document("$set", adapter.reverseAdapt(newEntity)) );
        closeConnection();
    }

    public long deleteDocument(String id){
        openConnection();
        MongoCollection<Document> collection = database.getCollection(collectionName);
        DeleteResult id1 = collection.deleteOne(eq("_id", new ObjectId(id)));

        return id1.getDeletedCount();
    }

    private void openConnection(){
        uri = new MongoClientURI(connectionString);
        mongoClient = new MongoClient(uri);
        database = mongoClient.getDatabase(databaseName);
    }

    private void closeConnection(){
        mongoClient.close();
    }

    public interface Adapter<BSON_ENTITY> {

        BSON_ENTITY adapt(Document doc);

        Document reverseAdapt(BSON_ENTITY entity);
    }

}
