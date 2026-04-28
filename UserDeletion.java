import com.musicdb.database.DatabaseManager;

public class UserDeletion {

    /**
     * Deletes a user from the database
     * @param user the username to delete
     * @param db the database manager
     */
    public static void deleteUser(String user, DatabaseManager db) {
        String sql = "DELETE FROM USER WHERE username = ?";

        db.executeUpdate(sql, user);

    }
}
