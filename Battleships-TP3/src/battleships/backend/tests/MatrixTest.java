/**
 * Cette classe sert à tester la classe Matrix. Elle vérifie toute les méthodes de celle-ci.
 * 
 * @author Kevin Tanguay
 */

package battleships.backend.tests;

import org.junit.Test;
import org.junit.Assert;

import battleships.backend.Game.Boats;
import battleships.backend.Matrix;
import battleships.backend.MatrixTiles;

public class MatrixTest 
{
	@Test
	public void testConstructor()
	{
		int counter = 0;
		Matrix matrixTest = new Matrix();
		
		for(int x = 0; x<10; x++)
		{
			for(int y = 0; y<10; y++)
			{
				if(matrixTest.getSquareContentNumber(x, y, false) == 0)
				{
					counter++;
				}
			}
		}
		//vérifie si la grille est bien initialisée.
		Assert.assertEquals(100, counter);
	}
	
	@Test
	public void testComputerConstructor()
	{
		int counter = 0;
		Matrix matrixTest = new Matrix(true);
		
		for(int x = 0; x<10; x++)
		{
			for(int y = 0; y<10; y++)
			{
				if(matrixTest.getSquareContentNumber(x, y, true) == 0)
				{
					counter++;
				}
			}
		}
		//vérifie si la grille est bien initialisée.
		Assert.assertEquals(100, counter);
	}
	
	@Test
	public void testSetSize()
	{
		Matrix test = new Matrix();
		MatrixTiles[][] testTiles = test.setSize();
		boolean valid = true;
		
		for(int x = 0; x<10; x++)
		{
			for(int y = 0; y<10; y++)
			{
				if (testTiles[x][y] != null)
				{
					valid = false;
				}
			}
		}
		
		Assert.assertTrue(valid);
	}
	
	@Test
	public void testTrueCheckSpace()
	{
		Matrix test = new Matrix(true);
		
		boolean valid = test.checkSpace(Boats.AIRCRAFT, true, 0, 0);
		
		Assert.assertTrue(valid);
	}
	
	@Test
	public void testFalseCheckSpace()
	{
		Matrix test = new Matrix(true);
		
		for(int x = 0; x<5; x++)
		{
			test.setSquareContent(x, 0, Boats.AIRCRAFT.getSize(), true);
		}
		
		boolean valid = test.checkSpace(Boats.AIRCRAFT, true, 0,0);
		
		Assert.assertFalse(valid);
	}
	
	@Test
	public void testInvalidCheckSpace()
	{
		Matrix test = new Matrix(true);
		
		boolean valid = test.checkSpace(Boats.AIRCRAFT, true, 6,7);
		
		Assert.assertFalse(valid);
	}
	
	@Test
	public void testTrueQuickCheck()
	{
		Matrix test = new Matrix(true);
		boolean valid = test.quickCheck(0, 0, 5, true);
		Assert.assertTrue(valid);
	}
	
	@Test
	public void testFalseQuickCheck()
	{
		Matrix test = new Matrix(true);
		test.setSquareContent(0, 0, 5, true);
		boolean valid = test.quickCheck(0, 0, 5, true);
		Assert.assertFalse(valid);
	}
	
	@Test
	public void testgetSquareContentNumber()
	{
		Matrix test = new Matrix();
		
		test.setSquareContent(0, 0, 5, false);
		
		Assert.assertEquals(5, test.getSquareContentNumber(0, 0, false));
	}
	
	//le test de SetSquareContent est exactement le même test, je ne le répètera pas.
	
	@Test
	public void testgetSquareContentCheck()
	{
		Matrix test = new Matrix();
		
		test.setSquareCheck(0, 0, true);
		
		Assert.assertTrue(test.getSquareContentCheck(0,0,false));
	}
	
	//le test de SetSquareCheck est exactement le même test, je ne le répètera pas.
	
}
