package controllers;

import fields.OurField;
import fields.OurTax;
import game.Die;
import game.GUIManager;
import game.Player;

public class TaxController extends FieldController{
	private OurTax tax;
	public TaxController(){
		
	}

	@Override
	public boolean landOnField(Player player, GUIManager display, OurField field, Die die) {
		tax =  (OurTax) field; 
		if(tax.getTaxRate() == 0) {
			display.sendMessage(player.getName() + " er landet på " + player.getName() + " og skal betale " + tax.getTaxAmount() + " kroner i skat.");
			return player.withdraw(tax.getTaxAmount());
			
		}else {
			switch (display.choosePayment(player.getName())) {
			case "10%":
				//Samlede værdi hentes
				int totalAssets = player.getTotalAssets();

				return player.withdraw((int) (totalAssets*tax.getTaxRate()));
			case "4000":
				return player.withdraw(tax.getTaxAmount());
			}
		}
		return true;
	}
}
