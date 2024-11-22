import java.io.File;
import java.util.ArrayList;

public class Movie extends Media{
    FileIO fileIO = new FileIO();
    int releaseYear;

    Movie(String title, int releaseYear, ArrayList<String> categories, float rating){
        super(title, categories, rating);
        this.releaseYear = releaseYear;
    }






}
