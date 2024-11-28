DROP TABLE IF EXISTS note;
DROP TABLE IF EXISTS student;

CREATE TABLE IF NOT EXISTS student(
                        id BIGINT GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
                        firstname VARCHAR(255),
                        lastname VARCHAR(255),
                        birthdate DATE,
                        username VARCHAR(255) UNIQUE NOT NULL
);

CREATE TABLE IF NOT EXISTS note(
                     id BIGINT GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
                     date DATE,
                     subject VARCHAR(30),
                     note_value NUMERIC(5, 2),
                     student_id BIGINT,
                     FOREIGN KEY (student_id) REFERENCES student(id)
);