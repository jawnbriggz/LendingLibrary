# Briggs John
# Leftist Lending Library
CREATE DATABASE library_db; 

GRANT ALL PRIVILEGES ON library_db.* TO ‘student’@’localhost’;
FLUSH PRIVILEGES;

use library_db;


CREATE TABLE Library(
	libraryName VARCHAR(30),
	address VARCHAR(25),
	numberOfBooks INT,
	genre VARCHAR(20),
	PRIMARY KEY(libraryName)
);

CREATE TABLE Books(
	title VARCHAR(30),
	author VARCHAR(30),
	genre VARCHAR(20),
	libraryName VARCHAR(30),
	availability VARCHAR(20) DEFAULT 'available',
	transactionID INT,
	PRIMARY KEY(title, libraryName)
);

CREATE TABLE Lending(
	transactionID INT,
	libraryName VARCHAR(30),
	dateBorrowed DATE,
	dateDue DATE,
	userName VARCHAR(20),
	PRIMARY KEY(transactionID)
);

CREATE TABLE User(
	userName VARCHAR(20),
	password VARCHAR(15),
	libraryName VARCHAR(100),
	PRIMARY KEY(userName)
);


#FIX FOREIGN KEY LATER
#ALTER TABLE Library
#ADD CONSTRAINT FK_gen
#FOREIGN KEY() REFERENCES Books(genre);

ALTER TABLE Books
ADD CONSTRAINT FK_libNa
FOREIGN KEY(libraryName) REFERENCES Library(libraryName) ON DELETE CASCADE;

ALTER TABLE Lending
ADD CONSTRAINT FK_usNa
FOREIGN KEY(userName) REFERENCES User(userName);

ALTER TABLE User
ADD CONSTRAINT FK_placeNa
FOREIGN KEY(libraryName) REFERENCES Library(libraryName);



#USER INSERTIONS
INSERT INTO User(userName, password, libraryName) VALUES('jdoe', 'password', 'jdoes bookhouse');
INSERT INTO User(userName, password, libraryName) VALUES('JordanPickles420','samizdat', 'The Observatory');
INSERT INTO User(userName, password, libraryName) VALUES('randFan360', 'spaceface', 'The hidey-hole');
INSERT INTO User(userName, password, libraryName) VALUES('JakeTheDonkey','horsewhisperer', 'Jakes place');
INSERT INTO User(userName, password, libraryName) VALUES('SportsFan', 'february22', 'Josies apartment');

