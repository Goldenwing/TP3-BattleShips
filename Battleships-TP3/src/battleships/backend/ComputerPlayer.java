package battleships.backend;

import java.util.Random;


/**
 * Extension de la super-classe Player qui permet d'instancier une intelligence artificielle simulant
 * un joueur. ComputerPlayer utilise majoritairement de fonctions aleatoires afin de placer les bateaux
 *  et tirer sur l'ennemi.
 * @author Annie Belzile
 *
 */
public class ComputerPlayer extends Player {
	
	private String playerName;
	
	public ComputerPlayer(String name) {
		this.playerName = name;
	}

	@Override
	public void setBoats(Matrix gameGrid) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void shootEnemy(Matrix gameGrid) {
		// TODO Auto-generated method stub
		
	}
	
	public String getPlayerName() {
		return this.playerName;
	}
	
	/////////////////////private methods

	private int genenrateRandomNumber(int minValue, int maxValue) {
		//Returns an random integer included between the two specified values 
		Random rand = new Random();
		return rand.nextInt(maxValue - minValue + 1) + minValue;
	}

}
