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
    @Operation(summary = "Shows greeting as mentioned in the introduction to REST")

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Greeting sent back", content = {
                    @Content(mediaType = "application/json") }),
            @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content),
            @ApiResponse(responseCode = "404", description = "/greeting not found", content = @Content) })
    public Greeting greeting(@RequestParam(value = "name", defaultValue = "World") String name){
        return new Greeting(counter.incrementAndGet(), String.format(template,name));
    }

    @GetMapping(value = "/todos")
    @Operation(summary = "Lists all TodoItems that are available")

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the list of items", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = TodoItem.class)) }),
            @ApiResponse(responseCode = "404", description = "TodoItems not found", content = @Content) })
    List<TodoItem> all(){
        return repository.findAll();
    }

    @PostMapping("/todos")
    @Operation(summary = "Creates a new TodoItem that are available")

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "TodoItem was created" ),
            @ApiResponse(responseCode = "404", description = "TodoItem not created", content = @Content) })
    TodoItem newTodoItem(@RequestBody TodoItem newTodoItem){
        return repository.save(newTodoItem);
    }

    @GetMapping(produces = "application/json", path = "/todos/{id}")
    @Operation(summary = "Shows a Todo Item with path variable name")

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the item", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = TodoItem.class)) }),
            @ApiResponse(responseCode = "400", description = "Invalid itemId supplied", content = @Content),
            @ApiResponse(responseCode = "404", description = "Item not found", content = @Content) })
    Optional<TodoItem> one(@PathVariable Long id){
        return repository.findById(id);
    }

    @PutMapping("/todos/{id}")
    @Operation(summary = "Updates todoItem by its id. If id is not already given to todoItem will create new todoItem")

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successfully updated todoItem", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = TodoItem.class)) }),
            @ApiResponse(responseCode = "404", description = "Item could not be updated", content = @Content) })
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
    @Operation(summary = "Deletes todoItem by its id")

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "successfully deleted todoItem"),
            @ApiResponse(responseCode = "404", description = "Item could not be updated", content = @Content) })
    void deleteTodoItem(@PathVariable Long id){
        repository.deleteById(id);
    }


}
