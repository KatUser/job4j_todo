package ru.job4j.todo.task;

import ru.job4j.todo.model.Task;

import java.util.Collection;
import java.util.Optional;

public interface TaskService {
    Task save(Task task);

    Optional<Task> findById(int id);

    boolean update(int id, Task task);

    boolean deleteById(int id);

    boolean setTaskAsDone(int id);

    Collection<Task> findAllTasks();
}
