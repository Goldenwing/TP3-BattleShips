package battleships.backend.tests;

import org.junit.Assert;
import org.junit.Test;

import battleships.backend.*;

/**
 * Classe de tests des classes HumanPlayer et ComputerPlayer
 * @author Annie Belzile
 *
 */
public class PlayerTest {
	
	@Test
	public void NewHumanPlayerConstructor() {
		HumanPlayer player = new HumanPlayer("Annie");
		Assert.assertEquals("Annie", player.getPlayerName());
	}
	
	@Test
	public void NewComputerPlayerConstructor() {
		ComputerPlayer player = new ComputerPlayer("HAL-9000");
		Assert.assertEquals("HAL-9000", player.getPlayerName());
	}

}
