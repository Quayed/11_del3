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
		// spilleren skal have muligheden for at købe grunden hvis den ikke er ejet af andre.
		// Hvis grunden er ejet af en anden spiller skal der rent overføres fra den ene spiller til den anden.
		if (super.isOwner(player)){
			System.out.println("Du er ejeren af denne grund");
		}
		else if(!super.isOwned()){
			
			System.out.println("Den er ikke ejet og du kan købe grunden");
		}
		else{
			System.out.println("Nu skal du sku betale");
		}
		
	}
	public String toString() {
		String s = "Rent: " + rent + " Price: " + super.getPrice() + " Name: " + super.getName() + " fieldId: " + super.getFieldId();
		return s;
	}
}
