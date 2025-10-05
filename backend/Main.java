public class Main {
    public static void main(String[] args) {
        SongDAO dao = new SongDAO();

        // Add sample song
        Song s1 = new Song("s01","Shape of You", "Ed Sheeran", "Divide", "Pop", 2017, "https://github/Cibani","pump","sunny");
        dao.addSong(s1);

        // Fetch songs
        for (Song s : dao.getAllSongs()) {
            System.out.println(s.getTitle() + " - " + s.getArtist());
        }
    }
}
