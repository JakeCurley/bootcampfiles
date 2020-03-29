DROP DATABASE IF EXISTS TrackIt;

CREATE DATABASE TrackIt;

USE TrackIt;

CREATE TABLE Project (
    ProjectId CHAR(50) PRIMARY KEY,
    `Name` VARCHAR(100) NOT NULL,
    Summary VARCHAR(2000) NULL,
    DueDate DATE NOT NULL,
    IsActive BOOL NOT NULL DEFAULT 1
);

CREATE TABLE Worker (
    WorkerId INT PRIMARY KEY AUTO_INCREMENT,
    FirstName VARCHAR(50) NOT NULL,
    LastName VARCHAR(50) NOT NULL
);

CREATE TABLE ProjectWorker (
    ProjectId CHAR(50) NOT NULL,
    WorkerId INT NOT NULL,
    PRIMARY KEY pk_ProjectWorker (ProjectId, WorkerId),
    FOREIGN KEY fk_ProjectWorker_Project (ProjectId)
        REFERENCES Project(ProjectId),
    FOREIGN KEY fk_ProjectWorker_Worker (WorkerId)
        REFERENCES Worker(WorkerId)
);

CREATE TABLE Task (
    TaskId INT PRIMARY KEY AUTO_INCREMENT,
    Title VARCHAR(100) NOT NULL,
    Details TEXT NULL,
    DueDate DATE NOT NULL,
    EstimatedHours DECIMAL(5, 2) NULL,
    ProjectId CHAR(50) NOT NULL,
    WorkerId INT NOT NULL,
    FOREIGN KEY fk_Task_ProjectWorker (ProjectId, WorkerId)
        REFERENCES ProjectWorker(ProjectId, WorkerId)
);

INSERT INTO Worker (WorkerId, FirstName, LastName)
	VALUES (1, 'Rosemonde', 'Featherbie');
    
INSERT INTO Worker (FirstName, LastName)
	VALUES ('Kingsly', 'Besantie');
    
INSERT INTO Worker (FirstName, LastName) Values
	('Goldi','Pilipets'),
    ('Borey','Rulf'),
    ('Panchito','Ashtonhurst');

INSERT INTO Project (ProjectId, `Name`, DueDate)
	VALUES ('db-milestone', 'Database Material', '2018-12-31');
    
INSERT INTO ProjectWorker (ProjectId, WorkerId)
	VALUES ('db-milestone', 2);
    
INSERT INTO Project (ProjectId, `Name`, DueDate)
	VALUES ('kitchen', 'Kitchen Remodel', '2025-07-15');
    
INSERT INTO ProjectWorker (ProjectId, WorkerId) VALUES
	('db-milestone', 1), -- Rosemonde, DataBase
    ('kitchen', 2), -- Kingsly, Kitchen
    ('db-milestone', 3), -- Goldi, Database
    ('db-milestone', 4); -- Dorey, Database

UPDATE Project SET
	Summary = 'All lessons and exercies for the relational database milestone.',
    DueDate = '2018-10-15'
WHERE ProjectId = 'db-milestone';

UPDATE Worker SET
	LastName = 'Oaks'
Where WorkerId = 2;

SET SQL_SAFE_UPDATES = 0;

UPDATE Project SET
	IsActive = 0
WHERE DueDate BETWEEN '2017-01-01' AND '2017-12-31'
AND IsActive = 1;

SET SQL_SAFE_UPDATES = 1;

SET SQL_SAFE_UPDATES = 0;

UPDATE Task SET
	EstimatedHours = EstimatedHours * 1.25
Where WorkerId = 2;

SET SQL_SAFE_UPDATES = 1;

DELETE FROM Worker
WHERE WorkerId = 50;

SET SQL_SAFE_UPDATES = 0;
DELETE FROM Task
Where WorkerId = 2;

DELETE FROM ProjectWorker
WHERE WorkerId = 2;

DELETE FROM Worker
WHERE WorkerId = 2;
SET SQL_SAFE_UPDATES = 1;