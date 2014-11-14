package game;

import boundaryToMatador.GUI;

public class GUIManager {
	
	public static void create() {
		GUI.create("fields.txt");
	}
	
	public static void addPlayer(String name, int balance) {
		GUI.addPlayer(name, balance);
	}

	public static void setDice(int dieOne, int dieTwo) {
		GUI.setDice(dieOne, 0, 4, 7, dieTwo, 0, 5, 7);
	}
	
	
}


