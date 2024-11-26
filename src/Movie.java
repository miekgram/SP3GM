public class Movie extends Media{
    Movie[] movies;
    Movie movie;
    FileIO fileIO = new FileIO();
    int releaseYear;
    String FilmDataPath ="data/film.csv";

    Movie(String title, int releaseYear, String category, float rating) {
        super(title, category, rating);
        this.releaseYear = releaseYear;
    }

}
