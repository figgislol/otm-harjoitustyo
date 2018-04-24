package hellofolio;
import java.io.IOException;
import os.OSHelloFolio;
import java.net.*;

public class HelloFolio {
    public static void main(String[] args) throws IOException {
        if (netIsAvailable()) {
            OSHelloFolio ohf = new OSHelloFolio();
        } else {
            System.out.println("There was a problem connecting to the API :(");
        }
    }
    
    public static boolean netIsAvailable() throws IOException {
        try {
            final URL url = new URL("https://api.coinmarketcap.com/");
            final URLConnection conn = url.openConnection();
            conn.connect();
            return true;
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            return false;
        }
    }
}
