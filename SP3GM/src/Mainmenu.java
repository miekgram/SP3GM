//import javax.print.attribute.standard.Media;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;

public class Mainmenu {
    TextUI textUI;
    FileIO fileIO;
    Movie movie;
    Series serie;
    ArrayList<Series> series = new ArrayList<Series>();
    ArrayList<Movie> movies= new ArrayList<Movie>();
    Media media;

    public Mainmenu() {
        textUI = new TextUI();
    }

  public void displayMainMenu(User user){
      textUI.displayMsg("Welcome to mainmenu!");

      while (true){
          int choice = textUI.promptNumeric( """
                Please choose an option:
                1. Search for a movie
                2. View categories
                3. View watched list
                4. View saved list
                5. Exit
                """);
          switch (choice){
              case 1 : search(user); break;
              case 2 : showCategory(user); break;
              case 3 : viewWatched(user); break;
              case 4 : viewSaved(user);break;
              case 5 : {
                  textUI.displayMsg("leaving mainmenu");
                  return;
              }
              default : textUI.displayMsg("invalid choice, try again");

          }
      }
  }


    private void search(User user) {
        ArrayList<Movie> movies = createMovie();
        ArrayList<Series> series = createSeries();

        ArrayList<Media> library = new ArrayList<>();
        library.addAll(movies);
        library.addAll(series);

        //while(true) {
            String title = textUI.promptText("Enter the title of the movie or series you want to search for");

            for (Media medias : library) {
                if (medias.getTitle().equalsIgnoreCase(title.trim())) {
                    int choice = textUI.promptNumeric( """
                    Please choose an option:
                    1. Play
                    2. Save title
                    3. Exit
                    """);
                    switch (choice){
                        case 1 : playChoice(medias.getTitle()); break;
                        case 2 : saveMedia(user, medias); break;
                        case 3 : {
                            textUI.displayMsg("leaving search");
                            return; //eller break
                        }
                        default : textUI.displayMsg("invalid choice, try again");
                    }
                } else {
                    textUI.displayMsg("Title doesn't match, try again");
                }
            }
        //}
    }




    private void showCategory(User user) {


    }
    private void viewWatched(User user){
        //textUI.displayMsg("Here is a list of movies/series you have watched:");
        movies = createMovie();
        series = createSeries();
        textUI.displayMovieList(movies, "Here is a list of movies you have watched: "); //Mangler liste
        textUI.displaySeriesList(series, "Here is a list of series you have watched: "); //Mangler liste
    }

    private void saveMedia(User user, Media media){
        textUI.displayMsg("Here is a list of movies/series you have saved:");
        if(!user.savedForLater.contains(media)){
            user.savedForLater.add(media);
            textUI.displayMsg(media.getTitle() + " has been added to your saved list.");

        }else{
            textUI.displayMsg(media.getTitle() + " is already in your saved list.");
        }
    }


    private void viewSaved(User user){
        textUI.displayMsg("Here is a list of movies/series you have saved:");
            for(Media media : user.savedForLater){
                textUI.displayMsg(media.getTitle()); //Printer kun title
            }
            //Overvej om brugeren skal have en switch case der spørger
            //om brugeren har lyst til at slette noget på listen

            //if yes
                //removeFromSaved(user, media);
            //else
                //tilbage

    }

    public void removeFromSaved(User user, Media media){
        if(!user.savedForLater.contains(media)){
            user.savedForLater.remove(media);
            System.out.println(media.getTitle() + " has been removed from your saved list.");

        }else{
            System.out.println(media.getTitle() + " is not on your saved list.");
        }

    }



    public void playChoice(String title) {
        textUI.displayMsg("Playing: " + title);
    }


    public ArrayList<Movie> createMovie() {
        ArrayList<String> listOfFilms = fileIO.readData("data/film.csv");
        movies = new ArrayList<>();

        for (int i = 0; i < listOfFilms.size(); i++) {
            //String[] values = listOfFilms.get(i).split(",");      //Hvorfor , i stedet for ;?
            String[] values = listOfFilms.get(i).split(";");  //values = [title ; year ; category ; rating]
            String title = (values[0].trim());                      //Skal vi bruge trim() på titlen? "TheGodfather"
            int releaseYear = Integer.parseInt(values[1].trim());   // trim() for at få " 1972" -> "1972"
            String category = values[2].trim();                     //  trim() for at " Crime, Drama" -> "Crime,Drama"
            float rating = Float.parseFloat((values[3].trim()));
            movie = new Movie(title, releaseYear, category, rating);
            movies.add(movie);
        }
        return movies;
    }

    public ArrayList<Series> createSeries() {
        ArrayList<String> listOfSeries = fileIO.readData("data/series.csv");
        series = new ArrayList<>();
        //Twin Peaks; 1990-1991; Crime, Drama, Mystery; 8,8; 1-8, 2-22;
        for (int i = 0; i < listOfSeries.size(); i++) {
            String[] values = listOfSeries.get(i).split(";");
            String title = (values[0].trim());
            String releaseYear = (values[1].trim());
            String category = values[2].trim();
            float rating = Float.parseFloat((values[3].trim()));
            String seasonsAndEpisodes = values[4].trim();
            serie = new Series(title, releaseYear, category, rating, seasonsAndEpisodes);
            series.add(serie);
        }
        return series;

    }
}

