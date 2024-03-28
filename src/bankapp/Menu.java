package bankapp;

import java.util.Scanner;

public class Menu {
	
	private Scanner in;
	private BankAccount account;
	private boolean exit;

	public static void main(String[] args) {
		Menu mainMenu = new Menu();
		mainMenu.runBankFeatures();
	}
	
	public Menu() {
		this.in = new Scanner(System.in);
		this.account = new BankAccount();
		this.exit = false;
	}
	
	public void runBankFeatures() {
		while(!exit) {
			this.displayingOptions();
			int choice = this.getValidUserInput();
			this.processingUserSelection(choice);
		}
	}
	
	public void displayingOptions() {
		System.out.println("");
		System.out.println("1. Check balance");
		System.out.println("2. Deposit money");
		System.out.println("3. Withdraw money");
		System.out.println("4. Exit");
		System.out.println("Select an action (enter number):");
	}
	
	public int getValidUserInput() {
		int choice = in.nextInt();
		while(choice < 1 || choice > 4) {
			System.out.println("Invalid choice.");
			System.out.println("1. Check balance");
			System.out.println("2. Deposit money");
			System.out.println("3. Withdraw money");
			System.out.println("4. Exit");
			System.out.println("Select an action (enter number):");
			choice = in.nextInt();
		}
		return choice;
	}
	
	public double getValidWithdrawAmount() {
		double amount = in.nextDouble();
		while (amount<0 || amount>account.getBalance()) {
			System.out.println("Amount must be 0 to " + account.getBalance());
			System.out.println("How much would you like to withdraw?");
			amount = in.nextDouble();
		}
		return amount;
	}
	
	public void processingUserSelection(int choice) {
		if (choice == 1) {
			System.out.println("Balance: " + account.getBalance());
		} else if (choice == 2) {
			// call methods for running deposit
			System.out.print("Enter an amount to deposit: ");
			depositAmount = in.nextDouble();
			while(depositAmount < 0) {
				System.out.print("Amount must be positive");
				System.out.print("Enter an amount to deposit: ");
				depositAmount = in.nextDouble();
			}
		} else if (choice == 3) {
			System.out.println("How much would you like to withdraw?");
			double withdrawAmount = getValidWithdrawAmount(); 
			account.withdraw(withdrawAmount); // when can i call getValidAmount?
			System.out.println("Amount withdrew: " + withdrawAmount + ". New balance = " + account.getBalance());
		} else if (choice == 4) {
			exit = true;
			System.out.println("Account exited");
		}
	}
	
	public BankAccount getAccount() {
		return account;
	}
}
