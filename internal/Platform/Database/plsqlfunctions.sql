CREATE FUNCTION getInstructorLessons(instructor_id_in VARCHAR(11))
RETURNS TABLE (
		name TEXT,
		classroom_id VARCHAR(20),
		lesson_date VARCHAR(20),
		lesson_ts VARCHAR(20)
) AS $$

BEGIN

RETURN QUERY SELECT name, classroom_id, lesson_date, lesson_ts FROM lesson WHERE instructor_id = instructor_id_in;

END;
$$ LANGUAGE plpgsql; 

CREATE OR REPLACE FUNCTION updateInstructorInfo(instructor_id_in VARCHAR(11), homePhone text[], cellPhone text[], knownLangs text[] )
RETURNS VOID AS $$

BEGIN

UPDATE person
SET home_number = homePhone , phone_number = cellPhone
WHERE person.id = instructor_id_in;

UPDATE instructor
SET known_languages = knownLangs
WHERE instructor.id = instructor_id_in;

END;
$$ LANGUAGE plpgsql; 

CREATE OR REPLACE FUNCTION updateStudentInfo(student_id_in VARCHAR(11), homePhone text[], cellPhone text[])
RETURNS VOID AS $$

BEGIN

UPDATE person
SET home_number = homePhone , phone_number = cellPhone
WHERE person.id = student_id_in;

END;
$$ LANGUAGE plpgsql; 

CREATE OR REPLACE FUNCTION getInstructors(branchName_in VARCHAR(31))
RETURNS TABLE (
		id CHAR(11),
		known_languages TEXT[],
		pworking_hours CHAR(21)
) AS $$

BEGIN

RETURN QUERY SELECT instructor.id, instructor.known_languages, instructor.pworking_hours 
FROM instructor, instructor_can_work_in 
WHERE instructor.id = instructor_id and branch_name = branchName_in ORDER BY id;

END;
$$ LANGUAGE plpgsql; 

CREATE OR REPLACE FUNCTION attachClassroomWithLesson(classroom_id_in VARCHAR(20), lesson_date_in VARCHAR(20), lesson_ts_in VARCHAR(20), lesson_name_in TEXT, lesson_course_no_in INT, instructor_id_in VARCHAR(11))
RETURNS VOID AS $$

BEGIN

UPDATE lesson
SET classroom_id = classroom_id_in ,lesson_date = lesson_date_in, lesson_ts = lesson_ts_in, instructor_id = instructor_id_in
WHERE lesson.name = lesson_name_in AND lesson.course_no = lesson_course_no_in;

END;
$$ LANGUAGE plpgsql; 

CREATE FUNCTION getClassroomSchedule(classroomId varchar(20))
RETURNS TABLE (
		classroom_id VARCHAR(20),
		lesson_date VARCHAR(20),
		lesson_ts VARCHAR(20)
) AS $$

BEGIN

RETURN QUERY SELECT lesson.classroom_id, lesson.lesson_date, lesson.lesson_ts 
From lesson
WHERE lesson.classroom_id = classroomId
ORDER BY classroom_id;

END;
$$ LANGUAGE plpgsql; 

CREATE FUNCTION createStudent (id_in char, fname_in varchar,mid_name_in varchar,lname_in varchar,phone_number_in text[],home_number_in text[],home_addr_in text, work_addr_in text)
RETURNS VOID AS '
BEGIN
INSERT INTO Person (id, fname, mid_name, lname, phone_number, home_number, home_addr, work_addr)
VALUES (id_in, fname_in, mid_name_in, lname_in, phone_number_in, home_number_in, home_addr_in, work_addr_in);
END;
'
LANGUAGE plpgsql; 


END;

$$ LANGUAGE plpgsql; 
-------- 
CREATE TYPE student_record Person%ROWTYPE;
CREATE FUNCTION queryStudent (id_in char)
RETURNS student_record AS $$
BEGIN
SELECT * INTO student_record FROM Person WHERE id = id_in
RETURN student_record;
END;
$$ LANGUAGE plpgsql; 

