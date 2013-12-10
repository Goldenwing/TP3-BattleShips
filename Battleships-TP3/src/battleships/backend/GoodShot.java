package battleships.backend;

import battleships.backend.Game.Boats;
/**
 * Classe servant a conserver les infos sur un coup reussi par le joueur-ordinateur du jeu Battleships
 * Ces infos sont conservees afin que le joueur-ordinateur puisse reprendre la ou il est
 * rendu chaque fois que son tour est venu
 * @author Annie Belzile
 *
 */
public class GoodShot {

	private int coordX = 0;
	private int coordY = 0;
	private String boatName = "";
	private int shotsRemaining = 0;
	private int boatSize = 0;
	
	public GoodShot(int x, int y, Boats boat) {
		this.coordX = x;
		this.coordY = y;
		this.boatName = boat.getBoatName();
		this.boatSize = boat.getSize();
		this.shotsRemaining = boat.getSize();
	}
	
	public GoodShot(int x, int y) {
		this.coordX = x;
		this.coordY = y;
		this.boatName = "";
		this.boatSize = 0;
		this.shotsRemaining = 0;
	}
	
    /**
     * Retourne la coordonnee en X d'un coup reussi
     * @return un entier contenant la coordonnee en X
     */
	public int getCoordX() {
		return this.coordX;
	}
	/**
	 * Place la coordonnee en X d'un coup reussi dans l'attribut
	 * @param coordX un entier contenant la coordonnee en X
	 */
	public void setCoordX(int coordX) {
		this.coordX = coordX;
	}
	/**
     * Retourne la coordonnee en Y d'un coup reussi
     * @return un entier contenant la coordonnee en Y
     */
	public int getCoordY() {
		return this.coordY;
	}
	/**
	 * Place la coordonnee en Y d'un coup reussi dans l'attribut
	 * @param coordY un entier contenant la coordonnee en Y
	 */
	public void setCoordY(int coordY) {
		this.coordY = coordY;
	}
	/**
	 * Retourne le nom du bateau touche
	 * @return String contenant le nom du bateau
	 */
	public String getBoatName() {
		return this.boatName;
	}
	/**
	 * Place le nom du bateau touche dans l'attribut
	 * @param boatName String contenant le nom du bateau
	 */
	public void setBoatName(String boatName) {
		this.boatName = boatName;
	}
	/**
	 * Retourne la taille du bateau touche
	 * @return un entier contenant la taille du bateau 
	 */
	public int getBoatSize() {
		return this.boatSize;
	}
	/**
	 * Place la taille du bateau touche en attribut
	 * @param boatSize un entier contenant la taille du bateau
	 */
	public void setBoatSize(int boatSize) {
		this.boatSize = boatSize;
	}
	/**
	 * Retourne le nombre de coups restants avant de couler le bateau. Cette valeur est
	 * decrementee a chaque fois que le bateau est touche. Lorsqu'elle est a 0, le bateau
	 * est coule
	 * @return un entier representant le nombre de coups restants
	 */
	public int getShotsRemaining() {
		return this.shotsRemaining;
	}
	/**
	 * Ajuste le nobre de coups restants avant de couler le bateau
	 * @param shotsRemaining un entier representant le nombre de coups restants
	 */
	public void setShotsRemaining(int shotsRemaining) {
		this.shotsRemaining = shotsRemaining;
	}
}
