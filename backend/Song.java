public class Song {
    private int id;
    private String title;
    private String artist;
    private String album;
    private String genre;
    private int year;

    // constructor
    public Song(String title, String artist, String album, String genre, int year) {
        this.title = title;
        this.artist = artist;
        this.album = album;
        this.genre = genre;
        this.year = year;
    }
    //constructor with id when fetching from the database
    public Song(int id, String title, String artist, String album, String genre, int year){
        this.id=id;
        this.title=title;
        this.artist=artist;
        this.album=album;
        this.genre=genre;
        this.year=year;
    }
    // getters + setters (optional)
    public int getId(){
        return id;
    }
    public String getTitle(){
        return title;
    }
    public String getArtist(){
        return artist;
    }
    public String getAlbum(){
        return album;
    }
    public String getGenre(){
        return genre;
    }
    public int getYear(){
        return year;
    }
    //setter functions;
    public void setId(int id){
        this.id=id;
    }
    public void setTitle(String title){
        this.title=title;
    }
    public void setArtist(String artist){
        this.artist=artist;
    }
    public void setAlbum(String album){
        this.album=album;
    }
    public void setGenre(String genre){
        this.genre=genre;
    }
    public void setYear(int year){
        this.year=year;
    }
    //done with the setter functions


}
