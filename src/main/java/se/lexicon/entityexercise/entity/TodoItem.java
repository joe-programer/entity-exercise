package se.lexicon.entityexercise.entity;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.id.UUIDGenerator;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "todoItems")
public class TodoItem {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    private String todoId;
    @Column(length = 100, nullable = false)
    private String title;
    @Column(length = 100)
    private String description;
    @Column(nullable = false)
    private LocalDateTime deadLine;
    private boolean done;

    public TodoItem() {
    }

    public TodoItem(String title, String description, LocalDateTime deadLine) {
        this.title = title;
        this.description = description;
        this.deadLine = deadLine;
    }

    public String getTodoId() {
        return todoId;
    }

    public void setTodoId(String todoId) {
        this.todoId = todoId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getDeadLine() {
        return deadLine;
    }

    public void setDeadLine(LocalDateTime deadLine) {
        this.deadLine = deadLine;
    }

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TodoItem todoItem = (TodoItem) o;
        return isDone() == todoItem.isDone() && Objects.equals(getTodoId(), todoItem.getTodoId()) && Objects.equals(getTitle(), todoItem.getTitle()) && Objects.equals(getDescription(), todoItem.getDescription()) && Objects.equals(getDeadLine(), todoItem.getDeadLine());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getTodoId(), getTitle(), getDescription(), getDeadLine(), isDone());
    }

    @Override
    public String toString() {
        return "TodoItem{" +
                "todoId='" + todoId + '\'' +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", deadLine=" + deadLine +
                ", done=" + done +
                '}';
    }
}
