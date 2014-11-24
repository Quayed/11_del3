package tests;

import static org.junit.Assert.*;

import org.junit.Test;
import game.*;
public class PlayerTest {

	@Test
	public void test() {
		//Vi starter med at oprette en spiller med navnet "THOMAS" og ID 101. Derefter ser vi om THOMAS har de rigtige startegenskaber
		Player player = new Player(101, "THOMAS");
		assertEquals(player.getName(),"THOMAS");
		assertEquals(player.getId(),101);
		assertEquals(player.getStartMoney(),5000);
		assertEquals(player.getField(),0);
		assertEquals(player.getNumberOfFleetsOwned(),0);
		assertEquals(player.getNumberOfLaborCampsOwned(),0);
		assertEquals(player.getNumberOfFieldsOwned(),0);
		player.addNumberOfFleetsOwned();
		player.addNumberOfLaborCamps();
		player.addToInventory(1);
		assertEquals(player.getNumberOfFleetsOwned(),1);
		assertEquals(player.getNumberOfLaborCampsOwned(),1);
		assertEquals(player.getNumberOfFieldsOwned(),1);
		assertEquals(player.isHasLost(),false);
		player.setHasLost(true);		
		assertEquals(player.isHasLost(),true);
		
	}

}
