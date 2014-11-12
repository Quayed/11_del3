package game;

public class Territory extends Ownable{
	private int rent;
	private int price;
	
	public Territory(int rent, int price){
		this.rent = rent;
		this.price = price;
	}
	
	public void setPrice(int price){
		this.price = price;
	}
	
	public int getPrice(){
		return price;
	}
	
	@Override
	public int getRent() {
		return rent;
	}

	@Override
	public void landOnField(Player player) {
		// spilleren skal have muligheden for at købe grunden hvis den ikke er ejet af andre.
		// Hvis grunden er ejet af en anden spiller skal der rent overføres fra den ene spiller til den anden.
	}
}
