package ru.job4j.todo.persistence.task;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.job4j.todo.model.Task;
import ru.job4j.todo.persistence.CrudRepository;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class HqlTaskStorage implements TaskStorage {

    private final CrudRepository crudRepository;

    @Override
    public Optional<Task> create(Task task) {
        crudRepository.run(session -> session.persist(task));
        return Optional.of(task);
    }


    @Override
    public Optional<Task> findById(int id) {
        return crudRepository.optional(
                        "FROM Task t LEFT JOIN FETCH t.priority WHERE t.id = :fId",
                        Task.class,
                        Map.of("fId", id)
                );
    }

    @Override
    public boolean update(int taskId, Task task) {
        return crudRepository.aBoolean(
                        """
                                UPDATE Task
                                SET title = :fTitle,
                                description = :fDescription,
                                priority = :fPriority
                                WHERE id = :fId
                                """,
                        Map.of("fId", taskId,
                                "fTitle", task.getTitle(),
                                "fDescription", task.getDescription(),
                                "fPriority", task.getPriority()
                        )
                );
    }

    @Override
    public boolean deleteById(int taskId) {
        return crudRepository.aBoolean(
                        "DELETE FROM Task WHERE id = :fId",
                        Map.of("fId", taskId)
                );
    }


    @Override
    public boolean setTaskAsDone(int id) {
        return crudRepository.aBoolean(
                        "UPDATE Task SET done = true WHERE id = :fId",
                        Map.of("fId", id)
                );
    }

    @Override
    public List<Task> findAllTasks() {
        return crudRepository.query(
                        "FROM Task t JOIN FETCH t.priority",
                        Task.class
                );
    }

    @Override
    public List<Task> findCompletedTasks() {
        return crudRepository.query(
                        "FROM Task t LEFT JOIN FETCH t.priority WHERE t.done = true",
                        Task.class
                );
    }

    @Override
    public List<Task> findNewTasks() {
        return crudRepository.query(
                        "FROM Task t LEFT JOIN FETCH t.priority WHERE t.done = false",
                        Task.class
                );
    }
}