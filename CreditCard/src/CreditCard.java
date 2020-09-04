/**
 * A simple model for a consumer credit card.
 *
 * @author Michael T. Goodrich
 * @author Roberto Tamassia
 * @author Michael H. Goldwasser
 */
public class CreditCard {
  // Instance variables:
  private String customer;      // name of the customer (e.g., "John Bowman")
  private String bank;          // name of the bank (e.g., "California Savings")
  private String account;       // account identifier (e.g., "5391 0375 9387 5309")
  private int limit;            // credit limit (measured in dollars)
  protected double balance;     // current balance (measured in dollars)

  // Constructors:
  /**
   * Constructs a new credit card instance.
   * @param cust        the name of the customer (e.g., "John Bowman")
   * @param bk          the name of the bank (e.g., "California Savings")
   * @param acnt        the account identifier (e.g., "5391 0375 9387 5309")
   * @param lim         the credit limit (measured in dollars)
   * @param initialBal  the initial balance (measured in dollars)
   */
  public CreditCard(String cust, String bk, String acnt, int lim, double initialBal) {
    if(cust != null && bk != null & acnt != null)
    {
      customer = cust;
      bank = bk;
      account = acnt;
    }
    else
      throw new IllegalArgumentException("Null values are not allowed.");

    if(lim > 0)
      limit = lim;
    else
      throw new IllegalArgumentException("The credit limit must be > 0");

    if(initialBal >= 0)
      balance = initialBal;
    else
      throw new IllegalArgumentException("Initial balance cannot be negative.");
  }

  /**
   * Constructs a new credit card instance with default balance of zero.
   * @param cust    the name of the customer (e.g., "John Bowman")
   * @param bk      the name of the bank (e.g., "California Savings")
   * @param acnt    the account identifier (e.g., "5391 0375 9387 5309")
   * @param lim     the credit limit (measured in dollars)
   */
  public CreditCard(String cust, String bk, String acnt, int lim) {
    this(cust, bk, acnt, lim, 0.0);    // use a balance of zero as default
  }

  // Accessor methods:
  /** Returns the name of the customer. */
  public String getCustomer() { 
    return customer; 
  }

  /** Returns the name of the bank */
  public String getBank() { 
    return bank; 
  }

  /** Return the account identifier. */
  public String getAccount() { 
    return account; 
  }

  /** Return the credit limit. */
  public int getLimit() { 
    return limit; 
  }

  /** Return the current balance. */
  public double getBalance() { 
	  return balance; 
  }

  /**
   * Charges the given price to the card, assuming sufficient credit limit.
   * @param price  the amount to be charged
   * @return true  if charge was accepted; false if charge was denied
   */
  public boolean charge(double price) {               // make a charge
    if (price + balance > limit)                      // if charge would surpass limit
      return false;                                   // refuse the charge
    // at this point, the charge is successful
    balance += price;                                 // update the balance
    return true;                                      // announce the good news
  }

  /**
   * Processes customer payment that reduces balance.
   * @param amount  the amount of payment made
   */
  public void makePayment(double amount) {            // make a payment
    if(amount > 0)
      balance -= amount;
    else
      throw new IllegalArgumentException("Negative payments are not allowed.");
  }

  // Utility method to print a card's information
  public static void printSummary(CreditCard card) {
    System.out.println("Customer = " + card.customer);
    System.out.println("Bank = " + card.bank);
    System.out.println("Account = " + card.account);
    System.out.println("Balance = " + card.balance);  // implicit cast
    System.out.println("Limit = " + card.limit);      // implicit cast
  }

  public static void main(String[] args) {
    CreditCard[] wallet = new CreditCard[3];
    
    // instantiate three different CreditCard objects into the wallet array
     wallet[0] = new CreditCard("Sally Fields", "Bank of nowhere", "0098673561", 9000);
     wallet[1] = new CreditCard("Rob Lowe", "Steelin Bank", "8764229099", 15000);
     wallet[2] = new CreditCard("Stumpy Brown", "LOL Got Money", "6678777761", 12000);
     
    // implement a for loop that will place at least 10 random charges to each object
     for(int i = 0; i <= 10; i++)
     {
    	 wallet[0].charge((Math.random()*((200-5)+1))+5);
    	 wallet[1].charge((Math.random()*((200-5)+1))+5);
    	 wallet[2].charge((Math.random()*((200-5)+1))+5);
     }

    // implement an enhanced for loop to print a summary report for each card
     int i = 0;
     for(CreditCard eachWallet:wallet)
     {
    	 printSummary(wallet[i]);
    	 System.out.println();
    	 i++;
     }
      
    // implement a while loop that makes a $200 payment to each card
     i = 0;
     while(i <= 2)
     {
    	 wallet[i].makePayment(200);
    	 i++;
     }

    // reprint the summary report for each card
     i = 0;
     for(CreditCard eachWallet:wallet)
     {
    	 printSummary(wallet[i]);
    	 System.out.println();
    	 i++;
     }
    }
}


