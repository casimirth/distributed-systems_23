package com.example.demo;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

@RestController
@RequestMapping
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

    @GetMapping(produces = "application/json", path = "/todos/{id}")
    @Operation(summary = "Creates a Todo Item with path variable name and default priority of 2")

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the item", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = TodoItem.class)) }),
            @ApiResponse(responseCode = "400", description = "Invalid itemId supplied", content = @Content),
            @ApiResponse(responseCode = "404", description = "Item not found", content = @Content) })
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
