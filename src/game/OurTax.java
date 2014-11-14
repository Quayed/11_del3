package game;

public class OurTax extends OurField{
	private int taxAmount;
	private double taxRate = 0.1; 
	
	public OurTax(int taxAmount, String name, int id){
		this.taxAmount = taxAmount;
		super.setName(name);
		super.setFieldId(id);
		super.setType("Tax");
	}
	
	@Override
	public void landOnField(Player player) {
		// felt 16, spilleren skal miste 2000
		// felt 17, spilleren skal vælge mellem at miste 10% eller 4000
	}
	
	public String toString() {
		String s = "Tax Amount: " + taxAmount + " Tax Rate: " + taxRate + " Name: " + super.getName() + " FieldID: " + super.getFieldId();
		
		return s;
	}
	
}
