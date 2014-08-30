package dbinterface;

import com.mongodb.*;
import domain.TodoItem;
import org.bson.types.ObjectId;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DatabaseConnection {
    private DatabaseConfig config;
    private Mongo client;
    private DB handle;
    private DBCollection collection;

    public DatabaseConnection() throws IOException {
        config = new DatabaseConfig();
    }

    public static DatabaseConnection factory() throws IOException {
        DatabaseConnection databaseConnection = new DatabaseConnection();
        MongoURI uri = new MongoURI(databaseConnection.config.dbUrl);
        databaseConnection.client = new Mongo(uri);
        databaseConnection.handle = databaseConnection.client.getDB(databaseConnection.config.dbName);
        databaseConnection.handle.authenticate(databaseConnection.config.dbUsername, databaseConnection.config.dbPassword);
        databaseConnection.client.setWriteConcern(WriteConcern.JOURNAL_SAFE);
        databaseConnection.collection = databaseConnection.handle.getCollection(databaseConnection.config.collectionName);
        return databaseConnection;
    }


    public void persist(BasicDBObject basicDBObject) {
        collection.insert(basicDBObject);
    }

    public List<DBObject> getAll() {
        DBCursor cursor = collection.find();
        List<DBObject> dbObjects = new ArrayList<>();
        try {
            while (cursor.hasNext()) {
                dbObjects.add(cursor.next());
            }
        } finally {
            cursor.close();
        }
        return dbObjects;
    }

    public DBObject get(String id) {
        ObjectId objectId = new ObjectId(id);
        return collection.findOne(new BasicDBObject("_id", objectId));
    }

    public void delete() {
        collection.remove(new BasicDBObject());
    }

    public DBObject delete(String id) {
        DBObject dbObject = get(id);
        collection.findAndRemove(dbObject);
        return dbObject;
    }

    public DBObject update(String id, BasicDBObject basicDBObject) {
        DBObject dbObject = get(id);
        collection.findAndModify(dbObject, basicDBObject);
        return get(id);
    }
}