---------
CREATE FUNCTION deleteLesson (name_in text, course_no_in int)
RETURNS VOID AS $$

BEGIN

DELETE FROM lesson WHERE name = name_in and course_no = course_no_in;

END;
$$ LANGUAGE plpgsql; 

---------
CREATE FUNCTION deleteBranch(name_in varchar(31))
RETURNS VOID AS $$

BEGIN

DELETE FROM branch WHERE name=name_in;

END;
$$ LANGUAGE plpgsql; 
---------

CREATE FUNCTION deleteCourse(CourseId_in int)
RETURNS VOID AS $$

BEGIN

DELETE FROM course 
WHERE id=CourseId_in;

END;
$$ LANGUAGE plpgsql; 
--------

CREATE FUNCTION deleteClassroom(ClassroomId_in varchar(20))
RETURNS VOID AS $$

BEGIN

DELETE FROM  classroom WHERE id=ClassroomId_in;

END;
$$ LANGUAGE plpgsql; 
-------

CREATE OR REPLACE FUNCTION addBranch(name_in varchar(31), phone_number_in text[], fax_in text[], address_in text, public_transport_in text[], private_transport_in text[], facilities_in text[])

RETURNS VOID AS $$
BEGIN

INSERT INTO Branch(name, phone_number, fax, address, public_transport, private_transport, facilities)
VALUES(name_in, phone_number_in, fax_in, address_in, public_transport_in, private_transport_in, facilities_in);
END;
$$ LANGUAGE plpgsql; 
------
CREATE FUNCTION getAllBranches
	RETURNS TABLE(
	Bname char ,		
	Bphone_number text[],			
	Bfax text[],
	Baddress  text,
	Bpublic_transport text[],			
	Bprivate_transport text[],			
	Bfacilities text[],
	Bacademy_name text,
)
AS $$
BEGIN	
	RETURN QUERY SELECT *
		name char(31) PRIMARY KEY,		
		phone_number text[],	
		fax text[],
		address text,
		public_transport text[],			
		private_transport text[],			
		facilities text[],
		academy_name text,
	FROM
		Branch
END; $$ 

LANGUAGE 'plpgsql';
------
--attach classroom to a branch
CREATE FUNCTION attachClassroom(branch_name_in varchar, classroom_name_in varchar)
RETURNS VOID AS $$
BEGIN 
UPDATE Classroom
SET branch_name = branch_name_in
WHERE name = classroom_name_in;
END;
$$ LANGUAGE plpgsql; 
--attach lesson to classroom
CREATE OR REPLACE FUNCTION attachLesson(name_in text, course_no_in int, classroom_id_in varchar)
RETURNS VOID AS $$
BEGIN
UPDATE Lesson
SET classroom_id= classroom_id_in
WHERE name = name_in AND course_no = course_no_in;
END;
$$ LANGUAGE plpgsql; 

--  Create a Classroom
CREATE OR REPLACE FUNCTION addClassroom(name varchar(20), capacity int, branch_name varchar(31))
RETURNS VOID AS $$
BEGIN
	
INSERT INTO Classroom(name, capacity, branch_name)
VALUES (name, capacity, branch_name);

END;
$$ LANGUAGE plpgsql; 

------
CREATE OR REPLACE FUNCTION addCourse(name varchar(15), language_in varchar(15), price_in int)
RETURNS VOID AS $$
BEGIN
	
INSERT INTO Course(name, language, price)
VALUES (name, language_in, price_in);

END;
$$ LANGUAGE plpgsql; 
------
// idsi verilen branche bagli classroom dondur
CREATE OR REPLACE FUNCTION getAvailableClassrooms (branchId_in VARCHAR) 
	RETURNS TABLE (
		class_id VARCHAR,
		class_capacity INT,
		class_branch_name VARCHAR
) 
AS $$
BEGIN
	RETURN QUERY SELECT
		id int,
		capacity int,
		branch_name varchar,
	FROM
		Classroom
	WHERE
		branchId_in = id ;
