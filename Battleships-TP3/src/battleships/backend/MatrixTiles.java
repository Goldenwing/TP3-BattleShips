/**
 * Classe qui g�re les cases de la matrice g�n�rale.
 * Chaque MatrixTiles est une tuile, une case de la matrice "Matrix". Cette classe contient les m�thodes n�c�ssaires � 
 * l'obtention des informations de chaque case.
 * 
 * Chaque case contient un Integer, et un Boolean. L'Integer est le num�ro reli� type de bateau, et le boolean
 * v�rifie la condition de si la case est r�v�l�e ou pas sur l'application visuelle.
 * 
 * @author Kevin Tanguay
 */

package battleships.backend;

public class MatrixTiles
{
	private int tileNumber = 0;
	private boolean clicked = false;
	
	/**
	 * Construit chaque case et les initialise par rapport au num�ro et au boolean entr�s en param�tre.
	 * @param tileNumber	Le num�ro de la case, par rapport aux bateaux sur la grille.
	 * @param check			La condition boolean si la case est r�v�l�e ou pas sur le champ visuel.
	 */
	public MatrixTiles(int tileNumber, boolean check) 
	{
		this.tileNumber = tileNumber;
		this.setClicked(check);
	}

	/**
	 * Retourne le num�ro sp�cifique de la case.
	 * @return		Le num�ro de la case.
	 */
	public int getNumber() 
	{
		return this.tileNumber;
	}

	/**
	 * V�rifie si la case est coch�e ou pas.
	 * @return	vraie si coch�e, false le cas �ch�ant.
	 */
	public boolean isClicked() 
	{
		return this.clicked;
	}

	public void setClicked(boolean check) 
	{
		this.clicked = check;
	}


}
