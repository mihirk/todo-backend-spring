package mapper;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import dbinterface.DatabaseConnection;
import domain.TodoItem;

import java.io.IOException;

public class TodoItemMapper {

    private DatabaseConnection databaseConnection;

    public TodoItemMapper() throws IOException {
        databaseConnection = DatabaseConnection.factory();
    }

    public DBObject get(String id) {
        return databaseConnection.get(id);
    }

    public DBObject delete(String id) {
        return databaseConnection.delete(id);
    }

    public DBObject patch(String id, BasicDBObject basicDBObject) {
        return databaseConnection.update(id, basicDBObject);
    }
}
