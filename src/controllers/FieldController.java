package controllers;

import game.*;
import fields.*;

public abstract class FieldController {
	abstract public void landOnField(Player player, GUIManager display, OurField field, Die die);
}
