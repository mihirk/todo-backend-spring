package service;

import com.mongodb.DBObject;
import domain.TodoItem;
import mapper.TodoListMapper;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
public class TodoListService {
    private TodoListMapper todoListMapper;

    public TodoListService() throws IOException {
        todoListMapper = new TodoListMapper();
    }

    public void save(TodoItem todoItem) throws Exception {
        todoListMapper.save(todoItem.dbObject());
    }

    public List<TodoItem> get() {
        List<DBObject> dbObjects = todoListMapper.get();
        List<TodoItem> todoList = new ArrayList<TodoItem>();
        for (DBObject dbObject : dbObjects) {
            todoList.add(new TodoItem(dbObject));
        }
        return todoList;
    }

    public void delete() {
        todoListMapper.delete();
    }
}
