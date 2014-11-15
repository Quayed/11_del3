package game;
import javax.swing.JFrame;
import javax.swing.JOptionPane;


public class GameController {

	public void run() {
		Die dice = new Die();
		Player players[] = new Player[6];
		Account accounts[] = new Account[6];
		int numberOfPlayers;
		
		
		//Kør spillet
		GUIManager display = new GUIManager();
		display.create();
		
		
		//Vælg antal spillere
		numberOfPlayers = display.getNumberOfPlayers();
		
		for(int i = 0; i < numberOfPlayers; i++){
			// her bør der tilføjes muligheden for spillerne at vælge navn - dette skal dog først gøres til sidst.
			players[i] = new Player(i, "Spiller"+i);
			accounts[i] = players[i].getAcc();
			display.addPlayer(players[i].getName(), accounts[i].getBalance());
		}
		
		//Første terningekast
		
		
		
		
		
		
	}

}
