package game;

abstract public class Field {
	private String name;
	private int fieldId;
	abstract public void landOnField(Player player);
	
	public String getName(){
		return this.name;
	}
	
	public int getFieldId(){
		return this.fieldId;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setFieldId(int fieldId) {
		this.fieldId = fieldId;
	}
}
