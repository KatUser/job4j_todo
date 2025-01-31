package ru.job4j.todo.util;

import ru.job4j.todo.model.Task;
import ru.job4j.todo.model.User;

import java.time.ZoneId;

public class SetTimeForTasks {

    public static Task setTimeToTask(Task task, User user) {
        task.setCreated(task.getCreated()
                .atZone(ZoneId.of("UTC"))
                .withZoneSameInstant(ZoneId.of(user.getTimezone()))
                .toLocalDateTime());
        return task;
    }
}


