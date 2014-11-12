package game;

public class Player {
	private String name;
	private int field;
	final private int ID;
	
	public Player(int id, String name){
		this.name = name;
		this.ID = id;
	}
	
	public void setName(String name){
		this.name = name;
	}
	
	public String getName(String name){
		return name;
	}
	
	public int getId(){
		return ID;
	}
	
	public void setField(int field){
		this.field = field;
	}
	
	public int getField(){
		return this.field;
	}
}
