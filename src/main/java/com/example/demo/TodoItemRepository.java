package com.example.demo;

import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

//@RepositoryRestResource(collectionResourceRel = "rest", path="rest")
public interface TodoItemRepository extends JpaRepository<TodoItem, Long> {
        List<TodoItem> findByTextStartsWithIgnoreCase(String text);
}
