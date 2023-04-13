package com.example.demo;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class TodoItem {
    @Id
    @GeneratedValue
    private Long id;

    private String text;

    protected TodoItem(){}

    public TodoItem(String text){
        this.text = text;
    }

    public Long getId() {
        return id;
    }


    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
    @Override
    public String toString(){
        return String.format("TodoItem[id=%d, text='%s']", id, text);
    }
}
