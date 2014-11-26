package controllers;
import fields.*;
import game.*;

public class TerritoryController extends OwnableController {
	Territory territory;
	
	public TerritoryController() {
		
	}
	
	public boolean isOwner(Player player) {
		if(player.getId() == territory.getOwner().getId()) {
			return true;
		} else {
			return false;
		}
	}
	
	public void payRent(Player player) {
		player.getAcc().transfer(territory.getOwner().getAcc(),territory.getRent());
	}

	@Override
	public void landOnField(Player player, GUIManager display, OurField field, Die die) {
		territory = (Territory) field;
		if(field.landOnField()) {
			if(this.isOwner(player)) {
				display.sendMessage("Du er ejer af denne grund");
			} else {
				display.sendMessage(player.getName() + " er landet på " + territory.getName() + ". Grunden er ejet, du skal betale " + territory.getRent() + " i leje.");
				payRent(player);
			}
			
		} else {
			if(display.chooseToBuyTerritory(territory.getName(), territory.getPrice(), player, territory.getRent()) == "Køb"){
				buyField(player, display, territory);
			}
		}
	}
}
