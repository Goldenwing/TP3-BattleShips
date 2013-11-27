package battleships.backend;

/**
 * TODO Complete...
 * Classe permettant d'instancier un joueur dans le jeu Battleships.
 * Deux classes enfants existent: HumanPlayer et ComputerPlayer
 * @author Annie Belzile
 */
public abstract class Player {
	/**
	 * TODO Complete...
	 * Methode permettant de placer les bateaux dans la grille de jeu en memoire. La source peut
	 * etre a partir de l'interface pour HumanPlayer ou aleatoire pour ComputerPlayer 
	 */
	public abstract void setBoats(Matrix gameGrid);
	/**
	 * TODO 
	 */
	public abstract void shootEnemy(Matrix gameGrid);
}
