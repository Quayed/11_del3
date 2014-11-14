package game;

abstract public class Ownable extends Field{
	protected int price;
	protected Player owner;
	abstract public int setRent();
	abstract public int getRent();
}
