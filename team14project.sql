CREATE DATABASE ShoutDB;
USE ShoutDB;

CREATE TABLE `User` (
	userID VARCHAR(10),
    firstName VARCHAR(10),
    lastName VARCHAR(15),
    `password` VARCHAR(10),
    PRIMARY KEY(userID)
);

CREATE TABLE Artist (
    artistName VARCHAR(25),
    userID VARCHAR(10),
    genre VARCHAR(15),
    PRIMARY KEY(artistName)
);

CREATE TABLE Account (
	accountNO INT,
    userID VARCHAR(10),
    creditCardNO INT,
    price DECIMAL(9, 2),
    PRIMARY KEY(accountNO)
);

CREATE TABLE Song (
	songID INT,
    albumID INT,
    songName VARCHAR(50),
    songLength TIME,
    PRIMARY KEY(songID, albumID)
);

CREATE TABLE Album (
	albumID INT, 
    artistName VARCHAR(25), 
    albumName VARCHAR(25), 
    albumGenre VARCHAR(15),
    PRIMARY KEY(albumID)
);

CREATE TABLE Favorite (
	favoriteNO INT,
    userID VARCHAR(10),
    artistName VARCHAR(25),
    songID INT,
    albumID INT,
    PRIMARY KEY(favoriteNO, userID)
);

CREATE TABLE Rating(
	ratingNO INT,  
    userID VARCHAR(10), 
    artistName VARCHAR(25),
    songID INT,
    albumID INT,
    rateValue INT,
    PRIMARY KEY(ratingNO, userID)
);

CREATE TABLE Review(
	reviewNO INT, 
    userID VARCHAR(10),
    artistName VARCHAR(25),
    songID INT,
    albumID INT,
    reviewStr VARCHAR(255),
    PRIMARY KEY(reviewNO, userID)
);

CREATE TABLE Shout(
	shoutNO INT, 
    artistName VARCHAR(25), 
    genre VARCHAR(15), 
    shout VARCHAR(255),
    PRIMARY KEY(shoutNO)
);

ALTER TABLE Artist
ADD CONSTRAINT `artist_fk` FOREIGN KEY (userID) REFERENCES `User`(userID);

ALTER TABLE Album
ADD CONSTRAINT `album_fk` FOREIGN KEY (artistName) REFERENCES Artist(artistName);

ALTER TABLE Account
ADD CONSTRAINT `acct_fk` FOREIGN KEY (userID) REFERENCES `User`(userID);

ALTER TABLE Song
ADD CONSTRAINT `song_fk2` FOREIGN KEY (albumID) REFERENCES Album(albumID);

ALTER TABLE Favorite
ADD CONSTRAINT `fav_fk` FOREIGN KEY (userID) REFERENCES `User`(userID);

ALTER TABLE Favorite
ADD CONSTRAINT `fav_fk2` FOREIGN KEY (artistName) REFERENCES Artist(artistName);

ALTER TABLE Favorite
ADD CONSTRAINT `fav_fk3` FOREIGN KEY (songID) REFERENCES Song(songID);

ALTER TABLE Favorite
ADD CONSTRAINT `fav_fk4` FOREIGN KEY (albumID) REFERENCES Album(albumID);

ALTER TABLE Rating
ADD CONSTRAINT `rat_fk` FOREIGN KEY (userID) REFERENCES `User`(userID);

ALTER TABLE Rating
ADD CONSTRAINT `rat_fk2` FOREIGN KEY (artistName) REFERENCES Artist(artistName);

ALTER TABLE Rating
ADD CONSTRAINT `rat_fk3` FOREIGN KEY (songID) REFERENCES Song(songID);

ALTER TABLE Rating
ADD CONSTRAINT `rat_fk4` FOREIGN KEY (albumID) REFERENCES Album(albumID);

ALTER TABLE Review
ADD CONSTRAINT `rev_fk` FOREIGN KEY (userID) REFERENCES `User`(userID);

ALTER TABLE Review
ADD CONSTRAINT `rev_fk2` FOREIGN KEY (artistName) REFERENCES Artist(artistName);

ALTER TABLE Review
ADD CONSTRAINT `rev_fk3` FOREIGN KEY (songID) REFERENCES Song(songID);

ALTER TABLE Review
ADD CONSTRAINT `rev_fk4` FOREIGN KEY (albumID) REFERENCES Album(albumID);

ALTER TABLE Shout
ADD CONSTRAINT `shout_fk` FOREIGN KEY (artistName) REFERENCES Artist(artistName);


