import com.musicdb.database.DatabaseManager;

import java.util.Objects;

public class LoginValidation {
    /**
     * Validates user credentials against the database
     * @param user username
     * @param pass password
     * @param db the database manager
     * @return true if credentials are valid, false otherwise
     */
    public static boolean validateCredentials(String user, String pass, DatabaseManager db) {

        if (user == null || pass == null) return false;

        String sql = "SELECT id FROM USER WHERE username = ? AND password = ?";

            String userId = db.query(sql, rs -> {
                if (rs.next()) {
                    return rs.getString(1);
                }
                return null;
            }, user, pass);

            userId = null ? return false : return true;
    }
}

