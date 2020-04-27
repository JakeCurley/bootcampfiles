DROP DATABASE IF EXISTS classRoster;
CREATE DATABASE classRoster;

USE classRoster;

CREATE TABLE teacher(
	id int PRIMARY KEY AUTO_INCREMENT,
    firstName VARCHAR(30) NOT NULL,
    lastName VARCHAR(50) NOT NULL,
    specialty VARCHAR(50)
);

CREATE TABLE student (
	id INT PRIMARY KEY AUTO_INCREMENT,
    firstName VARCHAR(30) NOT NULL,
    lastName VARCHAR(50) NOT NULL
);

CREATE TABLE course (
	id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(50) NOT NULL,
    description VARCHAR(255),
    teacherId INT NOT NULL,
    FOREIGN KEY (teacherId) REFERENCES teacher(Id)
);

CREATE TABLE course_student(
	courseId INT NOT NULL,
    studentId INT NOT NULL,
    PRIMARY KEY (courseId, studentId),
    FOREIGN KEY (courseId) REFERENCES course(Id),
    FOREIGN KEY (studentId) REFERENCES student(Id)
);