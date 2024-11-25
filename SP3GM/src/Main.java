public class Main {
    public static void main(String[] args) {
        Startmenu startmenu = new Startmenu();
        Mainmenu mainmenu = new Mainmenu();
        //User user = new User("Malene", "Næ");

        //user.savedForLater.
        startmenu.startProgram();
        User user = startmenu.getCurrentUser();
        mainmenu.displayMainMenu(user);

    }
}