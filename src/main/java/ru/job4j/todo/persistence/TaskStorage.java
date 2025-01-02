package ru.job4j.todo.persistence;

import lombok.AllArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import ru.job4j.todo.model.Task;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class TaskStorage implements Storage {
    private final SessionFactory sessionFactory;

    @Override
    public Task save(Task task) {
        Session session = sessionFactory.openSession();
        try {
            session.beginTransaction();
            session.save(task);
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
        return task;
    }

    @Override
    public Optional<Task> findById(int id) {
        Session session = sessionFactory.openSession();
        Optional<Task> foundTask = Optional.empty();
        try {
            session.beginTransaction();
            foundTask = session.createQuery(
                            "from Task AS t WHERE t.id = :fId", Task.class
                    ).setParameter("fId", id)
                    .uniqueResultOptional();
        } catch (Exception e) {
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
        return foundTask;
    }

    @Override
    public boolean update(int id, Task task) {
        Session session = sessionFactory.openSession();
        int affectedRows = 0;
        try {
            session.beginTransaction();
            affectedRows = session.createQuery("""
                            UPDATE Task
                            SET description = :fDescription
                            WHERE id = :fid
                            """)
                    .setParameter("fDescription", task.getDescription())
                    .setParameter("fid", id)
                    .executeUpdate();
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
        return affectedRows > 0;
    }

    @Override
    public boolean deleteById(int taskId) {
        Session session = sessionFactory.openSession();
        int affectedRows = 0;
        try {
            session.beginTransaction();
            affectedRows = session.createQuery("DELETE from Task WHERE id = :fId")
                    .setParameter("fId", taskId)
                    .executeUpdate();
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
        return affectedRows > 0;
    }


    @Override
    public boolean setTaskAsDone(int id) {
        Session session = sessionFactory.openSession();
        int affectedRows = 0;
        try {
            session.beginTransaction();
            affectedRows = session.createQuery("""
                            UPDATE Task
                            SET done = true
                            WHERE id = :fId
                            """)
                    .setParameter("fId", id)
                    .executeUpdate();
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
        return affectedRows > 0;
    }

    @Override
    public List<Task> findAllTasks() {
        Session session = sessionFactory.openSession();
        List<Task> foundTasks = Collections.emptyList();
        try {
            session.beginTransaction();
            foundTasks = session.createQuery("FROM Task", Task.class)
                    .getResultList();
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
        }
        return foundTasks;
    }

    @Override
    public List<Task> findCompletedTasks() {
        Session session = sessionFactory.openSession();
        List<Task> foundTasks = Collections.emptyList();
        try {
            session.beginTransaction();
            foundTasks = session.createQuery("""
                    FROM Task AS t
                    WHERE t.done = true""", Task.class)
                    .getResultList();
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
        }
        return foundTasks;
    }

    @Override
    public List<Task> findNewTasks() {
        Session session = sessionFactory.openSession();
        List<Task> foundTasks = Collections.emptyList();
        try {
            session.beginTransaction();
            foundTasks = session.createQuery("""
                    FROM Task AS t
                    WHERE t.done = false""", Task.class)
                    .getResultList();
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
        }
        return foundTasks;
    }
}