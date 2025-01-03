CREATE TABLE task
(
    id          SERIAL PRIMARY KEY,
    title       TEXT not null,
    description TEXT not null,
    created     TIMESTAMP,
    done        BOOLEAN
);


