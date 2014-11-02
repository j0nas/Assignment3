public class Album {
    private String title, artist, genre;
    private int tracks, releasedYear;

    public Album(String title, String artist, String genre, int tracks, int releasedYear) {
        setTitle(title);
        setArtist(artist);
        setGenre(genre);
        setTracks(tracks);
        setReleasedYear(releasedYear);
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getGenre() {
        return this.genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public int getTracks() {
        return tracks;
    }

    public void setTracks(int tracks) {
        this.tracks = tracks;
    }

    public int getReleasedYear() {
        return releasedYear;
    }

    public void setReleasedYear(int releasedYear) {
        this.releasedYear = releasedYear;
    }

    public String toString() {
        return String.format("%s (%s) | %d tracks | released in %d",
                getArtist(), getTitle(), tracks, getReleasedYear());
    }
}