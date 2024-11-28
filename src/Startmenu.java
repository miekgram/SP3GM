import java.util.ArrayList;
import java.util.HashMap;


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
        //this.users = new ArrayList<>();
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
        this.currentUser = new User(userName, password);
        fileIO.saveData(this.userDataPath,userName, password);

    }

    public void login() {
        ArrayList<String> users = new ArrayList<>();
        HashMap<String,String> usernamePasswordMap = new HashMap<>();
        users = fileIO.readData(this.userDataPath);
        for (int i = 0; i < users.size(); i++) {
            String[] values = users.get(i).split(",");
            String username = (values[0].trim());
            String password = (values[1].trim());
            usernamePasswordMap.put(username,password);
            System.out.println(username);
        }

        String userName = textUI.promptText("-----\nTo login, write the username of the wanted user: ");
        if (usernamePasswordMap.containsKey(userName)) {
            String password = textUI.promptText("Write your password: ");
            if (usernamePasswordMap.containsValue(password)) {
                this.currentUser = new User(userName, password);
                textUI.displayMsg("Welcome back " + currentUser.userName + "!");
                mainmenu.displayMainMenu(this.currentUser);
            } else {
                textUI.displayMsg("Wrong password, start over login");
                login();
            }
        }else {
            textUI.displayMsg("Username doesn't match, let's try again");
            setupUsers();
        }
    }
    public User getCurrentUser() {
        return this.currentUser;
    }


}

