import java.sql.*;
import java.util.*;

public class SongDAO {
    public static void main(String args[]){
        SongDAO dao=new SongDAO();
        List<Song>songs=dao.getAllSongs();
        songs.forEach(s->System.out.println(s.getTitle()));
    }
    public void addSong(Song song) {
        try (Connection con = DBConnection.connect()) {
            String query = "INSERT INTO songs (id,title, artist, album, genre, year,url,moods,weatherTags) VALUES (?,?,?,?,?,?,?,?,?)";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1,song.getId());
            ps.setString(2, song.getTitle());
            ps.setString(3, song.getArtist());
            ps.setString(4, song.getAlbum());
            ps.setString(5, song.getGenre());
            ps.setInt(6, song.getYear());
            ps.setString(7,song.getUrl());
            ps.setString(8,song.getMood());
            ps.setString(9,song.getWeatherTags());
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
                    rs.getString("id"),
                    rs.getString("title"),
                    rs.getString("artist"),
                    rs.getString("album"),
                    rs.getString("genre"),
                    rs.getInt("year"),
                    rs.getString("url"),
                    rs.getString("moods"),
                    rs.getString("weatherTags")
                );
                list.add(song);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
}
