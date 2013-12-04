/** Classe qui appartient � la logique derri�re l'application visuelle. Cette classe va construire le champ de mines, et
 * qui va g�rer toute la logique appartenant au champ de mines. Elle contient les m�thodes ayant rapport � l'information n�c�ssaire
 * � la matrice du champ de mines.
 * 
 * @author Kevin Tanguay
 */

package battleships.backend;

import battleships.backend.Game.Boats;

public class Matrix
{
	private MatrixTiles[][] gameMatrix;
	private MatrixTiles[][] computerGameMatrix;
	 
	/**
	 * Construit la matrice de jeu. Elle place les dimensions, et initialise les cases.
	 * 
	 * @param level		Le niveau de jeu choisi.
	 */
	public Matrix()
	{
		this.gameMatrix = null;
		this.gameMatrix = setSize();
		
		for(int x = 0; x < 10; x++)
		{
			for(int y= 0 ; y < 10; y++)
			{
				MatrixTiles gameTile = new MatrixTiles(0, false);
				this.gameMatrix[x][y] = gameTile;
			}
		}
	}
	
	public Matrix(boolean computer)
	{
		this.computerGameMatrix = null;
		this.computerGameMatrix = setSize();
		
		for(int x = 0; x < 10; x++)
		{
			for(int y= 0 ; y < 10; y++)
			{
				MatrixTiles gameTile = new MatrixTiles(0, false);
				this.computerGameMatrix[x][y] = gameTile;
			}
		}
	}

	public MatrixTiles[][] setSize() 
	{
		MatrixTiles[][] matrix = new MatrixTiles[10][10];
		return matrix;
	}

	public boolean checkSpace(Boats boat, boolean direction, int x, int y)
	{
		int size = boat.getSize();
		boolean valid = true;
		
		
		if(x + size > 10 || y + size > 10)
		{
			valid = false;
		}
		else
		{
			valid = quickCheck(x, y, size, direction);
		}
		
		return valid;
	}
	
	public boolean quickCheck(int x, int y, int boatSize, boolean direction)
	{
		boolean valid = true;
		
		if(direction)
		{
			for(int space = x; space < x + boatSize; space++)
			{
				if(valid)
				{
					if(this.computerGameMatrix[space][y].getNumber() != 0)
					{
						valid = false;
					}
				}
			}
		}
		else
		{
			for(int space = y; space < y + boatSize; space++)
			{
				if(valid)
				{
					if(this.computerGameMatrix[x][space].getNumber() != 0)
					{
						valid = false;
					}
				}
			}
		}
		return valid;
	}

	/** 
	 * Retourne la matrice pr�sentement en jeu.
	 * @return 	La matrice en question.
	 */

//	public MatrixTiles[][] getMatrix()
//	{
//		return this.gameMatrix;
//	}
	
	/**
	 * Retourne le num�ro de la case selon son emplacement sp�cifique sur la grille.
	 * @param x		La coordonn�e en X de la case sp�cifi�e.
	 * @param y		La coordonn�e en Y de la case sp�cifi�e.
	 * @return		le num�ro de la case sp�cifique.
	 * 
	 * @see MatrixTiles.getNumber()
	 */

	public int getSquareContentNumber(int x, int y, boolean computer)
	{
		if(!computer)
		{
			return this.gameMatrix[x][y].getNumber();
		}
		else
			return this.computerGameMatrix[x][y].getNumber();
	}
	
	/**
	 * Retourne la condition de la case sp�cifique, si elle est d�j� coch�e ou pas. Ceci est
	 * v�rifi� par un boolean.
	 * 
	 * @param x		La coordonn�e en X de la case sp�cifi�e.
	 * @param y		La coordonn�e en Y de la case sp�cifi�e.
	 * @return		vrai si d�j� coch�e, false le cas �ch�ant.
	 * 
	 * @see MatrixTiles.isClicked()
	 */

	public boolean getSquareContentCheck(int x, int y, boolean computer)
	{
		if(!computer)
		{
			return this.gameMatrix[x][y].isClicked();	
		}
		else
			return this.computerGameMatrix[x][y].isClicked();
		
	}

	
	/**
	 * Place l'information dans la case sp�cifique de la grille. Soit un num�ro, et la condition coch�e.
	 * @param x			L'emplacement X de la case d�sir�e.
	 * @param y			L'emplacement Y de la case d�sir�e.
	 * @param boat		le num�ro du bateau de la case sp�cifique.
	 */

	public void setSquareContent(int x, int y, int boat, boolean computer)
	{
		if(!computer)
		{
			MatrixTiles newTile = new MatrixTiles(boat, false);
			this.gameMatrix[x][y] = newTile;
		}
		else
		{
			MatrixTiles newTile = new MatrixTiles(boat, false);
			this.computerGameMatrix[x][y] = newTile;
		}
	}

	
	/** 
	 * Change la condition boolean de la case sp�cifique. Elle rends la case coch�e ou pas, d�pendamment du boolean
	 * en param�tre. Cette case deviens donc r�v�l�e, ou pas, visuellement.
	 * 
	 * @param x			L'emplacement X de la case.
	 * @param y			L'emplacement Y de la case.
	 * @param check		Le boolean qui d�cide si la case est coch�e ou pas.
	 */

	public void setSquareCheck(int x, int y, boolean check)
	{
		this.gameMatrix[x][y].setClicked(check);	
	}
	
//	public static void main(String[] args)
//	{
//		
//		Matrix game = new Matrix();
//		for(int i = 0; i < 10; i++)
//		   {
//		      for(int j = 0; j < 10; j++)
//		      {
//		         System.out.printf("%5d ", game.getSquareContentNumber(i, j));
//		      }
//		      System.out.println();
//		   }
//	}

	
}

