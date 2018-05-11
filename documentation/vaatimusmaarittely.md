# HelloFolio, a portfolio manager for cryptoassets

HelloFolio will be used to manage a portfolio of wanted cryptoassets, to display their price at the current moment, and calculate a total value of the user's 
holdings.

## Functionalities
* Each user can create their own username/password combination
  * Username and password are saved in a file named *users.txt*
  * Users can log in to the system if needed
* The application can be used without logging in, a basic view is shown
* Users can track the price of all the cryptoassets that they need
  * Allows the user to create their own portfolio of holdings in each asset
    * Said cryptoassets will be saved per user to a file called *holdings.txt*
    * Through this, the prices can still be tracked every time the program is reopened
    * Assets can also be removed from the portfolio
    * The application will automatically calculate the total portfolio value based on holdings
      * Portfolio total values can be calculated in USD and BTC
  * Assets can be searched with the asset name e.g. bitcoin
    * Asset values are searched via coinmarketcap.com API

## TODOs
* Graphical UI
* Upgrade data saving (*users.txt* and *holdings.txt*) to a database on a server located on a Raspberry Pi
* See the total value of holdings in more currencies
* More user friendly searching e.g. searching with either "BTC", "Bitcoin", or "bitcoin" will result in finding bitcoin's price.
* Graphs on an asset's price history
  * Graphing the history of the portfolio's total value.

