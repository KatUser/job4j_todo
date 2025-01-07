package ru.job4j.todo.persistence.user;

import lombok.AllArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import ru.job4j.todo.model.User;

import java.util.Optional;

@Repository
@AllArgsConstructor
public class HqlUserTaskStorage implements UserStorage {
    private final SessionFactory sessionFactory;

    @Override
    public Optional<User> create(User user) {
        Session session = sessionFactory.openSession();
        try {
            session.beginTransaction();
            session.save(user);
            session.getTransaction().commit();
            return Optional.of(user);
        } catch (Exception e) {
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
        return Optional.empty();
    }

    @Override
    public Optional<User> findByLoginAndPassword(String login, String password) {
        Session session = sessionFactory.openSession();
        Optional<User> foundUser = Optional.empty();
        try {
            session.beginTransaction();
            foundUser = session.createQuery("""
                            from User AS u
                            WHERE u.login = :fLogin
                            AND u.password = :fPassword
                            """, User.class
                    ).setParameter("fLogin", login)
                    .setParameter("fPassword", password)
                    .uniqueResultOptional();
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
        return foundUser;
    }
}
