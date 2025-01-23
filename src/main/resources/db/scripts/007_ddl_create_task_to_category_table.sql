CREATE TABLE task_to_category
(
    id   SERIAL PRIMARY KEY,
    task_id INT NOT NULL REFERENCES task (id),
    category_id INT NOT NULL REFERENCES category (id),
    UNIQUE (task_id, category_id)
);