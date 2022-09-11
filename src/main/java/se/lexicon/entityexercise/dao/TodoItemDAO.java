package se.lexicon.entityexercise.dao;

import se.lexicon.entityexercise.entity.TodoItem;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface TodoItemDAO {
    Optional<TodoItem> findById(String id);
    TodoItem save(TodoItem todoItem);
    TodoItem update(TodoItem todoItem);
    TodoItem delete(TodoItem todoItem);
    List<TodoItem> findByTitle(String title);
    List<TodoItem> findAll();
    List<TodoItem> findByDone();
    List<TodoItem> findByDeadLineBetween(LocalDateTime start, LocalDateTime end);
    List<TodoItem> findByDeadLineBefore(LocalDateTime end);
    List<TodoItem> findByDeadLineAfter(LocalDateTime start);


}
