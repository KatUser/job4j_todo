CREATE TABLE todo_user
(
    id       SERIAL PRIMARY KEY,
    name     VARCHAR NOT NULL,
    login    VARCHAR UNIQUE NOT NULL,
    password VARCHAR NOT NULL
)
