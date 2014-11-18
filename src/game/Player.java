package game;

public class Player{
	private String name;
	private int previous_field = 0;
	private int field = 0;
	final private int ID;
	final private int STARTMONEY = 30000;
	private String payMethod = "10%"; // Kan være "10%" eller "4000"
	private Account acc;
	
	public Player(int id, String name){
		this.name = name;
		this.ID = id;
		this.acc = new Account(this.STARTMONEY, id);
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
	
	public int getPrevField() {
		return this.previous_field;
	}

	public Account getAcc() {
		return acc;
	}
	
	public int getStartMoney(){
		return STARTMONEY;
	}

	public void move(int roll) {
		previous_field = field;
		if (field+roll == 21) field=21;
		else field = (field+roll)%21;
	}
	
	public void setPayMethod(String method) {
		this.payMethod = method;
	}
	public String getPayMethod() {
		return this.payMethod;
	}
	
}
