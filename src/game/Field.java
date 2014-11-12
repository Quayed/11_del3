package game;

abstract public class Field {
	protected String name;
	protected int fieldId;
	abstract public void landOnField(Player player);
	
	public String getName(){
		return this.name;
	}
	
	public int getFieldId(){
		return this.fieldId;
	}
}
