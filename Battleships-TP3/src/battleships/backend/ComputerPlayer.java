package battleships.backend;

import java.util.Random;

import battleships.BattleGame;
import battleships.backend.Game.Boats;


/**
 * Extension de la super-classe Player qui permet d'instancier une intelligence artificielle simulant
 * un joueur. ComputerPlayer utilise majoritairement de fonctions aleatoires afin de placer les bateaux
 * et tirer sur l'ennemi.
 * @author Annie Belzile
 *
 */
        
    public class ComputerPlayer extends Player 
    {
        
        private final int GAME_SIZE = 10;
        private String playerName;
        private Random coordinateNumber = new Random();
        private Game game;
        private GoodShot lastGoodShot;
        private BattleGame battleGame;
            
        public ComputerPlayer(Game game) 
        {
            this.playerName = "HAL-9000";
            this.lastGoodShot = null;
            this.game = game;
        }
    
    @Override
    public void setBoats(Matrix gameGrid, boolean direction, Boats boat) 
    {

            int randomX = 0;
            int randomY = 0;
            
            while(randomX == 0 || randomY == 0 ||!gameGrid.checkSpace(boat, direction, randomX, randomY, gameGrid.getComputerGameMatrix()))
            {
                    randomX = coordinateNumber.nextInt(this.GAME_SIZE) +1;
                randomY = coordinateNumber.nextInt(this.GAME_SIZE) +1;
            }
            
            if(direction) 
            { //si horizontal
                    for(int x = randomX; x < randomX + boat.getSize(); x++) 
                    {
                            gameGrid.setSquareContent(x, randomY, boat, true, boat.getBoatName());   
                    }
            } 
            else 
            { // si vertical
                    for(int y = randomY; y < randomY + boat.getSize(); y++) 
                    {      
                            gameGrid.setSquareContent(randomX, y, boat, true, boat.getBoatName());
                    }
            }   
    }

    @Override
    public boolean shootEnemy(Matrix gameGrid) { //"intelligence" artificielle O_o
            
            boolean isMyTurn = true; //Signale que c'est a l'autre joueur a jouer
            int coordX = 1;
            int coordY = 1;
            
            if (this.lastGoodShot == null) { //Partir d'un coup random ou du dernier bon coup
                    do {
                            coordX = coordinateNumber.nextInt(this.GAME_SIZE);
                    coordY = coordinateNumber.nextInt(this.GAME_SIZE);
                    } while(gameGrid.getSquareContentCheck(coordX, coordY, false));
            } else {
                    coordX = this.lastGoodShot.getCoordX();
                    coordY = this.lastGoodShot.getCoordY();
            }
            
            //On shoot des bombes!
            do {
                        if(gameGrid.getSquareContentNumber(coordX, coordY, false) == 0) { //Coup dans l'eau
                                isMyTurn = false;
                                this.lastGoodShot = null;
                                gameGrid.setSquareCheck(coordX, coordY, true, false);
                                this.game.UpHitCounter(true);
                                this.battleGame.getMyGrid().changeStateSquare(false, coordX, coordY);
                        }
                        else { //Hit!
                                gameGrid.setSquareCheck(coordX, coordY, true, false);
                                this.battleGame.getMyGrid().changeStateSquare(true, coordX, coordY);
                                this.game.UpHitCounter(true);
                                
                                if(this.game.DidWeWin(gameGrid))
                            	{
                            		this.game.DefeatPanel();
                            	}
                                else
                                {
	                                do { //On regarde en haut
	                                        GoodShot goodShotAttempt = new GoodShot(coordX, coordY);
	                                        goodShotAttempt = checkSquareUp(this.lastGoodShot.getCoordX(), this.lastGoodShot.getCoordY(), gameGrid);
	                                        if (goodShotAttempt != null) {
	                                                isMyTurn = true;
	                                                this.lastGoodShot = goodShotAttempt;
	                                                this.lastGoodShot.setShotsRemaining(this.lastGoodShot.getShotsRemaining() - 1);
	                                                gameGrid.setSquareCheck(this.lastGoodShot.getCoordX(), this.lastGoodShot.getCoordY(), true, false);
	                                                this.battleGame.getMyGrid().changeStateSquare(true, this.lastGoodShot.getCoordX(), this.lastGoodShot.getCoordY());
	                                        }
	                                } while(this.lastGoodShot.getCoordY() < this.GAME_SIZE - 1 && this.lastGoodShot.getShotsRemaining() != 0);
	                                
	                                do { //On regarde en bas
	                                        GoodShot goodShotAttempt = new GoodShot(coordX, coordY);
	                                        goodShotAttempt = checkSquareDown(this.lastGoodShot.getCoordX(), this.lastGoodShot.getCoordY(), gameGrid);
	                                        if (goodShotAttempt != null) {
	                                                isMyTurn = true;
	                                                this.lastGoodShot = goodShotAttempt;
	                                                this.lastGoodShot.setShotsRemaining(this.lastGoodShot.getShotsRemaining() - 1);
	                                                gameGrid.setSquareCheck(this.lastGoodShot.getCoordX(), this.lastGoodShot.getCoordY(), true, false);
	                                                this.battleGame.getMyGrid().changeStateSquare(true, this.lastGoodShot.getCoordX(), this.lastGoodShot.getCoordY());
	                                        }
	                                } while(this.lastGoodShot.getCoordY() >= 0 && this.lastGoodShot.getShotsRemaining() != 0);
	                                
	                                do { //On regarde a gauche
	                                        GoodShot goodShotAttempt = new GoodShot(this.lastGoodShot.getCoordX(), this.lastGoodShot.getCoordY());
	                                        goodShotAttempt = checkSquareLeft(this.lastGoodShot.getCoordX(), this.lastGoodShot.getCoordY(), gameGrid);
	                                        if (goodShotAttempt != null) {
	                                                isMyTurn = true;
	                                                this.lastGoodShot = goodShotAttempt;
	                                                this.lastGoodShot.setShotsRemaining(this.lastGoodShot.getShotsRemaining() - 1);
	                                                gameGrid.setSquareCheck(this.lastGoodShot.getCoordX(), this.lastGoodShot.getCoordY(), true, false);
	                                                this.battleGame.getMyGrid().changeStateSquare(true, this.lastGoodShot.getCoordX(), this.lastGoodShot.getCoordY());
	                                        }
	                                } while(this.lastGoodShot.getCoordX() >= 0 && this.lastGoodShot.getShotsRemaining() != 0);
	                                
	                                do { //On regarde a droite
	                                        GoodShot goodShotAttempt = new GoodShot(this.lastGoodShot.getCoordX(), this.lastGoodShot.getCoordY());
	                                        goodShotAttempt = checkSquareRight(this.lastGoodShot.getCoordX(), this.lastGoodShot.getCoordY(), gameGrid);
	                                        if (goodShotAttempt != null) {
	                                                isMyTurn = true;
	                                                this.lastGoodShot = goodShotAttempt;
	                                                this.lastGoodShot.setShotsRemaining(this.lastGoodShot.getShotsRemaining() - 1);
	                                                gameGrid.setSquareCheck(this.lastGoodShot.getCoordX(), this.lastGoodShot.getCoordY(), true, false);
	                                                this.battleGame.getMyGrid().changeStateSquare(true, this.lastGoodShot.getCoordX(), this.lastGoodShot.getCoordY());
	                                        }
	                                } while(this.lastGoodShot.getCoordX() < this.GAME_SIZE - 1 && this.lastGoodShot.getShotsRemaining() != 0);
                                }
                        }
            } while(isMyTurn);
            return isMyTurn;
    }
        
    public String getPlayerName() {
            return this.playerName;
    }
    
    ///////private methods
    
    private GoodShot checkSquareUp(int x, int y, Matrix gameGrid) {
            if (gameGrid.getSquareContentNumber(x, y + 1, false) == 0) {
                    return null;
            }
            else {
                    GoodShot newGoodShot = new GoodShot(x, y + 1, null);
                    return newGoodShot;
            }
    }
    
    private GoodShot checkSquareDown(int x, int y, Matrix gameGrid) {
            if (gameGrid.getSquareContentNumber(x, y - 1, false) == 0) {
                    return null;
            }
            else {
                    GoodShot newGoodShot = new GoodShot(x, y - 1, null);
                    return newGoodShot;
            }
    }
    
    private GoodShot checkSquareLeft(int x, int y, Matrix gameGrid) {
            if (gameGrid.getSquareContentNumber(x - 1, y, false) == 0) {
                    return null;
            }
            else {
                    GoodShot newGoodShot = new GoodShot(x - 1, y, null);
                    return newGoodShot;
            }
    }
    
    private GoodShot checkSquareRight(int x, int y, Matrix gameGrid) {
            if (gameGrid.getSquareContentNumber(x + 1, y, false) == 0) {
                    return null;
            }
            else {
                    GoodShot newGoodShot = new GoodShot(x + 1, y, null);
                    return newGoodShot;
            }
    }
    
}
