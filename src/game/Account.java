package game;

public class Account {
	private int balance;
	final private int ID;
	
	public Account(int balance, int id){
		this.balance = balance;
		this.ID = id;
	}
	
	public void setBalance(int balance){
		this.balance = balance;
	}
	
	public int getBalance(){
		return this.balance;
	}
	
	public int getId(){
		return this.ID;
	}
	
	public void deposit(int amount){
		if(amount > 0){
			this.balance += amount;
		} else if(amount < 0){
			System.out.println("Du må ikke indsætte negative beløb.");
		}
	}
	
	public void withdraw(int amount){
		this.balance -= amount; 
	}
	
	public void transfer(Account reciever, int amount){
		if(amount > this.balance){
			reciever.deposit(balance);
			this.withdraw(amount);
		} else{
			this.withdraw(amount);
			reciever.deposit(amount);
		}
		
	}
	
	public String toString() {
		String s = "";
		s += getId() + " " + getBalance();
		
		return s;
	}
	
}
