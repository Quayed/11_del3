package game;

public class Territory extends Ownable{
	private int rent;
	private int fieldPossition;
	
	public Territory(int rent, int price, String name, int id, int fieldPossition){
		this.rent = rent;
		super.setPrice(price);;
		super.setName(name);
		super.setFieldId(id);
		super.setType("Territory");
		this.setFieldPossition(fieldPossition);
	}
	
	public void setRent(int rent){
		this.rent = rent;
	}
	
	@Override
	public int getRent() {
		return rent;
	}

	public int getFieldPossition() {
		return fieldPossition;
	}

	public void setFieldPossition(int fieldPossition) {
		this.fieldPossition = fieldPossition;
	}

	@Override
	public void landOnField(Player player, GUIManager display) {
		
	}
	public String toString() {
		String s = "Rent: " + rent + " Price: " + super.getPrice() + " Name: " + super.getOwner().getName() + " fieldId: " + super.getFieldId();
		return s;
	}
}
