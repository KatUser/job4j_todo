package ru.job4j.todo.persistence.user;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.job4j.todo.model.User;
import ru.job4j.todo.persistence.CrudRepository;

import java.util.Map;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

@Repository
@AllArgsConstructor
public class HqlUserStorage implements UserStorage {

    private final CrudRepository crudRepository;
    static Logger logger = Logger.getLogger(HqlUserStorage.class.getName());

    @Override
    public Optional<User> create(User user) {
        try {
            crudRepository.run(session -> session.persist(user));
            return Optional.of(user);
        } catch (Exception e) {
            logger.log(Level.WARNING, "что-то пошло не так", e);
        }
        return Optional.empty();
    }

    @Override
    public Optional<User> findByLoginAndPassword(String login, String password) {
        return crudRepository.optional(
                """
                            FROM User
                            WHERE login = :fLogin
                            AND password = :fPassword
                            """,
                User.class, Map.of("fLogin", login, "fPassword", password)
        );
    }
}
