package controllers;

import fields.Fleet;
import fields.OurField;
import game.Die;
import game.GUIManager;
import game.Player;

public class FleetController extends OwnableController {
	Fleet fleet;
	public FleetController(){
		
	}

	@Override
	public boolean landOnField(Player player, GUIManager display, OurField field, Die die) {
		fleet = (Fleet) field;
		if(fleet.isOwned()){
			if(!isOwner(player, field)){
				if(player.getAcc().getBalance() > fleet.getPrice()){
					display.sendMessage(player.getName() + " er landet på " + fleet.getName() + " og skal betale " + fleet.getRent() + " kroner.");
					//Her overføres penge fra spilleren der landte på 
					return player.getAcc().transfer(fleet.getOwner().getAcc(), fleet.getRent());
				} 
			}
			
		} else{
			if(display.chooseToBuyFleet(fleet.getName(), fleet.getPrice(), player) == "Køb"){
				if(buyField(player, display, fleet)){
					player.addNumberOfFleetsOwned();
				}
			}
		}
		return true;
	}
}
