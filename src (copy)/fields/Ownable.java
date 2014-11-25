package fields;

import game.Player;

abstract public class Ownable extends OurField{
	private int price;
	private Player owner;
	abstract public int getRent();
	
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}

	public Player getOwner() {
		return owner;
	}

	public void setOwner(Player owner) {
		this.owner = owner;
	}
	public boolean isOwned(){
		if(owner == null){
			return false;
		}
		else{
			return true;
		}
	}
	public boolean isOwner(Player player){
		if(player==this.owner){
			return true;
		}
		else{
			return false;
		}
	}
}
