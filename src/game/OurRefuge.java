package game;

public class OurRefuge extends OurField{
	private int bonus;
	
	public OurRefuge(int bonus, String name, int id){
		this.bonus = bonus;
		super.setName(name);
		super.setFieldId(id);
		super.setType("Refuge");
	}
	
	public void setBonus(int bonus){
		this.bonus = bonus;
	}
	
	public int getBonus(){
		return this.bonus;
	}

	@Override
	public void landOnField(Player player, GUIManager display) {
		player.getAcc().deposit(this.bonus);	
	}
	
	public String toString() {
		String s = "Bonus: " + bonus + " Name: " + super.getName() + " FieldID: " + super.getFieldId();
		return s;
	}
}
