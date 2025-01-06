package ru.job4j.todo.service.user;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.job4j.todo.model.User;
import ru.job4j.todo.persistence.user.HqlUserTaskStorage;

import java.util.Optional;

@Service
@AllArgsConstructor
public class SimpleUserService implements UserService {

    private final HqlUserTaskStorage storage;

    @Override
    public Optional<User> save(User user) {
        return storage.save(user);
    }

    @Override
    public Optional<User> findByLoginAndPassword(String login, String password) {
        return storage.findByLoginAndPassword(login, password);
    }
}
