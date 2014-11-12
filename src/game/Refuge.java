package game;

public class Refuge extends Field{
	private int bonus;
	
	public Refuge(int bonus){
		this.bonus = bonus;
	}

	@Override
	public void landOnField(Player player) {
		// bonusen skal indsættes på spillerens konto.		
	}
}
