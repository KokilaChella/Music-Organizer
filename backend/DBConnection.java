import java.sql.*;

public class DBConnection {
    private static final String URL = "jdbc:mysql://127.0.0.1:3306/musicdb";

    private static final String USER = "root"; // or your username
    private static final String PASS = "";     // or your password

    public static void main(String[] args) {
    Connection c = DBConnection.connect();
    if(c != null) System.out.println("Connected!");
    else System.out.println("Failed to connect.");
}


    public static Connection connect() {
        try {
            // Load the MySQL driver
            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection(URL, USER, PASS);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
