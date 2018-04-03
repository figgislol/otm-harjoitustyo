package com.mycompany.hellofolio;

import java.util.ArrayList;

public class HelloFolio {
    
    public static void main(String[] args)
    {
        ArrayList<String> tickers = new ArrayList(); //items to track, this would be imported from user data

        //this would be prompted from the user, once the user wants to add coins to the portfolio
        /*
        tickers.add("bitcoin");
        tickers.add("litecoin");
        tickers.add("ethereum");
        tickers.add("neo");
        */

        Portfolio pf = new Portfolio(tickers);
        pf.showPortfolio();
    }
}
