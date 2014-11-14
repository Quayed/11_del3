package game;

public class GameBoard {
	Field[] list = new Field[21];
	
	public GameBoard(){
		list[0] = new Territory(100, 1000, "Tribe Encampment", 1);
		list[1] = new Territory(300, 1500, "Crater", 2);
		list[2] = new Territory(500, 2000, "Mountain", 3);
		list[3] = new Territory(700, 3000, "Cold Desert", 4);
		list[4] = new Territory(1000, 4000, "Black Cave", 5);
		list[5] = new Territory(1300, 4300, "The Werewall", 6);
		list[6] = new Territory(1600, 4750, "Mountain village", 7);
		list[7] = new Territory(2000, 5000, "South Citadel", 8);
		list[8] = new Territory(2600, 5500, "Palace gates", 9);
		list[9] = new Territory(3200, 6000, "Tower", 10);
		list[10] = new Territory(4000, 8000, "Castle", 11);
		list[11] = new Refuge(5000, "Walled city", 12);
		list[12] = new Refuge(500, "Monastery", 13);
		list[13] = new LaborCamp(2500, "Huts in the mountain", 14);
		list[14] = new LaborCamp(2500, "The pit", 15);
		list[15] = new Tax(2000, "Goldmine", 16);
		list[16] = new Tax(4000, "Caravan", 17);
		list[17] = new Fleet(4000, "Second Sail", 18);
		list[18] = new Fleet(4000, "Sea Grover", 19);
		list[19] = new Fleet(4000, "The Buccaneers", 20);
		list[20] = new Fleet(4000, "Privateer armade", 21);
	}
	
	public Field getField(int id){
		Field field = list[id-1];
		return field;
	}
	
	public String toString() {
		String s = "";
		for(int i = 0; i <= list.length-1; i++) {
			s += list[i] + "\n";
		}
		return s;
	}
	
}
