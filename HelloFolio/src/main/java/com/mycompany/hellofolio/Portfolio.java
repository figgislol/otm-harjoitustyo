package com.mycompany.hellofolio;

import com.lucadev.coinmarketcap.CoinMarketCap;
import com.lucadev.coinmarketcap.model.*;
import java.util.*;

public class Portfolio 
{
    CoinMarketList cml;
    CoinMarket cm;
    HashMap<String, Double> holdings;
    ArrayList<String> tickers;
    double BTCPrice;

    public Portfolio(HashMap<String, Double> h, boolean basic)
    {
        cml = CoinMarketCap.ticker().setLimit(10).get(); //basic view = top 10 coins
        if(basic)
        {
            holdings = new HashMap<String, Double>();
            for(Iterator<CoinMarket> i = cml.getMarkets().iterator(); i.hasNext();)
            {
                CoinMarket cm = i.next();
                holdings.put(cm.getId(), new Double(0.0));
                //nice bug here, bitcoin goes to last index in hashmap even though it is added first :D
                //System.out.println("put: " + cm.getId()); //DEBUG
                //System.out.println("holdings: " + holdings); //DEBUG
            }
        }
        else
        {
            holdings = h;
        }
    }

    public void showPortfolio()
    {
        double totalWorthUSD = 0;
        double totalWorthBTC = 0;
        tickers = new ArrayList<>(holdings.keySet());
        //System.out.println("holdingskeyset: " + holdings.keySet()); //DEBUG
        BTCPrice = cml.findMarket("bitcoin").getPriceUSD();
        String printable;
        if(!tickers.isEmpty())
        {
            for(String i : tickers)
            {
                cm = CoinMarketCap.ticker(i).get();
                double netWorthBTC =  holdings.get(cm.getId()) * cm.getPriceBTC();
                double netWorthUSD = netWorthBTC * BTCPrice;
                printable = cm.getName() + " " + cm.getSymbol() + " (Market cap: " + cm.getMarketCapUSD() + " USD) ### Last price: $" + cm.getPriceUSD() + ", " + cm.getPriceBTC() + " BTC. ### Your holdings: " + holdings.get(cm.getId()) + " " + cm.getSymbol() + " ### Net worth: $" + netWorthUSD + ", " + netWorthBTC + " BTC";
                totalWorthUSD += netWorthUSD;
                totalWorthBTC += netWorthBTC;
                System.out.println(printable);
            }
        }
        else
        {
            System.out.println("Your portfolio is empty!");
        }

        System.out.println("Total value of holdings: $" + totalWorthUSD + ", " + totalWorthBTC +  " BTC.");
    }

    public void addTicker(String tick, double amt) //adding negative values is allowed, basically the same thing as removing.
    {
        if(holdings.containsKey(tick))
        {
            double currentAmount = holdings.get(tick);
            currentAmount += amt;
            holdings.put(tick, currentAmount);
        }
        else
            holdings.put(tick, amt);
        if(amt != 0)
            System.out.println(amt + " " + tick + " added!");
        else
            System.out.println(tick + " added!");
    }

    public void removeTicker(String tick, double amt) //not sure if should disallow removing negative values (could cause confusion)
    {

        if(holdings.containsKey(tick))
        {
            if(amt == -1)
            {
                holdings.remove(tick);
                System.out.println(tick + " removed completely");
                return;
            }
            double currentAmount = holdings.get(tick);
            currentAmount -= amt;
            holdings.put(tick, currentAmount);
            System.out.println(amt + " " + tick + " removed!");
        }
        else
            System.out.println("Can't remove, since " + tick + " was not found in your portfolio.");
    }

    public boolean tickerIsValid(String tick)
    {
        try
        {
            CoinMarket cmtest = CoinMarketCap.ticker(tick).get();
            String cmname = cmtest.getId();
        }
        catch(Exception e)
        {
            return false;
        }
        return true;
    }
}
