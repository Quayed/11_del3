package fields;

import game.GUIManager;
import game.Player;

public class Fleet extends Ownable{

	private int[] rent = {500,1000,2000,4000};
	private int possition;
	
	public Fleet(int price, String name, int id, int possition){
		super.setPrice(price);
		super.setName(name);
		super.setFieldId(id);
		super.setType("Fleet");
		this.possition = possition;
		
	}
	
	public void setRent(int rent, int indexOfRent){
		this.rent[indexOfRent] = rent;
	}
	
	@Override
	public int getRent() {
		if (super.getOwner() != null){
			return this.rent[super.getOwner().getNumberOfFleetsOwned()-1];
		}
			return 0; // returner 0 hvis grunden ikke ejes af nogle.
	}
	
	@Override
	public void landOnField(Player player, GUIManager display) {
		// Spilleren skal have mulighed for at købe feltet.
		// Hvis feltet allerede er ejet af en skal spilleren miste et antal penge.
		if(super.isOwned()){
			if(!super.isOwner(player)){
				if(player.getAcc().getBalance() > super.getPrice()){
					display.sendMessage(player.getName() + " er landet på " + super.getName() + " og skal betale " + this.rent[super.getOwner().getNumberOfFleetsOwned()-1] + " kroner.");
					
					//Her overføres penge fra spilleren der landte på 
					player.getAcc().transfer(super.getOwner().getAcc(), this.rent[super.getOwner().getNumberOfFleetsOwned()-1]);
					
					
				} else{
					// her skal der kaldes en metode for at spilleren har tabt.
				}
			}
		} else{
			if(display.chooseToBuyFleet(super.getName(), super.getPrice(), player) == "Køb"){
				if(player.getAcc().getBalance() > super.getPrice()){
					player.getAcc().withdraw(super.getPrice());
					super.setOwner(player);
					player.addNumberOfFleetsOwned();
					display.setOwner(possition, player.getName());
				} else{
					display.sendMessage("Du har ikke nok penge til at købe denne grund.");
				}
			}
		}
	}
	
	public String toString() {
		String s = "Rent: " + rent[0] + ", " + rent[1] + ", " + rent [2] + ", " + rent[3] + " Name: " + super.getName() + " FieldID: " + super.getFieldId();
		
		return s;
	}
}
