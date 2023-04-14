package com.example.demo;


import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.KeyNotifier;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.spring.annotation.SpringComponent;
import com.vaadin.flow.spring.annotation.UIScope;
import org.springframework.beans.factory.annotation.Autowired;

@SpringComponent
@UIScope
public class TodoItemEditor extends VerticalLayout implements KeyNotifier {

    private final TodoItemRepository repository;
    private TodoItem todoItem;

    TextField text = new TextField("Description");

    Button save = new Button("Save", VaadinIcon.CHECK.create());
    Button cancel = new Button("Cancel");
    Button delete = new Button("Delete", VaadinIcon.TRASH.create());
    HorizontalLayout actions = new HorizontalLayout(save, cancel, delete);

    Binder<TodoItem> binder = new Binder<>(TodoItem.class);
    private ChangeHandler changeHandler;

    @Autowired
    public TodoItemEditor(TodoItemRepository repository) {
        this.repository = repository;

        add(text, actions);

        // bind using naming convention
        binder.bindInstanceFields(this);

        // Configure and style components
        setSpacing(true);

        save.getElement().getThemeList().add("primary");
        delete.getElement().getThemeList().add("error");

        addKeyPressListener(Key.ENTER, e -> save());

        // wire action buttons to save, delete and reset
        save.addClickListener(e -> save());
        delete.addClickListener(e -> delete());
        cancel.addClickListener(e -> editItem(todoItem));
        setVisible(false);
    }

    void delete() {
        repository.delete(todoItem);
        changeHandler.onChange();
    }

    void save() {
        repository.save(todoItem);
        changeHandler.onChange();
    }

    public interface ChangeHandler {
        void onChange();
    }

    public final void editItem(TodoItem t) {
        if (t == null) {
            setVisible(false);
            return;
        }
        final boolean persisted = t.getId() != null;
        if (persisted) {
            // Find fresh entity for editing
            todoItem = repository.findById(t.getId()).get();
        }
        else {
            todoItem = t;
        }
        cancel.setVisible(persisted);


        binder.setBean(todoItem);

        setVisible(true);

        text.focus();
    }

    public void setChangeHandler(ChangeHandler h) {

        changeHandler = h;
    }

}