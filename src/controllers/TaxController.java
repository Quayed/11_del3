package controllers;

import fields.OurField;
import fields.OurTax;
import game.Die;
import game.GUIManager;
import game.Player;

public class TaxController extends FieldController{
	OurTax tax;
	public TaxController(){
		
	}

	@Override
	public boolean landOnField(Player player, GUIManager display, OurField field, Die die) {
		tax =  (OurTax) field; 
		if(player.getField() == 9) {
			display.sendMessage(player.getName() + " er landet på " + player.getName() + " og skal betale 2000 kroner i skat.");
			return player.getAcc().withdraw(2000);
			
		}else if (player.getField() == 19) {
			switch (display.choosePayment(player.getName())) {
			case "10%":
				//Samlede værdi hentes
				int totalAssets = player.getTotalAssets();

				return player.getAcc().withdraw((int) (totalAssets*tax.getTaxRate()));
			case "4000":
				return player.getAcc().withdraw(4000);
			}
		}
		return true;
	}
}
