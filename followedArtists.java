package com.musicdb.for_submission;
import com.musicdb.database.DatabaseManager;

import java.util.LinkedList;
import java.util.List;

public class followedArtists {

    public List<String> followedArtists(int user_id, DatabaseManager db) {

        List<String> returnList = new LinkedList<>();

        String sql = "SELECT ARTIST.name FROM ARTIST " +
                "JOIN FOLLOW ON ARTIST.id = FOLLOW.artist_id " +
                "WHERE FOLLOW.user_id = ?";

        db.query(sql, rs -> {
            while (rs.next()) {
                returnList.add(rs.getString(1));
            }
            return null;
        }, user_id);

        return returnList;

    }

}
