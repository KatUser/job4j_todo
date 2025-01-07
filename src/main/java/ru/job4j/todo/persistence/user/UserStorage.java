package ru.job4j.todo.persistence.user;

import ru.job4j.todo.model.User;

import java.util.Optional;

public interface UserStorage {

    Optional<User> create(User user);

    Optional<User> findByLoginAndPassword(String login, String password);
}
