package game;

public class Fleet extends Ownable{
	private int rent_one = 500;
	private int rent_two = 1000;
	private int rent_three = 2000;
	private int rent_four = 4000;
	
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
}
