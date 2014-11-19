package game;

public class OurTax extends OurField{
	private int taxAmount;
	private int taxPercentage;
	private double taxRate = 0.1; 
	
	public OurTax(int taxAmount, int taxRate, String name, int id){
		this.taxAmount = taxAmount;
		this.taxPercentage = taxRate;
		this.taxRate = (double) taxRate/100;
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
	
	public void setTaxPercentage(int taxPercentage) {
		this.taxPercentage = taxPercentage;
	}
	
	public int getTaxAmount(){
		return this.taxAmount;
	}
	
	public double getTaxRate(){
		return this.taxRate;
	}
	
	public int getTaxPercentage() {
		return taxPercentage;
	}

	@Override
	public void landOnField(Player player) {
		// felt 16, spilleren skal miste 2000
		if(player.getField() == 9)
			player.getAcc().withdraw(2000);
		
		// felt 17, spilleren skal vælge mellem at miste 10% eller 4000
		else if (player.getField() == 19) {
			switch (player.getPayMethod()) {
			case "10%":
				player.getAcc().withdraw((int) (player.getAcc().getBalance()*this.taxRate));
				break;
			case "4000":
				player.getAcc().withdraw(4000);
				break;
			}
		}
	}
	
	public String toString() {
		String s = "Tax Amount: " + taxAmount + " Tax Rate: " + taxRate + " Name: " + super.getName() + " FieldID: " + super.getFieldId();
		
		return s;
	}
	
}
