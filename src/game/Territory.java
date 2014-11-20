package game;

public class Territory extends Ownable{
	private int rent;
	private int fieldPossition;
	
	public Territory(int rent, int price, String name, int id, int fieldPossition){
		this.rent = rent;
		super.setPrice(price);;
		super.setName(name);
		super.setFieldId(id);
		super.setType("Territory");
		this.setFieldPossition(fieldPossition);
	}
	
	public void setRent(int rent){
		this.rent = rent;
	}
	
	@Override
	public int getRent() {
		return rent;
	}

	public int getFieldPossition() {
		return fieldPossition;
	}

	public void setFieldPossition(int fieldPossition) {
		this.fieldPossition = fieldPossition;
	}

	@Override
	public void landOnField(Player player, GUIManager display) {
		if (super.isOwner(player)){
			System.out.println("Du er ejeren af denne grund");
		}
		else if(!super.isOwned()){
			if(display.chooseToBuyTerritory(super.getName(), super.getPrice(), player, this.rent) == "Køb"){
				if(player.getAcc().getBalance() > super.getPrice()){
					player.getAcc().withdraw(super.getPrice());
					super.setOwner(player);
					display.setOwner(fieldPossition, player.getName());
				} else{
					display.sendMessage("Du har ikke nok penge til at købe denne grund");
				}
			}
		}
		else{
			System.out.println("Nu skal du sku betale");
			display.sendMessage(player.getName() + " er landet på " + super.getName() + ". Grunden er ejet, du skal betale " + this.rent + " i leje.");
			player.getAcc().transfer(super.getOwner().getAcc(), this.rent);
		}
		
	}
	public String toString() {
		String s = "Rent: " + rent + " Price: " + super.getPrice() + " Name: " + super.getOwner().getName() + " fieldId: " + super.getFieldId();
		return s;
	}
}
