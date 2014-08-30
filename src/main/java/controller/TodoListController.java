package controller;

import domain.TodoItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import service.TodoListService;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/todos")
public class TodoListController {
    @Autowired
    private TodoListService todoListService;

    @RequestMapping(method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public List<TodoItem> get() {
        return todoListService.get();
    }

    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public TodoItem post(HttpServletRequest request,
                         @RequestParam(value = "title", required = false) String title,
                         @RequestParam(value = "completed", required = false) Boolean completed,
                         @RequestParam(value = "url", required = false) String url,
                         @RequestParam(value = "order", required = false) Integer order) throws Exception {
        String absoluteUrl = request.getRequestURL().toString();
        url = absoluteUrl.replace("/todos", "/todo/{id}");
        TodoItem todoItem = new TodoItem(title, completed, url, order);
        return todoListService.save(todoItem);
    }

    @RequestMapping(method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public List<TodoItem> delete() throws Exception {
        todoListService.delete();
        return new ArrayList<>();
    }

}
