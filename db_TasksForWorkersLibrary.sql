use TasksForWorkersLibrary;

CREATE TABLE task (id int PRIMARY KEY AUTO_INCREMENT, title VARCHAR(50) NOT NULL, description TEXT, state VARCHAR(20) NOT NULL, deathline DATE, UNIQUE INDEX `title_UNIQUE` (`title` ASC) VISIBLE);
CREATE TABLE worker (id int PRIMARY KEY AUTO_INCREMENT, taskId int, FOREIGN KEY(taskId) REFERENCES task(id), name VARCHAR(50) NOT NULL, lastName VARCHAR(50) NOT NULL, e_mail VARCHAR(50) NOT NULL);

INSERT INTO task(title, description, state, deathline) VALUES ("Project 1", " description ", "in progress", '2022-06-4');
INSERT INTO worker(taskId, name, lastName, e_mail) VALUES (1, "Peter", "Retep", "retep@gmail.com");

SELECT * FROM worker;