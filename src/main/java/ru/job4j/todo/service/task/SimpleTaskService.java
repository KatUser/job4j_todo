package ru.job4j.todo.service.task;

import org.springframework.stereotype.Service;
import ru.job4j.todo.model.Task;
import ru.job4j.todo.persistence.task.TaskStorage;

import java.util.Collection;
import java.util.Optional;

@Service
public class SimpleTaskService implements TaskService {

    private final TaskStorage storage;

    public SimpleTaskService(TaskStorage storage) {
        this.storage = storage;
    }

    @Override
    public Optional<Task> save(Task task) {
        return storage.save(task);
    }

    @Override
    public Optional<Task> findById(int id) {
        return storage.findById(id);
    }

    @Override
    public boolean update(int id, Task task) {
        return storage.update(id, task);
    }

    @Override
    public boolean deleteById(int id) {
        return storage.deleteById(id);
    }

    @Override
    public boolean setTaskAsDone(int id) {
        return storage.setTaskAsDone(id);
    }

    @Override
    public Collection<Task> findAllTasks() {
        return storage.findAllTasks();
    }

    @Override
    public Collection<Task> findCompletedTasks() {
        return storage.findCompletedTasks();
    }

    @Override
    public Collection<Task> findNewTasks() {
        return storage.findNewTasks();
    }
}
