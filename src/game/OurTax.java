package game;

public class OurTax extends OurField{
	private int taxAmount;
	private double taxRate = 0.1; 
	
	public OurTax(int taxAmount, double taxRate, String name, int id){
		this.taxAmount = taxAmount;
		this.taxRate = taxRate;
		super.setName(name);
		super.setFieldId(id);
		super.setType("Tax");
	}
	
	public void setTaxAmount(int taxAmount){
		this.taxAmount = taxAmount;
	}
	
	public void setTaxRate(double taxRate){
		this.taxRate = taxRate;
	}
	
	public int getTaxAmount(){
		return this.taxAmount;
	}
	
	public double getTaxRate(){
		return this.taxRate;
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
