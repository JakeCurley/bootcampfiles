USE HotelReservation;
INSERT INTO RoomType (RoomType, StandardOccupancy, MaxOccupancy, ExtraPerson) VALUES
		('Single', 2, 2, NULL),
        ('Double', 2, 4, 10),
        ('Suite', 3, 8, 20);
        
	INSERT INTO Room (RoomNumber, isADAaccessible, Price, RoomType) VALUES
		(201, 0, 199.99, 'Double'),
        (202, 1, 174.99, 'Double'),
        (203, 0, 199.99, 'Double'),
        (204, 1, 174.99, 'Double'),
        (205, 0, 174.99, 'Single'),
        (206, 1, 149.99, 'Single'),
        (207, 0, 174.99, 'Single'),
        (208, 1, 149.99, 'Single'),
        (301, 0, 199.99, 'Double'),
        (302, 1, 174.99, 'Double'),
        (303, 0, 199.99, 'Double'),
        (304, 1, 174.99, 'Double'),
        (305, 0, 174.99, 'Single'),
        (306, 1, 149.99, 'Single'),
        (307, 0, 174.99, 'Single'),
        (308, 1, 149.99, 'Single'),
        (401, 1, 399.99, 'Suite'),
        (402, 1, 399.99, 'Suite');
        
	INSERT INTO AmenityName (AmenityName) VALUES
		('Microwave'),
        ('Refrigerator'),
        ('Oven'),
        ('Jacuzzi');
	
    INSERT INTO RoomAmenity (RoomNumber, AmenityName) VALUES
		(201, 'Microwave'),
        (201, 'Jacuzzi'),
        (202, 'Refrigerator'),
        (203, 'Microwave'),
        (203, 'Jacuzzi'),
        (204, 'Refrigerator'),
        (205, 'Microwave'),
        (205, 'Refrigerator'),
        (205, 'Jacuzzi'),
        (206, 'Microwave'),
        (206, 'Refrigerator'),
        (207, 'Microwave'),
        (207, 'Refrigerator'),
        (207, 'Jacuzzi'),
        (208, 'Microwave'),
        (208, 'Refrigerator'),
        (301, 'Microwave'),
        (301, 'Jacuzzi'),
        (302, 'Refrigerator'),
        (303, 'Microwave'),
        (303, 'Jacuzzi'),
        (304, 'Refrigerator'),
        (305, 'Microwave'),
        (305, 'Refrigerator'),
        (305, 'Jacuzzi'),
        (306, 'Microwave'),
        (306, 'Refrigerator'),
        (307, 'Microwave'),
        (307, 'Refrigerator'),
        (307, 'Jacuzzi'),
        (308, 'Microwave'),
        (308, 'Refrigerator'),
        (401, 'Microwave'),
        (401, 'Refrigerator'),
        (401, 'Oven'),
        (402, 'Microwave'),
        (402, 'Refrigerator'),
        (402, 'Oven');
    
INSERT INTO Guest (FirstName, LastName, Address, City, State, Zipcode, PhoneNumber) VALUES
	('Jake', 'Curley', '123 Fake St.', 'Minneapolis', 'MN', 55401, '123-456-7890'),
    ('Mack', 'Simmer', '379 Old Shore Street', 'Council Bluffs', 'IA', 51501, '291-553-0508'),
    ('Bettyann', 'Seery', '750 Wintergreen Dr.', 'Wasilla', 'AK', 99654, '478-277-9632'),
    ('Duane', 'Cullison', '9662 Foxrun Lane', 'Harlingen', 'TX', 78552, '308-494-0198'),
    ('Karie', 'Yang', '9378 W. Augusta Ave.', 'West Deptford', 'NJ', 08096, '214-730-0298'),
    ('Aurore', 'Lipton', '762 Wild Rose Street', 'Saginaw', 'MI', 48601, '377-507-0974'),
    ('Zachery', 'Luechtefeld', '7 Poplar Dr.', 'Arvada', 'CO', 80003, '814-485-2615'),
    ('Jeremiah', 'Pendergrass', '70 Oakwood St.', 'Zion', 'IL', 60099, '279-491-0960'),
    ('Walter', 'Holaway', '7556 Arrowhead St.', 'Cumberland', 'RI', 02864, '446-396-6785'),
    ('Wilfred', 'Vise', '77 West Surrey Street', 'Oswego', 'NY', 13126, '834-727-1001'),
    ('Maritza', 'Tilton', '939 Linda Rd.', 'Burke', 'VA', 22015, '446-351-6860'),
    ('Joleen', 'Tison', '87 Queen St.', 'Drexel Hill', 'PA', 19026, '231-893-2755'),
    ('Bob', 'Smith', '123 Faker St.', 'FakeTOwn', 'MN', '12345', '123-123-1234');


