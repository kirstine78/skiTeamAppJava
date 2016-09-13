-- Australian Ski Association
-- Kirstine B. Nielsen
-- 05.06.2016

-- delete database and rebuild new database

Drop Database If Exists ski;
Create Database If Not Exists ski;
Use ski;

Drop Table IF EXISTS Member;
Drop Table IF EXISTS Team;


CREATE TABLE IF NOT EXISTS Team
(
teamId			int(8)		not null,
teamName		varchar(30)	not null,
CONSTRAINT Team_pk PRIMARY KEY(teamId)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;


INSERT INTO Team (teamId, teamName)
VALUES (1, "Melbourne Ski Club");

INSERT INTO Team (teamId, teamName)
VALUES (2, "Sydney Ski Club");


CREATE TABLE IF NOT EXISTS Member
(
memberId		int(8)			not null,
memberType		char(1)			not null,
firstName		varchar(20)		not null,
lastName		varchar(30)		not null,
streetNo		varchar(15)		not null,
streetName		varchar(30)		not null,
suburb			varchar(30)		not null,
postCode		int(4)			not null,
mobile			varchar(10)		not null,
email 			varchar(30)		not null,
fee 			decimal(6,2)	not null,
totalRaces		int(5)			not null,
totalWins		int(5)			not null,
isFrontrunner 	char(1),
experience 		varchar(4),
disabilityType	varchar(20),
needFrontRunner	char(1),
teamId			int(8)			not null,
CONSTRAINT Member_pk PRIMARY KEY(memberId),
CONSTRAINT teamId_Member_fk FOREIGN KEY(teamId) REFERENCES Team(teamId),
CONSTRAINT memberType_ck CHECK (format IN ('A', 'D'))
)ENGINE=InnoDB DEFAULT CHARSET=utf8;


INSERT INTO Member (memberId, memberType, firstName, lastName, streetNo, streetName, suburb, postCode, mobile, email, fee, totalRaces, totalWins, isFrontrunner, experience, disabilityType, needFrontRunner, teamId)
VALUES (1, "A", "John", "Sanders", "28", "Auburn rd", "Hawthorn", 3122, "0258842259", "john_smith@mail.mail", 130.0, 0, 0, "Y", "High", null, null, 2);

INSERT INTO Member (memberId, memberType, firstName, lastName, streetNo, streetName, suburb, postCode, mobile, email, fee, totalRaces, totalWins, isFrontrunner, experience, disabilityType, needFrontRunner, teamId)
VALUES (2, "D", "Adam", "Ganzer", "33", "Burwood rd", "Hawthorn", 3122, "0659842125", "adam@yahoo.uk", 195.0, 6, 3, null, null, "Visually impaired", "Y", 1);
INSERT INTO Member (memberId, memberType, firstName, lastName, streetNo, streetName, suburb, postCode, mobile, email, fee, totalRaces, totalWins, isFrontrunner, experience, disabilityType, needFrontRunner, teamId) 
VALUES (3, "A", 'Peter', 'Jackson', '3', 'Wattletree st', 'Kew', 1233, '0123123123', 'some@email.com', 300.0, 5, 2, 'N', null, null, null, 1);

INSERT INTO Member (memberId, memberType, firstName, lastName, streetNo, streetName, suburb, postCode, mobile, email, fee, totalRaces, totalWins, isFrontrunner, experience, disabilityType, needFrontRunner, teamId) 
VALUES (4, "A", 'John', 'Jackson', '3', 'Wattletree st', 'Kew', 1233, '0123123123', 'some@email.com', 300.0, 5, 2, 'N', null, null, null, 2);
INSERT INTO Member (memberId, memberType, firstName, lastName, streetNo, streetName, suburb, postCode, mobile, email, fee, totalRaces, totalWins, isFrontrunner, experience, disabilityType, needFrontRunner, teamId) 
VALUES (5, "A", 'Donna', 'Ericsen', '3', 'Wattletree st', 'Kew', 1233, '0123123123', 'some@email.com', 300.0, 5, 2, 'N', null, null, null, 1);
INSERT INTO Member (memberId, memberType, firstName, lastName, streetNo, streetName, suburb, postCode, mobile, email, fee, totalRaces, totalWins, isFrontrunner, experience, disabilityType, needFrontRunner, teamId) 
VALUES (6, "A", 'Lach', 'Smith', '3', 'Wattletree st', 'Kew', 1233, '0123123123', 'some@email.com', 140.0, 5, 2, 'Y', "Low", null, null, 2);
INSERT INTO Member (memberId, memberType, firstName, lastName, streetNo, streetName, suburb, postCode, mobile, email, fee, totalRaces, totalWins, isFrontrunner, experience, disabilityType, needFrontRunner, teamId) 
VALUES (7, "D", "Charles", "Ganzer", "33", "Burwood rd", "Hawthorn", 3122, "0659842125", "adam@yahoo.uk", 195.0, 6, 3, null, null, "Visually impaired", "Y", 1);
INSERT INTO Member (memberId, memberType, firstName, lastName, streetNo, streetName, suburb, postCode, mobile, email, fee, totalRaces, totalWins, isFrontrunner, experience, disabilityType, needFrontRunner, teamId) 
VALUES (8, "D", "Karina", "Ganzer", "33", "Burwood rd", "Hawthorn", 3122, "0659842125", "adam@yahoo.uk", 105.0, 6, 3, null, null, "Sitting", null, 1);
INSERT INTO Member (memberId, memberType, firstName, lastName, streetNo, streetName, suburb, postCode, mobile, email, fee, totalRaces, totalWins, isFrontrunner, experience, disabilityType, needFrontRunner, teamId) 
VALUES (9, "A", 'Lola', 'Nelson', '3', 'Wattletree st', 'Kew', 1233, '0123123123', 'some@email.com', 300.0, 5, 2, 'N', null, null, null, 2);
INSERT INTO Member (memberId, memberType, firstName, lastName, streetNo, streetName, suburb, postCode, mobile, email, fee, totalRaces, totalWins, isFrontrunner, experience, disabilityType, needFrontRunner, teamId) 
VALUES (10, "A", 'Tessa', 'Schultz', '3', 'Wattletree st', 'Kew', 1233, '0123123123', 'some@email.com', 300.0, 5, 2, 'N', null, null, null, 1);
