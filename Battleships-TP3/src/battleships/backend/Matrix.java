/**
 * Classe qui appartient � la logique derri�re l'application visuelle. Cette classe va construire le champ de mines, et
 * qui va g�rer toute la logique appartenant au champ de mines. Elle contient les m�thodes ayant rapport � l'information n�c�ssaire
 * � la matrice du champ de mines.
 * 
 * @author Kevin Tanguay
 */

package battleships.backend;


public class Matrix
{
	private MatrixTiles[][] gameMatrix;
	 
	/**
	 * Construit la matrice de jeu du champ de mines. Elle place les dimensions, et initialise les cases.
	 * 
	 * @param level		Le niveau de jeu choisi.
	 */
	public Matrix()
	{
		this.gameMatrix = null;
		this.gameMatrix = SetSize(this.gameMatrix);
		
		for(int x = 0; x < 10; x++)
		{
			for(int y= 0 ; y < 10; y++)
			{
				MatrixTiles gameTile = new MatrixTiles(0, false);
				this.gameMatrix[x][y] = gameTile;
			}
		}
	}

	public MatrixTiles[][] SetSize(MatrixTiles[][] matrix) 
	{
		matrix = new MatrixTiles[10][10];
		return matrix;
	}

	/** 
	 * Retourne la matrice pr�sentement en jeu.
	 * @return 	La matrice en question.
	 */

	public MatrixTiles[][] getMatrix()
	{
		return this.gameMatrix;
	}
	
	/**
	 * Retourne le num�ro de la case selon son emplacement sp�cifique sur la grille.
	 * @param x		La coordonn�e en X de la case sp�cifi�e.
	 * @param y		La coordonn�e en Y de la case sp�cifi�e.
	 * @return		le num�ro de la case sp�cifique.
	 * 
	 * @see MatrixTiles.getNumber()
	 */

	public int getSquareContentNumber(int x, int y)
	{
		return this.gameMatrix[x][y].getNumber();
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

	public boolean getSquareContentCheck(int x, int y)
	{
		return this.gameMatrix[x][y].isClicked();
	}
	
	/**
	 * Place l'information dans la case sp�cifique de la grille. Soit un num�ro, et la condition coch�e.
	 * @param x			L'emplacement X de la case d�sir�e.
	 * @param y			L'emplacement Y de la case d�sir�e.
	 * @param boat		le num�ro du bateau de la case sp�cifique.
	 */

	public void setSquareContent(int x, int y, int boat)
	{
		MatrixTiles newTile = new MatrixTiles(boat, false);
		this.gameMatrix[x][y] = newTile;
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
	
	public static void main(String[] args)
	{
		
		Matrix game = new Matrix();
		for(int i = 0; i < 10; i++)
		   {
		      for(int j = 0; j < 10; j++)
		      {
		         System.out.printf("%5d ", game.getSquareContentNumber(i, j));
		      }
		      System.out.println();
		   }
	}

	
}
