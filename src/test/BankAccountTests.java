package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import bankapp.BankAccount;

class BankAccountTests {

	@Test
	void testSimpleDeposit() {
		
		BankAccount testAccount = new BankAccount();
		
		testAccount.deposit(25);
		
		assertEquals(25.0, testAccount.getBalance(), 0.01);	
	}
	
	@Test
	void testNegativeDeposit() {
		BankAccount testAccount = new BankAccount();
		
		try {
			testAccount.deposit(-25);
			fail();
		} catch (IllegalArgumentException e) {
			assertTrue(true);
		}
	}

	@Test
	void testZeroDeposit() {
		BankAccount testAccount = new BankAccount();
		
		testAccount.deposit(0);
		
		assertEquals(0, testAccount.getBalance(), 0.01);	
	}
	
	@Test
	void testMultipleDeposit() {
		BankAccount testAccount = new BankAccount();
		
		testAccount.deposit(25);
		testAccount.deposit(50);
		
		assertEquals(75.0, testAccount.getBalance(), 0.01);	
	}
	
	@Test
	void testDecimalDeposit() {
		BankAccount testAccount = new BankAccount();
		
		testAccount.deposit(12.34);
		
		assertEquals(12.34, testAccount.getBalance(), 0.01);	
	}
	
	@Test
	void testSimpleWithdraw() {
		
		BankAccount testAccount = new BankAccount();
		testAccount.deposit(50);
		
		testAccount.withdraw(25);
		
		assertEquals(25.0, testAccount.getBalance(), 0.01);	
	}
	
	@Test
	void testNegativeWithdraw() {
		BankAccount testAccount = new BankAccount();
		testAccount.deposit(50);
		
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
		BankAccount testAccount = new BankAccount();
		testAccount.deposit(20);
		
		testAccount.withdraw(0);
		
		assertEquals(20.0, testAccount.getBalance(), 0.01);	
	}
	
	@Test
	void testMultipleWithdraw() {
		BankAccount testAccount = new BankAccount();
		testAccount.deposit(100);
		
		testAccount.withdraw(25);
		testAccount.withdraw(50);
		
		assertEquals(25.0, testAccount.getBalance(), 0.01);	
	}
	
	@Test
	void testDecimalWithdraw() {
		BankAccount testAccount = new BankAccount();
		testAccount.deposit(20);
		
		testAccount.withdraw(10.30);
		
		assertEquals(9.70, testAccount.getBalance(), 0.01);	
	}
	
	@Test
	void testWithdrawMoreThanBalance() {	
		BankAccount testAccount = new BankAccount();
		testAccount.deposit(50);
		
		try {
			testAccount.withdraw(70);
			fail();
		} catch (IllegalArgumentException e) {
			//we expect to end up here
			assertTrue(true);
		}
	}
}
