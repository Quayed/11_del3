package tests;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.Before;

import game.*;
import controllers.*;
import fields.*;

public class LaborCampTest {
	Die die;
	LaborCampController labcController;
	LaborCamp laborCamp;
	LaborCamp laborCamp2;
	Player player1;
	Player player2;
	GUIManager display;
	boolean HasLost1;
	boolean HasLost2;

	
	@Before
	public void setUp(){
		die = new Die(6, "test");
		labcController = new LaborCampController();
		laborCamp = new LaborCamp(2000, "Andeby", 15, 10);
		display = new GUIManager("test", "10%", "Køb");
		
	}	
	
	//@Test
	public void NotOwnedBuyHasMoney(){
		Player player = new Player(1,"Joachim von And");
		player.setField(10);
		labcController.landOnField(player, display, laborCamp, die);
		assertEquals(player.getInventory()[0],10);
		assertEquals(player.getNumberOfFieldsOwned(),1);
		assertEquals(player.getAcc().getBalance(),28000);
		assertEquals(labcController.landOnField(player, display, laborCamp, die),true);
	}
	//@Test
	public void NotOwnedBuyExactMoney(){
		Player player = new Player(1,"Joachim von And");
		player.setField(10);
		player.getAcc().setBalance(2000);
		labcController.landOnField(player, display, laborCamp, die);
		assertEquals(player.getInventory()[0],10);
		assertEquals(player.getNumberOfFieldsOwned(),1);
		assertEquals(player.getAcc().getBalance(),0);
		assertEquals(labcController.landOnField(player, display, laborCamp, die),true);
	}
	//@Test
	public void NotOwnedBuyNoMoney(){
		Player player = new Player(1,"Joachim von And");
		player.setField(10);
		player.getAcc().setBalance(1500);
		labcController.landOnField(player, display, laborCamp, die);
		assertEquals(player.getInventory()[0],0);
		assertEquals(player.getNumberOfFieldsOwned(),0);
		assertEquals(player.getAcc().getBalance(),1500);
		assertEquals(labcController.landOnField(player, display, laborCamp, die),true);
	}
	
	
	//@Test
	//Der testes om en spiller med rigeligt penge kan afvise et felt.  
	public void NotOwnedRejectHasMoney(){
		Player player = new Player(1,"Joachim von And");
		display = new GUIManager("test","10%","Afslå"); 
		labcController.landOnField(player, display, laborCamp, die);
		assertEquals(player.getAcc().getBalance(),30000);
		assertEquals((int)player.getInventory()[0],0);
		assertEquals(player.getNumberOfFieldsOwned(),0);	
	}
	
	@Test
	//Der testes om en spiller der lander på sit eget felt skal betale noget.
	public void OwnedSelf() {
		Player player = new Player(1,"Joachim von And");
		player.setField(10);
		labcController.landOnField(player, display, laborCamp, die);
		labcController.landOnField(player, display, laborCamp, die);
		assertEquals(player.getInventory()[0],10);
		assertEquals(player.getNumberOfFieldsOwned(),1);
		assertEquals(player.getAcc().getBalance(),28000);
		assertEquals(labcController.landOnField(player, display, laborCamp, die),true);
	}

	@Test
	//Der testes om en spiller med rigeligt penge, der lander på et allerede eget felt af en anden betaler og mister det rigtige antal penge,
	//og ikke taber spillet selvom man har penge nok 
	public void OwnedOtherOnePayRentAbove(){
		Player player1 = new Player(1,"Joachim Von And");
		Player player2 = new Player(2,"Guld-Iver Flintesten");
		HasLost1 = labcController.landOnField(player1, display, laborCamp, die);
		HasLost2 = labcController.landOnField(player2, display, laborCamp, die);
		assertEquals(player1.getInventory()[0],10);
		assertEquals(player1.getNumberOfFieldsOwned(),1);
		assertEquals(player1.getAcc().getBalance(),29200);
		
		assertEquals(player2.getInventory()[0],0);
		assertEquals(player2.getNumberOfFieldsOwned(),0);
		assertEquals(player2.getAcc().getBalance(),28800);
		
		assertEquals(HasLost1,true);
		assertEquals(HasLost2,true);

	}
	
	@Test
	//Der testes om en spiller med det nøjagtige antal penge til at betale en anden spiller, betaler det rigtige beløb
	//og ikke ryger ud af spillet
	public void OwnedOtherOnePayRentExact(){
		Player player1 = new Player(1,"Joachim Von And");
		Player player2 = new Player(2,"Guld-Iver Flintesten");
		player2.getAcc().setBalance(1200);
		
		HasLost1 = labcController.landOnField(player1, display, laborCamp, die);
		HasLost2 = labcController.landOnField(player2, display, laborCamp, die);
		assertEquals(player1.getInventory()[0],10);
		assertEquals(player1.getNumberOfFieldsOwned(),1);
		assertEquals(player1.getAcc().getBalance(),29200);
		
		assertEquals(player2.getInventory()[0],0);
		assertEquals(player2.getNumberOfFieldsOwned(),0);
		assertEquals(player2.getAcc().getBalance(),0);
		
		assertEquals(HasLost1,true);
		assertEquals(HasLost2,true);
	}
	
