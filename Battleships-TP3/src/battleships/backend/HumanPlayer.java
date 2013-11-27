package battleships.backend;


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

}
