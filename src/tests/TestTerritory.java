package tests;

import static org.junit.Assert.*;

import org.junit.Test;

import fields.*;
import game.*;
import controllers.*;
public class TestTerritory {

	@Test
	public void testNotOwnedMoney() {
		Player player = new Player(1,"Joachim von And");
		Die die = new Die();
		GUIManager display = new GUIManager("test","10%","Køb");
		Territory territory = new Territory(1000, 2000, "Andeby", 1, 1);
		TerritoryController Territory = new TerritoryController(); 
		Territory.landOnField(player, display, territory, die);
		//System.out.println(player.getField());
		//System.out.println(player.getAcc());
		//System.out.println(player.getInventory());
		
		
	}

}
