package tests;
import game.*;
import fields.*;
import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.Before;

public class Taxtest {
	Player player;
	
	@Before
	public void setup() {
		Player player = new Player();
		
		
	}

	@Test
	public void test() {
	
		player.setField(19);
		GameBoard board = new GameBoard();
		OurTax currentTax =  (OurTax) board.getField(player.getField() -1);
		
		//10%
		int assets = player.getTotalAssets(board);
		assets = (int)(assets*currentTax.getTaxRate());

		assertEquals(assets,500);

		//Spilleren bliver tildelt et felt
		Territory field = (Territory) board.getField(2);
		field.setOwner(player);
		
		assets = player.getTotalAssets(board);
		System.out.println(assets);
		assertEquals(assets,600);
	}
}
