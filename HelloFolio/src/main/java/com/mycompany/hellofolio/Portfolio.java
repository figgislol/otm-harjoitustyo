package com.mycompany.hellofolio;

import com.lucadev.coinmarketcap.CoinMarketCap;
import com.lucadev.coinmarketcap.model.*;
import java.util.*;

public class Portfolio 
{
    CoinMarketList cml;
    CoinMarket cm;
    ArrayList<String> tickers;

    public Portfolio(ArrayList<String> t)
    {
        tickers = t;
        cml = CoinMarketCap.ticker().setLimit(10).get(); //basic view = top 10 coins
        for(Iterator<CoinMarket> i = cml.getMarkets().iterator(); i.hasNext();)
        {
            CoinMarket cm = i.next();
            tickers.add(cm.getId());
        }
    }

    public void showPortfolio()
    {
        String printable;
        if(!tickers.isEmpty())
        {
            for(String i : tickers)
            {
                cm = CoinMarketCap.ticker(i).get();
                printable = cm.getName() + " " + cm.getSymbol() + " (Market cap: " + cm.getMarketCapUSD() + " USD) Last price: " + cm.getPriceUSD() + " USD, " + cm.getPriceBTC() + " BTC.";
                System.out.println(printable);
            }
        }
        else
        {
            System.out.println("Your portfolio is empty!");
        }
    }

    public void addTicker(String tick)
    {
        tickers.add(tick);
    }

    public void removeTicker(String tick)
    {
        tickers.remove(tick);
    }

    public boolean tickerIsValid(String tick)
    {
        try
        {
            CoinMarket cmtest = CoinMarketCap.ticker(tick).get();
            String cmname = cm.getId();
        }
        catch(Exception e)
        {
            return false;
        }
        return true;
    }
}
