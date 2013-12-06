package battleships.backend.tests;

import org.junit.Test;
import org.junit.Assert;

import battleships.backend.Game;

public class GameTest 
{

	@Test
	public void testConstructor()
	{
		Game test = new Game();
		
		Assert.assertNotNull(test.getMatrix());
		Assert.assertNotNull(test.getComputerMatrix());
	}
	
	@Test
	public void testSetPlayerBoats()
	{
		Game test = new Game();
		
		//test.getGamer().setBoats();
	}
}
