package tests;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.Before;

import game.*;
import controllers.*;
import fields.*;

public class LaborCampTest {
	Die dieOne;
	Die dieTwo;
	LaborCampController labcController;
	LaborCamp laborCamp;
	Player player1;
	Player player2;

	
	@Before
	public void setUp(){
		dieOne = new Die();
		dieTwo = new Die();
		labcController = new LaborCampController();
		laborCamp = new LaborCamp(2500, "The pit", 15, 14);
		
		
		
		
	}
	
	@Test
	//Der testes om en spiller med rigeligt penge kan afvise et felt.  
	public void NotOwnedRejectHasMoney(){
		
		
	}
	
	@Test
	//Der testes om en spiller der lander på sit eget felt skal betale noget.
	public void OwnedSelf() {

		
		
	}

	@Test
	//Der testes om en spiller med rigeligt penge, der lander på et allerede eget felt af en anden betaler og mister det rigtige antal penge,
	//og ikke taber spillet selvom man har penge nok 
	public void OwnedOtherPayRentAbove(){

	}
	
	@Test
	//Der testes om en spiller med det nøjagtige antal penge til at betale en anden spiller, betaler det rigtige beløb
	//og ikke ryger ud af spillet
	public void OwnedOtherPayRentExact(){
		
	}
	
	//Der undersøges om en spiller uden penge nok betaler hvad han har tilbage til den anden spiller, 
	//og den anden spiller får det rigtige beløb. Til sidst undersøges der hvilke af spillerne der har tabt. 
	@Test 
	public void OwnedOtherPayRentBelow(){
		
	}
}
