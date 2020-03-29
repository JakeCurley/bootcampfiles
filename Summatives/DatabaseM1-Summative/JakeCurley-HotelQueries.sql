USE HotelReservation;
-------------------------------------------------------------------------------------------
-- 1.
    -- Write a query that returns a list of reservations that end in July 2023, 
    -- including the name of the guest, the room number(s), and the reservation dates.
-------------------------------------------------------------------------------------------

SELECT
	CONCAT(Guest.FirstName, ' ', Guest.LastName) GuestName,
    Reservation.RoomNumber,
    Reservation.StartDate,
    Reservation.EndDate
FROM Reservation
INNER JOIN Guest ON Reservation.GuestID = Guest.GuestID
INNER JOIN Room ON Reservation.RoomNumber = Room.RoomNumber
INNER JOIN RoomReservation ON Reservation.ReservationID = RoomReservation.ReservationID
WHERE Reservation.EndDate BETWEEN '2023/07/01' AND '2023/07/31';

-- Returns
--   GuestName  RoomNumber  StartDate   EndDate
-- 'Jake Curley', '205', '2023-06-28', '2023-07-02'
-- 'Walter Holaway', '204', '2023-07-13', '2023-07-14'
-- 'Wilfred Vise', '401', '2023-07-18', '2023-07-21'
-- 'Bettyann Seery', '303', '2023-07-28', '2023-07-29'
------------------------------------------------------------------------
-- 2
	-- Write a query that returns a list of all reservations for rooms with a jacuzzi, 
	-- displaying the guest's name, the room number, and the dates of the reservation.
------------------------------------------------------------------------------------

SELECT
	CONCAT(Guest.FirstName, ' ', Guest.LastName) GuestName,
    Reservation.RoomNumber,
    Reservation.StartDate,
    Reservation.EndDate
FROM Reservation
INNER JOIN RoomAmenity ON Reservation.RoomNumber = RoomAmenity.RoomNumber
INNER JOIN Guest ON Reservation.GuestID = Guest.GuestID
WHERE RoomAmenity.AmenityName = 'Jacuzzi';

