package battleships.backend;

/**
 * TODO Compléter...
 * Classe permettant d'instancier un joueur dans le jeu Battleships.
 * Deux classes enfants existent: HumanPlayer et ComputerPlayer
 * @author Annie Belzile
 */
public abstract class Player {
	/**
	 * TODO Compléter...
	 * Méthode permettant de placer les bateaux dans la grille de jeu en mémoire. La source peut
	 * être à partir de l'interface pour HumanPlayer ou aléatoire pour ComputerPlayer 
	 */
	public abstract void setBoats();
	/**
	 * TODO 
	 */
	public abstract void shootEnemy();
}
