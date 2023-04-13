package com.example.demo;

import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

public class MainView extends VerticalLayout{
    private final TodoItemRepository repo;
    final Grid<TodoItem> grid;
    public MainView(TodoItemRepository repo){
      this.repo = repo;
      this.grid = new Grid<>(TodoItem.class);
      add(grid);
      listTodoItems();
    }

    private void listTodoItems(){
        grid.setItems(repo.findAll());
    }
}
