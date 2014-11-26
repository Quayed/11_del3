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
	}
	@Test
	public void OwnedSelf() {
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
	}
	
}
