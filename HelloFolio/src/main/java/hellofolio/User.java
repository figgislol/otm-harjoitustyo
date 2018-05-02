package hellofolio;
import java.util.*;
import java.io.*;

public class User {
    String name;
    Portfolio pf;
    Scanner reader;
    boolean basic = false;
    File holdingsFile; //TODO start working on databases
    public User(String name) {
        this.name = name;
        if (name.equals("basic")) {
            basic = true;
        }
        HashMap<String, Double> holdings = new HashMap<String, Double>(); //get this from DB with name
        ArrayList<String> tickers = new ArrayList<>(holdings.keySet());
        pf = new Portfolio(holdings, basic);
        conversate();
    }

    public Portfolio getPortfolio() {
        return pf;
    }

    /**
     * Metodi aloittaa käyttäjän kanssa keskustelun.
     */

    public void conversate() {
        reader = new Scanner(System.in);
        System.out.println("Logged in to " + name);
        System.out.println("What to do with your portfolio? (show/add/remove/exit)");
        while (true) {
            String cmd = reader.nextLine();
            if (cmd.equals("show")) {
                System.out.println(pf.showPortfolio());
            } else if (cmd.equals("add")) {
                System.out.print("Give coin/token name (e.g. litecoin): ");
                String tick = reader.nextLine();
                System.out.print("Add how many (use 0 if you'd like to just track the asset)? ");
                double amount = reader.nextDouble();
                if (pf.tickerIsValid(tick)) {
                    System.out.println(pf.addTicker(tick, amount));
                } else {
                    System.out.println(tick + " was not found :(");
                }
            } else if (cmd.equals("remove")) {
                System.out.print("Give coin/token name (e.g. litecoin): ");
                String tick = reader.nextLine();
                System.out.print("Remove how many (-1 to remove token completely from portfolio)? ");
                double amount = reader.nextDouble();
                if (pf.tickerIsValid(tick)) {
                    System.out.println(pf.removeTicker(tick, amount));
                } else {
                    System.out.println(tick + " was not found :(");
                }
            } else if (cmd.equals("exit")) {
                break;
            }
        }
    }
}
