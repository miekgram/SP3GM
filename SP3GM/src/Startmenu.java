import java.util.ArrayList;


public class Startmenu {
    TextUI textUI;
    FileIO fileIO;
    private String userDataPath;
    private ArrayList<User> users;
    Mainmenu mainmenu;



    public Startmenu() {
        this.textUI = new TextUI();
        this.fileIO = new FileIO();
        this.userDataPath = "data/userdata.csv";
        this.mainmenu = new Mainmenu();
        this.users = new ArrayList<>();
    }

    public void startProgram() {
        textUI.displayMsg("Welcome to M.M.M");
        setupUsers();

    }

    public void setupUsers() {
        ArrayList<String> data = FileIO.readData(this.userDataPath);
        boolean createUser = textUI.promptBinary("Would you like to login, or create a user? login/create user");

        if (createUser == false) {
            login();
        } else {
            registerUsers();
        }
    }

    public void registerUsers() {
        String userName = textUI.promptText("Write a username: ");
        String password = textUI.promptText("Write a password: ");
        User u = new User(userName, password);
        this.users.add(u);
    }

    public void login() {
        textUI.displayMsg("Welcome back!");
        String userName = textUI.promptText("Write your username: ");
        String password = textUI.promptText("Write your password: ");
    }


}