END; $$ 

LANGUAGE 'plpgsql';
------
CREATE FUNCTION getAvailableInstructors 168b map??//	
------
CREATE FUNCTION addLesson(name_in text, course_no_in int, instructor_id_in char(11), classroom_id_in varchar(20), lesson_date_in varchar(20), lesson_ts_in varchar(20))
RETURNS VOID AS $$

BEGIN

INSERT INTO Lesson (name, course_no, instructor_id, classroom_id, lesson_date, lesson_ts)
VALUES(name_in , course_no_in , instructor_id_in , classroom_id_in, lesson_date_in, lesson_ts_in);

END;
$$ LANGUAGE plpgsql; 
------
CREATE TYPE personal_record Person%ROWTYPE;
CREATE FUNCTION getPersonalDetails (id_in char)
RETURNS personal_record AS $$
BEGIN
SELECT * INTO personal_record FROM Person WHERE id = id_in;
RETURN personal_record;
END;
$$ LANGUAGE plpgsql; 
------

CREATE FUNCTION updatePersonfname(id_in char, fname_in varchar)
RETURNS VOID AS $$

BEGIN
UPDATE Person
SET fname = (fname_in)
WHERE id = id_in;

END;
$$ LANGUAGE plpgsql; 
-
CREATE FUNCTION updatePersonmid_name(id_in char, mid_name_in varchar)
RETURNS VOID AS $$

BEGIN
UPDATE Person
SET mid_name = (mid_name_in)
WHERE id = id_in;

END;
$$ LANGUAGE plpgsql; 
-
CREATE FUNCTION updatePersonl_name(id_in char, l_name_in varchar)
RETURNS VOID AS $$

BEGIN
UPDATE Person
SET lname = (l_name_in)
WHERE id = id_in;

END;
$$ LANGUAGE plpgsql; 

-
CREATE FUNCTION updatePersonphone_number(id_in char, phone_number_in text)
RETURNS VOID AS $$

BEGIN
UPDATE Person
SET phone_number = (phone_number_in)
WHERE id = id_in;

END;
$$ LANGUAGE plpgsql; 

---
CREATE FUNCTION updatePersonhome_number(id_in char,home_number_in text)
RETURNS VOID AS $$

BEGIN
UPDATE Person
SET home_number = (home_number_in)
WHERE id = id_in;

END;
$$ LANGUAGE plpgsql; 

CREATE FUNCTION updatePersonhome_addr(id_in char,home_addr_in text)
RETURNS VOID AS $$

BEGIN
UPDATE Person
SET home_addr = (home_addr_in)
WHERE id = id_in;

END;
$$ LANGUAGE plpgsql; 
--
CREATE FUNCTION updatePersonwork_addr(id_in char,work_addr_in text)
RETURNS VOID AS $$

BEGIN
UPDATE Person
SET work_addr = (work_addr_in)
WHERE id = id_in;

END;
$$ LANGUAGE plpgsql; 





------
UPDATING Branch
--
CREATE FUNCTION updateBranch_phone(name_in varchar,phone_number_in text[])
RETURNS VOID AS $$

BEGIN
UPDATE Branch
SET phone_number = phone_number_in
WHERE name = name_in;

END;
$$ LANGUAGE plpgsql;
-
CREATE FUNCTION updateBranch_fax(name_in varchar,fax_in text[])
RETURNS VOID AS $$

BEGIN
UPDATE Branch
SET fax = fax_in
WHERE name = name_in;

END;
$$ LANGUAGE plpgsql;
-
CREATE FUNCTION updateBranch_addres(name_in varchar,address_in text[])
RETURNS VOID AS $$

BEGIN
UPDATE Branch
SET address = address_in
WHERE name = name_in;

END;
$$ LANGUAGE plpgsql;
-
CREATE FUNCTION updateBranch_public_transport(name_in varchar,public_transport_in text[])
RETURNS VOID AS $$

