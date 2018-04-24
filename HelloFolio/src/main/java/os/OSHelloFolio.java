package os;
import hellofolio.User;
import java.util.*;
import ui.UIHelloFolio;

public class OSHelloFolio {
    Scanner reader;
    UIHelloFolio uhf;
    HashMap<String, String> users;
    String username;
    User user;
    boolean loggedIn = false;

    public OSHelloFolio() {
        reader = new Scanner(System.in);
        users = new HashMap<String, String>(); //get this from DB later
        users.put("basic", "basic");
        users.put("perkele", "123");
        uhf = new UIHelloFolio();
        if (uhf.getNewUser()) {
            loggedIn = createUser();
        } else if (uhf.getWantToLogin()) {
            loggedIn = promptLogin();
        } else {
            useBasicUser();
        }
        if (loggedIn) {
            user = new User(username);
        }
    }

    public boolean promptLogin() { //getWantToLogin() -> promptLogin() in OS, return true on good login
        boolean correctPassword = false;
        reader = new Scanner(System.in);
        while (true) {
            System.out.print("Username: ");
            username = reader.nextLine();
            System.out.print("Password: ");
            String password = reader.nextLine();
            String cmd;
            if (!users.containsKey(username)) {
                while (true) {
                    System.out.println("Username does not exist! Try again (y/n)?.");
                    cmd = reader.nextLine();
                    if (cmd.equals("y") || cmd.equals("n")) {
                        break;
                    } else {
                        System.out.println("Invalid command!");
                    }
                }
                if (cmd.equals("y")) {
                    continue;
                } else {
                    break;
                }
            }
            if (users.get(username).equals(password)) {
                return true;
            }
        }
        return correctPassword;
    }

    public boolean createUser() { //return true upon creation success
        reader = new Scanner(System.in);
        while (true) {
            System.out.print("Username (exit to exit): ");
            username = reader.nextLine();
            if (username.equals("exit")) {
                System.exit(0); //easy way out now, need to implement a way to go back to previous "screen"
            }
            if (users.containsKey(username)) {
                System.out.println("Username already exists!");
            } else {
                break;
            }
        }
        System.out.print("Password: ");
        String password = reader.nextLine();
        users.put(username, password);
        return true;
    }

    public void useBasicUser() {
        username = "basic";
        loggedIn = true;
    }
}


