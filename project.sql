# Briggs John
# Leftist Lending Library
CREATE DATABASE library_db; 

GRANT ALL PRIVILEGES ON library_db.* TO ‘student’@’localhost’;
FLUSH PRIVILEGES;

use library_db;

#SQL COMMANDS (CREATEs, INSERTs, ALTER TABLEs) WITH COMMENTS.

CREATE TABLE Library(
	libraryName VARCHAR(10),
	address VARCHAR(20),
	numberOfBooks INT,
	genre VARCHAR(10),
	PRIMARY KEY(libraryName)
	FOREIGN KEY(genre) REFERENCES Books
);

CREATE TABLE Books(
	title VARCHAR(10),
	author VARCHAR(10),
	libraryName VARCHAR(10),
	availability BOOLEAN DEFAULT true,
	PRIMARY KEY(title, libraryName),
	FOREIGN KEY(libraryName) REFERENCES Library ON DELETE CASCADE
);

CREATE TABLE Lending(
	transactionID INT,
	libraryName VARCHAR(10),
	dateBorrowed DATETIME,
	dateDue DATETIME,
	dateReturned DATETIME,
	userName VARCHAR(10),
	PRIMARY KEY(transactionID),
	FOREIGN KEY(userName) REFERENCES User
);

CREATE TABLE User(
	userName VARCHAR(10),
	password VARCHAR(10),
	libraryName VARCHAR(10),
	PRIMARY KEY(userName),
	FOREIGN KEY(libraryName) REFERENCES Library
);