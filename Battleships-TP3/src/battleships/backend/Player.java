package battleships.backend;

/**
 * TODO Compl�ter...
 * Classe permettant d'instancier un joueur dans le jeu Battleships.
 * Deux classes enfants existent: HumanPlayer et ComputerPlayer
 * @author Annie Belzile
 */
public abstract class Player {
	/**
	 * TODO Compl�ter...
	 * M�thode permettant de placer les bateaux dans la grille de jeu en m�moire. La source peut
	 * �tre � partir de l'interface pour HumanPlayer ou al�atoire pour ComputerPlayer 
	 */
	public abstract void setBoats();
	/**
	 * TODO 
	 */
	public abstract void shootEnemy();
}
