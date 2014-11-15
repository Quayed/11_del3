package game;

import boundaryToMatador.*;

public class GUIManager {
	
	public static void create() {
		GameBoard board = new GameBoard();
		
		Field[] fields = new Field[board.getNumberOfFields()];
		
		for(int i = 0; i < board.getNumberOfFields(); i++){
			OurField field = board.getField(i);
			String fieldType = field.getType();
			switch (fieldType){
			case "Territory":
				Territory territory = (Territory) field;
				fields[i] = new Street.Builder()
					.setTitle(territory.getName())
					.setDescription("Rent: " + territory.getRent())
					.setSubText("Price: " + territory.getPrice())
					.build();
				break;
			case "Refuge":
				OurRefuge refuge = (OurRefuge) field;
				fields[i] = new Refuge.Builder()
					.setTitle(refuge.getName())
					.setDescription("Receive: " + refuge.getBonus())
					.setSubText("Recieve: " + refuge.getBonus())
					.build();
				break;
			case "LaborCamp":
				LaborCamp laborCamp = (LaborCamp) field;
				fields[i] = new Brewery.Builder()
					.setTitle(laborCamp.getName())
					.setDescription("Rent: 100x dice roll")
					.setSubText("Price: " + laborCamp.getPrice())
					.build();
				break;
			case "Tax":
				OurTax tax = (OurTax) field;
				if(tax.getTaxRate() == 0){
					fields[i] = new Tax.Builder()
					.setTitle(tax.getName())
					.setDescription("Pay: " + tax.getTaxAmount())
					.setSubText("Pay: " + tax.getTaxAmount())
					.build();
				}else {
					fields[i] = new Tax.Builder()
					.setTitle(tax.getName())
					.setDescription("Pay: " + tax.getTaxAmount() + " or Pay: " + tax.getTaxPercentage() + "% of your total Assets")
					.setSubText("Pay: " + tax.getTaxAmount() + " or Pay: " + tax.getTaxPercentage() + "% of your total Assets")
					.build();
				}
				break;		
			case "Fleet":
				Fleet fleet = (Fleet) field;
				fields[i] = new Shipping.Builder()
					.setTitle(fleet.getName())
					.setSubText("Price: " + fleet.getPrice())
					.setDescription("Rent 1: 500 | Rent 2: 1000 | Rent 3: 2000 | Rent 4: 4000")
					.build();
				break;
			}
		}
		
		GUI.create(fields);
	}
	
	public void addPlayer(String name, int balance) {
		GUI.addPlayer(name, balance);
	}

	public void setDice(int dieOne, int dieTwo) {
		GUI.setDice(dieOne, 0, 4, 7, dieTwo, 0, 5, 7);
	}
	
	public int getNumberOfPlayers(){
		int numberOfPlayers = Integer.parseInt(GUI.getUserButtonPressed("Vælg hvor mange spillere der skal være", "2", "3", "4", "5", "6"));
		return numberOfPlayers;
	}
	
}


