package battleships.backend.tests;

import org.junit.Assert;
import org.junit.Test;

import battleships.backend.*;

/**
 * Classe de tests de HumanPlayer et ComputerPlayer
 * @author Annie Belzile
 *
 */
public class PlayerTest {
	//Constructors tests
	@Test
	public void NewHumanPlayerConstructor() {
		HumanPlayer player = new HumanPlayer("Annie");
		Assert.assertEquals("Annie", player.getPlayerName());
	}
	
	@Test
	public void NewComputerPlayerConstructor() {
		ComputerPlayer player = new ComputerPlayer();
		Assert.assertEquals("HAL-9000", player.getPlayerName());
		Assert.assertEquals(null, player.getLastGoodShot());
	}
	
	//SetBoats() methods tests
	@Test
	public void GenerateRandomCoordinatesComputerPlayerTest() {
		
		
	}

}
