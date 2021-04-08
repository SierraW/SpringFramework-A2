INSERT INTO User(name, encrypted_Password, enabled) VALUES 
('Simon', '$2a$10$1ltibqiyyBJMJQ4hqM7f0OusP6np/IHshkYc4TjedwHnwwNChQZCy', true),
('Capheny', '$2a$10$kVkaijwIFskOuxurXxMRsuemxqxlpcWA0xtZCnRiJSFO6p9mgY9u6', true);

INSERT INTO Role(rolename) VALUES ('ROLE_USER'), ('ROLE_GUEST');
INSERT INTO User_Role_List VALUES (1, 2), (2, 1), (2, 2);

INSERT INTO Thread(title, body, USER_ID, TIME) VALUES 
('Greetings', 'How are you doing?', 2, '2021-04-07');
INSERT INTO Thread(title, body, USER_ID, TIME) VALUES 
('Assignment 2', 'Do you have any question?', 1, '2021-04-07');

INSERT INTO Reply(body, USER_ID, THREAD_ID, TIME) VALUES 
('Not so good.', 2, 1, '2021-04-07');
INSERT INTO Reply(body, USER_ID, THREAD_ID, TIME) VALUES 
('Me too.', 1, 1, '2021-04-07');
INSERT INTO Reply(body, USER_ID, THREAD_ID, TIME) VALUES 
('No', 2, 2, '2021-04-07');