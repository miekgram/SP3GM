public class Movie {
    Movie[] movies;
    Movie movie;
    FileIO fileIO = new FileIO();
    int releaseYear;
    String FilmDataPath ="data/film.csv";

    Movie(String title, int releaseYear, String category, float rating) {
        super();
        this.releaseYear = releaseYear;
    }

}
