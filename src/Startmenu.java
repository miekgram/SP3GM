import java.util.ArrayList;


public class Startmenu {
    TextUI textUI;
    FileIO fileIO;
    private String userDataPath;
    private ArrayList<User> users = new ArrayList<User>();
    Mainmenu mainmenu;
    public User currentUser;



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
        User user = new User(userName, password);
        fileIO.saveData(this.userDataPath,"Username, password", userName, password);
    }

    public void login() {
        ArrayList<String> users = new ArrayList<>();
        users = fileIO.readData(this.userDataPath);
        for (int i = 0; i < users.size(); i++) {
            String[] values = users.get(i).split(",");
            String username = (values[0].trim());
            System.out.println(username);
        }
        String userName = textUI.promptText("Write your username: ");
        String password = textUI.promptText("Write your password: ");
        this.currentUser = new User(userName, password);
        textUI.displayMsg("Welcome back!");

    }
    public User getCurrentUser() {
        return this.currentUser;
    }


}

