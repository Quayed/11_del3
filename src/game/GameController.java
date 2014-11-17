package game;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class GameController {
	
	public GameController(){
		
	}
	
	public void run() {
		Die dice = new Die();
		Player players[] = new Player[6];
		int numberOfPlayers;
		int turn = 0; 
		int dieOne, dieTwo, field;
		
		
		//Kør spillet
		GUIManager display = new GUIManager();
		display.create();
		
		
		//Vælg antal spillere
		numberOfPlayers = display.getNumberOfPlayers();
		
		for(int i = 0; i < numberOfPlayers; i++){
			// her bør der tilføjes muligheden for spillerne at vælge navn - dette skal dog først gøres til sidst.
			players[i] = new Player(i, "Spiller"+i);
			display.addPlayer(players[i].getName(), players[i].getAcc().getBalance());
		}
		
		//Første terningekast
		
		while(true){
			display.roll(players[turn].getName());
			dieOne = dice.roll();
			dieTwo = dice.roll();
			display.setDice(dieOne, dieTwo);
			players[turn].move(dieOne+dieTwo);
			display.movePlayer(players[turn].getPrevField(), players[turn].getField(), players[turn].getName());
			turn = ++turn % numberOfPlayers;
		}
		
		
		
		
	}

}