-- Returns
--   GuestName  RoomNumber StartDate   EndDate
-- 'Karie Yang', '201', '2023-03-06', '2023-03-07'
-- 'Bettyann Seery', '203', '2023-02-05', '2023-02-10'
-- 'Karie Yang', '203', '2023-09-13', '2023-09-15'
-- 'Jake Curley', '205', '2023-06-28', '2023-07-02'
-- 'Wilfred Vise', '207', '2023-04-23', '2023-04-24'
-- 'Walter Holaway', '301', '2023-04-09', '2023-04-13'
-- 'Mack Simmer', '301', '2023-11-22', '2023-11-25'
-- 'Bettyann Seery', '303', '2023-07-28', '2023-07-29'
-- 'Duane Cullison', '305', '2023-02-22', '2023-02-24'
-- 'Bettyann Seery', '305', '2023-08-30', '2023-09-01'
-- 'Jake Curley', '307', '2023-03-17', '2023-03-20'
-----------------------------------------------------------------------------------------------------------
-- 3
    -- Write a query that returns all the rooms reserved for a specific guest, including the guest's name, 
    -- the room(s) reserved, the starting date of the reservation, 
    -- and how many people were included in the reservation. (Choose a guest's name from the existing data.)
------------------------------------------------------------------------------------------------------------

SELECT
	CONCAT(Guest.FirstName, ' ', Guest.LastName) GuestName,
    Reservation.RoomNumber,
    Reservation.NumAdults NumberOfAdults,
    Reservation.NumChildren NumberOfChildren,
    Reservation.StartDate
FROM Reservation
INNER JOIN Guest ON Reservation.GuestID = Guest.GuestID
WHERE Guest.GuestID = 2;

-- RETURNS
-- GuestName	RoomNumber	NumAdults NumChildren EndDate
-- 'Mack Simmer', '308',       '1',       '0', '2023-02-02'
-- 'Mack Simmer', '208',       '2',       '0', '2023-09-18'
-- 'Mack Simmer', '206',       '2',       '0', '2023-11-22'
-- 'Mack Simmer', '301',       '2',       '2', '2023-11-22'

-----------------------------------------------------------------------------------------------------------
-- 4
    -- Write a query that returns a list of rooms, reservation ID, and per-room cost for each reservation. 
    -- The results should include all rooms, whether or not there is a reservation associated with the room.
-----------------------------------------------------------------------------------------------------------

SELECT
	RoomReservation.ReservationNumber,
    Room.RoomNumber,
    Room.Price
FROM Room
LEFT OUTER JOIN RoomReservation ON Room.RoomNumber = RoomReservation.RoomNumber
ORDER BY ReservationNumber;

-- Returns
-- ReservationNumber RoomNumber CostPerRoom
-- NULL, '306', '149.99'
-- NULL, '402', '399.99'
-- '1', '308', '149.99'
-- '2', '203', '199.99'
-- '3', '305', '174.99'
-- '4', '201', '199.99'
-- '5', '307', '174.99'
-- '6', '302', '174.99'
-- '7', '202', '174.99'
-- '8', '304', '174.99'
-- '9', '301', '199.99'
-- '10', '207', '174.99'
-- '11', '401', '399.99'
-- '12', '206', '149.99'
-- '12', '208', '149.99'
-- '13', '304', '174.99'
-- '14', '205', '174.99'
-- '15', '204', '174.99'
-- '16', '401', '399.99'
-- '17', '303', '199.99'
-- '18', '305', '174.99'
-- '19', '208', '149.99'
-- '20', '203', '199.99'
-- '21', '206', '149.99'
-- '21', '301', '199.99'
-- '21', '401', '399.99'
-- '22', '302', '174.99'
-------------------------------------------------------------------------------------------------------------------------------------
-- 5
    -- Write a query that returns all the rooms accommodating at least three guests and that are reserved on any date in April 2023.
--------------------------------------------------------------------------------------------------------------------------------------

SELECT
	Reservation.RoomNumber
FROM Reservation
INNER JOIN Guest ON Reservation.GuestID = Guest.GuestID
WHERE (StartDate BETWEEN '2023/04/01' AND '2023/04/30') OR (EndDate BETWEEN '2023/04/01' AND '2023/04/30')
GROUP BY Reservation.RoomNumber
HAVING  SUM(NumAdults + NumChildren) >= 3;
-- Returns nothing?  No rooms booked in April have 3 or more guests.
--------------------------------------------------------------------------------------------------------------
-- 6
    -- Write a query that returns a list of all guest names and the number of reservations per guest, 
    -- sorted starting with the guest with the most reservations and then by the guest's last name.
--------------------------------------------------------------------------------------------------------------

SELECT
	CONCAT(Guest.FirstName, ' ', Guest.LastName) GuestName,
    COUNT(DISTINCT(RoomReservation.ReservationNumber)) NumberOFReservations
FROM RoomReservation
INNER JOIN Reservation ON RoomReservation.ReservationID = Reservation.ReservationID
INNER JOIN Guest ON Reservation.GuestID = Guest.GuestID
GROUP BY GuestName
ORDER BY NumberOfReservations DESC, Guest.LastName ASC;

-- Returns 
--  GuestName NumberOfReservations
-- 'Bettyann Seery', '3'
-- 'Mack Simmer', '3'
-- 'Duane Cullison', '2'
-- 'Jake Curley', '2'
-- 'Walter Holaway', '2'
-- 'Aurore Lipton', '2'
-- 'Maritza Tilton', '2'
-- 'Wilfred Vise', '2'
-- 'Karie Yang', '2'
-- 'Zachery Luechtefeld', '1'
-- 'Joleen Tison', '1'


------------------------------------------------------------------------------------------------------------------
-- 7
	-- Write a query that displays the name, address, and phone number of a guest based on their phone number. 
	-- (Choose a phone number from the existing data.)
----------------------------------------------------------------------------------------------------------------   
SELECT
	CONCAT(Guest.FirstName, ' ', Guest.LastName) GuestName,
    Address,
    PhoneNumber
FROM Guest
WHERE PhoneNumber = '291-553-0508';

-- Returns
-- GuestName             Address           PhoneNumber
-- 'Mack Simmer', '379 Old Shore Street', '291-553-0508'


