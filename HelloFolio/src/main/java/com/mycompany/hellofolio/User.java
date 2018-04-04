package com.mycompany.hellofolio;
import java.util.*;

public class User
{
    String name;
    Portfolio pf;
    Scanner reader;
    public User(String name)
    {
        reader = new Scanner(System.in);
        this.name = name;
        System.out.println("Logged in to " + this.name);
        ArrayList<String> tickers = new ArrayList(); //get this from DB with name
        pf = new Portfolio(tickers);
        System.out.println("What to do with your portfolio? (show/add/remove/exit)");
        while(true)
        {
            String cmd = reader.nextLine();
            if(cmd.equals("show"))
                pf.showPortfolio();
            else if(cmd.equals("add"))
            {
                System.out.print("Give coin/token name (e.g. litecoin): ");
                String tick = reader.nextLine();
                if(pf.tickerIsValid(tick))
                {
                    pf.addTicker(tick);
                    System.out.println(tick + " added!");
                }
                else
                {
                    System.out.println(tick + " was not found :(");
                }
            }
            else if(cmd.equals("remove"))
            {
                System.out.print("Give coin/token name (e.g. litecoin): ");
                String tick = reader.nextLine();
                if(pf.tickerIsValid(tick))
                {
                    pf.removeTicker(tick);
                    System.out.println(tick + " removed!");
                }
                else
                {
                    System.out.println(tick + " was not found :(");
                }
            }
            else if(cmd.equals("exit"))
                break;
        }
    }

    public Portfolio getPortfolio()
    {
        return pf;
    }
}
