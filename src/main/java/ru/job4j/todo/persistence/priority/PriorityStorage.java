package ru.job4j.todo.persistence.priority;

import ru.job4j.todo.model.Priority;

import java.util.Collection;

public interface PriorityStorage {
    Collection<Priority> findAllPriorities();
}
