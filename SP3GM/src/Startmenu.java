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
    }

    public void startProgram() {
        textUI.displayMsg("Welcome to M.M.M");
        setupUsers();

    }

    public void setupUsers() {
        ArrayList<String> data = FileIO.readData(this.userDataPath);
        boolean createUser = textUI.promptBinary("Would you like to login, or create a user? login/create user");

        if (!data.isEmpty() && createUser == false) {
            for (String s : data) {
                String[] values = s.split(",");
                String userName = values[0];
                String password = (values[1].trim());
                User u = new User(userName, password);
                users.add(u);
            }
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
    }

