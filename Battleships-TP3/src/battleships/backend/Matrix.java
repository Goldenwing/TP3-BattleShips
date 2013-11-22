/**
 * Classe qui appartient à la logique derrière l'application visuelle. Cette classe va construire le champ de mines, et
 * qui va gérer toute la logique appartenant au champ de mines. Elle contient les méthodes ayant rapport à l'information nécéssaire
 * à la matrice du champ de mines.
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
		MatrixTiles[][] newGame = new MatrixTiles[10][10];
		matrix = newGame;
		return matrix;
	}

	/** 
	 * Retourne la matrice présentement en jeu.
	 * @return 	La matrice en question.
	 */

	public MatrixTiles[][] getMatrix()
	{
		return this.gameMatrix;
	}
	
	/**
	 * Retourne le numéro de la case selon son emplacement spécifique sur la grille.
	 * @param x		La coordonnée en X de la case spécifiée.
	 * @param y		La coordonnée en Y de la case spécifiée.
	 * @return		le numéro de la case spécifique.
	 * 
	 * @see MatrixTiles.getNumber()
	 */

	public int getSquareContentNumber(int x, int y)
	{
		return this.gameMatrix[x][y].getNumber();
	}
	
	/**
	 * Retourne la condition de la case spécifique, si elle est déjà cochée ou pas. Ceci est
	 * vérifié par un boolean.
	 * 
	 * @param x		La coordonnée en X de la case spécifiée.
	 * @param y		La coordonnée en Y de la case spécifiée.
	 * @return		vrai si déjà cochée, false le cas échéant.
	 * 
	 * @see MatrixTiles.isClicked()
	 */

	public boolean getSquareContentCheck(int x, int y)
	{
		return this.gameMatrix[x][y].isClicked();
	}
	
	/**
	 * Place l'information dans la case spécifique de la grille. Soit un numéro, et la condition cochée.
	 * @param x			L'emplacement X de la case désirée.
	 * @param y			L'emplacement Y de la case désirée.
	 * @param boat		le numéro du bateau de la case spécifique.
	 */

	public void setSquareContent(int x, int y, int boat)
	{
		MatrixTiles newTile = new MatrixTiles(boat, false);
		this.gameMatrix[x][y] = newTile;
	}
	
	/** 
	 * Change la condition boolean de la case spécifique. Elle rends la case cochée ou pas, dépendamment du boolean
	 * en paramètre. Cette case deviens donc révélée, ou pas, visuellement.
	 * 
	 * @param x			L'emplacement X de la case.
	 * @param y			L'emplacement Y de la case.
	 * @param check		Le boolean qui décide si la case est cochée ou pas.
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
