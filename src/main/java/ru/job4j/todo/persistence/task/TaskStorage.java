package ru.job4j.todo.persistence.task;

import org.springframework.stereotype.Repository;
import ru.job4j.todo.model.Task;

import java.util.Collection;
import java.util.Optional;

@Repository
public interface TaskStorage {

    Optional<Task> save(Task task);

    Optional<Task> findById(int id);

    boolean update(int id, Task task);

    boolean deleteById(int id);

    boolean setTaskAsDone(int id);

    Collection<Task> findAllTasks();

    Collection<Task> findCompletedTasks();

    Collection<Task> findNewTasks();
}
