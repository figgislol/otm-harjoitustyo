package com.mycompany.hellofolio;
import java.util.*;

public class UIHelloFolio
{
    Scanner reader;
    boolean wantToLogin = false;
    boolean newUser = false;
    public UIHelloFolio()
    {
        reader = new Scanner(System.in);
        System.out.println("Welcome to HelloFolio! Do you wish to login (y/n) or create a new user (new)?");
        String wantToLoginStr;
        while(true)
        { 
            wantToLoginStr = reader.nextLine();
            if(wantToLoginStr.equals("y"))
            {
                wantToLogin = true;
                break;
            }
            else if(wantToLoginStr.equals("exit") || wantToLoginStr.equals("quit"))
            {
                System.exit(0);
            }
            else if(wantToLoginStr.equals("n"))
            {
                break;
            }
            else if(wantToLoginStr.equals("new"))
            {
                newUser = true;
                break;
            }
            else
            {
                System.out.println("Invalid input, try again. (new/y/n/exit)");
            }
        }
    }

    public boolean getNewUser()
    {
        return newUser;
    }

    public boolean getWantToLogin()
    {
        return wantToLogin;
    }
}
