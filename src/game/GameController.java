package game;
import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class GameController {
	
	public GameController(){
		
	}
	
	public void run() {
		GameBoard board = new GameBoard();
		Die dice = new Die();
		Player players[] = new Player[6];
		Color colors[] = new Color[] {Color.BLUE, Color.RED, Color.GREEN, Color.CYAN, Color.ORANGE, Color.YELLOW};
		int numberOfPlayers;
		int turn = 0;
		int dieOne, dieTwo, field;
		
		
		//Kør spillet
		GUIManager display = new GUIManager();
		display.create(board);
		
		
		//Vælg antal spillere
		numberOfPlayers = display.getNumberOfPlayers();
		
		for(int i = 0; i < numberOfPlayers; i++){
			// her bør der tilføjes muligheden for spillerne at vælge navn - dette skal dog først gøres til sidst.
			players[i] = new Player(i, "Spiller "+i);
			display.addPlayer(players[i].getName(), players[i].getAcc().getBalance(), colors[i]);
		}
		
		//Første terningekast
		Player activePlayer;
		while(true){
			activePlayer = players[turn];
			display.roll(activePlayer);
			dieOne = dice.roll();
			dieTwo = dice.roll();
			display.setDice(dieOne, dieTwo);
			activePlayer.move(dieOne+dieTwo);
			//Spiller bevæger sig
			display.movePlayer(activePlayer.getPrevField(), activePlayer.getField(), activePlayer.getName());
			
                        
			//Landing på felt
			if (activePlayer.getField() == 19) 
				activePlayer.setPayMethod(display.choosePayment(activePlayer));
			board.getField(activePlayer.getField()-1).landOnField(activePlayer, display);
			//Opdatering af gameboard
			
			
			
			turn = ++turn % numberOfPlayers;
			for(int i = 0; i < numberOfPlayers; i++) {
				display.updateBalance(players[i]);
			}
		}
		
	}

}
