import java.sql.*;
import static java.lang.System.exit;

public class DatabaseManager {
    private Connection conn;

    /*
    Initialize connection to the database
     */
    public void connect() {
        try {
            conn = DriverManager.getConnection(
                    "INSERT URL HERE",
                    "INSERT USER HERE",
                    "INSERT PASSWORD HERE"
            );
        } catch(SQLException e) {
            handleError(e);
        }
    }

    /*
    Disconnect from database upon exiting
     */
    public void disconnect() {
        try {
            if (conn != null && !conn.isClosed()) {
                conn.close();
            }
        } catch(SQLException e) {
            handleError(e);
        }
    }

    /*
    Run a database query with a return value
     */
    public <T> T query(String sql, ResultSetMapper<T> mapper, Object... params) {

        // if connection disconnects, attempt to reconnect
        try {
            if (conn == null || conn.isClosed()) {
                connect();
            }
        } catch(SQLException e) {
            handleError(e);
            exit(1);
        }

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            //Set the parameters
            for (int i = 0; i < params.length; i++) {
                pstmt.setObject(i + 1, params[i]);
            }

            //Execute and pass the ResultSet to the mapper
            try (ResultSet rs = pstmt.executeQuery()) {
                return mapper.map(rs);
            }
        } catch (SQLException e) {
            handleError(e);
            return null;
        }
    }

    /*
    Run a database query without a return value
     */
    public void executeUpdate(String sql, Object... params) {

        // if connection disconnects, attempt to reconnect
        try {
            if (conn == null || conn.isClosed()) {
                connect();
            }
        } catch(SQLException e) {
            handleError(e);
            exit(1);
        }

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            //Set the parameters
            for (int i = 0; i < params.length; i++) {
                pstmt.setObject(i + 1, params[i]);
            }

            //Execute the update (no ResultSet needed)
            pstmt.executeUpdate();

        } catch (SQLException e) {
           handleError(e);
        }
    }


    /*
    The interface that handles the ResultSet
     */
    @FunctionalInterface
    public interface ResultSetMapper<T> {
        T map(ResultSet rs) throws SQLException;
    }

    /*
    Handle any errors
     */
    private void handleError(SQLException e) {
        System.out.println("SQLException: " + e.getMessage());
    }
}