#BOOK INSERTIONS         (title, author, genre, libraryName, availability, transactionID) --LIBERTARIAN
INSERT INTO Books VALUES ('Anarchy, State, and Utopia', 'Nozick, Robert', 'political science', 'The hidey-hole', 'available', 0);
INSERT INTO Books VALUES ('Atlas Shrugged', 'Rand, Ayn', 'fiction', 'The hidey-hole', 'checked out', 432);
INSERT INTO Books VALUES ('The Meaning of Conservatism', 'Scruton, Roger', 'political science', 'The hidey-hole', 'available', 0);
INSERT INTO Books VALUES ('The Wealth of Nations', 'Smith, Adam', 'political science', 'The hidey-hole', 'checked out', 871);
INSERT INTO Books VALUES ('Civilization and Capitalism', 'Braudel, Fernand', 'history', 'The hidey-hole', 'available', 0);
INSERT INTO Books VALUES ('Explaining Postmodernism', 'Hicks, Stephen', 'political science', 'The hidey-hole', 'available', 0);
#BOOK INSERTIONS         (title, author, genre, libraryName, availability, transactionID) --MARXIST
INSERT INTO Books VALUES ('Capital', 'Marx, Karl', 'political science', 'The Observatory', 'checked out', 851);
INSERT INTO Books VALUES ('Wretched of the Earth', 'Fanon, Frantz', 'political science', 'The Observatory', 'available', 0);
INSERT INTO Books VALUES ('Phenomenology of Spirit', 'Hegel, Georg', 'philosophy', 'The Observatory', 'available', 0);
INSERT INTO Books VALUES ('Settlers', 'Sakai, J.', 'history', 'The Observatory', 'checked out', 546);
INSERT INTO Books VALUES ('Quotations from Chairman Mao', 'Zedong, Mao', 'political science', 'The Observatory', 'checked out', 253);
INSERT INTO Books VALUES ('Caliban and the Witch', 'Federici, Silvia', 'political science', 'The Observatory', 'available', 0);
#BOOK INSERTIONS         (title, author, genre, libraryName, availability, transactionID) --POSTMODERNIST
INSERT INTO Books VALUES ('A Thousand Plateaus', 'Deleuze, Gilles', 'philosophy', 'jdoes bookhouse', 'checked out', 867);
INSERT INTO Books VALUES ('Discipline and Punish', 'Foucault, Michel', 'philosophy', 'jdoes bookhouse', 'checked out', 456);
INSERT INTO Books VALUES ('Course in General Linguistics', 'Saussure, Ferdinand', 'linguistics', 'jdoes bookhouse', 'available', 0);
INSERT INTO Books VALUES ('Gender Trouble', 'Butler, Judith', 'philosophy', 'jdoes bookhouse', 'available', 0);
INSERT INTO Books VALUES ('Of Grammatology', 'Derrida, Jacques', 'philosophy', 'jdoes bookhouse', 'available', 0);
INSERT INTO Books VALUES ('Negative Dialectics', 'Adorno, Theodor', 'philosophy', 'jdoes bookhouse', 'checked out', 765);
#BOOK INSERTIONS         (title, author, genre, libraryName, availability, transactionID) --CENTRIST
INSERT INTO Books VALUES ('A Theory of Justice', 'Rawls, John', 'political science', 'Josies apartment', 'available', 0);
INSERT INTO Books VALUES ('The General Theory of ...', 'Keynes, John', 'economics', 'Josies apartment', 'available', 0);
INSERT INTO Books VALUES ('A World Lit Only By Fire', 'Manchester, William', 'history', 'Josies apartment', 'checked out', 543);
INSERT INTO Books VALUES ('Critique of Pure Reason', 'Kant, Immanuel', 'philosophy', 'Josies apartment', 'available', 0);
INSERT INTO Books VALUES ('The Protestant Ethic', 'Weber, Max', 'sociology', 'Josies apartment', 'checked out', 234);
INSERT INTO Books VALUES ('Decline and Fall of the Roman', 'Gibbon, Edward', 'history', 'Josies apartment', 'available', 0);
#BOOK INSERTIONS         (title, author, genre, libraryName, availability, transactionID)
INSERT INTO Books VALUES ('Against Method', 'Feyerabend, Paul', 'philosophy', 'Jakes place', 'available', 0);
INSERT INTO Books VALUES ('Manufacturing Consent', 'Chomsky, Noam', 'political science', 'Jakes place', 'available', 0);
INSERT INTO Books VALUES ('Peoples History of the U.S.', 'Zinn, Howard', 'history', 'Jakes place', 'checked out', 989);
INSERT INTO Books VALUES ('The Haymarket Tragedy', 'Avrich, Paul', 'history', 'Jakes place', 'available', 0);
INSERT INTO Books VALUES ('Living My Life', 'Goldman, Emma', 'history', 'Jakes place', 'checked out', 654);
INSERT INTO Books VALUES ('Anarchism', 'Guerin, Daniel', 'political science', 'Jakes place', 'available', 0);

#LIBRARY INSERTIONS        libraryName, address, numOfBooks, popGenre
INSERT INTO Library VALUES('The hidey-hole', '123 Fake St', 6, 'political science');
INSERT INTO Library VALUES('The Observatory', '3604 n Lincoln ave', 6, 'political science');
INSERT INTO Library VALUES('jdoes bookhouse', '1718 w 18th pl', 6, 'philosophy');
INSERT INTO Library VALUES('Josies apartment', '1609 n Ashland', 6, 'history');
INSERT INTO Library VALUES('Jakes place', '3303 s Wallace', 6, 'history');

#LENDINGS INSERTIONS       transactionID, libraryName, dateBorrowed, dateDue,, borrower   DATE FORMAT:  YYYY-MM-DD
INSERT INTO Lending VALUES(432, 'The hidey-hole', '2018-07-22', '2018-08-22', 'jdoe');
INSERT INTO Lending VALUES(871, 'The hidey-hole', '2018-07-14', '2018-08-14', 'JakeTheDonkey');
INSERT INTO Lending VALUES(851, 'The Observatory', '2018-07-10', '2018-08-10', 'SportsFan');
INSERT INTO Lending VALUES(253, 'The Observatory', '2018-07-10', '2018-08-10', 'SportsFan');
INSERT INTO Lending VALUES(867, 'jdoes bookhouse', '2018-07-09', '2018-08-09', 'JordanPickles420');
INSERT INTO Lending VALUES(456, 'jdoes bookhouse', '2018-07-08', '2018-08-08', 'JakeTheDonkey');
INSERT INTO Lending VALUES(765, 'jdoes bookhouse', '2018-07-16', '2018-08-16', 'randFan360');
INSERT INTO Lending VALUES(543, 'Josies apartment', '2018-07-20', '2018-08-20', 'JordanPickles420');
INSERT INTO Lending VALUES(234, 'Josies apartment', '2018-07-10', '2018-08-10', 'jdoe');
INSERT INTO Lending VALUES(989, 'Jakes place', '2018-07-13', '2018-08-13', 'jdoe');
INSERT INTO Lending VALUES(654, 'Jakes place', '2018-07-25', '2018-08-25', 'randFan360');


