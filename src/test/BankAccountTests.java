package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import bankapp.BankAccount;

class BankAccountTests {

	@Test
	void testSimpleDeposit() {
		//1. Setup Objects
		
		BankAccount testAccount = new BankAccount();
		
		//2. Call the method being tested
		testAccount.deposit(25);
		
		//3. Use assertions to verify results
		assertEquals(25.0, testAccount.getBalance(), 0.01);	
	}
	
	@Test
	void testNegativeDeposit() {
		//1. Setup Objects	
		BankAccount testAccount = new BankAccount();
		
		//2. Call the method being tested
		try {
			testAccount.deposit(-25);
			fail();
		} catch (IllegalArgumentException e) {
			//we expect to end up here, -25 is a bad input
			assertTrue(true);
		}
	}

	@Test
	void testZeroDeposit() {
		//1. Setup Objects	
		BankAccount testAccount = new BankAccount();
		
		//2. Call the method being tested
		testAccount.deposit(0);
		
		//3. Use assertions to verify results
		assertEquals(0, testAccount.getBalance(), 0.01);	
	}
	
	@Test
	void testMultipleDeposit() {
		BankAccount testAccount = new BankAccount();
		
		//2. Call the method being tested
		testAccount.deposit(25);
		testAccount.deposit(50);
		
		//3. Use assertions to verify results
		assertEquals(75.0, testAccount.getBalance(), 0.01);	
	}
	
	@Test
	void testDecimalDeposit() {
		BankAccount testAccount = new BankAccount();
		
		//2. Call the method being tested
		testAccount.deposit(12.34);
		
		//3. Use assertions to verify results
		assertEquals(12.34, testAccount.getBalance(), 0.01);	
	}
	
	@Test
	void testSimpleWithdraw() {
		//1. Setup Objects
		
		BankAccount testAccount = new BankAccount();
		testAccount.deposit(50);
		
		//2. Call the method being tested
		testAccount.withdraw(25);
		
		//3. Use assertions to verify results
		assertEquals(25.0, testAccount.getBalance(), 0.01);	
	}
	
	@Test
	void testNegativeWithdraw() {
		//1. Setup Objects	
		BankAccount testAccount = new BankAccount();
		testAccount.deposit(50);
		
		//2. Call the method being tested
		try {
			testAccount.withdraw(-5);
			fail();
		} catch (IllegalArgumentException e) {
			//we expect to end up here, -5 is a bad input
			assertTrue(true);
		}
	}

	@Test
	void testZeroWithdraw() {
		//1. Setup Objects	
		BankAccount testAccount = new BankAccount();
		testAccount.deposit(20);
		
		//2. Call the method being tested
		testAccount.withdraw(0);
		
		//3. Use assertions to verify results
		assertEquals(20.0, testAccount.getBalance(), 0.01);	
	}
	
	@Test
	void testMultipleWithdraw() {
		BankAccount testAccount = new BankAccount();
		testAccount.deposit(100);
		
		//2. Call the method being tested
		testAccount.withdraw(25);
		testAccount.withdraw(50);
		
		//3. Use assertions to verify results
		assertEquals(25.0, testAccount.getBalance(), 0.01);	
	}
	
	@Test
	void testDecimalWithdraw() {
		BankAccount testAccount = new BankAccount();
		testAccount.deposit(20);
		
		//2. Call the method being tested
		testAccount.withdraw(10.30);
		
		//3. Use assertions to verify results
		assertEquals(9.70, testAccount.getBalance(), 0.01);	
	}
	
	@Test
	void testWithdrawMoreThanBalance() {
		//1. Setup Objects	
		BankAccount testAccount = new BankAccount();
		testAccount.deposit(50);
		
		//2. Call the method being tested
		try {
			testAccount.withdraw(70);
			fail();
		} catch (IllegalArgumentException e) {
			//we expect to end up here
			assertTrue(true);
		}
	}
}
