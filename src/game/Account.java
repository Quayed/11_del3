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
		if(amount > 0){
			if(amount < this.balance){
				this.balance -= amount;
			} else{
				System.out.println("Der er ikke nok penge på kontoen til at udtrække dette beløb.");
			}
		} else if(amount < 0){
			System.out.println("Du kan ikke udtrække negative beløb.");
		}
	}
	
	public void transfer(Account reciever, int amount){
		if(amount < this.balance && amount > 0){
			this.withdraw(amount);
			reciever.deposit(amount);
		} else if(amount > this.balance){
			System.out.println("Der er ikke penge nok på kontoen til at trække dette beløb");
		} else if(amount < 0){
			System.out.println("Der kan ikke flyttes negative beløb.");
		}
	}
	
}
