import com.musicdb.database.DatabaseManager;

public class NewUserCreation {

    /**
     * Creates a new user
     * @param user the username
     * @param pass the password
     * @param db the database manager
     * @return true if created successfully, false if otherwise (or in other words, if the user already exists)
     */
    public static boolean createNewUser(String user, String pass, DatabaseManager db) {

        String check = "SELECT user_id FROM USER WHERE username = ?";

        boolean exists = db.query(check, rs -> {
            return rs.next();
        }, user);

        String insert = "INSERT INTO USER (username, password) VALUES (?, ?)";

        if(exists) return false;
        db.executeUpdate(insert, user, pass);
        return true;

    }

}
