package tests;

import static org.junit.Assert.*;

import org.junit.Test;

import fields.*;
import game.*;
import controllers.*;
public class TestTerritory {
	
	
//Der er lavet test for LandOnField() af Territory. LandOnField tjekker følgende:
		//Lander man på et felt der ikke ejes får man muligheden for:
		//		- At købe feltet
		//      - Ikke at købe feltet 
		//
		//Lander man på et felt der ejes i forvejen:
		//		- Er det ejet af én selv sker der ikke noget
		//		- Er det ikke eget af en selv skal man betale penge til ejeren. Følgende konsekvenser for dette er:
		//				- Har man penge nok betaler man til ejeren, og beløbet man betaler bliver lagt til ejerens konto.
		//				- Har man ikke penge nok taber man spillet
		//
//Formålet med denne test er at se om LandOnField virker som den skal. Dette gøres ved følgende tests:
	//	Lander man på et felt der ikke ejes af nogen testes der for følgende scenarier: 
		//		- Man vælger at købe feltet, og:
			//		- Man har penge nok til at betale for feltet
			//		- Man har lige præcis nok penge til at betale for feltet
			//		- Man har ikke penge nok til at betale for feltet
		//		- Man vælger ikke at købe feltet	
			//		- Dette testes kun hvor man har penge nok, da man ikke betaler noget
	//	Lander man på et felt der ejes af nogen i forvejen testes der for følgende scenarier:
			//		- Er man selv ejer skal der ikke ske noget
			//		- Er man ikke selv ejer betaler man til en anden spiller. For dette testes der:
				//		- Når den betalende spiller har penge nok penge
				//		- Når den betalende spiller har lige præcis nok penge
				//		- Når den betalende spiller ikke har nok penge
	
	//Disse objekter ændrer sig aldrig, udover display til en enkelt test i NotOwnedRejectHasMoney().
	Die die = new Die();
	Territory territory = new Territory(1000, 2000, "Andeby", 2, 10);
	TerritoryController Territory = new TerritoryController();
	GUIManager display = new GUIManager("test","10%","Køb"); 
	
	@Test
	//Der testes om en spiller med rigeligt penge kan købe et felt og ende med de rigtige antal penge til sidst, 
	// og om hans data er opdateret til sidst
	public void NotOwnedBuyHasMoney() {
		//Jeg starter med at oprette en spiller der står på felt 10.
		Player player = new Player(1,"Joachim von And");
		player.setField(10);
		
		//Denne spiller køber feltet
		Territory.landOnField(player, display, territory, die);
		
		//Der testes for om det rigtige beløb er blevet trukket, om listen med spillerens egede felter og hans antal af ejede felter opdateres. 
		//- til sidst undersøges der om han har tabt.
		assertEquals(player.getAcc().getBalance(),28000);
		assertEquals(player.getField(),10);
		assertEquals(player.getInventory()[0],10);
		assertEquals(player.getNumberOfFieldsOwned(),1);
		assertEquals(Territory.landOnField(player, display, territory, die),true);
	}
	
	@Test
	//Der testes om en spiller med det nøjagtige antal penge kan købe et felt. De samme ting undersøges bagefter.
	public void NotOwnedBuyEqualMoney() {
		Player player = new Player(1,"Anders And");
		player.getAcc().setBalance(2000);
		player.setField(10);
		Territory.landOnField(player, display, territory, die);
		assertEquals(player.getAcc().getBalance(),0);
		assertEquals(player.getField(),10);
		assertEquals(player.getInventory()[0],10);
		assertEquals(player.getNumberOfFieldsOwned(),1);
		assertEquals(Territory.landOnField(player, display, territory, die),true);
		
	}
	
	@Test
	//Der testes om en spiller uden penge nok får lov til at købe et felt 
	public void NotOwnedBuyNoMoney() {
		Player player = new Player(1,"Anders And");
		player.getAcc().setBalance(1500);
		player.setField(10);
		Territory.landOnField(player, display, territory, die);
		assertEquals(player.getAcc().getBalance(),1500);
		assertEquals(player.getField(),10);
		assertEquals(player.getInventory()[0],0);
		assertEquals(player.getNumberOfFieldsOwned(),0);
		assertEquals(Territory.landOnField(player, display, territory, die),true);
	}
	
