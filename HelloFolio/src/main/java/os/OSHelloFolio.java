package os;
import hellofolio.User;
import java.util.*;
import java.io.*;
import ui.UIHelloFolio;

public class OSHelloFolio {
    Scanner reader;
    UIHelloFolio uhf;
    HashMap<String, String> users;
    String file = "./users.txt";
    String username;
    User user;
    boolean loggedIn = false;

    public OSHelloFolio() throws IOException, Exception {
        users = new HashMap<String, String>(); //get this from DB later
        initializeUsers();
        reader = new Scanner(System.in);
        while (!loggedIn) {
            uhf = new UIHelloFolio();
            if (uhf.getNewUser()) {
                loggedIn = createUser();
            } else if (uhf.getWantToLogin()) {
                loggedIn = promptLogin();
            } else {
                useBasicUser();
            }
        }
        user = new User(username);
    }

    /**
     * Metodi lukee tiedostosta users.txt käyttäjät ja asettaa ne users-nimiseen HashMap:iin.
     */

    public void initializeUsers() throws IOException, Exception {
        try {
            Scanner fileRead = new Scanner(new File(file));
            while (fileRead.hasNextLine()) {
                String[] parts = fileRead.nextLine().split(";");
                users.put(parts[0], parts[1]);
            }
        } catch (Exception e) {
            FileWriter writer = new FileWriter(new File(file));
            writer.close();
        }
    }

    /**
     * Metodi aloittaa loginin.
     *
     * @return Palauttaa true, jos login onnistui, false jos ei onnistunut.
     */

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

    /**
     * Metodi aloittaa uuden käyttäjän luonnin.
     *
     * @return Palauttaa true, jos käyttäjän luominen onnistui, false jos ei onnistunut.
     */

    public boolean createUser() throws Exception { 
        reader = new Scanner(System.in);
        while (true) {
            System.out.print("Username (exit to exit): ");
            username = reader.nextLine();
            if (username.equals("exit")) {
                return false;
            }
            if (users.containsKey(username)) {
                System.out.println("Username already exists!");
            } else {
                break;
            }
        }
        System.out.print("Password: ");
        String password = reader.nextLine();
        try (FileWriter writer = new FileWriter(new File(file), true)) {
            writer.write(username + ";" + password + "\n");
        } catch (Exception e) {
            FileWriter writer = new FileWriter(new File(file));
            writer.close();
        }
        users.put(username, password);
        return true;
    }

    /**
     * Metodi aloittaa basic käyttäjän käyttämisen.
     */

    public void useBasicUser() {
        username = "basic";
        loggedIn = true;
    }
}