INSERT INTO Reservation (GuestId, NumAdults, NumChildren, StartDate, EndDate, TotalCost) VALUES
	(2, 1, 0, '2023/2/2', '2023/2/4', 299.98),
    (3, 2, 1, '2023/2/5', '2023/2/10', 999.95),
    (4, 2, 0, '2023/2/22', '2023/2/24', 349.98),
    (5, 2, 2, '2023/3/6', '2023/3/7', 199.99),
    (1, 1, 1, '2023/3/17', '2023/3/20', 524.97),
    (6, 3, 0, '2023/3/18', '2023/3/23', 924.95),
    (7, 2, 2, '2023/3/29', '2023/3/31', 349.98),
    (8, 2, 0, '2023/3/31', '2023/4/5', 874.95),
    (9, 1, 0, '2023/4/9', '2023/4/13', 799.96),
    (10, 1, 1, '2023/4/23', '2023/4/24', 174.99),
    (11, 2, 4, '2023/5/30', '2023/6/2', 1199.97),
    (12, 2, 0, '2023/6/10', '2023/6/14', 599.96),
    (12, 1, 0, '2023/6/10', '2023/6/14', 599.96),
    (6, 3, 0, '2023/6/17', '2023/6/18', 184.99),
    (1, 2, 0, '2023/6/28', '2023/7/2', 699.96),
    (9, 3, 1, '2023/7/13', '2023/7/14', 184.99),
    (10, 4, 2, '2023/7/18', '2023/7/21', 1259.97),
    (3, 2, 1, '2023/7/28', '2023/7/29', 199.99),
    (3, 1, 0, '2023/8/30', '2023/9/1', 349.98),
    (2, 2, 0, '2023/9/18', '2023/9/17', 149.99),
    (5, 2, 2, '2023/9/13', '2023/9/15', 399.98),
    (4, 2, 2, '2023/11/22', '2023/11/25', 1199.97),
    (2, 2, 0, '2023/11/22', '2023/11/25', 449.97),
    (2, 2, 2, '2023/11/22', '2023/11/25', 599.97),
    (11, 2, 0, '2023/12/24', '2023/12/28', 699.96),
    (13, 2, 2, '2023/03/27', '2023/05/04', 5500.00);
    
INSERT INTO RoomReservation (ReservationNumber, RoomNumber, ReservationID) VALUES
	(1, 308, 1),
	(2, 203, 2),
	(3, 305, 3),
	(4, 201, 4),
	(5, 307, 5),
	(6, 302, 6),
	(7, 202, 7),
	(8, 304, 8),
	(9, 301, 9),
	(10, 207, 10),
	(11, 401, 11),
	(12, 206, 12),
	(12, 208, 13),
	(13, 304, 14),
	(14, 205, 15),
	(15, 204, 16),
	(16, 401, 17),
	(17, 303, 18),
	(18, 305, 19),
	(19, 208, 20),
	(20, 203, 21),
	(21, 401, 22),
	(22, 206, 23),
	(22, 301, 24),
	(23, 302, 25),
    (24, 308, 26);

SET SQL_SAFE_UPDATES = 0;

DELETE FROM RoomReservation
WHERE ReservationID = 8;

DELETE FROM Reservation
WHERE GuestID = 8;

DELETE FROM Guest
WHERE GuestID = 8;
SET SQL_SAFE_UPDATES = 1;

SELECT *
FROM RoomReservation
ORDER BY ReservationNumber;
    