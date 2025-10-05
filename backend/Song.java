public class Song {
    private String id;
    private String title;
    private String artist;
    private String album;
    private String genre;
    private int year;
    private String url;
    private String moods;
    private String weatherTags;

    // constructor
    public Song(String title, String artist, String album, String genre, int year) {
        this.title = title;
        this.artist = artist;
        this.album = album;
        this.genre = genre;
        this.year = year;
    }
    //constructor with id when fetching from the database
    public Song(String id, String title, String artist, String album, String genre, int year, String url, String moods, String weatherTags){
        this.id=id;
        this.title=title;
        this.artist=artist;
        this.album=album;
        this.genre=genre;
        this.year=year;
        this.url=url;
        this.moods=moods;
        this.weatherTags=weatherTags;
    }
    // getters + setters (optional)
    public String getId(){
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
    public String getUrl(){
        return url;
    }
    public String getMood(){
        return moods;
    }
    public String getWeatherTags(){
        return weatherTags;
    }
    //setter functions;
    public void setId(String id){
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
    public void setUrl(String url){
        this.url=url;
    }
    public void setMood(String moods){
        this.moods=moods;
    }
    public void setWeatherTags(String weather){
        this.weatherTags=weather;
    }
    //done with the setter functions


}
