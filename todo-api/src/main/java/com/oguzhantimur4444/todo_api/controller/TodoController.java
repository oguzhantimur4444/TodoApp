package com.oguzhantimur4444.todo_api.controller;

import com.oguzhantimur4444.todo_api.model.Todo;
import com.oguzhantimur4444.todo_api.model.request.TodoRequest;
import com.oguzhantimur4444.todo_api.service.TodoService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/todos")
public class TodoController {

    private final TodoService todoService;

    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }


    @GetMapping(path = "/get-all")
    List<Todo> getUserTodos(){
        return todoService.getTodos();
    }

    @PostMapping(path = "/add")
    Todo addTodo(@RequestBody TodoRequest request){
        return todoService.addTodoByToken(request);
    }

}
