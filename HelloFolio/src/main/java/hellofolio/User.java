package hellofolio;
import java.util.*;

public class User {
    String name;
    Portfolio pf;
    Scanner reader;
    boolean basic = false;
    public User(String name) {
        reader = new Scanner(System.in);
        this.name = name;
        if (this.name.equals("basic")) {
            basic = true;
        }
        System.out.println("Logged in to " + this.name);
        HashMap<String, Double> holdings = new HashMap<String, Double>(); //get this from DB with name
        ArrayList<String> tickers = new ArrayList<>(holdings.keySet());
        pf = new Portfolio(holdings, basic);
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

    public Portfolio getPortfolio() {
        return pf;
    }
}
