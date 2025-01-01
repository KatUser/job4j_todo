package ru.job4j.todo.model;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

@Entity
@Table(name = "task")
@Data
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String title;
    private String description;
    private LocalDateTime created
            = LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS);
    private boolean done;

    public Task(int id,
                String title,
                String description,
                LocalDateTime created,
                boolean done) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.created = created;
        this.done = done;
    }

    public Task() {
    }
}
