package tests;
import game.*;
import controllers.*;
import fields.*;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class FleetTest {
	Die die;
	Fleet fleet1;
	Fleet fleet2;
	Fleet fleet3;
	Fleet fleet4;
	FleetController fleetController;
	GUIManager display;
	Player player1;
	Player player2;
	
	@Before
	public void setUp(){
		die = new Die();
		fleet1 = new Fleet(4000, "skib", 1, 1);
		fleet2 = new Fleet(4000, "båd", 2, 2);
		fleet3 = new Fleet(4000, "Yacht", 3, 3);
		fleet4 = new Fleet(4000, "Færge", 4, 4);
		fleetController = new FleetController();
		display = new GUIManager("test", "", "Køb");
		player1 = new Player(1, "Mathias");
		player2 = new Player(2, "Victor");
	}
	
	@Test
	public void testPurchasingFields() {
		assertEquals(30000, player1.getBalance());
		assertEquals(true, fleetController.landOnField(player1, display, fleet1, die));
		assertEquals(26000, player1.getBalance());
		assertEquals(1, player1.getInventory()[0]);
		assertEquals(1, player1.getNumberOfFleetsOwned());
		assertEquals(1, player1.getNumberOfFieldsOwned());
		assertEquals(player1, fleet1.getOwner());
		assertEquals(true, fleetController.landOnField(player1, display, fleet2, die));
		assertEquals(22000, player1.getBalance());
		assertEquals(2, player1.getInventory()[1]);
		assertEquals(2, player1.getNumberOfFleetsOwned());
		assertEquals(2, player1.getNumberOfFieldsOwned());
		assertEquals(player1, fleet2.getOwner());
	}
	
	@Test
	public void testPurchasingFieldsWithoutMoney(){
		player1.getAcc().setBalance(1000);
		assertEquals(1000, player1.getBalance());
		assertEquals(true, fleetController.landOnField(player1, display, fleet1, die));
		assertEquals(1000, player1.getBalance());
		assertEquals(null, fleet1.getOwner());
		assertEquals(0, player1.getNumberOfFleetsOwned());
		assertEquals(0, player1.getNumberOfFieldsOwned());
		assertEquals(0, player1.getInventory()[0]);
	}
	
	@Test
	public void testLandingOnOwnField(){
		assertEquals(true, fleetController.landOnField(player1, display, fleet1, die));
		assertEquals(player1, fleet1.getOwner());
		assertEquals(26000, player1.getBalance());
		assertEquals(1, player1.getInventory()[0]);
		assertEquals(0, player1.getInventory()[1]);
		assertEquals(true, fleetController.landOnField(player1, display, fleet1, die));
		assertEquals(26000, player1.getBalance());
		assertEquals(1, player1.getInventory()[0]);
		assertEquals(0, player1.getInventory()[1]);
	}
	
	@Test
	public void testPayingRentWhenOneFleetOwned(){
		assertEquals(true, fleetController.landOnField(player1, display, fleet1, die));
		assertEquals(player1, fleet1.getOwner());
		assertEquals(26000, player1.getBalance());
		assertEquals(30000, player2.getBalance());
		assertEquals(true, fleetController.landOnField(player2, display, fleet1, die));
		assertEquals(26500, player1.getBalance());
		assertEquals(29500, player2.getBalance());
		assertEquals(true, fleetController.landOnField(player2, display, fleet1, die));
		assertEquals(27000, player1.getBalance());
		assertEquals(29000, player2.getBalance());
	}
	
	@Test
	public void testPayingRentWhenMultipleFleetsOwned(){
		assertEquals(true, fleetController.landOnField(player1, display, fleet1, die));
		assertEquals(true, fleetController.landOnField(player1, display, fleet2, die));
		assertEquals(player1, fleet1.getOwner());
		assertEquals(player1, fleet2.getOwner());
		assertEquals(22000, player1.getBalance());
		assertEquals(30000, player2.getBalance());
		assertEquals(true, fleetController.landOnField(player2, display, fleet1, die));
		assertEquals(23000, player1.getBalance());
		assertEquals(29000, player2.getBalance());
		assertEquals(true, fleetController.landOnField(player1, display, fleet3, die));
		assertEquals(player1, fleet3.getOwner());
		assertEquals(19000, player1.getBalance());
		assertEquals(true, fleetController.landOnField(player2, display, fleet3, die));
		assertEquals(21000, player1.getBalance());
		assertEquals(27000, player2.getBalance());
		assertEquals(true, fleetController.landOnField(player1, display, fleet4, die));
		assertEquals(player1, fleet4.getOwner());
		assertEquals(17000, player1.getBalance());
		assertEquals(true, fleetController.landOnField(player2, display, fleet4, die));
		assertEquals(21000, player1.getBalance());
		assertEquals(23000, player2.getBalance());
	}
}
