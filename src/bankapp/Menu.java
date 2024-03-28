package bankapp;

import java.util.InputMismatchException;
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
		int choice = 0;
		boolean invalidChoice = true;
		while(invalidChoice) {
			try {
				choice = in.nextInt();
				if (choice < 1 || choice > 4) {
					System.out.println("Invalid choice");
					displayingOptions();
				} else {
					invalidChoice = false;
				}
			} catch (InputMismatchException e) {
				System.out.println("Must enter an integer");
				displayingOptions();
				in.nextLine();
			}
		}
		System.out.println("");
		return choice;
	}
	
	public double getValidDepositAmount() {
		double amount = -1;
		boolean invalidAmount = true;
		while(invalidAmount) {
			try {
				amount = in.nextDouble();
				if (amount < 0) {
					System.out.println("Amount must be positive");
					System.out.println("How much would you like to deposit?");
				} else {
					invalidAmount = false;
				}
			} catch (InputMismatchException e) {
				System.out.println("Must enter an number");
				System.out.println("How much would you like to deposit?");
				in.nextLine();
			}
		}
		return amount;
	}
	
	public double getValidWithdrawAmount() {
		double amount = -1;
		boolean invalidAmount = true;
		while(invalidAmount) {
			try {
				amount = in.nextDouble();
				if (amount<0 || amount>account.getBalance()) {
					System.out.println("Amount must be from 0 to " + String.format("%.2f", account.getBalance()));
					System.out.println("How much would you like to withdraw?");
				} else {
					invalidAmount = false;
				}
			} catch (InputMismatchException e) {
				System.out.println("Must enter an number");
				System.out.println("How much would you like to withdraw?");
				in.nextLine();
			}
		}
		return amount;
	}
	
	public void processingUserSelection(int choice) {
		if (choice == 1) {
			System.out.println("Balance: " + String.format("%.2f", account.getBalance()));
		} else if (choice == 2) {
			depositAction();
		} else if (choice == 3) {
			withdrawAction();
		} else if (choice == 4) {
			exit = true;
			System.out.println("Account exited");
		}
	}

	private void depositAction() {
		System.out.println("How much would you like to deposit?");
		double depositAmount = getValidDepositAmount();
		account.deposit(depositAmount);
		System.out.println("Amount deposited: " + depositAmount);
		System.out.println("New balance = " + String.format("%.2f", account.getBalance()));
	}

	private void withdrawAction() {
		System.out.println("How much would you like to withdraw?");
		double withdrawAmount = getValidWithdrawAmount(); 
		account.withdraw(withdrawAmount);
		System.out.println("Amount withdrew: " + withdrawAmount);
		System.out.println("New balance = " + String.format("%.2f", account.getBalance()));	}
	
	public BankAccount getAccount() {
		return account;
	}
}
