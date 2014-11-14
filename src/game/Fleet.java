package game;

public class Fleet extends Ownable{

	private int[] rent = {500,1000,2000,4000};
	
	public Fleet(int price, String name, int id){
		this.price = price;
		this.name = name;
		this.fieldId = id;
	}
	
	@Override
	public int getRent() {
		// der skal tjekkes hvor mange fleet felter spilleren ejer.
		return 0;
	}
	
	@Override
	public void landOnField(Player player) {
		// Spilleren skal have mulighed for at købe feltet.
		// Hvis feltet allerede er ejet af en skal spilleren miste et antal penge.
	}
	
	public String toString() {
		String s = "Rent: " + rent[0] + ", " + rent[1] + ", " + rent [2] + ", " + rent[3] + " Name: " + name + " FieldID: " + fieldId;
		
		return s;
	}
}
