package game;

public class Tax extends Field{
	private int taxAmount;
	private double taxRate = 0.1;
	
	public Tax(int taxAmount, String name, int id){
		this.taxAmount = taxAmount;
		this.name = name;
		this.fieldId = id;
	}
	
	@Override
	public void landOnField(Player player) {
		// felt 16, spilleren skal miste 2000
		// felt 17, spilleren skal vælge mellem at miste 10% eller 4000
	}
	
}
