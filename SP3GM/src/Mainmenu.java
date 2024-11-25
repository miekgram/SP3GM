import javax.print.attribute.standard.Media;
import java.util.ArrayList;
import java.util.List;

public class Mainmenu {
    TextUI textUI;
    FileIO fileIO;
    Movie movie;
    Series serie;
    ArrayList<Series> series;
    ArrayList<Movie> movies;
    private ArrayList<Media> savedForLater;
    Media media;

  public void displayMainMenu(User user){
      textUI.displayMsg("Welcome to mainmenu!");

      while (true){
          int choice = textUI.promptNumeric("""
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
              default -> textUI.displayMsg("invalid choice, try again");

          }
      }
  }


private void search() {
    ArrayList<String> movies = getCreateMovie();
    ArrayList<String> series = getCreateSeries();

    ArrayList<String> library = new ArrayList<>();
    library.addAll(movies);
    library.addAll(series);

    textUI.promptText("Enter the title of the movie or series you want to search for");

    for (String mia : library) {
        if (mia.equalsIgnoreCase(title)) {
            found = true;

            boolean searchInLibrary = textUI.promptBinary("Would you like to watch this? (y/n)");
            if (searchInLibrary == true) {
                playChoice();
            } else
                boolean save = textUI.promptBinary("Would you like to save this title for later? (y/n)");
            if (save == true)
                addToSaved();
        }
    }
}




private void showCategory(){


}
    private void viewWatched(){
        //textUI.displayMsg("Here is a list of movies/series you have watched:");
        movies = createMovie();
        series = createSeries();
        textUI.displayList(movies, "Here is a list of movies you have watched: "); //Mangler liste
        textUI.displayList(series, "Here is a list of series you have watched: "); //Mangler liste
    }


    private void viewSaved(){
        textUI.displayMsg("Here is a list of movies/series you have saved:");
        if(!savedForLater.contains(media)){
            savedForLater.add(media);
            System.out.println(media.getTitle() + " has been added to your saved list.");

        }else{
            System.out.println(media.getTitle() + " is already in your saved list.");
        }


    }
    public void removeFromSaved(){
        if(!savedForLater.contains(media)){
            savedForLater.remove(media);
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
            String[] values = listOfFilms.get(i).split(",");
            String title = (values[0].trim());
            int releaseYear = Integer.parseInt(values[1].trim());
            String category = values[2].trim();
            float rating = Float.parseFloat((values[3].trim()));
            movie = new Movie(title, releaseYear, category, rating);
            movies.add(movie);
        }
        return movies;
    }

    public ArrayList<Series> createSeries() {
        ArrayList<String> listOfSeries = fileIO.readData("data/film.csv");
        series = new ArrayList<>();

        for (int i = 0; i < listOfSeries.size(); i++) {
            String[] values = listOfSeries.get(i).split(",");
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

