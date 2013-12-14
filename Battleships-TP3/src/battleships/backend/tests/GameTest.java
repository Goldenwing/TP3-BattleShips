package battleships.backend.tests;

import org.junit.Test;
import org.junit.Assert;

import battleships.backend.Game;
import battleships.backend.HumanPlayer;


public class GameTest 
{

	@Test
	public void testConstructorStart()
	{
		Game test = new Game();
		
		Assert.assertNotNull(test.getMatrix());
		Assert.assertNotNull(test.getComputerMatrix());
	}
	
	@Test
	public void testSetGamer()
	{
		Game test = new Game();
		HumanPlayer gamer = new HumanPlayer("Pikachu");
		test.setGamer(gamer);
		Assert.assertNotNull(test.getGamer());
	
	}
	
	//le test de SetEnemy est exactement le mÃªme test, je ne le rÃ©pÃ¨tera pas.
	
	@Test
	public void testGetGamer()
	{
		Game test = new Game();
		HumanPlayer gamer = new HumanPlayer("Pikachu");
		test.setGamer(gamer);
		Assert.assertEquals(gamer, test.getGamer() );
	
	}
	
	//le test de GetEnemy est exactement le mÃªme test, je ne le rÃ©pÃ¨tera pas.
	
	@Test
	public void testDidWeWin()
	{
		Game test = new Game();
	
		Assert.assertFalse(test.DidWeWin(test.getMatrix()));
	
	}
	
}
