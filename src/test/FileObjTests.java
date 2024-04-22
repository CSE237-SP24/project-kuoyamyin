package test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import bankapp.FileObj;

class FileObjTests {
	
	@Test
	void testGetContentUsernames() {
		FileObj namesFile = new FileObj("infoFiles/testFiles/namesTest.txt");
		
		int numAccounts = namesFile.getContent().size();
		
		assertTrue(numAccounts == 2);
	}
	
	@Test
	void testGetContentPasswords() {
		FileObj passwordsFile = new FileObj("infoFiles/testFiles/passwordsTest.txt");
		
		int numAccounts = passwordsFile.getContent().size();
		
		assertTrue(numAccounts == 2);
	}
	
	@Test
	void testGetContentBalances() {
		FileObj balancesFile = new FileObj("infoFiles/testFiles/namesTest.txt");
		
		int numAccounts = balancesFile.getContent().size();
		
		assertTrue(numAccounts == 2);
	}
	
	@Test
	void testGetContentUsernamesEmpty() {
		FileObj namesFile = new FileObj("infoFiles/testFiles/namesTestEmpty.txt");
		
		int numAccounts = namesFile.getContent().size();
		
		assertTrue(numAccounts == 0);
	}
	
	@Test
	void testGetContentPasswordsEmpty() {
		FileObj passwordsFile = new FileObj("infoFiles/testFiles/passwordsTestEmpty.txt");
		
		int numAccounts = passwordsFile.getContent().size();
		
		assertTrue(numAccounts == 0);
	}
	
	@Test
	void testGetContentBalancesEmpty() {
		FileObj balancesFile = new FileObj("infoFiles/testFiles/balancesTestEmpty.txt");
		
		int numAccounts = balancesFile.getContent().size();
		
		assertTrue(numAccounts == 0);
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
		int oldNumAccounts = passwordsFile.getContent().size();
		
		passwordsFile.setContent(content);
		int newNumAccounts = passwordsFile.getContent().size();
		
		assertTrue(oldNumAccounts+2 == newNumAccounts);
		
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
		int oldNumAccounts = balancesFile.getContent().size();
		
		balancesFile.setContent(content);
		int newNumAccounts = balancesFile.getContent().size();
		
		assertTrue(oldNumAccounts+2 == newNumAccounts);
		
		//reset file
		content.remove(3);
		content.remove(2);
		balancesFile.setContent(content);
	}

}
