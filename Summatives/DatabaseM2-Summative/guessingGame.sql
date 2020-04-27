DROP DATABASE IF EXISTS guessingGame;

CREATE DATABASE guessingGame;

USE guessingGame;

CREATE TABLE game (
	gameID INT PRIMARY KEY AUTO_INCREMENT,
    answer CHAR(4) NOT NULL,
    isFinished BOOLEAN NOT NULL
);

CREATE TABLE guess (
	guessID INT PRIMARY KEY AUTO_INCREMENT,
    guess CHAR(4) NOT NULL,
    guessTime DATETIME NOT NULL,
    part INT NOT NULL,
    exact INT NOT NULL,
    correctGuess BOOLEAN NOT NULL,
    gameID INT,
    FOREIGN KEY (gameID)
		REFERENCES game(gameID)
);

SELECT * FROM game;