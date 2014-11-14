package game;

public class Territory extends Ownable{
	private int rent;
	
	public Territory(int rent, int price, String name, int id){
		this.rent = rent;
		super.setPrice(price);;
		this.name = name;
		this.fieldId = id;
	}
	
	public void setPrice(int price){
		super.setPrice(price);
	}
	
	public void setRent(int rent){
		this.rent = rent;
	}
	
	public int getPrice(){
		return super.getPrice();
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
	public String toString() {
		String s = "Rent: " + rent + " Price: " + super.getPrice() + " Name: " + name + " fieldId: " + fieldId;
		return s;
	}
}
