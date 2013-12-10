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
        
        public HumanPlayer(String name)
        {
                this.playerName = name;
        }

       
 
        
        @Override
        public boolean shootEnemy(Matrix gameGrid) {
                        return false;
                // TODO Auto-generated method stub
                
        }
        
        public String getPlayerName() {
                return this.playerName;
        }
        
        public void setPlayerName(String name) {
                this.playerName = name;
        }


		
		public void setBoats(Matrix gameGrid, int verifX, int verifY, boolean direction, Boats boat)
		
		{
			
			int randomX = 0;
			int randomY = 0;
			
		
			
			 while(randomX == 0 || randomY == 0 ||!gameGrid.checkSpace(boat, direction, randomX, randomY))
	            {
	            	randomX++;
	                randomY++;
	            }
	            
        	
//        	if(direction) 
//            { //si horizontal
//        		
//                for(int x =verifX; x <verifX + boat.getSize(); x++) 
//                {
//                    gameGrid.setSquareContent(x, verifY, boat, true, boat.getBoatName());   
//                }
//            } 
//               
//        	else 
//               
//        	{ // si vertical
//                      
//        		for(int y = verifY; y < verifY + boat.getSize(); y++) 
//                      
//        		{      
//                    gameGrid.setSquareContent(verifX, y, boat, true, boat.getBoatName());
//                      
//        		}
//              
//        	}  
			
		}




		@Override
		public void setBoats(Matrix gameGrid, boolean direction, Boats boat) {
			// TODO Auto-generated method stub
			
		}
}
