package tests;

import fields.OurRefuge;
import game.GameBoard;
import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.Before;

public class GameBoardTest {
	GameBoard board;
	
	@Before
	public void setUp(){
		board = new GameBoard();
	}
	
	@Test
	public void testNumberOfFields() {
		board.getNumberOfFields();
		assertEquals(board.getNumberOfFields(),21);
	}
}