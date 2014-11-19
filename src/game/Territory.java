package game;

public class Territory extends Ownable{
	private int rent;
	
	public Territory(int rent, int price, String name, int id){
		this.rent = rent;
		super.setPrice(price);;
		super.setName(name);
		super.setFieldId(id);
		super.setType("Territory");
	}
	
	public void setRent(int rent){
		this.rent = rent;
	}
	
	@Override
	public int getRent() {
		return rent;
	}
	
	

	@Override
	public void landOnField(Player player, GUIManager display) {
		// spilleren skal have muligheden for at købe grunden hvis den ikke er ejet af andre.
		// Hvis grunden er ejet af en anden spiller skal der rent overføres fra den ene spiller til den anden.
		if (super.isOwner(player)){
			System.out.println("du er ejeren");
		}
		else if(!super.isOwned()){
			System.out.println("den er ikke ejet og du kan k�be grunden");
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
