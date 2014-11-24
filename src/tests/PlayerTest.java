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
	}

}
