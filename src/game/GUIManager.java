package game;

import boundaryToMatador.GUI;

public class GUIManager {
	
	public static void create() {
		GUI.create("fields.txt");
	}
	
	public static void addPlayer(String name, int balance) {
		GUI.addPlayer(name, balance);
	}
	
	
}


