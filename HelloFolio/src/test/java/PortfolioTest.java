import com.mycompany.hellofolio.Portfolio;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import java.util.*;

public class PortfolioTest {
    
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
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void nonBasicNewPortfolioIsEmpty()
    {
        Portfolio pf = new Portfolio(new HashMap<String, Double>(), false);
        assertEquals("Your portfolio is empty!", pf.showPortfolio());
    }
}
