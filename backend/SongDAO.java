import java.sql.*;
import java.util.*;

public class SongDAO {
    public void addSong(Song song) {
        try (Connection con = DBConnection.connect()) {
            String query = "INSERT INTO songs (title, artist, album, genre, year) VALUES (?,?,?,?,?)";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, song.getTitle());
            ps.setString(2, song.getArtist());
            ps.setString(3, song.getAlbum());
            ps.setString(4, song.getGenre());
            ps.setInt(5, song.getYear());
            ps.executeUpdate();
            System.out.println("Song added!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<Song> getAllSongs() {
        List<Song> list = new ArrayList<>();
        try (Connection con = DBConnection.connect()) {
            String query = "SELECT * FROM songs";
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                Song song = new Song(
                    rs.getString("title"),
                    rs.getString("artist"),
                    rs.getString("album"),
                    rs.getString("genre"),
                    rs.getInt("year")
                );
                list.add(song);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
}
