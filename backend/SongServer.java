import com.sun.net.httpserver.*;
import java.io.*;
import java.net.InetSocketAddress;
import java.util.*;
import com.google.gson.Gson;

public class SongServer {
    public static void main(String[] args) throws Exception {
        HttpServer server = HttpServer.create(new InetSocketAddress(8080), 0);

        server.createContext("/songs", (exchange -> {
            // Allow CORS
            exchange.getResponseHeaders().add("Access-Control-Allow-Origin", "*");
            exchange.getResponseHeaders().add("Access-Control-Allow-Methods", "GET, POST, OPTIONS");
            exchange.getResponseHeaders().add("Access-Control-Allow-Headers", "Content-Type");

            if ("OPTIONS".equals(exchange.getRequestMethod())) {
                // Preflight request for CORS
                exchange.sendResponseHeaders(204, -1); 
                return;
            }

            if ("GET".equals(exchange.getRequestMethod())) {
                SongDAO dao = new SongDAO();
                List<Song> songs = dao.getAllSongs();

                String json = new Gson().toJson(songs);
                exchange.getResponseHeaders().set("Content-Type", "application/json");
                exchange.sendResponseHeaders(200, json.getBytes().length);
                try (OutputStream os = exchange.getResponseBody()) {
                    os.write(json.getBytes());
                }
            }
        }));

        server.setExecutor(null);
        server.start();
        System.out.println("Server running at http://localhost:8080");
    }
}