BEGIN
UPDATE Branch
SET public_transport = public_transport_in
WHERE name = name_in;

END;
-
CREATE FUNCTION updateBranch_private_transport(name_in varchar,private_transport_in text[])
RETURNS VOID AS $$

BEGIN
UPDATE Branch
SET private_transport = private_transport_in
WHERE name = name_in;

END;
$$ LANGUAGE plpgsql;
-
CREATE FUNCTION updateBranch_facilities(name_in varchar,facilities_in text[])
RETURNS VOID AS $$

BEGIN
UPDATE Branch
SET facilities = facilities_in
WHERE name = name_in;

END;
$$ LANGUAGE plpgsql;

---------
CREATE FUNCTION getTotal(personId_in)

------
CREATE FUNCTION getUnpaidAmount(personId)
------
CREATE FUNCTION getListOfPaymentsToBeMade(personId) 
------
CREATE FUNCTION getLastPayDate(personId_in)

CREATE OR REPLACE FUNCTION public.checkUserCredentials(
	IN email LOGIN.email%TYPE, 
	IN password LOGIN.password%TYPE,
	OUT person_id LOGIN.person_id%TYPE,
	OUT authorization_level LOGIN.authorization_level%TYPE
) AS $$
BEGIN
	SELECT l.person_id, l.authorization_level INTO person_id, authorization_level 
	FROM LOGIN loglin 
	WHERE l.email = email AND l.password = password;
END;
$$ LANGUAGE plpgsql; 


CREATE OR REPLACE FUNCTION public.addPerson(
	IN person_id PERSON.id%TYPE,
	IN fname PERSON.fname%TYPE,
	IN lname PERSON.lname%TYPE,
	IN phone_number PERSON.phone_number%TYPE,
	IN home_number PERSON.home_number%TYPE,
	IN home_addr PERSON.home_addr%TYPE,
	IN work_addr PERSON.work_addr%TYPE
) RETURNS PERSON.id%TYPE AS $$
BEGIN
	INSERT INTO Person (id, fname, lname, phone_number, home_number, home_addr, work_addr)
	VALUES (person_id, fname, lname, phone_number, home_number, home_addr, work_addr);
	RETURN person_id;
END;
$$ LANGUAGE plpgsql; 

CREATE OR REPLACE FUNCTION public.addLogin(
	IN email LOGIN.email%TYPE,
	IN password LOGIN.password%TYPE,
	IN person_id LOGIN.person_id%TYPE,
	IN authorization_level LOGIN.authorization_level%TYPE
) RETURNS LOGIN.person_id%TYPE AS $$
BEGIN
	INSERT INTO LOGIN (email, password, person_id, authorization_level)
	VALUES (email, password, person_id, authorization_level);
	RETURN person_id;
END;
$$ LANGUAGE plpgsql;

CREATE OR REPLACE FUNCTION public.getUserType(
	IN id LOGIN.person_id%TYPE,
	OUT authLevel LOGIN.authorization_level%TYPE
) RETURNS INTEGER AS $$
BEGIN
	SELECT authorization_level INTO authLevel
	FROM LOGIN
	WHERE person_id = id;
	
	if authLevel IS null then
      authLevel=-1;
    end if;
	
	
END;
$$ LANGUAGE plpgsql;


CREATE OR REPLACE FUNCTION public.getCourses(
	IN branchName CLASSROOM.branch_name%TYPE
) RETURNS TABLE 
	(id COURSE.id%TYPE,
	language COURSE.language%TYPE,
	name COURSE.name%TYPE,
	price COURSE.price%TYPE)
AS $$
BEGIN

RETURN QUERY 
	SELECT *
	FROM COURSE c 
	WHERE c.id IN (
		SELECT course_no
		FROM LESSON l
		WHERE classroom_id IN (
			SELECT c.name
			FROM CLASSROOM c
			WHERE c.branch_name=branchName
		)
	);

END;
$$ LANGUAGE plpgsql; 
