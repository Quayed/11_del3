package game;

public class OurRefuge extends OurField{
	private int bonus;
	private int fieldPossition;
	
	public OurRefuge(int bonus, String name, int id, int fieldPossition){
		this.bonus = bonus;
		super.setName(name);
		super.setFieldId(id);
		super.setType("Refuge");
		this.setFieldPossition(fieldPossition);
	}
	
	public void setBonus(int bonus){
		this.bonus = bonus;
	}
	
	public int getBonus(){
		return this.bonus;
	}

	public int getFieldPossition() {
		return fieldPossition;
	}

	public void setFieldPossition(int fieldPossition) {
		this.fieldPossition = fieldPossition;
	}

	@Override
	public void landOnField(Player player, GUIManager display) {
		display.sendMessage(player.getName() + " landede på " + super.getName() + " og modtager " + this.bonus);
		player.getAcc().deposit(this.bonus);	
	}
	
	public String toString() {
		String s = "Bonus: " + bonus + " Name: " + super.getName() + " FieldID: " + super.getFieldId();
		return s;
	}
}
