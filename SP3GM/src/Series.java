import java.io.File;
import java.util.ArrayList;

public class Series extends Media{
    FileIO fileIO;
    String releaseYear;
    int season;
    int episode;

    public Series(String title, String releaseYear, ArrayList<String> categories, float rating, int season, int episode) {
        super(title, categories, rating);
        this.releaseYear = releaseYear;
        this.season = season;
        this.episode = episode;
    }


}
