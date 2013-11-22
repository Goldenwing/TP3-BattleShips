/**
 * Classe qui g�re les cases de la matrice g�n�rale.
 * Chaque MatrixTiles est une tuile, une case de la matrice "Matrix". Cette classe contient les m�thodes n�c�ssaires � 
 * l'obtention des informations de chaque case.
 * 
 * Chaque case contient un Integer, et un Boolean. L'Integer est le num�ro reli� au nombre de mines autour de lui-m�me, et le boolean
 * v�rifie sa condition de si elle est r�v�l�e ou pas sur l'application visuelle.
 * 
 * @author Kevin Tanguay
 */

package gameField.backend;

public class MatrixTiles
{
	private int tileNumber = 0;
	private boolean clicked = false;
	
	/**k
	 * Construit chaque case et les initialise par rapport au num�ro et au boolean entr�s en param�tre.
	 * @param tileNumber	Le num�ro de la case, par rapport aux bateaux sur la grille.
	 * @param check			La condition boolean si elle est r�v�l�e ou pas sur le champ visuel.
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
