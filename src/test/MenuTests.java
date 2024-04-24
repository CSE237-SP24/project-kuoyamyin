package test;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import bankapp.FileObj;
import bankapp.Menu;

class MenuTests {

	private int indexOfAccount;
	
	
	@Test
	void testChangePassword() {
		Menu menu1 = new Menu();
		indexOfAccount = 0;
		menu1.getTestingFiles();
		FileObj passwordsFile = new FileObj("infoFiles/testFiles/passwordsTest.txt");
		ArrayList<String> passwords = passwordsFile.getContent();
		String passwordOld = passwords.get(indexOfAccount);
		assertTrue(passwordOld.equals("shook"));
		
		menu1.changePassword(indexOfAccount, "SHOOK", passwords);
		passwords = passwordsFile.getContent();
		String passwordNew = passwords.get(indexOfAccount);
		assertTrue(passwordNew.equals("SHOOK"));
		
		//reset file
		menu1.changePassword(indexOfAccount, "shook", passwords);
	}

	@Test
	void testChangeUsername() {
		Menu menu2 = new Menu();
		indexOfAccount = 1;
		menu2.getTestingFiles();
        FileObj namesFile = new FileObj("infoFiles/testFiles/namesTest.txt");
        ArrayList<String> usernames = namesFile.getContent();
        String usernameOld = usernames.get(indexOfAccount);
        assertTrue(usernameOld.equals("jim"));
        
        menu2.changeUsername(indexOfAccount, "JIM", usernames);
        usernames = namesFile.getContent();
        String usernameNew = usernames.get(indexOfAccount);
        assertTrue(usernameNew.equals("JIM"));
        
        //reset file 
        menu2.changeUsername(indexOfAccount, "jim", usernames);
	}
	
	
	@Test
	void testAddName() {
		Menu menu3 = new Menu();
		menu3.getTestingFiles();
		FileObj usernamesFile = new FileObj("infoFiles/testFiles/namesTest.txt");
		ArrayList<String> usernames = usernamesFile.getContent();
		menu3.addNewName("Emma", usernames);
		assertTrue(usernames.get(2).equals("Emma"));
	
		//reset file
		ArrayList<String> usernamesOriginal = new ArrayList<String>();
		usernamesOriginal.add("doug");
		usernamesOriginal.add("jim");
		usernamesFile.setContent(usernamesOriginal);
	}
	
	@Test
	void testAddNameMultiple() {
		Menu menu4 = new Menu();
		menu4.getTestingFiles();
		FileObj usernamesFile = new FileObj("infoFiles/testFiles/namesTest.txt");
		ArrayList<String> usernames = usernamesFile.getContent();
		menu4.addNewName("Emma", usernames);
		menu4.addNewName("Eric", usernames);
		menu4.addNewName("Scott", usernames);
		assertTrue(usernames.get(2).equals("Emma") && usernames.get(3).equals("Eric") && usernames.get(4).equals("Scott"));
	
		//reset file
		ArrayList<String> usernamesOriginal = new ArrayList<String>();
		usernamesOriginal.add("doug");
		usernamesOriginal.add("jim");
		usernamesFile.setContent(usernamesOriginal);
	}
	
	@Test
	void testAddPassword() {
		Menu menu5 = new Menu();
		menu5.getTestingFiles();
		FileObj passwordFile = new FileObj("infoFIles/testFiles/passwordsTest.txt");
		ArrayList<String> passwords = passwordFile.getContent();
		menu5.addNewPassword("kuo", passwords);
		assertTrue(passwords.get(2).equals("kuo"));
		
		// reset file
		ArrayList<String> passwordsOriginal = new ArrayList<String>();
		passwordsOriginal.add("shook");
		passwordsOriginal.add("slim");
		passwordFile.setContent(passwordsOriginal);
	}
	
	@Test
	void testAddPasswordMultiple() {
		Menu menu6 = new Menu();
		menu6.getTestingFiles();
		FileObj passwordFile = new FileObj("infoFIles/testFiles/passwordsTest.txt");
		ArrayList<String> passwords = passwordFile.getContent();
		menu6.addNewPassword("Yin", passwords);
		menu6.addNewPassword("Kuo", passwords);
		menu6.addNewPassword("Yam", passwords);
		assertTrue(passwords.get(2).equals("Yin") && passwords.get(3).equals("Kuo") && passwords.get(4).equals("Yam"));
		
		// reset file
		ArrayList<String> passwordsOriginal = new ArrayList<String>();
		passwordsOriginal.add("shook");
		passwordsOriginal.add("slim");
		passwordFile.setContent(passwordsOriginal);
	}
	
	@Test
	void testAddNewBalance() {
		Menu menu7 = new Menu();
		menu7.getTestingFiles();
		FileObj balancesFile = new FileObj("infoFiles/testFiles/balancesTest.txt");
		ArrayList<String> balances = balancesFile.getContent();
		menu7.addNewBalance(balances);
		assertTrue(balances.get(2).equals("0"));
		
		// reset file
		ArrayList<String> balancesOriginal = new ArrayList<String>();
		balancesOriginal.add("45.32");
		balancesOriginal.add("63.12");
		balancesFile.setContent(balancesOriginal);
	}
	
	@Test
	void testAddNewBalanceMultiple() {
		Menu menu8 = new Menu();
		menu8.getTestingFiles();
		FileObj balancesFile = new FileObj("infoFiles/testFiles/balancesTest.txt");
		ArrayList<String> balances = balancesFile.getContent();
		menu8.addNewBalance(balances);
		menu8.addNewBalance(balances);
		menu8.addNewBalance(balances);
		assertTrue(balances.get(2).equals("0") && balances.get(3).equals("0") && balances.get(4).equals("0"));
		
		// reset file
		ArrayList<String> balancesOriginal = new ArrayList<String>();
		balancesOriginal.add("45.32");
		balancesOriginal.add("63.12");
		balancesFile.setContent(balancesOriginal);
	}
}

	