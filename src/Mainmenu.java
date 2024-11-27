//import javax.print.attribute.standard.Media;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Mainmenu {
    TextUI textUI;
    FileIO fileIO;
    Movie movie;
    Series serie;
    ArrayList<Series> series = new ArrayList<Series>();
    ArrayList<Movie> movies= new ArrayList<Movie>();
    Media media;
    String userPath = "data/userdata.csv";
    HashMap<String, String> categoryMap = new HashMap<>();


    public Mainmenu() {
        textUI = new TextUI();
    }

  public void displayMainMenu(User user){
      textUI.displayMsg("Welcome to mainmenu" + " " + user.userName + "!");


          int choice = textUI.promptNumeric( """
                Please choose an option:
                1. Search for a movie
                2. View categories
                3. View watched list
                4. View saved list
                5. Exit
                """);
          switch (choice){
              case 1 : search(user, "Please type the title of what you are searching for: "); break;
              case 2 : showCategory(user); break;
              case 3 : viewWatched(user);break;
              case 4 : viewSaved(user);break;
              case 5 : {
                  textUI.displayMsg("leaving mainmenu");
                  return;
              }
              default : textUI.displayMsg("invalid choice, try again");

          }

  }


    public void search(User user, String msgBeforeTypingTitle) {
        ArrayList<Movie> movies = createMovie();
        ArrayList<Series> series = createSeries();

        ArrayList<Media> library = new ArrayList<>();
        library.addAll(movies);
        library.addAll(series);

        String title = textUI.promptText(msgBeforeTypingTitle);
        boolean foundMatchingTitle = false;

        for (Media media : library){
            if(media.getTitle().equalsIgnoreCase(title)){
                foundMatchingTitle = true;
                int choice = textUI.promptNumeric("""
                        Please choose an option:
                        1. Play
                        2. Save title
                        3. Remove from saved list
                        4. Exit
                        """);
                switch (choice) {
                    case 1:
                        playChoice(user,media);
                        break;
                    case 2:
                        saveMedia(user, media);
                        displayMainMenu(user);
                        break;
                    case 3:
                        removeFromSaved(user,media);
                        break;
                    case 4: {
                        textUI.displayMsg("leaving search");
                        displayMainMenu(user);
                        break;
                    }
                    default:
                        textUI.displayMsg("invalid choice, try again");
                        break;
                }
            }
        }

        if(foundMatchingTitle == false){
            textUI.displayMsg("Title doesn't match, try again");
            search(user, msgBeforeTypingTitle);
    }
    }

    

    private void showCategory(User user) {
        ArrayList<Movie> movies = createMovie();
        ArrayList<Series> series = createSeries();

        ArrayList<Media> library = new ArrayList<>();
        library.addAll(movies);
        library.addAll(series);

        String choice = textUI.promptText("""
                Please choose an option by writing the name of the category:
                1. Drama
                2. Family
                3. Biography
                4. History
                5. Sport
                6. Crime
                7. Adventure
                8. Fantasy
                9. Musical
                10. Thriller
                11. Film-noir
                12. Horror
                13. Romance
                14. Comedy
                15. Mystery
                16. War
                17. Action
                18. Western
                19. Music
                20. Sci-fi
                21. Animation
                22. Talk-show
                23. Documentary
                ---------------
                24. Exit
                """);

            categoryMap.forEach ((m, c) -> {if (c.contains(choice)){
            textUI.displayMsg(m+" : "+c);
            }
            });

        search(user,"Please type the title of what you would like to watch in this category");
        displayMainMenu(user);
        //TODO: Hvis tid og lyst, så kan vi prøve at lave choice til int, så bruger kan skrive kategori ved tal.
    }




    private void viewWatched(User user){
        textUI.displayList(user.haveWatched, "Here is a list of movies you have watched: ");
        displayMainMenu(user);
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
        textUI.displayList(user.savedForLater,"Here is a list of what you have saved: " );//Printer kun title
        displayMainMenu(user);

            //TODO:Brugeren skal have en switch case, der spørger om brugeren har lyst til at slette noget på listen
            //if yes
                //removeFromSaved(user, media);
            //else
                //tilbage

    }

    public void removeFromSaved(User user, Media media){
        if(user.savedForLater.contains(media)){
            user.savedForLater.remove(media);
            textUI.displayMsg(media.getTitle() + " has been removed from your saved list.");

        }else{
            textUI.displayMsg(media.getTitle() + " is not on your saved list.");
        }

    }

    public void playChoice(User user, Media media) {
        textUI.displayMsg("Playing: " + media.getTitle());
        user.haveWatched.add(media);
        for(Media m : user.haveWatched){
            textUI.displayMsg(m.getTitle());    //Printer kun title
        }
        displayMainMenu(user);
    }


    public ArrayList<Movie> createMovie() {
        ArrayList<String> listOfFilms = fileIO.readData("data/film.csv");
        movies = new ArrayList<>();

        for (int i = 0; i < listOfFilms.size(); i++) {
            //The Godfather; 1972; Crime, Drama; 9,2;
            String[] values = listOfFilms.get(i).split(";");  //values = [title ; year ; category ; rating]
            String title = (values[0].trim());                      //Skal vi bruge trim() på titlen? "TheGodfather"
            int releaseYear = Integer.parseInt(values[1].trim());   // trim() for at få " 1972" -> "1972"
            String category = values[2].trim();                     //  trim() for at " Crime, Drama" -> "Crime,Drama"
            String r = (values[3].trim());
            r = r.replace(',', '.');
            float rating = Float.parseFloat(r);
            movie = new Movie(title, releaseYear, category, rating);
            movies.add(movie);
            this.categoryMap.put(title, category);
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
            String r = (values[3].trim());
            r = r.replace(',', '.');
            float rating = Float.parseFloat(r);
            String seasonsAndEpisodes = values[4].trim();
            serie = new Series(title, releaseYear, category, rating, seasonsAndEpisodes);
            series.add(serie);
            this.categoryMap.put(title, category);
        }
        return series;

    }
}