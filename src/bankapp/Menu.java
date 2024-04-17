package bankapp;

import java.io.File;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.ArrayList;

public class Menu {
	
	private Scanner in;
	private Scanner usernameOutput;
	private Scanner passwordOutput;
	private Scanner balanceOutput;
	private BankAccount account;
	private boolean exit;
	private boolean loginComplete;
	private File namesFile;
	private File passwordsFile;
	private File balancesFile;
	private int indexOfAccount;
	private ArrayList<Double> balances;
	private ArrayList<String> usernames;
	private ArrayList<String> passwords;

	public static void main(String[] args) {
		Menu mainMenu = new Menu();
		mainMenu.getFiles();
		mainMenu.createScanners();
		mainMenu.loginOrCreateAccount();
		mainMenu.runBankFeatures();
	}
	
	public Menu() {
		this.account = new BankAccount();
		this.exit = false;
		this.loginComplete = false;
		this.balances = new ArrayList<>();
		this.usernames = new ArrayList<>();
		this.passwords = new ArrayList<>();
		
	}
	
	public void getFiles() {
		namesFile = new File("../names.txt");
		passwordsFile = new File("../passwords.txt");
		balancesFile = new File("../balances.txt");
	}
	
	public void createScanners() {
		in = new Scanner(System.in);
		try {
			usernameOutput = new Scanner(namesFile);
		} catch (FileNotFoundException e) {
			System.out.println("usernameOutput scanner not created");
			e.printStackTrace();
		}
		try {
			passwordOutput = new Scanner(passwordsFile);
		} catch (FileNotFoundException e) {
			System.out.println("passwordOutput scanner not created");
			e.printStackTrace();
		}
		try {
			balanceOutput = new Scanner(balancesFile);
		} catch (FileNotFoundException e) {
			System.out.println("balanceOutput scanner not created");
			e.printStackTrace();
		}
	}
	
	public void loginOrCreateAccount() {
		int minChoice = 1;
		int maxChoice = 3;
		this.loginOrCreateAccountDisplay();
		int choice = this.getValidUserInput(minChoice, maxChoice);
		this.processingUserLoginSelection(choice);
	}
	
	public void loginOrCreateAccountDisplay() {
		System.out.println("");
		System.out.println("1. Login");
		System.out.println("2. Create Account");
		System.out.println("3. Exit");
		System.out.println("Select an action (enter number):");
	}
	
