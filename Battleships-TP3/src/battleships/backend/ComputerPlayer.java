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
    	GoodShot lastGoodShot;
    	int coordX = 0;
    	int coordY = 0;
    	
    	if (goodShotsStack.peek() == null) {
    		coordX = coordinateNumber.nextInt(this.GAME_SIZE -1);
        	coordY = coordinateNumber.nextInt(this.GAME_SIZE -1);
    	} else {
    		lastGoodShot = goodShotsStack.peek();
    		coordX = lastGoodShot.getCoordX();
    		coordY = lastGoodShot.getCoordY();
    	}
    	
    	if(gameGrid.getSquareContentCheck(coordX, coordY, true)) { //Verifie si la case a deja ete cliquee
    		coordX = coordinateNumber.nextInt(this.GAME_SIZE -1);
        	coordY = coordinateNumber.nextInt(this.GAME_SIZE -1);
    	}
    	
    	while(isMyTurn) {
			if(gameGrid.getSquareContentNumber(coordX, coordY, true) == 0) {
				gameGrid.setSquareCheck(coordX, coordY, true);
				isMyTurn = false;
			}
			else {
				if (checkSquareUp(coordX, coordY, gameGrid)) {
					
				}
				
				
			}
    	}
    	return isMyTurn;
    }
        
    public String getPlayerName() {
    	return this.playerName;
    }
    
    ///////private methods
    
    private boolean checkSquareUp(int x, int y, Matrix gameGrid) {
    	if (gameGrid.getSquareContentNumber(x, y + 1, true) == 0) {
    		return false;
    	}
    	else {
    		return true;
    	}
    }
    
    private boolean checkSquareDown(int x, int y, Matrix gameGrid) {
    	if (gameGrid.getSquareContentNumber(x, y - 1, true) == 0) {
    		return false;
    	}
    	else {
    		return true;
    	}
    }
    
    private boolean checkSquareLeft(int x, int y, Matrix gameGrid) {
    	if (gameGrid.getSquareContentNumber(x - 1, y, true) == 0) {
    		return false;
    	}
    	else {
    		return true;
    	}
    }
    
    private boolean checkSquareRight(int x, int y, Matrix gameGrid) {
    	if (gameGrid.getSquareContentNumber(x + 1, y, true) == 0) {
    		return false;
    	}
    	else {
    		return true;
    	}
    }
    
}