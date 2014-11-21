package game;
import java.awt.Color;

public class GameController {
	Territory currentTerritory;
	Fleet currentFleet;
	LaborCamp currentLaborCamp;
	OurRefuge currentRefuge;
	OurTax currentTax;
	Ownable currentOwnable;
	Player activePlayer;
	GameBoard board;
	Die dice;
	Player players[];
	Color colors[];
	GUIManager display;
	public GameController(){
		
	}
	
	public void run() {
		board = new GameBoard();
		dice = new Die();
		players = new Player[6];
		colors = new Color[] {Color.BLUE, Color.RED, Color.GREEN, Color.CYAN, Color.ORANGE, Color.YELLOW};
		int numberOfPlayers;
		int turn = 0;
		int dieOne, dieTwo;
		//Kør spillet
		display = new GUIManager();
		display.create(board);
		
		
		//Vælg antal spillere
		numberOfPlayers = display.getNumberOfPlayers();
		
		for(int i = 0; i < numberOfPlayers; i++){
			// her bør der tilføjes muligheden for spillerne at vælge navn - dette skal dog først gøres til sidst.
			players[i] = new Player(i, "Spiller "+i);
			display.addPlayer(players[i].getName(), players[i].getAcc().getBalance(), colors[i]);
		}
		
		//loop er får vores spil til at køre.
		while(true){
			activePlayer = players[turn];
			if (activePlayer == null) continue; // tjekker om spilleren har tabt eller stadig er med.
			display.roll(activePlayer);
			dieOne = dice.roll();
			dieTwo = dice.roll();
			display.setDice(dieOne, dieTwo);
			activePlayer.move(dieOne+dieTwo);
			
			//Spiller bevæger sig
			display.movePlayer(activePlayer.getPrevField(), activePlayer.getField(), activePlayer.getName());
                        
			//Logik til at kontrollere hvilket felt der er landet på.
			switch(board.getField(activePlayer.getField()-1).getType()) {
			case("Territory"):
				currentTerritory = (Territory) board.getField(activePlayer.getField()-1);
				if (currentTerritory.isOwner(activePlayer)){
					
				}
				else if(!currentTerritory.isOwned()){
					if(display.chooseToBuyTerritory(currentTerritory.getName(), currentTerritory.getPrice(), activePlayer, currentTerritory.getRent()) == "Køb"){
						buyField(currentTerritory);
					}
				}
				else{
					display.sendMessage(activePlayer.getName() + " er landet på " + currentTerritory.getName() + ". Grunden er ejet, du skal betale " + currentTerritory.getRent() + " i leje.");
					activePlayer.getAcc().transfer(currentTerritory.getOwner().getAcc(), currentTerritory.getRent());
				}
				break;
				
			case("LaborCamp"):
				//statements
				currentLaborCamp = (LaborCamp) board.getField(activePlayer.getField()-1);
			    if(currentLaborCamp.isOwned()){
			    	if(!currentLaborCamp.isOwner(activePlayer)){
			    		if(activePlayer.getAcc().getBalance() > currentLaborCamp.getPrice()){
			    			//Jeg sender en besked han skal bekræfte for at fortsætte, hvor der står hvilket felt han har landt på og hvad der skal ske
			    			display.sendMessage(activePlayer.getName() + "er landet på " + currentLaborCamp.getName() + "og skal slå med tegningerne. Der betales 100*øjne*ejet Labor Camps");
			    			//Jeg slår med 2 terninger, og viser dette i grafikken
			    			dieOne = dice.roll();
			    			dieTwo = dice.roll();
			    			display.setDice(dieOne, dieTwo);
			    			//Jeg udregner hvad spilleren skal betale til ejeren. Dette er øjne*ejet*100
			    			int rent = currentLaborCamp.getOwner().getNumberOfLaborCampsOwned()*100*(dieOne+dieTwo);
			    			//Jeg sender en besked han skal bekræfte for at fortsætte, hvor der står hvad han slog og hvad han skal betale
			    			display.sendMessage("du har slået " + (dieOne + dieTwo) + ", og skal betale " + rent);
			    			//Jeg sender penge fra den aktive spiller til ejeren af feltet. Jeg ved han har penge nok da dette var condition til at komme herned 
			    			activePlayer.getAcc().transfer(currentLaborCamp.getOwner().getAcc(), rent);
			    		}
			    		else{
			    			//her skal han smides ud
			    		}
			    	}
			    }else{
			    	if(display.chooseToBuyLaborCamp(currentLaborCamp.getName(), currentLaborCamp.getPrice(), activePlayer) == "Køb"){
			    		buyField(currentLaborCamp);
			    	}
			    }
				break;
				
			case("Refuge"):
				currentRefuge = (OurRefuge) board.getField(activePlayer.getField()-1);
				display.sendMessage(activePlayer.getName() + " landede på " + currentRefuge.getName() + " og modtager " + currentRefuge.getBonus());
				activePlayer.getAcc().deposit(currentRefuge.getBonus());
				break;
				
			case("Fleet"):
				currentFleet = (Fleet) board.getField(activePlayer.getField()-1);
				if(currentFleet.isOwned()){
					if(!currentFleet.isOwner(activePlayer)){
						if(activePlayer.getAcc().getBalance() > currentFleet.getPrice()){
							display.sendMessage(activePlayer.getName() + " er landet på " + currentFleet.getName() + " og skal betale " + currentFleet.getRent() + " kroner.");
							//Her overføres penge fra spilleren der landte på 
							activePlayer.getAcc().transfer(currentFleet.getOwner().getAcc(), currentFleet.getRent());
						} else{
							// her skal der kaldes en metode for at spilleren har tabt.
						}
					}
				} else{
					if(display.chooseToBuyFleet(currentFleet.getName(), currentFleet.getPrice(), activePlayer) == "Køb"){
						buyField(currentFleet);
					}
				}
				break;
				
			case("Tax"):
				currentTax =  (OurTax) board.getField(activePlayer.getField() -1); 
				if(activePlayer.getField() == 9) {
					display.sendMessage(activePlayer.getName() + " er landet på " + currentTax.getName() + " og skal betale 2000 kroner i skat.");
					activePlayer.getAcc().withdraw(2000);
				}else if (activePlayer.getField() == 19) {
					switch (display.choosePayment(activePlayer.getName())) {
					case "10%":
						int totalAssets = activePlayer.getAcc().getBalance();
						int[] playerInventory = activePlayer.getInventory();
						for (int i = 0; i < playerInventory.length; i++){
							if (playerInventory[i] != 0){
								Ownable currentOwnable = (Ownable) board.getField(playerInventory[i]-1);
								totalAssets += currentOwnable.getPrice();
							}
						}
						activePlayer.getAcc().withdraw((int) (totalAssets*currentTax.getTaxRate()));
						break;
					case "4000":
						activePlayer.getAcc().withdraw(4000);
						break;
					}
				}
				break;
			}

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
	
	private void buyField(Ownable field){
		if(activePlayer.getAcc().getBalance() > field.getPrice()){
    		activePlayer.getAcc().withdraw(field.getPrice());
    		field.setOwner(activePlayer);
    		activePlayer.addNumberOfLaborCamps();
    		display.setOwner(activePlayer.getField(), activePlayer.getName());
    	}
    	else{
    		display.sendMessage("Du har ikke nok penge til at købe denne grund");
    	}
	}
}
