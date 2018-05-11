package hellofolio;
import java.util.*;
import java.io.*;

public class User {
    String name;
    Portfolio pf;
    Scanner reader;
    boolean isBasic = false;
    boolean newUser = true;
    String file = "./holdings.txt";
    String holdingsString = "";
    String holdingsStringFull = "";
    HashMap<String, Double> holdings;

    public User(String name) throws IOException, Exception {
        this.name = name;
        if (name.equals("basic")) {
            isBasic = true;
        }
        holdings = new HashMap<String, Double>(); 
        initializeUserHoldings();
        pf = new Portfolio(holdings, isBasic);
        conversate();
    }

    /**
     * Metodi lukee tiedostosta holdings.txt käyttäjän omistamat valuutat.
     */

    public void initializeUserHoldings() throws IOException, Exception {
        try {
            Scanner fileRead = new Scanner(new File(file));
            while (fileRead.hasNextLine()) {
                holdingsStringFull = fileRead.nextLine();
                String[] parts = holdingsStringFull.split(";");
                if (parts[0].equals(name)) {
                    holdingsString = parts[1];
                    newUser = false;
                    break;
                }
            }
            if (!newUser) {
                handleHoldingsString(holdingsString);
            } else {
                holdingsStringFull = name + ";";
                holdingsString = "";
            }
        } catch (Exception e) {
            FileWriter writer = new FileWriter(new File(file));
            writer.close();
        }
    }

    /**
     * Metodi lukee tiedostosta holdings.txt tarvittavan rivin ja tekee siitä käyttökelpoisen ohjelmalle.
     */

    public void handleHoldingsString(String holds) {
        String[] separateCurrencies = holds.split("-");
        for (String currencyAndAmount : separateCurrencies) {
            String[] parts = currencyAndAmount.split(",");
            holdings.put(parts[0], new Double(parts[1]));
        }
    }

    /**
     * Metodi generoi stringin, joka tullaan kirjoittamaan tiedostoon holdings.txt
     *
     * @return palauttaa generoidun stringin.
     */

    public String generateHoldingsString() {
        String holdingsStringToReturn = "";
        for (String key : holdings.keySet()) {
            holdingsStringToReturn += key + "," + holdings.get(key) + "-";
        }
        if (holdingsStringToReturn != null && holdingsStringToReturn.length() > 0) {
            holdingsStringToReturn = holdingsStringToReturn.substring(0, holdingsStringToReturn.length() - 1);
        }
        return holdingsStringToReturn;
    }

    /**
     * Metodi kirjoittaa käyttäjän portfolion tiedostoon holdings.txt
     */

    public void writeHoldingsToFile() throws Exception {
        String toWrite = generateHoldingsString();
        String toRemove = holdingsStringFull;
        String currentLine;
        BufferedWriter writer;
        BufferedReader reader;
        boolean success;
        boolean lineFound = false;
        File tempFile = new File("./tempHoldings.txt");
        File holdingsFile = new File(file);
        try {
            writer = new BufferedWriter(new FileWriter(tempFile, true));
            reader = new BufferedReader(new FileReader(holdingsFile));
            while ((currentLine = reader.readLine()) != null) {
                String trimmed = currentLine.trim();
                if (trimmed.equals(toRemove)) {
                    lineFound = true;
                    writer.write(name + ";" + toWrite);
                    writer.newLine();
                    continue;
                }
                writer.write(currentLine);
                writer.newLine();
            }
            if (!lineFound) {
                writer.write(name + ";" + toWrite);
                writer.newLine();
            }
            writer.close();
            reader.close();
            success = tempFile.renameTo(holdingsFile);
        } catch (Exception e) {
            FileWriter fileWriter = new FileWriter(new File(file));
            fileWriter.close();
        }
    }

    public Portfolio getPortfolio() {
        return pf;
    }

    /**
     * Metodi aloittaa käyttäjän kanssa keskustelun.
     */

    public void conversate() throws Exception {
        reader = new Scanner(System.in);
        System.out.println("Logged in to " + name);
        while (true) {
            System.out.println("What to do with your portfolio? (show/add/remove/exit)");
            String cmd = reader.nextLine();
            if (cmd.equals("show")) {
                System.out.println(pf.showPortfolio());
            } else if (cmd.equals("add")) {
                System.out.print("Give coin/token name (e.g. litecoin): ");
                String tick = reader.nextLine();
                System.out.print("Add how many (use 0 if you'd like to just track the asset)? ");
                double amount;
                try {
                    amount = Double.parseDouble(reader.next());
                    reader.nextLine(); //parseDouble doesn't remove the "\n" from reader.next() so it remains in buffer, this line is to clear it.
                } catch (NumberFormatException e) {
                    System.out.println("Invalid input!");
                    reader.nextLine();
                    continue;
                }
                if (pf.tickerIsValid(tick)) {
                    System.out.println(pf.addTicker(tick, amount));
                } else {
                    System.out.println(tick + " was not found :(");
                }
            } else if (cmd.equals("remove")) {
                System.out.print("Give coin/token name (e.g. litecoin): ");
                String tick = reader.nextLine();
                System.out.print("Remove how many (-1 to remove token completely from portfolio)? ");
                double amount;
                try {
                    amount = Double.parseDouble(reader.next());
                    reader.nextLine();
                } catch (Exception e) {
                    System.out.println("Invalid input!");
                    reader.nextLine();
                    continue;
                }
                if (pf.tickerIsValid(tick)) {
                    System.out.println(pf.removeTicker(tick, amount));
                } else {
                    System.out.println(tick + " was not found :(");
                }
            } else if (cmd.equals("exit")) {
                if (!name.equals("basic")) {
                    writeHoldingsToFile();
                }
                break;
            } else {
                System.out.println("Invalid input!");
            }
        }
    }
}
