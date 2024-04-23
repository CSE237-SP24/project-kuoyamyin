package bankapp;

import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.ArrayList;

public class Menu {
	
	private Scanner in;
	private BankAccount account;
	private boolean exit;
	private boolean loginComplete;
	private FileObj namesFile;
	private FileObj passwordsFile;
	private FileObj balancesFile;
	private int indexOfAccount;
	private ArrayList<String> balances;
	private ArrayList<String> usernames;
	private ArrayList<String> passwords;

	public static void main(String[] args) {
		Menu mainMenu = new Menu();
		mainMenu.getFiles();
		mainMenu.loginOrCreateAccount();
		mainMenu.runBankFeatures();
	}
	
	public Menu() {
		this.account = new BankAccount();
		this.in = new Scanner(System.in);
		this.exit = false;
		this.loginComplete = false;
		this.balances = new ArrayList<>();
		this.usernames = new ArrayList<>();
		this.passwords = new ArrayList<>();
		
	}
	
	public void getFiles() {
		namesFile = new FileObj("infoFiles/names.txt");
		passwordsFile = new FileObj("infoFiles/passwords.txt");
		balancesFile = new FileObj("infoFiles/balances.txt");
		
		usernames = namesFile.getContent();
		passwords = passwordsFile.getContent();
		balances = balancesFile.getContent();
	}
	
	public void loginOrCreateAccount() {
		int minChoice = 1;
		int maxChoice = 3;
		System.out.println("");
		System.out.println("1. Login");
		System.out.println("2. Create Account");
		System.out.println("3. Exit");
		System.out.println("Select an action (enter number):");
		int choice = this.getValidUserInput(minChoice, maxChoice);
		this.processingUserLoginSelection(choice);
	}
	
	public void processingUserLoginSelection(int choice) {
		if (choice == 1) {
			askForLogin();
		} else if (choice == 2) {
			createAccountAction();
		} else if (choice == 3) {
			exit = true;
			System.out.println("Account exited");
		}
	}
	
	private void createAccountAction() {
		String[] accountInfo = getNewAccountInfo();
		addNewName(accountInfo[0]);
		addNewPassword(accountInfo[1]);
		addNewBalance();
		indexOfAccount = balances.size()-1;
	}

	private String[] getNewAccountInfo() {
		in.nextLine();
		System.out.println("What is your name?");
		String name = in.nextLine();
		System.out.println("What will your password be?");
		String password = in.nextLine();
		String[] accountInfo = {name, password};
		return accountInfo;
	}

	private void addNewBalance() { //testable
		balances.add("0");
		balancesFile.setContent(balances);
	}

	private void addNewPassword(String password) { //testable
		passwords.add(password);
		passwordsFile.setContent(passwords);
	}

	private void addNewName(String name) { //testable
		usernames.add(name);
		namesFile.setContent(usernames);
	}
	
	public void runBankFeatures() {
		while(!exit) {
			int minChoice = 1;
			int maxChoice = 6;
			System.out.println("");
			displayOptions();
			int choice = this.getValidUserInput(minChoice, maxChoice);
			this.processingUserActionSelection(choice);
		}
	}

	private void displayOptions() {
		System.out.println("1. Check balance");
		System.out.println("2. Deposit money");
		System.out.println("3. Withdraw money");
		System.out.println("4. Delete account");
		System.out.println("5. Manage Account (change username/password)");
		System.out.println("6. Exit");
		System.out.println("Select an action (enter number):");
	}
	
	public int getValidUserInput(int min, int max) {
		int choice = 0;
		boolean invalidChoice = true;
		while(invalidChoice) {
			try {
				choice = in.nextInt();
				if (choice < min || choice > max) {
					System.out.println("Invalid choice");
					in.nextLine();
					return getValidUserInput(min, max);
				} else {
					invalidChoice = false;
				}
			} catch (InputMismatchException e) {
				System.out.println("Must enter an integer");
				in.nextLine();
				return getValidUserInput(min, max);
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
	
	public void processingUserActionSelection(int choice) {
		if (choice == 1) {
			System.out.println("Balance: " + String.format("%.2f", account.getBalance()));
		} else if (choice == 2) {
			depositAction();
		} else if (choice == 3) {
			withdrawAction();
		} else if (choice == 4) {
			verifyPassword();
			deleteAccount();
			exit = true;
			System.out.println("Account deleted");
		} else if (choice == 5) {
			manageAccount();
		} else if (choice == 6) {
			exit = true;
			balances.set(indexOfAccount, Double.toString(account.getBalance()));
			balancesFile.setContent(balances);
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

	private void manageAccount() {
		System.out.println("Enter 1 to change your username, 2 to change your password, or 3 to return to other options");
		int userChoice = in.nextInt();
		while (userChoice!= 1 && userChoice != 2 && userChoice!=3) {
			System.out.println("Must enter either 1, 2, or 3");
		}
		if (userChoice == 1) {
			System.out.println("What would you like your new username to be?");
			in.nextLine();
			String newUsername = in.nextLine();
			changeUsername(newUsername);
		}
		else if(userChoice == 2) {
			verifyPassword();
			System.out.println("What would you like your new password to be?");
			String newPassword = in.nextLine();
			changePassword(newPassword);
		}
		else if (userChoice == 3) {
			System.out.println("");
			displayOptions();
		}
	}

	private void changeUsername(String newUsername) { //testable
		usernames.set(indexOfAccount, newUsername);
		namesFile.setContent(usernames);
	}

	private void changePassword(String newPassword) { //testable
		passwords.set(indexOfAccount, newPassword);
		passwordsFile.setContent(passwords);
	}

	private void askForLogin() {
		in.nextLine();
		while (!loginComplete) {
			System.out.println("Username: ");
			String username = in.nextLine();
			System.out.println("Password: ");
			String password = in.nextLine();
			indexOfAccount = usernames.indexOf(username);
			System.out.println(usernames);
			System.out.println(passwords);
			verifyLoginPassword(password);
		}
	}

	private void verifyLoginPassword(String password) {
		if (indexOfAccount == -1) {
			System.out.println("Incorrect username and/or password");
		} else {
			if (passwords.get(indexOfAccount).equals(password)) {
				double currentBalance = Double.parseDouble(balances.get(indexOfAccount));
				account.setBalance(currentBalance);
				loginComplete = true;
			} else {
				System.out.println("Incorrect username and/or password");
			}
		}
	}
	
	public void deleteAccount() {
			passwords.remove(indexOfAccount);
			usernames.remove(indexOfAccount);
			balances.remove(indexOfAccount);
			
			passwordsFile.setContent(passwords);
			namesFile.setContent(usernames);
			balancesFile.setContent(balances);
		}
	
	public void verifyPassword() {
		System.out.println("Verify your current password");
		in.nextLine();
		String tempPassword = in.nextLine();
		int passwordIndex = passwords.indexOf(tempPassword);
		while (passwordIndex != indexOfAccount) {
			System.out.println("Password is incorrect, try again.");
			tempPassword = in.nextLine();
			passwordIndex = passwords.indexOf(tempPassword);
		}
	}
	
	
}