	@Test
	//Der testes om en spiller med rigeligt penge kan afvise et felt.  
	public void NotOwnedRejectHasMoney() {
		Player player = new Player(1,"Anders And");
		player.getAcc().setBalance(2001);
		GUIManager display = new GUIManager("test","10%","Afslå"); 
		player.setField(10);
		Territory.landOnField(player, display, territory, die);
		assertEquals(player.getAcc().getBalance(),2001);
		assertEquals(player.getField(),10);
		assertEquals(player.getInventory()[0],0);
		assertEquals(Territory.landOnField(player, display, territory, die),true);
	}
	@Test
	//Der testes om en spiller der lander på sit eget felt skal betale noget.
	public void OwnedSelf() {
		Player player = new Player(1,"Onkel Joachim");
		player.getAcc().setBalance(6000);
		player.setField(10);
		Territory.landOnField(player, display, territory, die);
		Territory.landOnField(player, display, territory, die);
		assertEquals(player.getAcc().getBalance(),4000);
		assertEquals(player.getField(),10);
		assertEquals(player.getInventory()[1],0);
		assertEquals(Territory.landOnField(player, display, territory, die),true);
	}

	@Test
	//Der testes om en spiller med rigeligt penge, der lander på et allerede eget felt af en anden betaler og mister det rigtige antal penge,
	// og ikke taber spillet selvom man har penge nok 
	public void OwnedOtherPayRentAbove() {
		Player player1 = new Player(1,"Anders And");
		Player player2 = new Player(2,"Fætter Højben");
		player2.setField(10);
		player1.setField(10);
		Territory.landOnField(player2, display, territory, die);
		Territory.landOnField(player1, display, territory, die);
		assertEquals(player1.getAcc().getBalance(),29000);
		assertEquals(player2.getAcc().getBalance(),29000);
		assertEquals(player2.getInventory()[0],10);
		assertEquals(player1.getInventory()[0],0);
		assertEquals(Territory.landOnField(player1, display, territory, die),true);
		assertEquals(Territory.landOnField(player2, display, territory, die),true);
	}
	
	@Test
	//Der testes om en spiller med det nøjagtige antal penge til at betale en anden spiller, betaler det rigtige beløb
	// og ikke ryger ud af spillet
	public void OwnedOtherPayRentExact() {
		Player player1 = new Player(1,"Anders And");
		Player player2 = new Player(2,"Fætter Højben");
		player1.getAcc().setBalance(1000);
		player2.setField(10);
		player1.setField(10);
		boolean HasLost2 = Territory.landOnField(player2, display, territory, die);
		boolean HasLost1 = Territory.landOnField(player1, display, territory, die);
		assertEquals(player1.getAcc().getBalance(),0);
		assertEquals(player2.getAcc().getBalance(),29000);
		assertEquals(player2.getInventory()[0],10);
		assertEquals(player1.getInventory()[0],0);
		assertEquals(HasLost1,true);
		assertEquals(HasLost2,true);
		
	}
	
	@Test
	//Der undersøges om en spiller uden penge nok betaler hvad han har tilbage til den anden spiller, 
	// og den anden spiller får det rigtige beløb. Til sidst undersøges der hvilke af spillerne der har tabt. 
	public void OwnedOtherPayRentBelow() {
		Player player1 = new Player(1,"Anders And");
		Player player2 = new Player(2,"Fætter Højben");
		player1.getAcc().setBalance(500);
		player2.setField(10);
		player1.setField(10);
		boolean HasLost2 = Territory.landOnField(player2, display, territory, die);
		boolean HasLost1 = Territory.landOnField(player1, display, territory, die);
		assertEquals(28500, player2.getAcc().getBalance());
		assertEquals(player2.getInventory()[0],10);
		assertEquals(player1.getInventory()[0],0);
		assertEquals(Territory.landOnField(player1, display, territory, die),false);
		assertEquals(Territory.landOnField(player2, display, territory, die),true);

	}
}
