package se.lexicon.entityexercise.dao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import se.lexicon.entityexercise.entity.TodoItem;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public class TodoItemDAOImpl implements TodoItemDAO{

    @PersistenceContext
    EntityManager entityManager;


    @Override
    @Transactional(readOnly = true)
    public Optional<TodoItem> findById(String id) {
        if(id==null) throw new IllegalArgumentException("Invalid Id");
        return Optional.ofNullable(entityManager.find(TodoItem.class, id));
    }

    @Override
    @Transactional
    public TodoItem save(TodoItem todoItem) {
        if(todoItem==null) throw new IllegalArgumentException("todoItem is null");
        entityManager.persist(todoItem);
        return todoItem;
    }

    @Transactional(noRollbackFor = IllegalArgumentException.class)
    @Override
    public TodoItem update(TodoItem todoItem) {
        if(todoItem==null) throw new IllegalArgumentException("todoItem is null");
        entityManager.merge(todoItem);
        return todoItem;
    }

    @Override
    @Transactional(noRollbackFor = IllegalArgumentException.class)
    public TodoItem delete(TodoItem todoItem) {
        if(todoItem==null) throw new IllegalArgumentException("todoItem is null");
        if(findById(todoItem.getTodoId()).isEmpty()) throw new IllegalArgumentException("todoItem not found");
        entityManager.remove(todoItem);
        return todoItem;
    }

    @Override
    @Transactional(readOnly = true)
    public List<TodoItem> findByTitle(String title) {
        Query selectQuery = entityManager.createQuery("select s FROM TodoItem s where s.title=?1");
        selectQuery.setParameter(1, title);
        return selectQuery.getResultList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<TodoItem> findAll() {
        Query query = entityManager.createQuery("select s from TodoItem s");
        return query.getResultList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<TodoItem> findByDone() {
        Query selectQuery = entityManager.createQuery("select t from TodoItem t where t.done=?1");
        selectQuery.setParameter(1, true);
        return selectQuery.getResultList();
    }

    @Override
    @Transactional
    public List<TodoItem> findByDeadLineBetween(LocalDateTime start, LocalDateTime end) {
        Query query = entityManager.createQuery("select t from TodoItem t where t.deadLine between ?1 and ?2");
        query.setParameter(1, start);
        query.setParameter(2,end);
        entityManager.flush();
        return query.getResultList();
    }

    @Override
    @Transactional
    public List<TodoItem> findByDeadLineBefore(LocalDateTime end) {
        Query query = entityManager.createQuery("select t from TodoItem t where t.deadLine<?1");
        query.setParameter(1,end);
        return query.getResultList();
    }

    @Override
    @Transactional
    public List<TodoItem> findByDeadLineAfter(LocalDateTime start) {
        Query query = entityManager.createQuery("select t from TodoItem t where t.deadLine>:st");
        query.setParameter("st",start);
        return query.getResultList();
    }
}
