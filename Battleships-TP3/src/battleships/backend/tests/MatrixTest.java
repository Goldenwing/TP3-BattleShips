/**
 * Cette classe sert Ã  tester la classe Matrix. Elle vÃ©rifie toute les mÃ©thodes de celle-ci.
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
		
		for(int x = 1; x<11; x++)
		{
			for(int y = 1; y<11; y++)
			{
				if(matrixTest.getSquareContentNumber(x, y, false) == 0)
				{
					counter++;
				}
			}
		}
	
		//vÃ©rifie si la grille est bien initialisÃ©e.
		Assert.assertEquals(100, counter);
	}
	
	@Test
	public void testComputerConstructor()
	{
		int counter = 0;
		Matrix matrixTest = new Matrix(true);
		
		for(int x = 1; x<11; x++)
		{
			for(int y = 1; y<11; y++)
			{
				if(matrixTest.getSquareContentNumber(x, y, true) == 0)
				{
					counter++;
				}
			}
		}
		//vÃ©rifie si la grille est bien initialisÃ©e.
		Assert.assertEquals(100, counter);
	}
	
	@Test
	public void testSetSize()
	{
		Matrix test = new Matrix();
		MatrixTiles[][] testTiles = test.setSize();
		boolean valid = true;
		
		for(int x = 1; x<11; x++)
		{
			for(int y = 1; y<11; y++)
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
		MatrixTiles[][] matrixTiles = test.getComputerGameMatrix();
		boolean valid = test.checkSpace(Boats.AIRCRAFT, true, 1, 1, matrixTiles);
		
		Assert.assertTrue(valid);
	}
	
	@Test
	public void testFalseCheckSpace()
	{
		Matrix test = new Matrix(true);
		
		for(int x = 1; x<6; x++)
		{
			test.setSquareContent(x, 1, Boats.AIRCRAFT, true, Boats.AIRCRAFT.getBoatName());
		}
		MatrixTiles[][] matrixTiles = test.getComputerGameMatrix();
		boolean valid = test.checkSpace(Boats.AIRCRAFT, true, 1, 1, matrixTiles);
		
		Assert.assertFalse(valid);
	}
	
	@Test
	public void testInvalidCheckSpace()
	{
		Matrix test = new Matrix(true);
		MatrixTiles[][] matrixTiles = test.getComputerGameMatrix();
		boolean valid = test.checkSpace(Boats.AIRCRAFT, true, 6,7, matrixTiles);
		
		Assert.assertFalse(valid);
	}
	
	@Test
	public void testTrueQuickCheck()
	{
		Matrix test = new Matrix(true);
		MatrixTiles[][] matrixTiles = test.getComputerGameMatrix();
		
		boolean valid = test.quickCheck(1, 1, 5, true, matrixTiles);
		Assert.assertTrue(valid);
	}
	
	@Test
	public void testFalseQuickCheck()
	{
		
		
		Matrix test = new Matrix(true);
		MatrixTiles[][] matrixTiles = test.getComputerGameMatrix();
		Boats boats = Boats.AIRCRAFT;
		test.setSquareContent(0, 0, boats, true, null);
		boolean valid = test.quickCheck(0, 0, 5, true, matrixTiles);
		Assert.assertFalse(valid);
	}
	
	@Test
	public void testgetSquareContentNumber()
	{
		Matrix test = new Matrix();
		Boats boats = Boats.AIRCRAFT;
		test.setSquareContent(0, 0, boats, false, null);
		
		Assert.assertEquals(5, test.getSquareContentNumber(0, 0, false));
	}
	
	//le test de SetSquareContent est exactement le mÃªme test, je ne le rÃ©pÃ¨tera pas.
	
	//le test de SquareContentNumber est exactement le mÃªme test, je ne le rÃ©pÃ¨tera pas.

	//le test de SetSquareCheck est exactement le mÃªme test, je ne le rÃ©pÃ¨tera pas.
	
}
