package ru.job4j.todo.service.user;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.job4j.todo.model.User;
import ru.job4j.todo.persistence.user.HqlUserUserStorage;

import java.util.Optional;

@Service
@AllArgsConstructor
public class SimpleUserService implements UserService {

    private final HqlUserUserStorage storage;

    @Override
    public Optional<User> create(User user) {
        return storage.create(user);
    }

    @Override
    public Optional<User> findByLoginAndPassword(String login, String password) {
        return storage.findByLoginAndPassword(login, password);
    }
}
