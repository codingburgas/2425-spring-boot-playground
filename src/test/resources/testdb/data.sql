INSERT INTO student(firstname, lastname, birthdate, username) VALUES
    ('Tim', 'Test', '2000-10-10', 'tim@test.com'),
    ('John', 'Best', '2000-01-01', 'john@best.com'),
    ('Michael', 'Worst', '2000-02-02', 'michael@worst.com'),
    ('Andy', 'Most6', '2000-01-03', 'andy@most6.com'),
    ('Tom', 'Most2', '2000-03-04', 'tom@most2.com');


INSERT INTO note(date, subject, note_value, student_id) VALUES
    -- TIM NOTES
    (NOW(), 'EN', 3.00, 1),
    (NOW(), 'EN', 4.00, 1),
    (NOW(), 'CHEMISTRY', 4.00, 1),
    (NOW(), 'DB', 5.00, 1),
    (NOW(), 'DB', 5.00, 1),
    -- John notes - best student
    (NOW(), 'EN', 6.00, 2),
    (NOW(), 'EN', 5.00, 2),
    (NOW(), 'CHEMISTRY', 4.00, 2),
    (NOW(), 'DB', 6.00, 2),
    (NOW(), 'DB', 6.00, 2),
    -- Michael notes - worst student
    (NOW(), 'EN', 2.00, 3),
    (NOW(), 'EN', 2.00, 3),
    (NOW(), 'CHEMISTRY', 3.00, 3),
    -- Andy notes - most 6 notes, but not best
    (NOW(), 'EN', 6.00, 4),
    (NOW(), 'EN', 6.00, 4),
    (NOW(), 'CHEMISTRY', 6.00, 4),
    (NOW(), 'CHEMISTRY', 6.00, 4),
    (NOW(), 'DB', 3.00, 4),
    (NOW(), 'DB', 2.00, 4),
    (NOW(), 'DB', 3.00, 4),
    (NOW(), 'DB', 3.00, 4),
    -- Tom notes - most 2's, but not worst
    (NOW(), 'EN', 2.00, 5),
    (NOW(), 'EN', 2.00, 5),
    (NOW(), 'CHEMISTRY', 2.00, 5),
    (NOW(), 'CHEMISTRY', 6.00, 5),
    (NOW(), 'DB', 5.00, 5),
    (NOW(), 'DB', 2.00, 5),
    (NOW(), 'DB', 2.00, 5),
    (NOW(), 'DB', 4.00, 5);
