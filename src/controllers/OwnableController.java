package controllers;

import game.*;
import fields.*;

public abstract class OwnableController extends FieldController {
	abstract public void buyField(Player player, GUIManager display, OurField field);
}