	//Der undersøges om en spiller uden penge nok betaler hvad han har tilbage til den anden spiller, 
	//og den anden spiller får det rigtige beløb. Til sidst undersøges der hvilke af spillerne der har tabt. 
	@Test 
	public void OwnedOtherOnePayRentBelow(){
		Player player1 = new Player(1,"Joachim Von And");
		Player player2 = new Player(2,"Guld-Iver Flintesten");
		player2.getAcc().setBalance(600);
		
		HasLost1 = labcController.landOnField(player1, display, laborCamp, die);
		HasLost2 = labcController.landOnField(player2, display, laborCamp, die);
		assertEquals(player1.getInventory()[0],10);
		assertEquals(player1.getNumberOfFieldsOwned(),1);
		assertEquals(player1.getAcc().getBalance(),28600);
		
		assertEquals(player2.getInventory()[0],0);
		assertEquals(player2.getNumberOfFieldsOwned(),0);
		
		assertEquals(HasLost1,true);
		assertEquals(HasLost2,false);
	}

		
		
	
	//Denne gang ejer han det andet felt 

	public void OwnedOtherTwoPayRentAbove(){
		Player player1 = new Player(1,"Joachim Von And");
		Player player2 = new Player(2,"Guld-Iver Flintesten");
		laborCamp2 = new LaborCamp(2000, "Bjørnehulen", 16, 11);
		
		labcController.landOnField(player1, display, laborCamp2, die);
		HasLost1 = labcController.landOnField(player1, display, laborCamp, die);
		HasLost2 = labcController.landOnField(player2, display, laborCamp, die);
		assertEquals(player1.getInventory()[0],10);
		assertEquals(player1.getNumberOfFieldsOwned(),1);
		assertEquals(player1.getAcc().getBalance(),30400);
		
		assertEquals(player2.getInventory()[0],0);
		assertEquals(player2.getNumberOfFieldsOwned(),0);
		assertEquals(player2.getAcc().getBalance(),27600);
		
		assertEquals(HasLost1,true);
		assertEquals(HasLost2,true);
		
	}
	
	@Test
	//Der testes om en spiller med det nøjagtige antal penge til at betale en anden spiller, betaler det rigtige beløb
	//og ikke ryger ud af spillet
	public void OwnedOtherTwoPayRentExact(){
		Player player1 = new Player(1,"Joachim Von And");
		Player player2 = new Player(2,"Guld-Iver Flintesten");
		laborCamp2 = new LaborCamp(2000, "Bjørnehulen", 16, 11);
		player2.getAcc().setBalance(2400);
		
		labcController.landOnField(player1, display, laborCamp2, die);
		HasLost1 = labcController.landOnField(player1, display, laborCamp, die);
		HasLost2 = labcController.landOnField(player2, display, laborCamp, die);
		assertEquals(player1.getInventory()[0],11);
		assertEquals(player1.getNumberOfFieldsOwned(),2);
		assertEquals(player1.getAcc().getBalance(),28400);
		
		assertEquals(player2.getInventory()[0],0);
		assertEquals(player2.getNumberOfFieldsOwned(),0);
		assertEquals(player2.getAcc().getBalance(),0);
		
		assertEquals(HasLost1,true);
		assertEquals(HasLost2,true);
	}
	
	//Der undersøges om en spiller uden penge nok betaler hvad han har tilbage til den anden spiller, 
	//og den anden spiller får det rigtige beløb. Til sidst undersøges der hvilke af spillerne der har tabt. 
	@Test 
	public void OwnedOtherTwoPayRentBelow(){
		Player player1 = new Player(1,"Joachim Von And");
		Player player2 = new Player(2,"Guld-Iver Flintesten");
		laborCamp2 = new LaborCamp(2000, "Bjørnehulen", 16, 11);
		player2.getAcc().setBalance(1200);
		
		labcController.landOnField(player1, display, laborCamp2, die);
		HasLost1 = labcController.landOnField(player1, display, laborCamp, die);
		HasLost2 = labcController.landOnField(player2, display, laborCamp, die);
		assertEquals(player1.getInventory()[0],11);
		assertEquals(player1.getNumberOfFieldsOwned(),2);
		assertEquals(player1.getAcc().getBalance(),27200);
		
		assertEquals(player2.getInventory()[0],0);
		assertEquals(player2.getNumberOfFieldsOwned(),0);
		
		assertEquals(HasLost1,true);
		assertEquals(HasLost2,false);
	}
	
	
}
