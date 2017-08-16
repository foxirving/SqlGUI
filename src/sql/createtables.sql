CREATE DATABASE StudentRecords;
GO

USE StudentRecords;
GO


IF OBJECT_ID('Residences') IS NOT NULL
	DROP TABLE Residences;

IF OBJECT_ID('Students') IS NOT NULL
	DROP TABLE Students;

IF OBJECT_ID('Vehicles') IS NOT NULL
	DROP TABLE Vehicles;

IF OBJECT_ID('studentVehicles') IS NOT NULL
	DROP TABLE studentVehicles;

IF OBJECT_ID('Instructors') IS NOT NULL
	DROP TABLE Instructors;

IF OBJECT_ID('Courses') IS NOT NULL
	DROP TABLE Courses;

IF OBJECT_ID('studentGrades') IS NOT NULL
	DROP TABLE studentGrades;

GO


CREATE TABLE Residences
	(ResidenceID					INT						NOT NULL PRIMARY KEY IDENTITY,
	ResidenceType					INT						NOT NULL
		CHECK (ResidenceType BETWEEN 0 AND 3),					-- 0 = off-campus, 1-3 are different dorms
	Address1						VARCHAR(128)			NOT NULL,
	Address2						VARCHAR(128)			NULL,
	City							VARCHAR(48)				NOT NULL,
	State							VARCHAR(2)				NOT NULL
		CHECK (State LIKE '[A-Z][A-Z]'), -- we could make this actually be a list of the 50 valid states but that seems pretty extreme
	ZipCode							VARCHAR(10)				NOT NULL
		CHECK (ZipCode LIKE '[0-9][0-9][0-9][0-9][0-9]'
 		OR ZipCode LIKE '[0-9][0-9][0-9][0-9][0-9]-[0-9][0-9][0-9][0-9]')
);

CREATE TABLE Students
	(StudentID 						INT 					NOT NULL PRIMARY KEY IDENTITY,
	FName 							VARCHAR(128) 			NOT NULL,
	MName							VARCHAR(128) 			NULL,
	LName 							VARCHAR(128) 			NOT NULL,
	Gender							VARCHAR(1)				NULL
		CHECK (Gender = 'M' OR Gender = 'F' OR Gender = '-'), -- '-' = prefer not to specify
	BirthDate 						DATE 					NOT NULL
		CHECK (DatePart(YYYY, BirthDate) BETWEEN 1900 AND DATEPART(YYYY, GETDATE())),
	PhoneNumber 					VARCHAR(14) 			NULL UNIQUE
		CHECK (PhoneNumber LIKE '([0-9][0-9][0-9])-[0-9][0-9][0-9]-[0-9][0-9][0-9][0-9]'),
	ResidenceID 					INT 					NOT NULL FOREIGN KEY REFERENCES Residences,
);


CREATE TABLE Vehicles
	(VehicleID 						INT 					NOT NULL PRIMARY KEY IDENTITY,
	LicenseNo 						VARCHAR(7) 				NOT NULL UNIQUE,
	Year							VARCHAR(4)				NOT NULL,
	Make 							VARCHAR(48) 			NOT NULL,
	Model 							VARCHAR(48) 			NOT NULL,
	Color 							VARCHAR(48) 			NOT NULL
 );


CREATE TABLE studentVehicles
	(StudentID 						INT 					NOT NULL FOREIGN KEY REFERENCES Students,
	VehicleID 						INT 					NOT NULL FOREIGN KEY REFERENCES Vehicles,
	PRIMARY KEY (StudentID, VehicleID)
 );


CREATE TABLE Instructors
	(InstructorID 					INT 					NOT NULL PRIMARY KEY IDENTITY,
	FName 							VARCHAR(128) 			NOT NULL,
	MName 							VARCHAR(128) 			NULL,
	LName 							VARCHAR(128) 			NOT NULL,
	PhoneNumber 					VARCHAR(14) 			NULL UNIQUE
		CHECK (PhoneNumber LIKE '([0-9][0-9][0-9])-[0-9][0-9][0-9]-[0-9][0-9][0-9][0-9]')
);


