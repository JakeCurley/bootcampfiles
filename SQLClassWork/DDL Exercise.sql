CREATE DATABASE IF NOT EXISTS MovieCatalogue;

USE MovieCatalogue;

CREATE TABLE Genre (
	GenreID INT PRIMARY KEY AUTO_INCREMENT,
    GenreName VARCHAR(30) NOT NULL
);

CREATE TABLE Director (
	DirectorID INT PRIMARY KEY AUTO_INCREMENT,
    FirstName VARCHAR(30) NOT NULL,
    LastName VARCHAR(30) NOT NULL,
    BirthDate DATE Null
);

CREATE TABLE Rating (
	RatingID INT PRIMARY KEY AUTO_INCREMENT,
    RatingName CHAR(5) NOT NULL
);

CREATE TABLE Actor (
	ActorID INT PRIMARY KEY AUTO_INCREMENT,
    FirstName VARCHAR(30) NOT NULL,
    LastName VARCHAR(30) NOT NULL,
    BirthDate DATE NULL
);

CREATE TABLE Movie (
	MovieID INT PRIMARY KEY AUTO_INCREMENT,
    Title VARCHAR(128) NOT NULL,
    ReleaseDate DATE NULL
);

ALTER TABLE Movie
	ADD COLUMN (
		GenreID INT NOT NULL
	),
    ADD CONSTRAINT fk_Movie_Genre
		FOREIGN KEY (GenreID)
        REFERENCES Genre(GenreID);

ALTER TABLE Movie
	ADD COLUMN (
		DirectorID INT NOT NULL
	),
    ADD CONSTRAINT fk_Movie_Director
		FOREIGN KEY (DirectorID)
        REFERENCES Genre(GenreID);
        
ALTER TABLE Movie
	DROP FOREIGN KEY fk_Movie_Director,
    DROP COLUMN DirectorID;
    
ALTER TABLE Movie
	ADD COLUMN (
		DirectorID INT NOT NULL
	),
    ADD CONSTRAINT fk_Movie_Director
		FOREIGN KEY (DirectorID)
        REFERENCES Director(DirectorID);
    
    
ALTER TABLE Movie
	ADD COLUMN (
		RatingID INT NOT NULL
	),
    ADD CONSTRAINT fk_Moive_Rating
		FOREIGN KEY (RatingID)
        REFERENCES Rating(RatingID);

CREATE TABLE CastMembers (
	CastMemberID INT PRIMARY KEY AUTO_INCREMENT,
	`Role` VARCHAR(50) NOT NULL
);

ALTER TABLE CastMembers
	ADD COLUMN (
		ActorID INT NOT NULL
	),
    ADD CONSTRAINT fk_CastMembers_Actor
		FOREIGN KEY (ActorID)
        REFERENCES Actor(ActorID);
	
ALTER TABLE CastMembers
	ADD COLUMN (
		MovieID INT NOT NULL
	),
    ADD CONSTRAINT fk_CastMembers_Movie
		FOREIGN KEY (MovieID)
        REFERENCES Movie(MovieID);

        


