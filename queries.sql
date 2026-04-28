SELECT s.title, a.name AS artist_name, s.release_date
FROM USER u
JOIN FOLLOW f ON u.user_id = f.user_id
JOIN ARTIST a ON f.artist_id = a.artist_id
JOIN ALBUM al ON a.artist_id = al.ARTIST_artist_id
JOIN SONG s ON al.album_id = s.ALBUM_album_id
WHERE u.user_id = 1
AND s.release_date >= DATE_SUB(CURDATE(), INTERVAL 1 YEAR);

SELECT u.user_id, u.username
FROM USER u
JOIN FOLLOW f ON u.user_id = f.user_id
JOIN ARTIST a ON f.artist_id = a.artist_id
WHERE a.name = 'The Neon Lights'
AND u.user_id NOT IN (
    SELECT p.USER_user_id
    FROM PLAYLIST p
    JOIN PLAYLIST_SONG ps ON p.playlist_id = ps.playlist_id
    JOIN SONG s ON ps.song_id = s.song_id
    JOIN ALBUM al ON s.ALBUM_album_id = al.album_id
    WHERE al.ARTIST_artist_id = a.artist_id
);

SELECT u.user_id, u.username
FROM USER u
JOIN PLAYLIST p ON u.user_id = p.USER_user_id
GROUP BY u.user_id, u.username
HAVING COUNT(p.playlist_id) > 1;