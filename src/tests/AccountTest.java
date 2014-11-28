package tests;
import game.*;
import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class AccountTest {
	
	@Test
	public void testDeposit() {
		Account acc = new Account(1000, 1);
		int expected = 1000;
		assertEquals(expected, acc.getBalance());
		acc.deposit(-100);
		assertEquals(expected, acc.getBalance());
		acc.deposit(100);
		expected += 100;
		assertEquals(expected, acc.getBalance());
		acc.deposit(-500);
		assertEquals(expected, acc.getBalance());
	}
	
	@Test
	public void testWithdraw(){
		Account acc = new Account(1000, 1);
		int expected = 1000;
		assertEquals(expected, acc.getBalance());
		acc.withdraw(100); // balance = 900
		expected -= 100; // expected now 900
		assertEquals(expected, acc.getBalance());
		// test that it returns true when the account does have enough money
		assertEquals(true, acc.withdraw(100)); // balance = 800
		expected -= 100; // expected now 800
		assertEquals(expected, acc.getBalance()); 		
		// test that it return false when the account doesn't have enough money
		assertEquals(false, acc.withdraw(1000)); // balance doesn't change
		assertEquals(expected, acc.getBalance());		
	}
	
	@Test
	public void testTransfer(){
		Player player1 = new Player();
		Player player2 = new Player();
		player1.getAcc().setBalance(1000);
		player2.getAcc().setBalance(1000);
		
		player1.getAcc().transfer(player2.getAcc(), 500);
		
		assertEquals(player1.getAcc().getBalance(),500);
		assertEquals(player2.getAcc().getBalance(),1500);
	}

}
