package test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import bankapp.FileObj;

class FileObjTests {
	
	@Test
	void testSetContentUsernames() {
		FileObj namesFile = new FileObj("infoFiles/namesTest.txt");
		ArrayList<String> content = new ArrayList<>();
		content.add("doug");
		content.add("jim");
		content.add("bruce");
		content.add("chuck");
		int oldNumAccounts = namesFile.getContent().size();
		
		namesFile.setContent(content);
		int newNumAccounts = namesFile.getContent().size();
		
		System.out.println(oldNumAccounts);
		System.out.println(newNumAccounts);
		
		assertTrue(oldNumAccounts+2 == newNumAccounts);
		
		//reset file
		content.remove(2);
		content.remove(3);
		namesFile.setContent(content);
	}

}
