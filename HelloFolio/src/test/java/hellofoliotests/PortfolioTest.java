package hellofoliotests;
import hellofolio.Portfolio;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import java.util.*;
import org.apache.commons.lang.StringUtils; //for countMatches

public class PortfolioTest {
    Portfolio pf;
    
    public PortfolioTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        pf = new Portfolio(new HashMap<String, Double>(), false);
    }
    
    @After
    public void tearDown() {
    }

    @Test 
    public void basicNewPortfolioIsSetupCorrectly()
    {
        pf = new Portfolio(new HashMap<String, Double>(), true);
        String portfolioString = pf.showPortfolio();
        int count = StringUtils.countMatches(portfolioString, "Market cap:");
        assertTrue(count == 10); //can only test if the portfolio holds a total of 10 assets, and nothing else, since it's dynamic; it's not for certain that bitcoin will stay in the top 10 coins for example
    }

    @Test
    public void nonBasicNewPortfolioIsEmpty()
    {
        assertEquals("Your portfolio is empty!", pf.showPortfolio());
    }
    
    @Test
    public void addTickerAddsCorrectly()
    {
        pf.addTicker("bitcoin",10);
        assertTrue(pf.showPortfolio().contains("Your holdings: 10.0 BTC")); //need to always actually use .contains for these string-tests, since the strings are dynamic, depending on the current price etc, so nothing is never 100% the same
    }

    @Test
    public void addTickerAddsZeroCorrectly()
    {
        assertEquals("bitcoin added!",pf.addTicker("bitcoin",0));
    }

    @Test
    public void addTickerOnExistingTickerAddsCorrectly()
    {
        pf.addTicker("bitcoin",10);
        pf.addTicker("bitcoin",10);
        assertTrue(pf.showPortfolio().contains("Your holdings: 20.0 BTC"));
    }
    
    @Test
    public void removeTickerRemovesCorrectly()
    {
        pf.addTicker("bitcoin",10);
        pf.removeTicker("bitcoin",5);
        assertTrue(pf.showPortfolio().contains("Your holdings: 5.0 BTC"));
    }

    @Test
    public void removeExistingTickerCompletelyWorks()
    {
        pf.addTicker("bitcoin",10);
        pf.removeTicker("bitcoin",-1);
        assertEquals("Your portfolio is empty!", pf.showPortfolio());
    }

    @Test
    public void removeNonExistingTickerCompletelyWorks()
    {
        assertEquals("Can't remove, since bitcoin was not found in your portfolio.",pf.removeTicker("bitcoin",-1));
    }

    @Test
    public void tickerIsValidReturnsFalseOnBadInput()
    {
        assertFalse(pf.tickerIsValid("adssffkoef")); //assuming no one makes a coin called adssffkoef AND gets it added to CMC
    }

    @Test
    public void tickerIsValidReturnsTrueOnGoodInput()
    {
        assertTrue(pf.tickerIsValid("bitcoin")); 
    }
}

