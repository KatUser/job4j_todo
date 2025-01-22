package ru.job4j.todo.service.priority;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.job4j.todo.model.Priority;
import ru.job4j.todo.persistence.priority.PriorityStorage;

import java.util.Collection;

@Service
@AllArgsConstructor
public class SimplePriorityService implements PriorityService {

    private final PriorityStorage storage;

    @Override
    public Collection<Priority> findAllPriorities() {
        return storage.findAllPriorities();
    }
}
