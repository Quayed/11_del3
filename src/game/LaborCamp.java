package game;

public class LaborCamp extends Ownable{
	private int baseRent = 100;

	public LaborCamp(int price, String name, int id){
		super.setPrice(price);
		super.setName(name);
		super.setFieldId(id);
	}
	
	@Override
	public int getRent() {
		// Ikke helt sikker på denne her metode i den her class.
		return 0;
		
	}

	@Override
	public void landOnField(Player player) {
		// Spilleren skal have muligheden for at købe feltet
		// Spilleren skal betale et antal penge til ejeren af feltet. 
	}
	
	public String toString() {
		String s = "Price: " + super.getPrice() + " Name: " + super.getName() + " FieldID: " + super.getFieldId();
		return s;
	}
}
