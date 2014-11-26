package controllers;
import fields.*;
import game.*;

public class TerritoryController extends OwnableController {
	Territory territory;
	
	public TerritoryController() {
		
	}

	@Override
	public boolean landOnField(Player player, GUIManager display, OurField field, Die die) {
		territory = (Territory) field;
		if(territory.isOwned()) {
			if(isOwner(player, field)) {
				display.sendMessage("Du er ejer af denne grund");
			} else {
				display.sendMessage(player.getName() + " er landet på " + territory.getName() + ". Grunden er ejet, du skal betale " + territory.getRent() + " i leje.");
				return payRent(player, field);
			}
			
		} else {
			if(display.chooseToBuyTerritory(territory.getName(), territory.getPrice(), player.getName(), territory.getRent()) == "Køb"){
				buyField(player, display, territory);
			}
		}
		return true;
	}
}
