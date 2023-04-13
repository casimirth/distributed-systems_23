package com.example.demo;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.Route;
import org.springframework.util.StringUtils;

@Route
public class MainView extends VerticalLayout{

    private final TodoItemRepository repo;
    private final TodoItemEditor editor;
    final TextField filter;
    private final Button addNewBtn;
    final Grid<TodoItem> grid;

    public MainView(TodoItemRepository repo, TodoItemEditor editor){
      this.repo = repo;
      this.editor = editor;
      this.grid = new Grid<>(TodoItem.class);
      this.filter = new TextField();
      this.addNewBtn = new Button("New TodoItem", VaadinIcon.PLUS.create());
      HorizontalLayout actions = new HorizontalLayout(filter, addNewBtn);
      add(actions, grid, editor);

      grid.setHeight("300px");
      grid.setColumns("id", "text");
      grid.getColumnByKey("id").setWidth("50px").setFlexGrow(0);

      filter.setPlaceholder("Filter by last name");

      filter.setValueChangeMode(ValueChangeMode.EAGER);
      filter.addValueChangeListener(e -> listTodoItems(e.getValue()));

        grid.asSingleSelect().addValueChangeListener(e -> {
            editor.editItem(e.getValue());
        });

        addNewBtn.addClickListener(e -> editor.editItem(new TodoItem("")));
        editor.setChangeHandler(()-> {
            editor.setVisible(false);
            listTodoItems(filter.getValue());
        });

      listTodoItems(null);
    }

    private void listTodoItems(){
        grid.setItems(repo.findAll());
    }

    void listTodoItems(String filterString){
        if(StringUtils.isEmpty(filterString)){
            grid.setItems(repo.findAll());
        }
        else {
            grid.setItems(repo.findByTextStartsWithIgnoreCase(filterString));
        }
    }


}
