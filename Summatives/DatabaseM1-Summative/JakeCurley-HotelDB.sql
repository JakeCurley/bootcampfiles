drop database if exists HotelReservation;
create database HotelReservation;
use HotelReservation;

CREATE TABLE IF NOT EXISTS Guest (
	GuestID INT PRIMARY KEY auto_increment,
    FirstName VARCHAR(20) NOT NULL,
    LastName VARCHAR(20) NOT NULL,
    PhoneNumber CHAR(12) NOT NULL,
    Address VARCHAR(40) NOT NULL,
    City VARCHAR(20) NOT NULL,
    State VARCHAR(10) NOT NULL,
    Zipcode INT NOT NULL
);

CREATE TABLE IF NOT EXISTS RoomType (
	RoomType CHAR(6) PRIMARY KEY NOT NULL,
    StandardOccupancy INT NOT NULL,
    MaxOccupancy INT NOT NULL,
    ExtraPerson DECIMAL(10,2) NULL
);

CREATE TABLE IF NOT EXISTS Room (
	RoomNumber INT PRIMARY KEY NOT NULL,
    isADAaccessible INT NOT NULL,
    Price DECIMAL(10,2) NOT NULL,
    RoomType CHAR(6) NOT NULL,
    FOREIGN KEY fk_Room_RoomType (RoomType)
	 REFERENCES RoomType(RoomType)
);

CREATE TABLE IF NOT EXISTS Reservation (
	ReservationID INT PRIMARY KEY AUTO_INCREMENT,
    NumAdults INT,
    NumChildren INT,
    StartDate DATE NOT NULL,
    EndDate DATE NOT NULL,
    TotalCost DECIMAL (10,2),
    GuestID INT NOT NULL,
    foreign key fk_Reservation_Guest (GuestID)
	 references Guest(GuestID)
);

CREATE TABLE IF NOT EXISTS AmenityName (
	AmenityName CHAR(12) PRIMARY KEY NOT NULL
);

CREATE TABLE IF NOT EXISTS RoomAmenity (
	RoomNumber INT NOT NULL,
    AmenityName CHAR(12) NOT NULL,
    PRIMARY KEY (RoomNumber, AmenityName),
    FOREIGN KEY fk_RoomAmenity_Room (RoomNumber)
		REFERENCES Room(RoomNumber),
	FOREIGN KEY fk_RoomAmenity_AmenityName (AmenityName)
		REFERENCES AmenityName (AmenityName)
);

CREATE TABLE IF NOT EXISTS RoomReservation (
	ReservationNumber INT,
	RoomNumber INT NOT NULL,
    ReservationID INT,
    PRIMARY KEY (ReservationNumber, RoomNumber),
    FOREIGN KEY fk_RoomReservation_Room (RoomNumber)
		references Room(RoomNumber),
	FOREIGN KEY fk_RoomReservation_Reservation (ReservationID)
		REFERENCES Reservation(ReservationID)
);