CREATE TABLE Courses
	(CourseID 						INT 					NOT NULL PRIMARY KEY IDENTITY,
	CourseName 						VARCHAR(128)			NOT NULL,
	InstructorID 					INT 					NOT NULL FOREIGN KEY REFERENCES Instructors
);


CREATE TABLE studentGrades
	(StudentID 						INT 					NOT NULL FOREIGN KEY REFERENCES Students,
	CourseID 						INT 					NOT NULL FOREIGN KEY REFERENCES Courses,
	Grade 							DECIMAL(2,1) 			NOT NULL
		CHECK (Grade BETWEEN 0.0 AND 4.0),
	PRIMARY KEY (StudentID, CourseID)
);

GO


CREATE INDEX IX_residences
	ON Residences (ResidenceType
		, Address1
		, ResidenceID
);

CREATE INDEX IX_Students
	ON Students (Lname
		, StudentID
);

CREATE INDEX IX_Vehicles
	ON Vehicles (LicenseNo
		, Make
		, Model
);

CREATE INDEX IX_Instructors
	ON Instructors (LName
		, InstructorID
);


INSERT INTO Residences (ResidenceType, Address1, Address2, City, State, ZipCode)
	VALUES (0, '123 Fake Street', NULL, 'Tacoma', 'WA', '98402')
			, (0, '234 Fake Street', NULL, 'Tacoma', 'WA', '98402')
			, (0, '999 Totally Real Blvd', NULL, 'Lakewood', 'WA', '98498')
			, (0, '777 This Place', 'Apt 99', 'Puyallup', 'WA', '98371')
			, (1, 'Dorm Building 1', 'Room 102', 'Tacoma', 'WA', '98402')
			, (1, 'Dorm Building 1', 'Room 204', 'Tacoma', 'WA', '98402')
			, (2, 'Dorm Building 2', 'Room 303', 'Tacoma', 'WA', '98402')
			, (2, 'Dorm Building 2', 'Room 404', 'Tacoma', 'WA', '98402')
			, (3, 'Dorm Building 3', 'Room 23', 'Tacoma', 'WA', '98402')
			, (3, 'Dorm Building 3', 'Room 51', 'Tacoma', 'WA', '98402')

INSERT INTO Students (Fname, MName, LName, Gender, BirthDate, PhoneNumber, ResidenceID)
	VALUES ('Vitomir', 'Ekkehard', 'Tritten', 'M', '1997-11-3', '(253)-555-5555'
				, (SELECT ResidenceID FROM Residences WHERE Address1 LIKE '123%'))
			, ('Isa', NULL, 'Thibault', '-', '1998-05-14', '(253)-555-5556'
				, (SELECT ResidenceID FROM Residences WHERE Address1 LIKE '234%'))
			, ('Annika', NULL, 'Whittaker', 'F', '1997-08-14', '(253)-555-5557'
				, (SELECT ResidenceID FROM Residences WHERE Address1 LIKE '999%'))
			, ('Marta', 'Ruzena', 'Ruzsa', 'F', '1996-02-04', '(253)-555-5558'
				, (SELECT ResidenceID FROM Residences WHERE Address1 LIKE '777%'))
			, ('Anila', NULL, 'Chaudhari', 'F', '1998-12-20', '(253)-555-5559'
				, (SELECT ResidenceID FROM Residences WHERE Address2 = 'Room 102'))
			, ('Nadya', NULL, 'Karim', 'F', '1996-04-19', '(206)-555-5555'
				, (SELECT ResidenceID FROM Residences WHERE Address2 = 'Room 204'))
			, ('John', 'Cohen', 'Plaskett', 'M', '1998-01-02', '(206)-555-5556'
				, (SELECT ResidenceID FROM Residences WHERE Address2 = 'Room 303'))
			, ('Antonio', NULL, 'Varela', 'M', '1997-10-28', '(360)-555-5555'
				, (SELECT ResidenceID FROM Residences WHERE Address2 = 'Room 404'))
			, ('Guo', 'Ju', 'Xun', 'M', '1995-05-05', '(206)-555-5559'
				, (SELECT ResidenceID FROM Residences WHERE Address2 = 'Room 23'))
			, ('Serafina', 'Ignacia', 'Ortiz', 'F', '1995-09-18', '(360)-555-5558'
				, (SELECT ResidenceID FROM Residences WHERE Address2 = 'Room 51'))

