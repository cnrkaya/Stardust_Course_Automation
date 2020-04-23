CREATE TABLE Login(
	email char(64) NOT NULL,
	password char(64) NOT NULL,
	person_id char(11) 								-- references to Turkish Identity number
		--NOT NULL                                  --  
		FOREIGN KEY REFERENCES Person(id)  							
		ON DELETE SET NULL,
	authorization_level int,						-- in order to create diff between instructor, registrar, and admin 
);


-- https://stackoverflow.com/questions/18656528/how-do-i-encrypt-passwords-with-postgresql

CREATE TABLE Payment(
	id int PRIMARY KEY,
	pay_date date NOT NULL,
	amount int NOT NULL,
	invoice_id int 
		NOT NULL 
		FOREIGN KEY REFERENCES Invoice(id)
		DEFAULT 0														
		ON DELETE SET DEFAULT							-- TRIGGER ON DELETE log_invoice_delete()
		ON UPDATE CASCADE
);

CREATE TABLE Invoice(
	id int PRIMARY KEY,							
	invoice_date date NOT NULL,
	total int NOT NULL,	
	executo_id char(11) 								-- references to Turkish Identity number
		NOT NULL 
		FOREIGN KEY REFERENCES Person(id) 
		DEFAULT '00000000000' 							
		ON DELETE SET DEFAULT  							-- TRIGGER ON DELETE log_invoice_delete()
);

CREATE TABLE Branch(
	name varchar(31) PRIMARY KEY,		-- Reasonable?
	phone_number text[],			-- since multiple phone numbers may exists, array more reasonable?
	fax text[],
	address NOT NULL text,
	public_transport text[],			-- Public Transportation
	private_transport text[],			
	facilities text[]
);

CREATE TABLE Person(
	id char(11) PRIMARY KEY,
	fname varchar,
	lname varchar,
	phone_number text[],
	home_number text[],
	home_addr text,
	work_addr text
);	

CREATE TABLE Sales(
	invoice_number int 
		FOREIGN KEY REFERENCES Invoice(id)
		DEFAULT 0
		ON DELETE SET DEFAULT
		ON UPDATE CASCADE,						-- TRIGGER ON DELETE log_sales_delete()
	customer_id char(11) 
		FOREIGN KEY REFERENCES Person(id)
		DEFAULT '00000000000'
		ON DELETE SET DEFAULT
		ON UPDATE CASCADE,						-- TRIGGER ON DELETE log_customer_delete() 
	course_id int FOREIGN KEY REFERENCES Course(id),
	total int,
	max_no_of_payments int,
	PRIMARY KEY(invoice_number, customer_id)
);

CREATE TABLE Course(
	id int PRIMARY KEY,
	language NOT NULL varchar,
	name varchar,
	price int
);

CREATE TABLE Lesson(
	name text,
	course_no int 
		NOT NULL 
		FOREIGN KEY REFERENCES Course(id)
		DEFAULT 0
		ON DELETE SET DEFAULT
		ON UPDATE CASCADE,						-- TRIGGER ON DELETE log_lesson_course_delete() 
	instructor_id char(11)
		NOT NULL 
		FOREIGN KEY REFERENCES Person(id)
		DEFAULT '00000000000'
		ON DELETE SET DEFAULT
		ON UPDATE CASCADE,						-- TRIGGER ON DELETE log_lesson_instructor_delete() ,
	classroom_id varchar 							-- no not null constraint, class may not have classroom, for ex online classes
		FOREIGN KEY REFERENCES Classroom(id)
		DEFAULT 0
		ON DELETE SET NULL
		ON UPDATE CASCADE,
	lesson_date date,
	lesson_ts timestamp,
	PRIMARY KEY(name, course_no),
);

CREATE TABLE Classroom(
	name varchar PRIMARY KEY, 
	capacity NOT NULL int,
	branch_name char(31) 
		NOT NULL 
		FOREIGN KEY REFERENCES Branch(name)
		ON DELETE CASCADE
		ON UPDATE CASCADE
);

------------------- Zamanda Çalışabilir tablosu silindi, öğretmene 'possible_working_hours text' eklending

CREATE TABLE Instructor(
	id char(11) 
		NOT NULL 
		FOREIGN KEY REFERENCES Person(id) 
		ON DELETE CASCADE 
		ON UPDATE CASCADE,
	start_date NOT NULL date,
	known_languages NOT NULL text[],
	pworking_hours char(168),		-- possible working hours, 168bit map
	PRIMARY KEY(id)
);	

CREATE TABLE InstructorCanWorkIn(
	branch_name char(31) 
		NOT NULL 
		FOREIGN KEY REFERENCES Branch(name) 
		ON DELETE CASCADE 
		ON UPDATE CASCADE,
	instructor_id char(11) 
		NOT NULL 
		FOREIGN KEY REFERENCES Instructor(id) 
		ON DELETE CASCADE,
	FOREIGN KEY(branch_name, Instructor_id)
);

CREATE TABLE works_in(
	branch_name char(31) 
		NOT NULL 
		FOREIGN KEY REFERENCES Branch(id) 
		ON DELETE CASCADE 
		ON UPDATE CASCADE,
	person_id char(11) 
		NOT NULL 
		FOREIGN KEY REFERENCES Person(id) 
		ON DELETE CASCADE,
	PRIMARY KEY(branch_name, person_id)
);


