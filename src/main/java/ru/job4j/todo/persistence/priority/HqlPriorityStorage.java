package ru.job4j.todo.persistence.priority;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.job4j.todo.model.Priority;
import ru.job4j.todo.persistence.CrudRepository;

import java.util.Collection;

@Repository
@AllArgsConstructor
public class HqlPriorityStorage implements PriorityStorage {

    private final CrudRepository crudRepository;

    @Override
    public Collection<Priority> findAllPriorities() {
        return crudRepository.query(
                "FROM Priority",
                Priority.class
        );
    }

}
