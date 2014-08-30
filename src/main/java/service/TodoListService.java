package service;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import domain.TodoItem;
import mapper.TodoItemMapper;
import mapper.TodoListMapper;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
public class TodoListService {
    private TodoListMapper todoListMapper;
    private TodoItemMapper todoItemMapper;

    public TodoListService() throws IOException {
        todoListMapper = new TodoListMapper();
        todoItemMapper = new TodoItemMapper();
    }

    public TodoItem save(TodoItem todoItem) throws Exception {
        BasicDBObject todoitemObject = todoItem.dbObject();
        String savedId = todoListMapper.save(todoitemObject);
        todoItem.setUrl(todoItem.getUrl().replace("{id}", savedId));
        todoItemMapper.patch(savedId, todoItem.dbObject());
        return todoItem;
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