INSERT INTO `User` VALUES
('cp123', 'Casey', 'Peterson', '123'),
('shins', 'The', 'Shins', '345'),
('dbowie', 'David', 'Bowie', '456'),
('gstarr', 'Gang', 'Starr', '789'),
('avett', 'Avett', 'Brothers', '987'),
('brooks', 'Brookes', 'Dunn', '654'),
('blink', 'Blink', '182', '321'),
('ledZ', 'Led', 'Zeppelin', '111'),
('jd345', 'John', 'Doe', '222'),
('jd987', 'Jane', 'Doe', '333');

INSERT INTO Artist VALUES
('The Shins', 'shins', 'Alternative'),
('David Bowie', 'dbowie', 'Rock'),
('Gang Starr', 'gstarr', 'Rap'),
('The Avett Brothers', 'avett', 'Alt-Country'),
('Brooks & Dunn', 'brooks', 'Country'),
('Blink-182', 'blink', 'Punk'),
('Led Zeppelin', 'ledZ', 'Classic-Rock');

INSERT INTO Album VALUES
(00001, 'The Shins', 'Chutes Too Narrow', 'Alternative'),
(00002, 'David Bowie', 'Ziggy Stardust', 'Rock'),
(00003, 'Gang Starr', 'Moment of Truth', 'Rap'),
(00004, 'The Avett Brothers', 'I and Love and You', 'Alt-Country'),
(00005, 'Brooks & Dunn', '1 and then Some', 'Country'),
(00006, 'Blink-182', 'Cheshire Cat', 'Punk'),
(00007, 'Led Zeppelin', 'Led Zeppelin II', 'Classic-Rock');

INSERT INTO Account VALUES
(01, 'cp123', 123456789, 5.00),
(02, 'jd345', 987654321, 5.00),
(03, 'jd987', 111222333, 5.00);

INSERT INTO Song VALUES
(1001, 00001, 'Kissing The Lipless', '3:18'),
(1002, 00001, 'Mine Not A High Horse', '3:28'),
(1003, 00001, 'So Says I', '2:49'),
(1001, 00002, 'Five Years', '4:42'),
(1002, 00002, 'Soul Love', '3:33'),
(1003, 00002, 'Moonage Daydream', '4:38'),
(1001, 00003, 'You Know My Steez', '4:07'),
(1002, 00003, 'Robbin Hood Theory', '3:44'),
(1003, 00003, 'Work', '2:56'),
(1001, 00004, 'I And Love And You', '5:00'),
(1002, 00004, 'January Wedding', '3:48'),
(1003, 00004, 'Head Full of Doubt', '4:48'),
(1001, 00005, 'Honky Tonk Stomp', '3:03'),
(1002, 00005, 'Brand New Man', '2:50'),
(1003, 00005, 'Hillbilly Deluxe', '4:20'),
(1001, 00006, 'Carousel', '3:55'),
(1002, 00006, 'M & M', '2:40'),
(1003, 00006, 'Fentoozler', '2:05'),
(1001, 00007, 'Whole Lotta Love', '5:34'),
(1002, 00007, 'What Is And What Should Never Be', '4:45'),
(1003, 00007, 'The Lemon Song', '6:19');

INSERT INTO Favorite VALUES
(1010, 'cp123', 'Led Zeppelin', NULL, NULL),
(1020, 'cp123', 'The Avett Brothers', 1001, 00004),
(1030, 'jd345', NULL, 1002, 00007),
(1040, 'shins', NULL, NULL, 00003);

INSERT INTO Rating VALUES
(2010, 'cp123', 'Gang Starr', NULL, NULL, 4),
(2020, 'cp123', NULL, 1001, NULL, 5),
(2030, 'blink', 'The Shins', NULL, 00001, 5),
(2040, 'jd987', 'The Avett Brothers', NULL, NULL, 4);

INSERT INTO Review VALUES
(3010, 'cp123', 'Led Zeppelin', NULL, NULL, 'One of the greatest bands of all time!'),
(3020, 'brooks', 'Gang Starr', 1003, 00003, 'I just do not understand rap'),
(3030, 'jd345', 'David Bowie', NULL, 00002, 'What is Ziggy Stardust anyway?');

INSERT INTO Shout VALUES
(4010, 'The Shins', 'Alternative', 'Hey everyone check out Led Zeppelin II, one of our favorite albums!'),
(4020, 'Brooks & Dunn', 'Country', 'We are really loving The Avett Brothers Alt-Country'),
(4030, 'Blink-182', 'Punk', 'Going on Tour next month! Opening with Carousel, go download it!'),
(4040, 'The Avett Brothers', 'Alt-Country', 'Alright shout out right back to Brooks & Dunn, they are a huge inspiration!');

