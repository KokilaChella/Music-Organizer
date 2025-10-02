import java.sql.*;

public class DBConnection {
    public static Connection connect() {
        try {
            return DriverManager.getConnection("jdbc:mysql://localhost:3306/musicdb", "root", "password");
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
