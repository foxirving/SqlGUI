USE StudentRecords

GO


-- Get the names and addresses of all students living off campus
CREATE FUNCTION dbo.fnOffCampusStudents ()
RETURNS TABLE
AS
RETURN
(
	SELECT s.*
		, r.Address1
		, r.Address2
		, r.City
		, r.State
		, r.ZipCode
	FROM Students AS s
	INNER JOIN Residences AS r
	ON s.ResidenceID = r.ResidenceID
	WHERE r.ResidenceType = 0
);
GO

SELECT * FROM dbo.fnOffCampusStudents()


--Get the students in a given dorm. Parameter @dormid should be 1, 2, or 3.
CREATE FUNCTION dbo.fnStudentsInDorm (@dormid INT)
RETURNS TABLE
AS
RETURN
(
	SELECT s.*
		, r.Address1
		, r.Address2
	FROM Students AS s
	INNER JOIN Residences AS r
	ON s.ResidenceID = r.ResidenceID
	WHERE r.ResidenceType = @dormid
);
GO

-- Replace 'x' with dorm number from frontend
SELECT * FROM dbo.fnStudentsInDorm (x)


-- Get student records for all students born in year XXXX
CREATE FUNCTION dbo.fnStudentBornInYear (@birthyear INT)
RETURNS TABLE
AS
RETURN
(
	SELECT *
	FROM Students
	WHERE DATEPART(YYYY, BirthDate) = @birthyear
);
GO

-- Replace XXXX with desired year as received from frontend
SELECT * FROM fnStudentBornInYear (XXXX)



-- get all students of requested gender
CREATE FUNCTION dbo.fnStudentGender (@gender VARCHAR(1))
RETURNS TABLE
AS
RETURN
(
	SELECT *
	FROM Students
	WHERE Gender = @gender
);
GO

--replace x with single-quoted one-char string ('m', 'f', '-') based on user input from frontend
SELECT * FROM dbo.fnStudentGender (X)


-- Get a list of all student vehicles w/ student info
CREATE FUNCTION dbo.fnAllStudentVehicles ()
RETURNS TABLE
AS
RETURN
(
	SELECT s.StudentID
		, s.FName
		, s.MName
		, s.LName
		, s.PhoneNumber
		, v.*
	FROM Vehicles AS v
	INNER JOIN studentVehicles AS sv
	ON v.VehicleID = sv.VehicleID
	INNER JOIN Students AS s
	ON s.StudentID = sv.StudentID
);
GO

SELECT * FROM dbo.fnAllStudentVehicles ()


-- Get a list of student vehicles with model year in a given decade
CREATE FUNCTION dbo.fnStudentVehiclesDecade (@dec INT)
RETURNS TABLE
AS
RETURN
(
	SELECT s.StudentID
		, s.FName
		, s.MName
		, s.LName
		, s.PhoneNumber
		, v.*
	FROM Vehicles AS v
	INNER JOIN studentVehicles AS sv
	ON v.VehicleID = sv.VehicleID
	INNER JOIN Students AS s
	ON s.StudentID = sv.StudentID
	WHERE v.Year >= @dec 
		AND v.Year < (@dec + 10)
);
GO

-- when called from frontend, the parameter needs to be the first year
-- of the decade, e.g. 1970, 1980, etc.
SELECT * FROM dbo.fnStudentVehiclesDecade (XXXX)


--get students, course info, instructor and grades
-- for grades over a given score
-- default gives all grades
CREATE FUNCTION dbo.fnStudentGrades (@threshold DECIMAL(2,1) = 0.0)
RETURNS TABLE
AS
RETURN
(
	SELECT s.StudentID
		, s.FName
		, s.MName
		, s.LName
		, c.CourseID
		, c.CourseName
		, i.FName + ' ' + i.LName AS [Instructor]
		, sg.Grade
	FROM Students AS s
	INNER JOIN studentGrades AS sg
	ON s.StudentID = sg.StudentID
	INNER JOIN Courses AS c
	ON sg.CourseID = c.CourseID
	INNER JOIN Instructors AS i
	ON c.CourseID = i.InstructorID
	WHERE sg.Grade >= @threshold
);
GO

-- to get all the grades, default must be passed as parameter
-- for any other threshold, frontend should pass decimal value
-- with one place after decimal e.g. 3.5, 2.0
SELECT * FROM dbo.fnStudentGrades (DEFAULT)