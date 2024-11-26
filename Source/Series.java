import java.util.ArrayList;

public class Series extends Media {
    FileIO fileIO;
    String releaseYear;
    String seasonsAndEpisode;

    public Series(String title, String releaseYear, String category, float rating, String seasonsAndEpisode) {
        super(title, category, rating);
        this.releaseYear = releaseYear;
        this.seasonsAndEpisode = seasonsAndEpisode;
    }


}