INSERT INTO Vehicles (LicenseNo, Year, Make, Model, Color)
	VALUES ('AAA-111', '1971', 'Ford', 'Pinto', 'Red')
			, ('BBB-222', '1970', 'AMC', 'Gremlin', 'Orange')
			, ('CCC-333', '1985', 'Yugo', 'Yugo', 'Red')
			, ('DDD-444', '1980', 'Chevrolet', 'Citation', 'Green')
			, ('EEE-555', '1971', 'Chevrolet', 'Vega', 'Grey')
			, ('FFF-666', '1972', 'Renault', 'Le Car', 'Blue')
			, ('GGG-777', '2001', 'Pontiac', 'Aztek', 'Red')
			, ('HHH-888', '2007', 'Chrysler', 'Sebring', 'Silver')
			, ('III-999', '2004', 'Volkswagen', 'Touareg', 'Silver')
			, ('JJJ-000', '2001', 'Chrysler', 'PT Cruiser', 'Red')

INSERT INTO studentVehicles (StudentID, VehicleID)
	VALUES (1,1)
			, (2,2)
			, (3,3)
			, (4,4)
			, (5,5)
			, (6,6)
			, (7,7)
			, (8,8)
			, (9,9)
			, (10,10)

GO

CREATE PROC spPopulateStudentVehicles
AS 
	DECLARE @sid INT, @vid INT;
	SET @sid = 1;
	SET @vid = 1;
	WHILE @sid < 11
	BEGIN
		INSERT INTO studentVehicles (studentID, VehicleID)
			VALUES (@sid, @vid)
		SET @sid = @sid + 1;
		set @vid = @vid + 1;
	END

EXEC spPopulateStudentVehicles;
GO

INSERT INTO Instructors (FName, MName, LName, PhoneNumber)
	VALUES ('Jack', NULL, 'Burton', '(555)-555-5555'),
			('Wang', NULL, 'Chi', '(555)-555-5556'),
			('Gracie', NULL, 'Law', '(555)-555-5557'),
			('Egg', NULL, 'Shen', '(555)-555-5558'),
			('Margo', NULL, 'Litzenberger', '(555)-555-5559')

INSERT INTO Courses (CourseName, InstructorID)
	VALUES ('Reflexes and the American Trucker', (SELECT InstructorID FROM Instructors WHERE LName = 'Burton'))
			, ('Physical Education', (SELECT InstructorID FROM Instructors WHERE LName = 'Chi'))
			, ('Immigration and Civil Rights', (SELECT InstructorID FROM Instructors WHERE LName = 'Law'))
			, ('Chinese Mysticism', (SELECT InstructorID FROM Instructors WHERE LName = 'Shen'))
			, ('Journalism 101', (SELECT InstructorID FROM Instructors WHERE LName = 'Litzenberger'))

INSERT INTO studentGrades (StudentID, CourseID, Grade)
	VALUES (1, 1, 4.0)
			, (2, 2, 3.8)
			, (3, 3, 2.5)
			, (4, 4, 3.6)
			, (5, 5, 3.6)
			, (6, 1, 3.2)
			, (7, 2, 4.0)
			, (8, 3, 2.4)
			, (9, 4, 3.8)
			, (10, 5, 3.9)

GO