package controllers;

import game.*;
import fields.*;

public abstract class OwnableController extends FieldController {
	
	public boolean buyField(Player player, GUIManager display, Ownable field){
		if(player.getAcc().getBalance() >= field.getPrice()){
			player.getAcc().withdraw(field.getPrice());
			player.addToInventory(player.getField());
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
		return player.getAcc().transfer(ownable.getOwner().getAcc(),ownable.getRent());
	}
}
