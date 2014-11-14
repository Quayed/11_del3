package game;

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
}
