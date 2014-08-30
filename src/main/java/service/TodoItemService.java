package service;

import com.mongodb.DBObject;
import domain.TodoItem;
import mapper.TodoItemMapper;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class TodoItemService {

    private TodoItemMapper todoItemMapper;

    public TodoItemService() throws IOException {
        todoItemMapper = new TodoItemMapper();
    }

    public TodoItem get(String id) {
        DBObject dbObject = todoItemMapper.get(id);
        return getTodoItem(dbObject);
    }

    public TodoItem delete(String id) {
        DBObject dbObject = todoItemMapper.delete(id);
        return getTodoItem(dbObject);
    }

    private TodoItem getTodoItem(DBObject dbObject) {
        return dbObject == null ? null : new TodoItem(dbObject);
    }

    public TodoItem patch(String id, TodoItem todoItem) {
        TodoItem current = get(id);
        todoItem = merge(current, todoItem);
        DBObject patch = todoItemMapper.patch(id, todoItem.dbObject());
        return getTodoItem(patch);
    }

    private TodoItem merge(TodoItem current, TodoItem todoItem) {
        todoItem.setUrl((String) getLatest(current.getUrl(), todoItem.getUrl()));
        todoItem.setTitle((String) getLatest(current.getTitle(), todoItem.getTitle()));
        todoItem.setCompleted((Boolean) getLatest(current.getCompleted(), todoItem.getCompleted()));
        todoItem.setOrder((Integer) getLatest(current.getOrder(), todoItem.getOrder()));
        return todoItem;
    }

    private Object getLatest(Object old, Object latest) {
        return latest == null ? old : latest;
    }
}
