package battleships.backend;

import java.util.Random;

import battleships.backend.Game.Boats;


/**
 * Extension de la super-classe Player qui permet d'instancier une intelligence artificielle simulant
 * un joueur. ComputerPlayer utilise majoritairement de fonctions aleatoires afin de placer les bateaux
 *  et tirer sur l'ennemi.
 * @author Annie Belzile
 *
 */
public class ComputerPlayer extends Player 
{
		private final int GAME_SIZE = 10;
        private String playerName;
        Random PCBoatPlacer = new Random();
        
        public ComputerPlayer(String name) 
        {
                this.playerName = name;
        }

        @Override
        public void setBoats(Matrix gameGrid, boolean direction, Boats boat) 
        {
        	int randomX = PCBoatPlacer.nextInt(this.GAME_SIZE -1);
    		int randomY = PCBoatPlacer.nextInt(this.GAME_SIZE -1);
    		
    		while(!gameGrid.checkSpace(boat, direction, randomX, randomY))
    		{
    			randomX = PCBoatPlacer.nextInt(this.GAME_SIZE -1);
    			randomY = PCBoatPlacer.nextInt(this.GAME_SIZE -1);
    		}

    		if(direction) //si horizontal
    		{
    			for(int x = randomX; x < randomX + boat.getSize(); x++)
    			{
    				gameGrid.setSquareContent(x, randomY, boat.getSize(), true);
    			}
    		}
    		else // si vertical
    		{
    			for(int y = randomY; y < randomY + boat.getSize(); y++)
    			{
    					gameGrid.setSquareContent(randomX, y, boat.getSize(), true);
    			}
    		}
                
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
