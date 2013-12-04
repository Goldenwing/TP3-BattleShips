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
public class ComputerPlayer extends Player {
        
                private final int GAME_SIZE = 10;
                String playerName;
        Random coordinateNumber = new Random();
        
        public ComputerPlayer() {
                
            this.playerName = "HAL-9000";
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
        public void shootEnemy(Matrix gameGrid) {
                // TODO Auto-generated method stub
                
        }
        
        public String getPlayerName() {
                return this.playerName;
        }

                public void setBoats() {
                        // TODO Auto-generated method stub
                        
                }

}