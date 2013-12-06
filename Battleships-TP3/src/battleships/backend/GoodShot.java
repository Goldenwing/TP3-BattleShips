package battleships.backend;

import battleships.backend.Game.Boats;
/**
 * Classe servant a conserver les infos sur un coup reussi par le joueur-ordinateur du jeu Battleships
 * Ces infos sont conservees dans une pile afin que le joueur-ordinateur puisse reprendre la ou il est
 * rendu chaque fois que son tour est venu
 * @author Annie Belzile
 *
 */
public class GoodShot {

	private int coordX = 0;
	private int coordY = 0;
	private String boatName = "";

	private int boatSize = 0;
	
	public GoodShot(int x, int y, Boats boat) {
		this.coordX = x;
		this.coordY = y;
		this.boatName = boat.getBoatName();
		this.boatSize = boat.getSize();
	}

	public int getCoordX() {
		return coordX;
	}

	public void setCoordX(int coordX) {
		this.coordX = coordX;
	}

	public int getCoordY() {
		return coordY;
	}

	public void setCoordY(int coordY) {
		this.coordY = coordY;
	}

	public String getBoatName() {
		return boatName;
	}

	public void setBoatName(String boatName) {
		this.boatName = boatName;
	}

	public int getBoatSize() {
		return boatSize;
	}

	public void setBoatSize(int boatSize) {
		this.boatSize = boatSize;
	}
}
