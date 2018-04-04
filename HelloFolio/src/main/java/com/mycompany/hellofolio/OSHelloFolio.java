package com.mycompany.hellofolio;
import java.util.*;

public class OSHelloFolio
{
    Scanner reader;
    UIHelloFolio uhf;
    HashMap<String,String> users;
    String username;
    User user;
    boolean loggedIn = false;

    public OSHelloFolio()
    {
        reader = new Scanner(System.in);
        users = new HashMap<String,String>(); //get this from DB later
        users.put("basic","basic");
        users.put("perkele","123");
        uhf = new UIHelloFolio();
        if(uhf.getNewUser())
        {
            loggedIn = createUser();
        }
        else if(uhf.getWantToLogin())
        {
            loggedIn = promptLogin();
        }
        else
            useBasicUser();
        if(loggedIn)
        {
            user = new User(username);
        }
    }

    public boolean promptLogin() //getWantToLogin() -> promptLogin() in OS, return true on good login
    {
        boolean correctPassword = false;
        reader = new Scanner(System.in);
        System.out.print("Username: ");
        username = reader.nextLine();
        System.out.print("Password: ");
        String password = reader.nextLine();
        if(users.get(username).equals(password))
            correctPassword = true;
        return correctPassword;
    }

    public boolean createUser() //return true upon creation success
    {
        reader = new Scanner(System.in);
        while(true) //gets stuck in while-loop if username creation keeps failing
        {
            System.out.print("Username: ");
            username = reader.nextLine();
            if(users.containsKey(username))
            {
                System.out.println("Username already exists!");
            }
            else
            {
                break;
            }
        }
        System.out.print("Password: ");
        String password = reader.nextLine();
        users.put(username, password);
        return true;
    }

    public void useBasicUser()
    {
        username = "basic";
        loggedIn = true;
    }
}
