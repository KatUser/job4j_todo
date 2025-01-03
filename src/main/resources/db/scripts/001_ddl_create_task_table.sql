CREATE TABLE task
(
    id          SERIAL PRIMARY KEY,
    title       VARCHAR(32) unique not null,
    description TEXT                not null,
    created     TIMESTAMP,
    done        BOOLEAN
);


