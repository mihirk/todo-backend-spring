package controller;

import domain.TodoItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import service.TodoItemService;

@RestController
@RequestMapping(value = "/todo")
public class TodoItemController {

    @Autowired
    private TodoItemService todoItemService;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<TodoItem> get(@PathVariable("id") String id) throws Exception {
        TodoItem todoItem = todoItemService.get(id);
        if (todoItem == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(todoItem, HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<TodoItem> delete(@PathVariable("id") String id) {
        TodoItem todoItem = todoItemService.delete(id);
        if (todoItem == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PATCH)
    public ResponseEntity<TodoItem> patch(@RequestBody TodoItem todoItem,
                                          @PathVariable("id") String id) {
        todoItem = todoItemService.patch(id, todoItem);
        if (todoItem == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(todoItem, HttpStatus.OK);
    }


}
