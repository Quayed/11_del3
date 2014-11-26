package controllers;

import fields.OurField;
import fields.OurRefuge;
import game.Die;
import game.GUIManager;
import game.Player;

public class RefugeController extends FieldController{
	private OurRefuge refuge;
	public RefugeController(){
		
	}

	@Override
	public boolean landOnField(Player player, GUIManager display, OurField field, Die die) {
		refuge = (OurRefuge) field;
		display.sendMessage(player.getName() + " landede på " + refuge.getName() + " og modtager " + refuge.getBonus());
		player.deposit(refuge.getBonus());
		return true;
	}
}
