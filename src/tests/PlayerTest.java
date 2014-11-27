package tests;

import static org.junit.Assert.*;

import org.junit.Test;
import game.*;
public class PlayerTest {

	@Test
	public void TestInitialConditions() {
		//Test af start egenskab:
		Player player = new Player(101, "THOMAS");
		assertEquals(player.getName(),"THOMAS");
		assertEquals(player.getId(),101);
		assertEquals(player.getStartMoney(),30000);
		assertEquals(player.getNumberOfFleetsOwned(),0);
		assertEquals(player.getNumberOfLaborCampsOwned(),0);
		assertEquals(player.getNumberOfFieldsOwned(),0);
	}
	
	@Test
	//Vi tester add og set metoderne, for felterne.
	public void testAddSet()  {
		Player player = new Player(101, "THOMAS");
		player.addNumberOfFleetsOwned();
		player.addNumberOfLaborCamps();
		player.addToInventory(10,1000);
		assertEquals(player.getNumberOfFleetsOwned(),1);
		assertEquals(player.getNumberOfLaborCampsOwned(),1);
		assertEquals(player.getNumberOfFieldsOwned(),1);
		assertEquals(player.getInventory()[0],10);
	}
	
	@Test
	//Vi tester om metoden Haslost, setHasLost og isHasLost virker.
	public void testLost(){
		Player player = new Player(101, "THOMAS");
		assertEquals(player.isHasLost(),false);
		player.setHasLost(true);		
		assertEquals(player.isHasLost(),true);
	}

	@Test
	//VI tester Field og move metoderne virker.
	public void testFieldmove() {
		Player player = new Player(101, "THOMAS");
		assertEquals(player.getField(),0);
		player.move(6);
		assertEquals(player.getField(),6);
		player.move(15);
		assertEquals(player.getField(),21);
		player.move(12);
		assertEquals(player.getField(),12);
		assertEquals(player.getPrevField(),21);		
	}
	


}
