package game;
import javax.swing.JFrame;
import javax.swing.JOptionPane;


public class GameController {

	public void run() {
		Die dice = new Die();
		int players;
		
		//Vælg antal spillere
		players = 2;
		Player player1 = new Player(1, "Jens");
		Player player2 = new Player(2, "Jens2");

		//Kør spillet
		GameBoard Board =  new GameBoard();
		
		//Opsætning af bordet
		GUIManager.create();
		GUIManager.addPlayer("JENS", 1000);
		
		//Første terningekast
		
		
		
		
		
		
	}

}
