import com.sun.net.httpserver.HttpServer;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpExchange;
import java.io.*;
import java.net.InetSocketAddress;
import java.sql.*;
import java.util.*;

public class SongServer {
    public static void main(String[] args) throws Exception {
        HttpServer server = HttpServer.create(new InetSocketAddress(8080), 0);

        server.createContext("/songs", new SongHandler());
        server.setExecutor(null);
        server.start();

        System.out.println("Server started at http://localhost:8080/");
    }

    static class SongHandler implements HttpHandler {
        public void handle(HttpExchange exchange) throws IOException {
            String response = getSongsFromDB();
            exchange.getResponseHeaders().set("Content-Type", "application/json");
            exchange.sendResponseHeaders(200, response.getBytes().length);
            OutputStream os = exchange.getResponseBody();
            os.write(response.getBytes());
            os.close();
        }

        private String getSongsFromDB() {
            StringBuilder json = new StringBuilder("[");
            try {
                Connection conn = DriverManager.getConnection(
                        "jdbc:mysql://localhost:3306/musicdb", "root", "password");

                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery("SELECT * FROM songs");

                while (rs.next()) {
                    if (json.length() > 1) json.append(",");
                    json.append("{\"id\":").append(rs.getInt("id"))
                        .append(",\"title\":\"").append(rs.getString("title"))
                        .append("\",\"artist\":\"").append(rs.getString("artist"))
                        .append("\",\"url\":\"").append(rs.getString("url"))
                        .append("\"}");
                }

                conn.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            json.append("]");
            return json.toString();
        }
    }
}
