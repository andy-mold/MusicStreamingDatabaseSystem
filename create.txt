CREATE SCHEMA MusicStreamingDatabaseSystem;
USE MusicStreamingDatabaseSystem;

CREATE TABLE USER (
	user_id INT PRIMARY KEY,
	username VARCHAR(45) NOT NULL,
	email VARCHAR(45) NOT NULL UNIQUE,
	password VARCHAR(45) NOT NULL,
	country VARCHAR(45),
	account_creation_date DATETIME
);

CREATE TABLE ARTIST (
	artist_id INT PRIMARY KEY,
	name VARCHAR(45) NOT NULL,
	genre VARCHAR(45) NOT NULL,
	country VARCHAR(45)
);

CREATE TABLE ALBUM (
    album_id INT PRIMARY KEY,
    title VARCHAR(45) NOT NULL,
    release_date DATE,
    num_tracks INT,
    ARTIST_artist_id INT,
    FOREIGN KEY (ARTIST_artist_id) REFERENCES ARTIST(artist_id)
);

CREATE TABLE SONG (
    song_id INT PRIMARY KEY,
    title VARCHAR(45) NOT NULL,
    duration INT,
    release_date DATE,
    ALBUM_album_id INT,
    UNIQUE (song_id),
    FOREIGN KEY (ALBUM_album_id) REFERENCES ALBUM(album_id)
);

CREATE TABLE FOLLOW (
	follow_date DATETIME,
	user_id INT,
	artist_id INT,
	FOREIGN KEY (user_id) REFERENCES USER(user_id),
	FOREIGN KEY (artist_id) REFERENCES ARTIST(artist_id)
);

CREATE TABLE PLAYLIST (
	playlist_id INT PRIMARY KEY,
	name VARCHAR(45) NOT NULL,
	description VARCHAR(120),
	creation_date DATETIME NOT NULL,
	visibility ENUM('Public','Private','Friends_Only') NOT NULL,
	USER_user_id INT,
	FOREIGN KEY (USER_user_id) REFERENCES USER(user_id)
);

CREATE TABLE PLAYLIST_SONG (
	playlist_id INT,
	song_id INT,
	date_added DATETIME NOT NULL,
	position INT NOT NULL,
	PRIMARY KEY (playlist_id, song_id),
	FOREIGN KEY (playlist_id) REFERENCES PLAYLIST (playlist_id),
	FOREIGN KEY (song_id) REFERENCES SONG (song_id)
);
	