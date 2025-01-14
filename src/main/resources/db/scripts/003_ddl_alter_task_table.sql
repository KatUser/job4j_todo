ALTER TABLE task
ADD COLUMN todo_user_id INT
    REFERENCES todo_user(id);