package game;

public class Refuge extends Field{
	private int bonus;
	
	public Refuge(int bonus, String name, int id){
		this.bonus = bonus;
		super.setName(name);
		super.setFieldId(id);
	}

	@Override
	public void landOnField(Player player) {
		// bonusen skal indsættes på spillerens konto.		
	}
	
	public String toString() {
		String s = "Bonus: " + bonus + " Name: " + super.getName() + " FieldID: " + super.getFieldId();
		return s;
	}
}
