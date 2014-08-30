package service;

import domain.TodoItem;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TodoListServiceTest {
    @Test
    public void testName() throws Exception {
        TodoListService todoListService = new TodoListService();
        TodoItem todoItem = new TodoItem();
        todoItem.setCompleted(true);
        todoItem.setOrder(1);
        todoItem.setTitle("Some Title");
        todoItem.setUrl("Some URL");
        todoListService.save(todoItem);
    }

}
