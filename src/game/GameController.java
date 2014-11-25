package game;
import java.awt.Color;

import fields.Fleet;
import fields.LaborCamp;
import fields.OurRefuge;
import fields.OurTax;
import fields.Ownable;
import fields.Territory;

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
			if (activePlayer == null) {// tjekker om spilleren har tabt eller stadig er med.
				turn = ++turn % numberOfPlayers;
				continue; 
			}
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
					if(!activePlayer.getAcc().transfer(currentTerritory.getOwner().getAcc(), currentTerritory.getRent())){
						bankruptcy(turn);
					}
				}
				break;
				
			case("LaborCamp"):
				//statements
				currentLaborCamp = (LaborCamp) board.getField(activePlayer.getField()-1);
			    if(currentLaborCamp.isOwned()){
			    	if(!currentLaborCamp.isOwner(activePlayer)){
			    			//Jeg sender en besked han skal bekræfte for at fortsætte, hvor der står hvilket felt han har landt på og hvad der skal ske
			    			display.sendMessage(activePlayer.getName() + "er landet på " + currentLaborCamp.getName() + "og skal slå med tegningerne. Der betales 100*øjne*ejet Labor Camps.");
			    			//Jeg slår med 2 terninger, og viser dette i grafikken
			    			dieOne = dice.roll();
			    			dieTwo = dice.roll();
			    			display.setDice(dieOne, dieTwo);
			    			//Jeg udregner hvad spilleren skal betale til ejeren. Dette er øjne*ejet*100
			    			int rent = currentLaborCamp.getOwner().getNumberOfLaborCampsOwned()*100*(dieOne+dieTwo);
			    			//Jeg sender en besked han skal bekræfte for at fortsætte, hvor der står hvad han slog og hvad han skal betale
			    			display.sendMessage("Du har slået " + (dieOne + dieTwo) + ", og skal betale " + rent + ".");
			    			//Jeg sender penge fra den aktive spiller til ejeren af feltet. Jeg ved han har penge nok da dette var condition til at komme herned 
			    			if(!activePlayer.getAcc().transfer(currentLaborCamp.getOwner().getAcc(), rent)){
			    				bankruptcy(turn);
			    			}
			    	}
			    }else{
			    	if(display.chooseToBuyLaborCamp(currentLaborCamp.getName(), currentLaborCamp.getPrice(), activePlayer) == "Køb"){
			    		if(buyField(currentLaborCamp)){
			    			activePlayer.addNumberOfLaborCamps();
			    		}
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
							if(!activePlayer.getAcc().transfer(currentFleet.getOwner().getAcc(), currentFleet.getRent())){
								bankruptcy(turn);
							}
						} 
					}
					
				} else{
					if(display.chooseToBuyFleet(currentFleet.getName(), currentFleet.getPrice(), activePlayer) == "Køb"){
						if(buyField(currentFleet)){
							activePlayer.addNumberOfFleetsOwned();
						}
					}
				}
				break;
				
			case("Tax"):
				currentTax =  (OurTax) board.getField(activePlayer.getField() -1); 
				if(activePlayer.getField() == 9) {
					display.sendMessage(activePlayer.getName() + " er landet på " + currentTax.getName() + " og skal betale 2000 kroner i skat.");
					if(!activePlayer.getAcc().withdraw(2000)){
						bankruptcy(turn);
					}
				}else if (activePlayer.getField() == 19) {
					switch (display.choosePayment(activePlayer.getName())) {
					case "10%":
						//Samlede værdi hentes
						int totalAssets = activePlayer.getTotalAssets(board);

						if(!activePlayer.getAcc().withdraw((int) (totalAssets*currentTax.getTaxRate()))){
							bankruptcy(turn);
						}
						break;
					case "4000":
						if(!activePlayer.getAcc().withdraw(4000)){
							bankruptcy(turn);
						}
						break;
					}
				}
				break;
			}

			//Opdatering af gameboard
			
			turn = ++turn % numberOfPlayers;
			for(int i = 0; i < numberOfPlayers; i++) {
				if (players[i] == null) continue;
				display.updateBalance(players[i]);
			}
		}	
	}
	
	private boolean buyField(Ownable field){
		if(activePlayer.getAcc().getBalance() >= field.getPrice()){
    		activePlayer.getAcc().withdraw(field.getPrice());
    		activePlayer.addToInventory(activePlayer.getField());
    		field.setOwner(activePlayer);
    		display.setOwner(activePlayer.getField(), activePlayer.getName());
    		return true;
    	}
    	else{
    		display.sendMessage("Du har ikke nok penge til at købe denne grund.");
    		return false;
    	}
	}
	
	private void bankruptcy(int turn){
		int[] playerInventory = activePlayer.getInventory();
		for (int i = 0; i < playerInventory.length; i++){
			if (playerInventory[i] != 0){
				Ownable currentOwnable = (Ownable) board.getField(playerInventory[i]-1);
				currentOwnable.setOwner(null);
				display.removeOwner(playerInventory[i]);
			}
		}
		activePlayer.resetInventory();
		display.removePlayer(activePlayer.getName());
		players[turn] = null;
	}
}