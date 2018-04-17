package hellofolio;

import com.lucadev.coinmarketcap.CoinMarketCap;
import com.lucadev.coinmarketcap.model.*;
import java.util.*;

public class Portfolio {
    CoinMarketList cml;
    CoinMarket cm;
    HashMap<String, Double> holdings;
    ArrayList<String> tickers;
    double currentBTCPrice;

    public Portfolio(HashMap<String, Double> h, boolean basic) {
        cml = CoinMarketCap.ticker().setLimit(10).get(); //basic view = top 10 coins
        if (basic) {
            holdings = new HashMap<String, Double>();
            for (Iterator<CoinMarket> i = cml.getMarkets().iterator(); i.hasNext();) {
                CoinMarket cm = i.next();
                holdings.put(cm.getId(), new Double(0.0));
                //nice bug here, bitcoin goes to last index in hashmap even though it is added first :D
                //System.out.println("put: " + cm.getId()); //DEBUG
                //System.out.println("holdings: " + holdings); //DEBUG
            }
        } else {
            holdings = h;
        }
    }

    public String showPortfolio() {
        double totalWorthUSD = 0;
        double totalWorthBTC = 0;
        tickers = new ArrayList<>(holdings.keySet());
        //System.out.println("holdingskeyset: " + holdings.keySet()); //DEBUG
        currentBTCPrice = cml.findMarket("bitcoin").getPriceUSD();
        String printable = "";
        if (!tickers.isEmpty()) {
            for (String i : tickers) {
                cm = CoinMarketCap.ticker(i).get();
                double netWorthBTC =  holdings.get(cm.getId()) * cm.getPriceBTC();
                double netWorthUSD = netWorthBTC * currentBTCPrice;
                printable += cm.getName() + " " + cm.getSymbol() + " (Market cap: " + cm.getMarketCapUSD() + " USD) ### Last price: $" + cm.getPriceUSD() + ", " + cm.getPriceBTC() + " BTC. ### Your holdings: " + holdings.get(cm.getId()) + " " + cm.getSymbol() + " ### Net worth: $" + netWorthUSD + ", " + netWorthBTC + " BTC\n";
                totalWorthUSD += netWorthUSD;
                totalWorthBTC += netWorthBTC;
            }
            printable += "Total value of holdings: $" + totalWorthUSD + ", " + totalWorthBTC +  " BTC.";
            return printable;
        } else {
            return "Your portfolio is empty!";
        }
    }

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

    public boolean tickerIsValid(String tick) {
        try {
            CoinMarket cmtest = CoinMarketCap.ticker(tick).get();
            String cmname = cmtest.getId();
        } catch (Exception e) {
            return false;
        }
        return true;
    }
}
