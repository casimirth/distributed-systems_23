package com.example.demo;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TodoItemRepository extends JpaRepository<TodoItem, Long> {
        List<TodoItem> findByTextStartsWithIgnoreCase(String text);
}
