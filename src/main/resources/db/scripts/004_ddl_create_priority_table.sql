CREATE TABLE priority (
                            id SERIAL PRIMARY KEY,
                            name TEXT UNIQUE NOT NULL,
                            position int
);

INSERT INTO priority (name, position) VALUES ('urgently', 1);
INSERT INTO priority (name, position) VALUES ('normal', 2);

ALTER TABLE task ADD COLUMN priority_id int REFERENCES priority(id);

UPDATE task SET priority_id = (SELECT id FROM priority WHERE name = 'urgently');