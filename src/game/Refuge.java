package game;

public class Refuge extends Field{
	private int bonus;
	
	public Refuge(int bonus, String name, int id){
		this.bonus = bonus;
		this.name = name;
		this.fieldId = id;
	}

	@Override
	public void landOnField(Player player) {
		// bonusen skal indsættes på spillerens konto.		
	}
}
