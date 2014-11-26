package tests;

import static org.junit.Assert.*;

import org.junit.Test;

import fields.*;
import game.*;
import controllers.*;
public class TestTerritory {

	@Test
	public void NotOwnedBuyHasMoney() {
		Player player = new Player(1,"Joachim von And");
		Die die = new Die();
		GUIManager display = new GUIManager("test","10%","Køb");
		Territory territory = new Territory(1000, 2000, "Andeby", 2, 10);
		TerritoryController Territory = new TerritoryController(); 
		player.setField(10);
		Territory.landOnField(player, display, territory, die);
		assertEquals(player.getAcc().getBalance(),28000);
		assertEquals(player.getField(),10);
		assertEquals(player.getInventory()[0],10);
		assertEquals(Territory.landOnField(player, display, territory, die),true);
	}
	
	@Test
	public void NotOwnedBuyNoMoney() {
		Player player = new Player(1,"Anders And");
		player.getAcc().setBalance(1500);
		Die die = new Die();
		GUIManager display = new GUIManager("test","10%","Køb");
		Territory territory = new Territory(1000, 2000, "Andeby", 2, 10);
		TerritoryController Territory = new TerritoryController(); 
		player.setField(10);
		Territory.landOnField(player, display, territory, die);
		assertEquals(player.getAcc().getBalance(),1500);
		assertEquals(player.getField(),10);
		assertEquals(player.getInventory()[0],0);
		assertEquals(Territory.landOnField(player, display, territory, die),true);
	}
	@Test
	public void NotOwnedBuyEqualMoney() {
		Player player = new Player(1,"Anders And");
		player.getAcc().setBalance(2000);
		Die die = new Die();
		GUIManager display = new GUIManager("test","10%","Køb");
		Territory territory = new Territory(1000, 2000, "Andeby", 2, 10);
		TerritoryController Territory = new TerritoryController(); 
		player.setField(10);
		Territory.landOnField(player, display, territory, die);
		assertEquals(player.getAcc().getBalance(),0);
		assertEquals(player.getField(),10);
		assertEquals(player.getInventory()[0],10);
		assertEquals(Territory.landOnField(player, display, territory, die),true);
		
	}
	@Test
	public void NotOwnedRejectHasMoney() {
		Player player = new Player(1,"Anders And");
		player.getAcc().setBalance(2001);
		Die die = new Die();
		GUIManager display = new GUIManager("test","10%","Afslå");
		Territory territory = new Territory(1000, 2000, "Andeby", 2, 10);
		TerritoryController Territory = new TerritoryController(); 
		player.setField(10);
		Territory.landOnField(player, display, territory, die);
		assertEquals(player.getAcc().getBalance(),2001);
		assertEquals(player.getField(),10);
		assertEquals(player.getInventory()[0],0);
		assertEquals(Territory.landOnField(player, display, territory, die),true);
	}
	@Test
	public void OwnedSelf() {
		Player player = new Player(1,"Onkel Joachim");
		player.getAcc().setBalance(6000);
		Die die = new Die();
		GUIManager display = new GUIManager("test","10%","Køb");
		Territory territory = new Territory(1000, 2000, "Andeby", 2, 10);
		TerritoryController Territory = new TerritoryController(); 
		player.setField(10);
		Territory.landOnField(player, display, territory, die);
		Territory.landOnField(player, display, territory, die);
		assertEquals(player.getAcc().getBalance(),4000);
		assertEquals(player.getField(),10);
		assertEquals(player.getInventory()[1],0);
		assertEquals(Territory.landOnField(player, display, territory, die),true);
	}

	@Test
	public void OwnedOtherPayRentAbove() {
		Player player1 = new Player(1,"Anders And");
		Player player2 = new Player(2,"Fætter Højben");
		Die die = new Die();
		GUIManager display = new GUIManager("test","10%","Køb");
		Territory territory = new Territory(10000, 2000, "Andeby", 2, 10);
		TerritoryController Territory = new TerritoryController(); 
		player2.setField(10);
		player1.setField(10);
		Territory.landOnField(player2, display, territory, die);
		Territory.landOnField(player1, display, territory, die);
		assertEquals(player1.getAcc().getBalance(),20000);
		assertEquals(player2.getAcc().getBalance(),38000);
		assertEquals(player2.getInventory()[0],10);
		assertEquals(player1.getInventory()[0],0);
		assertEquals(Territory.landOnField(player1, display, territory, die),true);
		assertEquals(Territory.landOnField(player2, display, territory, die),true);
	}
	
	@Test
	public void OwnedOtherPayRentExact() {
		Player player1 = new Player(1,"Anders And");
		Player player2 = new Player(2,"Fætter Højben");
		Die die = new Die();
		player1.getAcc().setBalance(10000);
		GUIManager display = new GUIManager("test","10%","Køb");
		Territory territory = new Territory(10000, 2000, "Andeby", 2, 10);
		TerritoryController Territory = new TerritoryController(); 
		player2.setField(10);
		player1.setField(10);
		boolean HasLost2 = Territory.landOnField(player2, display, territory, die);
		boolean HasLost1 = Territory.landOnField(player1, display, territory, die);
		assertEquals(player1.getAcc().getBalance(),0);
		assertEquals(player2.getAcc().getBalance(),38000);
		assertEquals(player2.getInventory()[0],10);
		assertEquals(player1.getInventory()[0],0);
		assertEquals(HasLost1,true);
		assertEquals(HasLost2,true);
		
	}
	
	@Test
	public void OwnedOtherPayRentBelow() {
		Player player1 = new Player(1,"Anders And");
		Player player2 = new Player(2,"Fætter Højben");
		Die die = new Die();
		player1.getAcc().setBalance(9000);
		GUIManager display = new GUIManager("test","10%","Køb");
		Territory territory = new Territory(10000, 2000, "Andeby", 2, 10);
		TerritoryController Territory = new TerritoryController(); 
		player2.setField(10);
		player1.setField(10);
		boolean HasLost2 = Territory.landOnField(player2, display, territory, die);
		boolean HasLost1 = Territory.landOnField(player1, display, territory, die);
		assertEquals(37000, player2.getAcc().getBalance());
		assertEquals(player2.getInventory()[0],10);
		assertEquals(player1.getInventory()[0],0);
		assertEquals(Territory.landOnField(player1, display, territory, die),false);
		assertEquals(Territory.landOnField(player2, display, territory, die),true);

	}
}
