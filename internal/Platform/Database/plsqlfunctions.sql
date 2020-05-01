

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
CREATE FUNCTION deleteLesson (name_in text)
RETURNS VOID AS '

BEGIN
DELETE FROM Lesson 
WHERE name=name_in;
END;
'LANGUAGE plpgsql; 

---------
CREATE FUNCTION deleteBranch (name_in text)
RETURNS VOID AS '

BEGIN
DELETE  FROM Branch 
WHERE name=name_in;
END;
' LANGUAGE plpgsql; 
---------

CREATE FUNCTION deleteCourse(CourseId_in char)
RETURNS VOID AS '

BEGIN
DELETE FROM Course 
WHERE id=CourseId_in;
END;
' LANGUAGE plpgsql; 
--------

CREATE FUNCTION deleteClassroom(ClassroomId_in char)
RETURNS VOID AS $$

BEGIN
DELETE FROM  Classroom
WHERE id=ClassroomId_in;
END;
$$ LANGUAGE plpgsql; 
-------

CREATE FUNCTION addBranch(name_in char, phone_number_in text[], fax_in text[], address_in text, public_transport_in text[], private_transport_in text[], facilties_in text[], academy_name_in text)

RETURNS VOID AS $$
BEGIN

INSERT INTO Branch(name, phone_number, fax, address, public_transport, private_transport, facilities, academy_name)
VALUES(name_in, phone_number_in, fax_in, address_in, public_transport_in, private_transport_in, facilities_in, academy_name_in);
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

------
CREATE FUNCTION addCourse(id_in int, name varchar, language_in varchar, price_in int)
RETURNS VOID AS $$
BEGIN
INSERT INTO Course(id, name, language_in, price_in)
VALUES (id_in, name_in, language_in, price_in);
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
CREATE FUNCTION addLesson(name_in text, course_no_in int, instructor_id_in char, classroom_id_in int)
RETURNS VOID AS $$

BEGIN

INSERT INTO Lesson (name, course_no, instructor_id, classroom_id)
VALUES(name_in , course_no_in , instructor_id_in , classroom_id_in);

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
