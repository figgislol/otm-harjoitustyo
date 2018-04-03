package com.mycompany.hellofolio;

import com.lucadev.coinmarketcap.CoinMarketCap;
import com.lucadev.coinmarketcap.model.*;
import java.util.*;

public class Portfolio {

    CoinMarketList cml;
    CoinMarket cm;
    ArrayList<String> tickers;

    public Portfolio(ArrayList<String> t)
    {
        tickers = t;
        cml = CoinMarketCap.ticker().setLimit(10).get(); //basic view = top 10 coins
    }

    //work on formatting output
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
            for(Iterator<CoinMarket> i = cml.getMarkets().iterator(); i.hasNext();)
            {
                CoinMarket cm = i.next();
                printable = cm.getName() + " " + cm.getSymbol() + " (Market cap: " + cm.getMarketCapUSD() + " USD) Last price: " + cm.getPriceUSD() + " USD, " + cm.getPriceBTC() + " BTC.";
                System.out.println(printable);
            }
        }
    }
}
