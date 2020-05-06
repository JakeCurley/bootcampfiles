DROP DATABASE IF EXISTS findmymovie;
CREATE DATABASE findmymovie;

USE findmymovie;

CREATE TABLE login (
    userName VARCHAR(50) PRIMARY KEY NOT NULL,
    enc VARCHAR(100) NOT NULL,
    salt VARCHAR(100) NOT NULL
);

CREATE TABLE movieList (
	listID int PRIMARY KEY AUTO_INCREMENT,
    listName VARCHAR(200) NOT NULL,
    userName VARCHAR(50),
    FOREIGN KEY (userName) REFERENCES login(userName)
);

CREATE TABLE movie (
	movieID INT PRIMARY KEY AUTO_INCREMENT,
	imdbID VARCHAR(10) NOT NULL,
    poster VARCHAR(200) NULL,
    title VARCHAR(100) NULL,
    userScore INT NOT NULL,
    releaseDate VARCHAR(20) NOT NULL,
    rating VARCHAR(5) NOT NULL,
    runTime INT NOT NULL,
    plot TEXT NOT NULL,
    popularity VARCHAR(5) NOT NULL,
    budget INT NOT NULL,
    userName VARCHAR(50),
    FOREIGN KEY (userName) REFERENCES login(userName)
);

CREATE TABLE genre (
	genreID INT PRIMARY KEY AUTO_INCREMENT,
	genreName VARCHAR(20),
    userName VARCHAR(50),
    FOREIGN KEY (userName) REFERENCES login(userName)
);

CREATE TABLE actor (
	actorID INT PRIMARY KEY AUTO_INCREMENT,
	actorName VARCHAR(40) NOT NULL,
    userName VARCHAR(50),
    FOREIGN KEY (userName) REFERENCES login(userName)
);

CREATE TABLE movieGenre (
	movieID INT NOT NULL,
    genreID INT NOT NULL,
	PRIMARY KEY (movieID, genreID),
    FOREIGN KEY (movieID) REFERENCES movie(movieID) ON DELETE CASCADE,
    FOREIGN KEY (genreID) REFERENCES genre(genreID) ON DELETE CASCADE
);

CREATE TABLE movieActor (
	movieID INT NOT NULL,
    actorID INT NOT NULL,
    PRIMARY KEY (movieID, actorID),
    FOREIGN KEY (movieID) REFERENCES movie(movieID) ON DELETE CASCADE,
    FOREIGN KEY (actorID) REFERENCES actor(actorID) ON DELETE CASCADE
);

CREATE TABLE movieListmovie (
	movieID INT NOT NULL,
    listID INT NOT NULL,
    PRIMARY KEY (movieID, listID),
    FOREIGN KEY (movieID) REFERENCES movie(movieID) ON DELETE CASCADE,
    FOREIGN KEY (listID) REFERENCES movieList(listID) ON DELETE CASCADE
);