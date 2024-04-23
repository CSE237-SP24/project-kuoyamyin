package test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import bankapp.FileObj;

class FileObjTests {
	
	@Test
	void testGetContentUsernames() {
		FileObj namesFile = new FileObj("infoFiles/testFiles/namesTest.txt");
		
		int numUsernames = namesFile.getContent().size();
		
		assertTrue(numUsernames == 2);
	}
	
	@Test
	void testGetContentPasswords() {
		FileObj passwordsFile = new FileObj("infoFiles/testFiles/passwordsTest.txt");
		
		int numPasswords = passwordsFile.getContent().size();
		
		assertTrue(numPasswords == 2);
	}
	
	@Test
	void testGetContentBalances() {
		FileObj balancesFile = new FileObj("infoFiles/testFiles/namesTest.txt");
		
		int numBalances = balancesFile.getContent().size();
		
		assertTrue(numBalances == 2);
	}
	
	@Test
	void testGetContentUsernamesEmpty() {
		FileObj namesFile = new FileObj("infoFiles/testFiles/namesTestEmpty.txt");
		
		int numUsernames = namesFile.getContent().size();
		
		assertTrue(numUsernames == 0);
	}
	
	@Test
	void testGetContentPasswordsEmpty() {
		FileObj passwordsFile = new FileObj("infoFiles/testFiles/passwordsTestEmpty.txt");
		
		int numPasswords = passwordsFile.getContent().size();
		
		assertTrue(numPasswords == 0);
	}
	
	@Test
	void testGetContentBalancesEmpty() {
		FileObj balancesFile = new FileObj("infoFiles/testFiles/balancesTestEmpty.txt");
		
		int numBalances = balancesFile.getContent().size();
		
		assertTrue(numBalances == 0);
	}
	
	@Test
	void testSetContentUsernames() {
		FileObj namesFile = new FileObj("infoFiles/testFiles/namesTest.txt");
		ArrayList<String> content = new ArrayList<>();
		content.add("doug");
		content.add("jim");
		content.add("bruce");
		content.add("chuck");
		int oldNumAccounts = namesFile.getContent().size();
		
		namesFile.setContent(content);
		int newNumAccounts = namesFile.getContent().size();

		assertTrue(oldNumAccounts+2 == newNumAccounts);
		
		//reset file
		content.remove(3);
		content.remove(2);
		namesFile.setContent(content);
	}
	
	@Test
	void testSetContentPasswords() {
		FileObj passwordsFile = new FileObj("infoFiles/testFiles/passwordsTest.txt");
		ArrayList<String> content = new ArrayList<>();
		content.add("shook");
		content.add("slim");
		content.add("lee");
		content.add("norris");
		int oldNumPasswords = passwordsFile.getContent().size();
		
		passwordsFile.setContent(content);
		int newNumPasswords = passwordsFile.getContent().size();
		
		assertTrue(oldNumPasswords+2 == newNumPasswords);
		
		//reset file
		content.remove(3);
		content.remove(2);
		passwordsFile.setContent(content);
	}
	
	@Test
	void testSetContentBalances() {
		FileObj balancesFile = new FileObj("infoFiles/testFiles/balancesTest.txt");
		ArrayList<String> content = new ArrayList<>();
		content.add("45.32");
		content.add("63.12");
		content.add("3.00");
		content.add("105573.37");
		int oldNumBalances = balancesFile.getContent().size();
		
		balancesFile.setContent(content);
		int newNumBalances = balancesFile.getContent().size();
		
		assertTrue(oldNumBalances+2 == newNumBalances);
		
		//reset file
		content.remove(3);
		content.remove(2);
		balancesFile.setContent(content);
	}

}
