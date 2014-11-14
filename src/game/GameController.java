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
		GUIManager display = new GUIManager();
		display.create();
		
		//Opsætning af bordet
		GUIManager.create();
		GUIManager.addPlayer("JENS", 1000);
		
		//Første terningekast
		
		
		
		
		
		
	}

}
