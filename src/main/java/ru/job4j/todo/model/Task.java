package ru.job4j.todo.model;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

@Entity
@Table(name = "tasks")
@Data
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String description;
    private LocalDateTime created
            = LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS);
    private boolean done;

    public Task(int id, String description, LocalDateTime created, boolean done) {
        this.id = id;
        this.description = description;
        this.created = created;
        this.done = done;
    }

    public Task() {
    }
}
