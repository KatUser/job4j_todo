CREATE TABLE task
(
    id          SERIAL PRIMARY KEY,
    title       VARCHAR(32) unique not null,
    description TEXT                not null,
    created     TIMESTAMP,
    done        BOOLEAN
);

select * from task

insert into task (title, description, created, done)
values ('Кошка', 'помыть лоток и миски', now(), false);

insert into task (title, description, created, done)
values ('Ёлка', 'достать ёлку и нарядить её', now(), false);

insert into task (title, description, created, done)
values ('Собака', 'выгулять собаку', now(), true);


