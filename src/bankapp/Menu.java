package bankapp;

import java.util.Scanner;

public class Menu {
	
	private Scanner in;
	private BankAccount account;

	public static void main(String[] args) {
		Menu mainMenu = new Menu();
		mainMenu.displayingOptions();
		String answer = mainMenu.getValidUserInput();
		mainMenu.processingUserSelection(answer);
	}
	
	public Menu() {
		this.in = new Scanner(System.in);
		this.account = new BankAccount();
	}
	
	public void displayingOptions() {
		System.out.println("Check balance? (y/n)");
	}
	
	public String getValidUserInput() {
		String answer = in.nextLine();
		while(!answer.equals("y") && !answer.equals("n")) {
			System.out.println("Invalid answer.");
			System.out.println("Check balance? (y/n)");
			answer = in.nextLine();
		}
		return answer;
	}
	
	public void processingUserSelection(String answer) {
		if (answer.equals("y")) {
			System.out.println("Balance: " + account.getBalance());
		} else {
			System.out.println("No balance displayed");
		}
	}
	
	public BankAccount getAccount() {
		return account;
	}
}
