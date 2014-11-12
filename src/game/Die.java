package game;
import java.util.Random;

public class Die {
	private int faceValue;
	private Random rnd = new Random();
	
	public int roll(){
		faceValue = rnd.nextInt(6)+1;
		return faceValue;
	}
	
	public void setDie(int value){
		faceValue = value;
	}
	
	public int getDie(){
		return faceValue;
	}
}
