package se.lexicon.entityexercise.dao;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureTestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.transaction.annotation.Transactional;
import se.lexicon.entityexercise.entity.TodoItem;
import javax.persistence.EntityManager;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@AutoConfigureTestEntityManager
@DirtiesContext
@Transactional
class TodoItemDAOImplTest {

    @Autowired
    TodoItemDAO todoItemDAO;
    @Autowired
    EntityManager entityManager;
    TodoItem todoItem;
    @BeforeEach
    void init(){
        todoItem = new TodoItem("exercise 1", "Java coding",
                LocalDateTime.of(2022,9,15,9,0,0));
        entityManager.persist(todoItem);
    }

    @Test
    void findById() {
        Optional<TodoItem> found = todoItemDAO.findById(todoItem.getTodoId());
        assertTrue(found.isPresent());
        assertNotEquals(found.get().getTodoId(), null);
    }

    @Test
    void save() {
        entityManager.remove(todoItem);
        TodoItem saved = todoItemDAO.save(todoItem);
        assertNotEquals(saved.getTodoId(),null);
    }

    @Test
    void update() {
        todoItem.setTitle("exercise 2");
        TodoItem updated = todoItemDAO.update(todoItem);
        assertEquals("exercise 2",updated.getTitle());
    }

    @Test
    void delete() {
        todoItemDAO.delete(todoItem);
        Optional<TodoItem> found = todoItemDAO.findById(todoItem.getTodoId());
        assertFalse(found.isPresent());

    }

    @Test
    void findByTitle() {
        List<TodoItem> found = todoItemDAO.findByTitle("exercise 1");
        List<TodoItem> test = new ArrayList<>();
        test.add(todoItem);
        assertEquals(found, test);
    }

    @Test
    void findAll() {
        List<TodoItem> found = todoItemDAO.findAll();
        List<TodoItem> test = new ArrayList<>();
        test.add(todoItem);
        assertEquals(found, test);
    }

    @Test
    void findByDone() {
        List<TodoItem> found = todoItemDAO.findByDone();
        assertTrue(found.isEmpty());
    }
    @Test
    void findByDeadLineBetween(){
        TodoItem todoItem1= new TodoItem("ex 2", "Challenge", LocalDateTime.of(2022,10,12, 9,0,0));
        entityManager.persist(todoItem1);
        List<TodoItem> found = todoItemDAO.findByDeadLineBetween(LocalDateTime.of(2022,9,5, 9,0,0),
                LocalDateTime.of(2022,10,30,9,0,0));
        assertEquals(found, todoItemDAO.findAll());
    }
    @Test
    void findByDeadLineBefore(){
        TodoItem todoItem1= new TodoItem("ex 2", "Challenge", LocalDateTime.parse("2022-10-12T09:00"));
        entityManager.persist(todoItem1);
        List<TodoItem> found = todoItemDAO.findByDeadLineBefore(LocalDateTime.of(2022,10,3,9,0));
        List<TodoItem> test = new ArrayList<>();
        test.add(todoItem);
        assertEquals(test, found);
    }
    @Test
    void findByDeadLineAfter(){
        TodoItem todoItem1= new TodoItem("ex 2", "Challenge", LocalDateTime.parse("2022-10-12T09:00"));
        entityManager.persist(todoItem1);
        List<TodoItem> found = todoItemDAO.findByDeadLineAfter(LocalDateTime.of(2022,10,3,9,0));
        List<TodoItem> test = new ArrayList<>();
        test.add(todoItem1);
        assertEquals(test, found);

    }
}