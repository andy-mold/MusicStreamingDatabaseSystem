START TRANSACTION;

INSERT INTO `USER` (user_id, username, email, password, country, account_creation_date)
VALUES (101, 'ex_name', 'name@email.com', 'pass123', 'USA', NOW());

INSERT INTO PLAYLIST (playlist_id, name, description, creation_date, visibility, USER_user_id)
VALUES (1001, 'Ex_Playlist', 'Default playlist', NOW(), 'Private', 101);

INSERT INTO PLAYLIST_SONG (playlist_id, song_id, date_added, position)
VALUES 
(1001, 301, NOW(), 1),
(1001, 302, NOW(), 2);

COMMIT;


START TRANSACTION;

DELETE FROM PLAYLIST_SONG
WHERE playlist_id IN (
    SELECT playlist_id FROM PLAYLIST WHERE USER_user_id = 101
);

DELETE FROM PLAYLIST
WHERE USER_user_id = 101;

DELETE FROM FOLLOW
WHERE user_id = 101;

DELETE FROM `USER`
WHERE user_id = 101;

COMMIT;


START TRANSACTION;

INSERT INTO `USER` (user_id, username, email, password, country, account_creation_date)
VALUES (101, 'person1', 'person1@email.com', 'pass123', 'USA', NOW());

INSERT INTO PLAYLIST (playlist_id, name, description, creation_date, visibility, USER_user_id)
VALUES (2001, 'Person1_Playlist', 'My new playlist', NOW(), 'Public', 101);

INSERT INTO PLAYLIST_SONG (playlist_id, song_id, date_added, position)
VALUES 
(2001, 301, NOW(), 1),
(2001, 302, NOW(), 2),
(2001, 303, NOW(), 3);

COMMIT;


START TRANSACTION;

UPDATE PLAYLIST_SONG
SET position = CASE
    WHEN song_id = 302 THEN 1
    WHEN song_id = 303 THEN 2
    WHEN song_id = 301 THEN 3
END
WHERE playlist_id = 2001;

COMMIT;


START TRANSACTION;

INSERT INTO ALBUM (album_id, title, release_date, num_tracks, ARTIST_artist_id)
VALUES (3001, 'New Album', CURDATE(), 1, 101);

INSERT INTO SONG (song_id, title, duration, release_date, ALBUM_album_id)
VALUES (4001, 'Brand New Song', 200, CURDATE(), 3001);

COMMIT;
