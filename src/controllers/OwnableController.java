package controllers;

import game.*;
import fields.*;

public abstract class OwnableController extends FieldController {
	public void buyField(Player player, GUIManager display, Ownable field){
		if(player.getAcc().getBalance() >= field.getPrice()){
			player.getAcc().withdraw(field.getPrice());
			player.addToInventory(player.getField());
    		field.setOwner(player);
    		display.setOwner(player.getField(), player.getName());
    	}
    	else{
    		display.sendMessage("Du har ikke nok penge til at købe denne grund.");
    	}
	}
}
