package bankapp;

public class BankAccount {
	
	private double balance;
	
	//Constructors - not tested
	public BankAccount() {
		this.balance = 0;
	}
	
	//public method doing some work - lots of tests
	public void deposit(double amount) {
		if(amount < 0) {
			throw new IllegalArgumentException("Amount must be positive");
		}
		this.balance += amount;
	}
	
	public void withdraw(double withdrawAmt) {
		if(withdrawAmt < 0) {
			throw new IllegalArgumentException("Amount must be positive");
		}
		
		this.balance -= withdrawAmt;
	}
	
	//getters and setters - not tested
	public double getBalance() {
		return this.balance;
	}



}
