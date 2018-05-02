package hellofolio;

import com.lucadev.coinmarketcap.CoinMarketCap;
import com.lucadev.coinmarketcap.model.*;
import java.util.*;

public class Portfolio {
    CoinMarketList coinMarketList;
    CoinMarket coinMarkets;
    HashMap<String, Double> holdings;
    ArrayList<String> tickers;
    double currentBTCPrice;

    public Portfolio(HashMap<String, Double> h, boolean basic) {
        coinMarketList = CoinMarketCap.ticker().setLimit(10).get(); //basic view = top 10 coins
        if (basic) {
            holdings = new HashMap<String, Double>();
            for (Iterator<CoinMarket> i = coinMarketList.getMarkets().iterator(); i.hasNext();) {
                CoinMarket coinMarkets = i.next();
                holdings.put(coinMarkets.getId(), new Double(0.0));
            }
        } else {
            holdings = h;
        }
    }

    /**
     * Metodi näyttää portfolion koostumuksen.
     *
     * @return Näytettävä portfolio String muodossa.
     */

    public String showPortfolio() {
        double totalWorthUSD = 0;
        double totalWorthBTC = 0;
        tickers = new ArrayList<>(holdings.keySet());
        currentBTCPrice = coinMarketList.findMarket("bitcoin").getPriceUSD();
        String printable = "";
        if (!tickers.isEmpty()) {
            for (String i : tickers) {
                coinMarkets = CoinMarketCap.ticker(i).get();
                double netWorthBTC =  holdings.get(coinMarkets.getId()) * coinMarkets.getPriceBTC();
                double netWorthUSD = netWorthBTC * currentBTCPrice;
                printable += coinMarkets.getName() + " " + coinMarkets.getSymbol() + " (Market cap: " + coinMarkets.getMarketCapUSD() + " USD) ### Last price: $" + coinMarkets.getPriceUSD() + ", " + coinMarkets.getPriceBTC() + " BTC. ### Your holdings: " + holdings.get(coinMarkets.getId()) + " " + coinMarkets.getSymbol() + " ### Net worth: $" + netWorthUSD + ", " + netWorthBTC + " BTC\n";
                totalWorthUSD += netWorthUSD;
                totalWorthBTC += netWorthBTC;
            }
            printable += "Total value of holdings: $" + totalWorthUSD + ", " + totalWorthBTC +  " BTC.";
            return printable;
        } else {
            return "Your portfolio is empty!";
        }
    }

    /**
     * Metodi lisää haluttavan "tickerin" eli valuutan nimen  portfolioon, jos annettu ticker on validi.
     *
     * @param tick Käyttäjän antama syöte, jonka on tarkoitus olla valuutan nimi pieninä kirjaimina, esim. litecoin
     * @param amt Määrä, eli kuinka monta kappaletta valuuttaa käyttäjä omistaa.
     *
     * @return Palauttaa käyttöjärjestelmälle Stringin onnistumisestaan.
     */

    public String addTicker(String tick, double amt) { //adding negative values is allowed, basically the same thing as removing.
        String returnable = "";
        if (holdings.containsKey(tick)) {
            double currentAmount = holdings.get(tick);
            currentAmount += amt;
            holdings.put(tick, currentAmount);
        } else {
            holdings.put(tick, amt);
        }
        if (amt != 0) {
            returnable = amt + " " + tick + " added!";
        } else {
            returnable = tick + " added!";
        }
        return returnable;
    }

    /**
     * Metodi poistaa haluttavan "tickerin" eli valuutan nimen tai valuutan määrää portfoliosta.
     *
     * @param tick Käyttäjän antama syöte, jonka on tarkoitus olla valuutan nimi pieninä kirjaimina, esim. litecoin
     * @param amt Määrä, eli kuinka monta kappaletta valuuttaa käyttäjä haluaa poistaa. Jos halutaan poistaa kokonaan, niin tämän täytyy olla -1.
     *
     * @return Palauttaa käyttöjärjestelmälle Stringin onnistumisestaan.
     */

    //TODO "all" should remove everything and make amt = 0
    public String removeTicker(String tick, double amt) { //not sure if should disallow removing negative values (could cause confusion)
        String returnable = "";
        if (holdings.containsKey(tick)) {
            if (amt == -1) {
                holdings.remove(tick);
                returnable = tick + " removed completely";
                return returnable;
            }
            double currentAmount = holdings.get(tick);
            currentAmount -= amt;
            holdings.put(tick, currentAmount);
            returnable = amt + " " + tick + " removed!";
        } else {
            returnable = "Can't remove, since " + tick + " was not found in your portfolio.";
        }
        return returnable;
    }

    /**
     * Metodi tarkistaa, onko annettu valuutta olemassa tietokannassa.
     *
     * @param tick Käyttäjän antama syöte, jonka on tarkoitus olla valuutan nimi pieninä kirjaimina, esim. litecoin
     *
     * @return Palauttaa true jos on olemassa, false jos ei ole olemassa.
     */

    public boolean tickerIsValid(String tick) {
        try {
            CoinMarket coinMarketsTest = CoinMarketCap.ticker(tick).get();
            String coinMarketsName = coinMarketsTest.getId();
        } catch (Exception e) {
            return false;
        }
        return true;
    }
}
