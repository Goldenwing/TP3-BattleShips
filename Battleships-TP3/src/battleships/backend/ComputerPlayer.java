package battleships.backend;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Random;

import battleships.backend.Game.Boats;


/**
 * Extension de la super-classe Player qui permet d'instancier une intelligence artificielle simulant
 * un joueur. ComputerPlayer utilise majoritairement de fonctions aleatoires afin de placer les bateaux
 *  et tirer sur l'ennemi.
 * @author Annie Belzile
 *
 */
public class ComputerPlayer extends Player {
        
    private final int GAME_SIZE = 10;
    private String playerName;
    private Random coordinateNumber = new Random();
    private Deque<GoodShot> goodShotsStack = new ArrayDeque<GoodShot>();
        
    public ComputerPlayer() {
    	
        this.playerName = "HAL-9000";
        this.goodShotsStack = null;
        }


    public void setBoats(Matrix gameGrid, boolean direction, Boats boat) {

    	int randomX = coordinateNumber.nextInt(this.GAME_SIZE -1);
    	int randomY = coordinateNumber.nextInt(this.GAME_SIZE -1);

    	while(!gameGrid.checkSpace(boat, direction, randomX, randomY)) {
    
    		randomX = coordinateNumber.nextInt(this.GAME_SIZE -1);         
    		randomY = coordinateNumber.nextInt(this.GAME_SIZE -1);
    	}
    	
    	if(direction) {        //si horizontal
    		for(int x = randomX; x < randomX + boat.getSize(); x++)        {
    			gameGrid.setSquareContent(x, randomY, boat.getSize(), true);   
    		}
    	} 
    	else { // si vertical
    		for(int y = randomY; y < randomY + boat.getSize(); y++)        {      
    			gameGrid.setSquareContent(randomX, y, boat.getSize(), true);
    		}
    	}   
    }

    @Override
    public boolean shootEnemy(Matrix gameGrid) { //"Supposée" intelligence artificielle O_o
    	
    	boolean isMyTurn = true; //Signale que c'est a l'autre joueur a jouer
    	int randomX = coordinateNumber.nextInt(this.GAME_SIZE -1);
    	int randomY = coordinateNumber.nextInt(this.GAME_SIZE -1);
    	
    	if(gameGrid.getSquareContentCheck(randomX, randomY, true)) { //Verifie si la case a deja ete cliquee
    		randomX = coordinateNumber.nextInt(this.GAME_SIZE -1);
        	randomY = coordinateNumber.nextInt(this.GAME_SIZE -1);
    	}
    	
    	while(isMyTurn) {
			if(gameGrid.getSquareContentNumber(randomX, randomY, true) == 0) {
				gameGrid.setSquareCheck(randomX, randomY, true);
				isMyTurn = false;
			}
			else {
				if(gameGrid.getSquareContentNumber(randomX + 1, randomY, true) == 0) {
					isMyTurn = false;
				} else if(gameGrid.getSquareContentNumber(randomX - 1, randomY, true) == 0) {
					isMyTurn = false;
				} else if(gameGrid.getSquareContentNumber(randomX, randomY + 1, true) == 0) {
					isMyTurn = false;
				} else if(gameGrid.getSquareContentNumber(randomX, randomY - 1, true) == 0) {
					isMyTurn = false;
				}
				
			}
    	}
    	return isMyTurn;
    }
        
    public String getPlayerName() {
    	return this.playerName;
    }
    
    
}