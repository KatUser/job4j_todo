CREATE TABLE tasks (
   id SERIAL PRIMARY KEY,
   description TEXT unique,
   created TIMESTAMP,
   done BOOLEAN
);