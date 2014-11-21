package game;
import java.awt.Color;

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
                        
			//Landing på felter
			switch(board.getField(activePlayer.getField()-1).getType()) {
			case("Territory"):
				Territory currentTerritory = (Territory) board.getField(activePlayer.getField()-1);
			
				if (currentTerritory.isOwner(activePlayer)){
					System.out.println("Du er ejeren af denne grund");
				}
				else if(!currentTerritory.isOwned()){
					if(display.chooseToBuyTerritory(currentTerritory.getName(), currentTerritory.getPrice(), activePlayer, currentTerritory.getRent()) == "Køb"){
						if(activePlayer.getAcc().getBalance() > currentTerritory.getPrice()){
							activePlayer.getAcc().withdraw(currentTerritory.getPrice());
							currentTerritory.setOwner(activePlayer);
							display.setOwner(activePlayer.getField(), activePlayer.getName());
						} else{
							display.sendMessage("Du har ikke nok penge til at købe denne grund");
						}
					}
				}
				else{
					System.out.println("Nu skal du sku betale");
					display.sendMessage(activePlayer.getName() + " er landet på " + currentTerritory.getName() + ". Grunden er ejet, du skal betale " + currentTerritory.getRent() + " i leje.");
					activePlayer.getAcc().transfer(currentTerritory.getOwner().getAcc(), currentTerritory.getRent());
				}
				break;
			case("LaborCamp"):
				//statements
				break;
			case("Refuge"):
				//statements
				break;
			case("Fleet"):
				//statements
				break;
			case("Tax"):
				//statements
				break;
			}

			if (activePlayer.getField() == 19) 
				activePlayer.setPayMethod(display.choosePayment(activePlayer));
			board.getField(activePlayer.getField()-1).landOnField(activePlayer, display);
			//Opdatering af gameboard
			
			turn = ++turn % numberOfPlayers;
			for(int i = 0; i < numberOfPlayers; i++) {
				display.updateBalance(players[i]);
				//Det kontrolleres om spilleren er gået bankerot
				if(players[i].getAcc().getBalance() < 0){
					players[i].bankruptcy();
				}
			}
		}	
	}
}
