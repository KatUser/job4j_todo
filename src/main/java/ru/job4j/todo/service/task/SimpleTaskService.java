package ru.job4j.todo.service.task;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.job4j.todo.model.Task;
import ru.job4j.todo.persistence.task.HqlTaskStorage;

import java.util.Collection;
import java.util.Optional;

@Service
@AllArgsConstructor
public class SimpleTaskService implements TaskService {

    private final HqlTaskStorage storage;

    @Override
    public Optional<Task> create(Task task) {
        return storage.createOrEdit(task);
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
