package com.example.demo;

import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

@RestController
public class Controller {
    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();
    private final TodoItemRepository repository;

    Controller(TodoItemRepository repository){
        this.repository = repository;
    }


    @GetMapping(value = "/greeting")
    public Greeting greeting(@RequestParam(value = "name", defaultValue = "World") String name){
        return new Greeting(counter.incrementAndGet(), String.format(template,name));
    }

    @GetMapping(value = "/todos")
    List<TodoItem> all(){
        return repository.findAll();
    }

    @PostMapping("/todos")
    TodoItem newTodoItem(@RequestBody TodoItem newTodoItem){
        return repository.save(newTodoItem);
    }

    @GetMapping("/todos/{id}")
    Optional<TodoItem> one(@PathVariable Long id){
        return repository.findById(id);
    }

    @PutMapping("/todos/{id}")
    TodoItem replaceTodoItem(@RequestBody TodoItem newTodoItem, @PathVariable Long id){
        return repository.findById(id)
                .map(todoItem -> {
                    todoItem.setText(newTodoItem.getText());
                    return repository.save(todoItem);
                }).orElseGet(()->{
                    newTodoItem.setText("");
                    return repository.save(newTodoItem);
                });
    }

    @DeleteMapping("/todos/{id}")
    void deleteTodoItem(@PathVariable Long id){
        repository.deleteById(id);
    }


}
