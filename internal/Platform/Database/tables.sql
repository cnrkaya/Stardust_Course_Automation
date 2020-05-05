
CREATE TABLE PERSON(
	id char(11) PRIMARY KEY,
	fname varchar(30),
	lname varchar(30),
	phone_number text[],
	home_number text[],
	home_addr text,
	work_addr text
);	


CREATE TABLE LOGIN(
	email varchar(64) NOT NULL,
	password char(64) NOT NULL,
	person_id char(11),			                       -- references to Turkish Identity number
	--NOT NULL                                  	   -- optional, enable if every login is associated with a person 
	authorization_level int,         		           -- in order to create diff between instructor, registrar, and admin

	FOREIGN KEY (person_id) REFERENCES Person(id)  							
		ON DELETE SET NULL	 
);


-- https://stackoverflow.com/questions/18656528/how-do-i-encrypt-passwords-with-postgresql
CREATE TABLE INVOICE(
	id char(11) PRIMARY KEY,							
	invoice_date date NOT NULL,
	total int NOT NULL,	
	executo_id char(11) 								-- references to Turkish Identity number
		NOT NULL 		 
		DEFAULT '00000000000',
	
	FOREIGN KEY (executo_id) REFERENCES Person(id)
		ON DELETE SET DEFAULT  						    -- TRIGGER ON DELETE log_invoice_delete()
);


CREATE TABLE PAYMENT(
	id char(11) PRIMARY KEY,
	pay_date date NOT NULL,
	amount varchar(10) NOT NULL,
	invoice_id char(11) 
		NOT NULL 
		DEFAULT 0,
	
	FOREIGN KEY (invoice_id) REFERENCES Invoice(id)																
		ON DELETE SET DEFAULT							-- TRIGGER ON DELETE log_invoice_delete()
		ON UPDATE CASCADE
);


CREATE TABLE BRANCH(
	name varchar(31) PRIMARY KEY,		
	phone_number text[],			    
	fax text[],
	address text NOT NULL,
	public_transport text[],			
	facilities text[],
	private_transport text[]			

);


CREATE TABLE COURSE(
	id char(11) PRIMARY KEY,
	language varchar(15) NOT NULL,
	name varchar(15),
	price varchar(10) NOT NULL
);


CREATE TABLE SALES(
	invoice_number char(11)		
		DEFAULT 0,		
	customer_id char(11)		
		DEFAULT '00000000000',		
	course_id char(11),
	total int,
	max_no_of_payments int,
	
	PRIMARY KEY(invoice_number, customer_id),
	FOREIGN KEY (invoice_number) REFERENCES Invoice(id)
		ON DELETE SET DEFAULT
		ON UPDATE CASCADE,						-- TRIGGER ON DELETE log_sales_delete()
	FOREIGN KEY (customer_id) REFERENCES Person(id)
		ON DELETE SET DEFAULT
		ON UPDATE CASCADE,						-- TRIGGER ON DELETE log_customer_delete() 
	FOREIGN KEY (course_id) REFERENCES Course(id)
);

CREATE TABLE CLASSROOM(
	name varchar(20) PRIMARY KEY, 
	capacity int NOT NULL,
	branch_name varchar(31) 
		NOT NULL,
	
	FOREIGN KEY (branch_name) REFERENCES Branch(name)
		ON DELETE CASCADE
		ON UPDATE CASCADE
);


CREATE TABLE LESSON(
	name text,
	course_no char(11) 
		NOT NULL 		
		DEFAULT 0,	 
	instructor_id char(11)
		NOT NULL 
		DEFAULT '00000000000',						
	classroom_id varchar(20) 						 -- no not null constraint, class may not have classroom, for ex online classes
		DEFAULT 0,
	lesson_date varchar(20),
	lesson_ts varchar(20),
	
	PRIMARY KEY(name, course_no),
	FOREIGN KEY (course_no) REFERENCES Course(id)
		ON DELETE SET DEFAULT
		ON UPDATE CASCADE,   						 -- TRIGGER ON DELETE log_lesson_course_delete()
	FOREIGN KEY (instructor_id) REFERENCES Person(id)		
		ON DELETE SET DEFAULT
		ON UPDATE CASCADE,							 -- TRIGGER ON DELETE log_lesson_instructor_delete()
	FOREIGN KEY (classroom_id) REFERENCES Classroom(name)		
		ON DELETE SET NULL
		ON UPDATE CASCADE	
);


CREATE TABLE INSTRUCTOR(
	id char(11),	
	start_date date NOT NULL,
	known_languages text[] NOT NULL,
	pworking_hours char(42),		      -- possible working hours, 168bit map
	
	PRIMARY KEY(id),
	FOREIGN KEY (id) REFERENCES Person(id) 
		ON DELETE CASCADE 
		ON UPDATE CASCADE
);	


CREATE TABLE INSTRUCTOR_CAN_WORK_IN(
	branch_name varchar(31) 
		NOT NULL,		
	instructor_id char(11) 
		NOT NULL,

	PRIMARY KEY(branch_name, instructor_id),
	FOREIGN KEY (branch_name) REFERENCES Branch(name) 
		ON DELETE CASCADE 
		ON UPDATE CASCADE,
	FOREIGN KEY (instructor_id) REFERENCES Instructor(id) 
		ON DELETE CASCADE
);


CREATE TABLE WORKS_ON(
	branch_name varchar(31) 
		NOT NULL,		
	person_id char(11) 
		NOT NULL,
		
	PRIMARY KEY(branch_name, person_id),
	FOREIGN KEY (branch_name) REFERENCES Branch(name) 
		ON DELETE CASCADE 
		ON UPDATE CASCADE,
	FOREIGN KEY (person_id) REFERENCES Person(id) 
		ON DELETE CASCADE
);