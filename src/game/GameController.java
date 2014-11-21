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
				LaborCamp currentLaborCamp = (LaborCamp) board.getField(activePlayer.getField()-1);
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
			    			display.sendMessage("du har slået " + dieOne + dieTwo + ", og skal betale " + rent);
			    			//Jeg sender penge fra den aktive spiller til ejeren af feltet. Jeg ved han har penge nok da dette var condition til at komme herned 
			    			activePlayer.getAcc().transfer(currentLaborCamp.getOwner().getAcc(), rent);
			    		}
			    		else{
			    			//her skal han smides ud
			    		}
			    	}else{
			    		if(display.chooseToBuyLaborCamp(currentLaborCamp.getName(), currentLaborCamp.getPrice(), activePlayer) == "Køb"){
			    			//Her skal der ske noget hvis han køber grunden
			    		}
							if(activePlayer.getAcc().getBalance() > currentLaborCamp.getPrice()){
								activePlayer.getAcc().withdraw(currentLaborCamp.getPrice());
								currentLaborCamp.setOwner(activePlayer);
								activePlayer.addNumberOfLaborCamps();
								display.setOwner(activePlayer.getField(), activePlayer.getName());
							}
							else{
								display.sendMessage("Du har ikke nok penge til at købe denne grund");
							}
			    	}
			    	
			    }

				break;
			case("Refuge"):
				//statements
				break;
			case("Fleet"):
				Fleet currentFleet = (Fleet) board.getField(activePlayer.getField()-1);
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
						if(activePlayer.getAcc().getBalance() > currentFleet.getPrice()){
							activePlayer.getAcc().withdraw(currentFleet.getPrice());
							currentFleet.setOwner(activePlayer);
							activePlayer.addNumberOfFleetsOwned();
							display.setOwner(activePlayer.getField(), activePlayer.getName());
						} else{
							display.sendMessage("Du har ikke nok penge til at købe denne grund");
						}
					}
				}
				//statements
				break;
			case("Tax"):
				OurTax currentTax =  (OurTax) board.getField(activePlayer.getField() -1); 
				// felt 16, spilleren skal miste 2000
				if(activePlayer.getField() == 9) {
					display.sendMessage(activePlayer.getName() + " er landet på " + currentTax.getName() + " og skal betale 2000 kroner i skat.");
					activePlayer.getAcc().withdraw(2000);
				}
				// felt 17, spilleren skal vælge mellem at miste 10% eller 4000
				else if (activePlayer.getField() == 19) {
					switch (display.choosePayment(activePlayer.getName())) {
					case "10%":
						//Dette skal laves om til total assets
						activePlayer.getAcc().withdraw((int) (activePlayer.getAcc().getBalance()*currentTax.getTaxRate()));
						break;
					case "4000":
						activePlayer.getAcc().withdraw(4000);
						break;
					}
				}
				break;
			}

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
