package game;

import boundaryToMatador.*;

public class GUIManager {
	
	public static void create() {
		GameBoard board = new GameBoard();
		
		Field[] fields = new Field[board.getNumberOfFields()];
		
		for(int i = 0; i < board.getNumberOfFields(); i++){
			String field = board.getField(i).getType();
			switch (field){
			case "Territory":
				fields[i] = new Street.Builder().build();
				break;
			case "Refuge":
				fields[i] = new Refuge.Builder().build();
				break;
			case "LaborCamp":
				fields[i] = new Brewery.Builder().build();
				break;
			case "Tax":
				fields[i] = new Tax.Builder().build();
				break;
			case "Fleet":
				fields[i] = new Shipping.Builder().build();
				break;
			}
		}
		
		GUI.create(fields);
	}
	
	public static void addPlayer(String name, int balance) {
		GUI.addPlayer(name, balance);
	}

	public static void setDice(int dieOne, int dieTwo) {
		GUI.setDice(dieOne, 0, 4, 7, dieTwo, 0, 5, 7);
	}
	
	
}


