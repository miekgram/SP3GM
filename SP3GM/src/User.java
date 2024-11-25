import java.util.ArrayList;

public class User {
    String userName;
    String password;
    ArrayList<Media> haveWatched = new ArrayList<Media>();
    ArrayList<Media> savedForLater = new ArrayList<Media>();



    public User(String userName, String password) {
        this.userName = userName;
        this.password = password;

    }


    public void logIn() {


    }

    public void createUser() {


    }
}