	public void processingUserLoginSelection(int choice) {
		if (choice == 1) {
			logInAction();
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
		balances = new ArrayList<>();
		while(balanceOutput.hasNextDouble()) {
			balances.add(balanceOutput.nextDouble());
		}
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

	private void addNewBalance() {
		try {
			PrintWriter balancesWriter = new PrintWriter(new FileOutputStream(balancesFile, true));
			balancesWriter.println(0);
			balancesWriter.close();
		} catch (FileNotFoundException e) {
			System.out.println("balancesFile not found.");
			e.printStackTrace();
		}
	}

	private void addNewPassword(String password) {
		try {
			PrintWriter passwordsWriter = new PrintWriter(new FileOutputStream(passwordsFile, true));
			passwordsWriter.println(password);
			passwordsWriter.close();
		} catch (FileNotFoundException e) {
			System.out.println("passwordsFile not found.");
			e.printStackTrace();
		}
	}

	private void addNewName(String name) {
		try {
			PrintWriter namesWriter = new PrintWriter(new FileOutputStream(namesFile, true));
			namesWriter.println(name);
			namesWriter.close();
		} catch (FileNotFoundException e) {
			System.out.println("namesFile not found.");
			e.printStackTrace();
		}
	}
	
	public void runBankFeatures() {
		while(!exit) {
			int minChoice = 1;
			int maxChoice = 6;
			this.displayingOptions();
			int choice = this.getValidUserInput(minChoice, maxChoice);
			this.processingUserActionSelection(choice);
		}
	}
	
	public void displayingOptions() {
		System.out.println("");
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
	
	public void processingUserActionSelection(int choice) {
		if (choice == 1) {
			System.out.println("Balance: " + String.format("%.2f", account.getBalance()));
		} else if (choice == 2) {
			depositAction();
		} else if (choice == 3) {
			withdrawAction();
		} else if (choice == 4) {
			manageAccount();
		} else if (choice == 5) {
			exit = true;
			updateBalancesFile();
		} else if (choice == 5) {
			//delete action
			deleteAction();
			//want to exit out of account home screen
			exit = true;
			System.out.println("Account deleted");
		}
	}

	private void updateBalancesFile() {
		try {
			PrintWriter exitWriter = new PrintWriter(balancesFile);
			for(int i = 0; i < balances.size(); i++) {
				if(indexOfAccount == i) {
					exitWriter.println(account.getBalance());
				} else {
					exitWriter.println(balances.get(i));
				}
			}
			exitWriter.close();
			System.out.println("Account exited");
		} catch (FileNotFoundException e){
			System.out.println("balancesFile not found.");
			e.printStackTrace();
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
	
	public void logInAction() {
		while(usernameOutput.hasNextLine()) {
			usernames.add(usernameOutput.nextLine());
		}
		while(passwordOutput.hasNextLine()) {
			passwords.add(passwordOutput.nextLine());
		}
		while(balanceOutput.hasNextDouble()) {
			balances.add(balanceOutput.nextDouble());
		}
		
		askForLogin();
			
	}
	
	private void manageAccount() {
		System.out.println("Enter 1 to change your username, 2 to change your password, or 3 to return to other options");
		int userChoice = in.nextInt();
		while (userChoice!= 1 && userChoice != 2 && userChoice!=3) {
			System.out.println("Must enter either 1, 2, or 3");
		}
		if (userChoice == 1) {
			changeUsername();
		}
		else if(userChoice == 2) {
			changePassword();
		}
		else if (userChoice == 3) {
			displayingOptions();
		}
	}
	
	private void changeUsername() {
		System.out.println("What would you like your new username to be?");
		in.nextLine();
		String newUsername = in.nextLine();
		updateUsernameFile(newUsername, indexOfAccount);
	}
	
	private void changePassword() {
		System.out.println("Verify your current password");
		in.nextLine();
		String tempPassword = in.nextLine();
		int passwordIndex = passwords.indexOf(tempPassword);
		while (passwordIndex != indexOfAccount) {
			System.out.println("Password is incorrect, try again.");
			tempPassword = in.nextLine();
			passwordIndex = passwords.indexOf(tempPassword);
		}
		System.out.println("What would you like your new password to be?");
		String newPassword = in.nextLine();
		updatePasswordFile(newPassword, indexOfAccount);
	}
	
	private void updateUsernameFile(String newUsername, int accountIndex) {
		try {
			PrintWriter exitWriter = new PrintWriter(namesFile);
			for(int i = 0; i < usernames.size(); i++) {
				if(accountIndex == i) {
					exitWriter.println(newUsername);
				}
				else {
					exitWriter.println(usernames.get(i));
				}
			}
			exitWriter.close();
			System.out.println("Username Changed");
		} catch (FileNotFoundException e){
			System.out.println("Username not updated");
			e.printStackTrace();
		}
	}
	
	private void updatePasswordFile(String newPassword, int accountIndex) {
		try {
			PrintWriter exitWriter = new PrintWriter(passwordsFile);
			for(int i = 0; i < usernames.size(); i++) {
				if(accountIndex == i) {
					exitWriter.println(newPassword);
				} 
				else {
					exitWriter.println(passwords.get(i));
				}
			}
			exitWriter.close();
			System.out.println("Password Changed");
		} catch (FileNotFoundException e){
			System.out.println("Password not updated");
			e.printStackTrace();
		}
	}

	private void askForLogin(/*ArrayList<String> usernames, ArrayList<String> passwords, ArrayList<Double> balances*/) {
		in.nextLine();
		while (!loginComplete) {
			System.out.println("Username: ");
			String username = in.nextLine();
			System.out.println("Password: ");
			String password = in.nextLine();
			indexOfAccount = usernames.indexOf(username);
			System.out.println(usernames);
			System.out.println(passwords);
			if (indexOfAccount == -1) {
				System.out.println("Incorrect username and/or password");
			} else {
				double currentBalance = balances.get(indexOfAccount);
				if (passwords.get(indexOfAccount).equals(password)) {
					account.setBalance(currentBalance);
					loginComplete = true;
				} else {
					System.out.println("login unsuccessful");
				}
			}
			
		}
	}
	
	public void deleteAction() {
		String pass = "";
		//want to check if password is correct, if not then reprompt. If it is correct then call deleteAccount()
		while(!pass.equals(passwords.get(indexOfAccount))) {
			System.out.println("Confirm your password: ");
			Scanner in = new Scanner(System.in);
			pass = in.nextLine();
		}
			deleteAccount(pass);
	}
	
	//get the index of the account(username, password, balance)
		//create new printWriter, rewrite every username,password, balance EXCEPT deleted account
	//UPDATE 4/16: deletes correct username and pass, but does not delete balance for some reason
		public void deleteAccount(String password) {
			indexOfAccount = passwords.indexOf(password);
			
			try {
				PrintWriter usernameWriter = new PrintWriter(namesFile);
				for(int i = 0; i < usernames.size(); i++) {
					if(indexOfAccount == i) {
						continue;
					} else {
						usernameWriter.println(usernames.get(i));
					}
				}
				usernameWriter.close();
			} catch (FileNotFoundException e1) {
				System.out.println("File not found");
			}
			
			try {
				PrintWriter passwordsWriter = new PrintWriter(passwordsFile);
				for(int i = 0; i < usernames.size(); i++) {
					if(indexOfAccount == i) {
						continue;
					} else {
						passwordsWriter.println(passwords.get(i));
					}
				}
				passwordsWriter.close();
			} catch (FileNotFoundException e1) {
				System.out.println("File not found");
			}
			
			try {
				PrintWriter balanceWriter = new PrintWriter(balancesFile);
				for(int i = 0; i < balances.size(); i++) {
					if(indexOfAccount == i) {
						continue;
					} else {
						balanceWriter.println(balances.get(i));
					}
				}
				
				balanceWriter.close();
			} catch (FileNotFoundException e) {
				System.out.println("File not found");
			}
		
		}
	
	
}
