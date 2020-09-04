package account;

import exceptions.AccountOverdrawnException;
import exceptions.InsufficientFundsException;
import exceptions.InvalidTransactionException;

public class BankAccount {

	// Constants
	private final static double requiredMinimum = 50.0;
	private final static double premiumAmount = 500.0;
	
	// Attributes
	private double balance;
	
	// Constructs a bank account with fifty dollar balance
	public BankAccount () {
		balance = BankAccount.requiredMinimum;
	}
	
	// Constructs a bank account with a given balance
	public BankAccount (double initialBalance) throws AccountOverdrawnException {
		if (initialBalance < BankAccount.requiredMinimum) {
			throw new AccountOverdrawnException();
		}
		balance = initialBalance;
	}
	
	// Deposits money into the bank account
	public void deposit (double amount) throws InvalidTransactionException {
		if (amount <=0) { throw new InvalidTransactionException(); }
		double newBalance = balance + amount;
		balance = newBalance;
	}
	
	// Withdraws money from the bank account
	public void withdraw (double amount) throws InsufficientFundsException {
		if ((balance - amount) < BankAccount.requiredMinimum) {
			throw new InsufficientFundsException();
		}
		double newBalance = balance - amount;
		balance = newBalance;
	}
	
	// Transfers money
	public void transfer(double amount, BankAccount toAccount) throws 
		InvalidTransactionException, InsufficientFundsException {
		this.withdraw(amount);
		toAccount.deposit(amount);
	}
	


    // Determines whether or not an account has premium status
	public boolean isPremiumAccount() {
		return (balance >= BankAccount.premiumAmount);
	}
	
	// Gets the current balance of the bank account
	public double getBalance () {
		return balance;
	}	
	
}
