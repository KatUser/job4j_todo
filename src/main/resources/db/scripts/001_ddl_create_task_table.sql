CREATE TABLE task
(
    id          SERIAL PRIMARY KEY,
    title       TEXT,
    description TEXT,
    created     TIMESTAMP,
    done        BOOLEAN
);


