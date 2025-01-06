package ru.job4j.todo.persistence.user;

import ru.job4j.todo.model.User;

import java.util.Optional;

public interface TaskStorage {
    Optional<User> save(User user);
    Optional<User> findByLoginAndPassword(String login, String password);
}
