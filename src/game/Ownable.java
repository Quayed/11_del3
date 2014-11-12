package game;

abstract public class Ownable extends Field{
	protected int price;
	private Player owner;
	abstract public int getRent();
}
