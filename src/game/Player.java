package game;

public class Player{
	private String name;
	private int field;
	final private int ID;
	final private int startMoney = 1000;
	private Account acc;
	
	public Player(int id, String name){
		this.name = name;
		this.ID = id;
		this.acc = new Account(startMoney, id);
	} 
	
	public void setName(String name){
		this.name = name;
	}
	
	public String getName(){
		return this.name;
	}
	
	public int getId(){
		return this.ID;
	}
	
	public void setField(int field){
		this.field = field;
	}
	
	public int getField(){
		return this.field;
	}

	public Account getAcc() {
		return acc;
	}
	
	
}
