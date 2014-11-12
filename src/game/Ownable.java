package game;

abstract public class Ownable extends Field{
	private int price;
	private Player owner;
	abstract public int getRent();
}
