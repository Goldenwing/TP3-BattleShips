package battleships.backend;

import battleships.backend.Game.Boats;


/**
 * Extension de la super-classe Player qui permet d'instancier un joueur humain (joueur interagissant
 * avec l'interface graphique pour jouer)
 * @author Annie Belzile
 *
 */
public class HumanPlayer extends Player {
	
	private String playerName;
	
	public HumanPlayer(String name) {
		this.playerName = name;
	}

	public void setBoats(Matrix gameGrid, int[][] humanBoatsCoordinates) {
		
		
	}

	@Override
	public void shootEnemy(Matrix gameGrid) {
		// TODO Auto-generated method stub
		
	}
	
	public String getPlayerName() {
		return this.playerName;
	}
	
	public void setPlayerName(String name) {
		this.playerName = name;
	}

	@Override
	public void setBoats() {
		// TODO Auto-generated method stub
		
	}

}
