package test;

import static org.junit.Assert.assertTrue;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import bankapp.BankAccount;
import bankapp.FileObj;
import bankapp.Menu;

class MenuTests {

	private int indexOfAccount;
	
	
	@Test
	void testChangePassword() {
		Menu menu1 = new Menu();
		indexOfAccount = 0;
		FileObj passwordsFile = new FileObj("infoFiles/testFiles/passwordsTest.txt");
		ArrayList<String> passwords = new ArrayList<String>();
		passwords = passwordsFile.getContent();
		String passwordOld = passwords.get(indexOfAccount);
		assertTrue(passwordOld.equals("shook"));
		
		menu1.changePassword(indexOfAccount, "SHOOK");
		passwords = passwordsFile.getContent();
		String passwordNew = passwords.get(indexOfAccount);
		assertTrue(passwordNew.equals("SHOOK"));
		
		//reset file
		menu1.changePassword(indexOfAccount, "shook");
	}

	@Test
	void testChangeUsername() {
		Menu menu2 = new Menu();
		indexOfAccount = 1;
        FileObj namesFile = new FileObj("infoFiles/testFiles/namesTest.txt");
        ArrayList<String> usernames = namesFile.getContent();
        String usernameOld = usernames.get(indexOfAccount);
        assertTrue(usernameOld.equals("jim"));
        
        menu2.changeUsername(indexOfAccount, "JIM");
        usernames = namesFile.getContent();
        String usernameNew = usernames.get(indexOfAccount);
        assertTrue(usernameNew.equals("JIM"));
        
        //reset file 
        menu2.changeUsername(indexOfAccount, "jim");
	}
	
	
	@Test
	void testAddNameMultiple() { // Emma's
		Menu menu3 = new Menu();
		FileObj usernamesFile = new FileObj("infoFiles/testFiles/namesTest.txt");
		ArrayList<String> unames = new ArrayList<String>();
		unames = usernamesFile.getContent();
		menu3.addNewName("Emma", unames);
		menu3.addNewName("Eric", unames);
		menu3.addNewName("Scott", unames);
		System.out.print(unames);
		assertTrue(unames.get(0).equals("Emma") && unames.get(1).equals("Eric") && unames.get(2).equals("Scott"));
	
	}
	
	@Test
	void testAddPasswordEmpty() { //Eric
		Menu menu4 = new Menu();
		FileObj passwordFile = new FileObj("infoFIles/testFiles/passwordsTest.txt");
		ArrayList<String> pwords = new ArrayList<String>();
		pwords = passwordFile.getContent();
		menu4.addNewPassword("kuo", pwords);
		assertTrue(pwords.get(0).equals("kuo"));
	}
	
	@Test
	void testAddNewBalance() {
		Menu menu5 = new Menu();
		FileObj balancesFile = new FileObj("infoFiles/testFiles/balancesTest.txt");
		ArrayList<String> balances = new ArrayList<String>();
		balances = balancesFile.getContent();
		menu5.addNewBalance();
		assertTrue(balances.get(0).equals("0"));
	}
	
	@Test
	void testAddPasswordMultiple() { //eric
		Menu menu6 = new Menu();
		FileObj passwordFile = new FileObj("infoFIles/testFiles/passwordsTest.txt");
		ArrayList<String> passwords = new ArrayList<String>();
		passwords = passwordFile.getContent();
		menu6.addNewPassword("kuo", passwords);
		menu6.addNewPassword("yam", passwords);
		menu6.addNewPassword("yin", passwords);
		assertTrue(passwords.get(0).equals("kuo") && (passwords.get(1)).equals("yam") && (passwords.get(2)).equals("yin"));
	}
	
}

	