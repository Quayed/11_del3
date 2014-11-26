package controllers;

import game.*;
import fields.*;

public abstract class OwnableController extends FieldController {
	
	public boolean buyField(Player player, GUIManager display, Ownable field){
		if(player.getBalance() >= field.getPrice()){
			player.withdraw(field.getPrice());
			player.addToInventory(player.getField(), field.getPrice());
    		field.setOwner(player);
    		display.setOwner(player.getField(), player.getName());
    		return true;
    	}
    	else{
    		display.sendMessage("Du har ikke nok penge til at købe denne grund.");
    		return false;
    	}
	}
	
	public boolean isOwner(Player player, OurField field) {
		Ownable ownable = (Ownable) field;
		if(player.getId() == ownable.getOwner().getId()) {
			return true;
		} else {
			return false;
		}
	}
	
	public boolean payRent(Player player, OurField field) {
		Ownable ownable = (Ownable) field;
		return player.transfer(ownable.getOwner(),ownable.getRent());
	}
	
	public boolean payRent(Player player, LaborCamp laborCamp, int sum){
		return player.transfer(laborCamp.getOwner(), laborCamp.getBaseRent()*laborCamp.getOwner().getNumberOfLaborCampsOwned());
	}
}
