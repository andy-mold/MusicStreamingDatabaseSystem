-- After Transaction 1
SELECT * FROM USER WHERE user_id = 101;

-- After Transaction 2
SELECT * FROM PLAYLIST_SONG WHERE playlist_id = 2001 ORDER BY position;

-- After Transaction 3
SELECT s.title, al.title AS album, a.name AS artist
FROM SONG s
JOIN ALBUM al ON s.ALBUM_album_id = al.album_id
JOIN ARTIST a ON al.ARTIST_artist_id = a.artist_id
WHERE s.song_id = 4001;