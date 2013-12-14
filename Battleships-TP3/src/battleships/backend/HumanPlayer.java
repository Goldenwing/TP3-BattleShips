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
        public void shootEnemy(Matrix gameGrid) {
                        
                
                
        }
        
        public String getPlayerName() {
                return this.playerName;
        }
        
        public void setPlayerName(String name) {
                this.playerName = name;
        }


		
		public boolean setBoats(Matrix gameGrid, int verifX, int verifY, boolean direction, Boats boat)
		
		{
			
			int randomX = 1;
			int randomY = 1;
			boolean valid = true;
			int size = boat.getSize();
			
			 
			if(gameGrid.checkSpace(boat, direction, verifX, verifY, gameGrid.getGameMatrix()) && (randomX <= size && randomY <= size) && valid == true)
			{
				randomX++;
                randomY++;
				valid = true;
			}
			else
			{
				valid = false;
			}
			
			
//			 while(randomX == 0 || randomY == 0 ||!gameGrid.checkSpace(boat, direction, randomX, randomY, gameGrid.getGameMatrix()))
//	            {
//	            	randomX++;
//	                randomY++;
//	            }
	            
        	if(valid)
        	{
        		
        	
	        	if(direction) 
	            { //si horizontal
	        		
	                for(int x =verifX; x <verifX + boat.getSize(); x++) 
	                {
	                    gameGrid.setSquareContent(x, verifY, boat, false, boat.getBoatName());   
	                }
	            } 
	               
	        	else 
	               
	        	{ // si vertical
	                      
	        		for(int y = verifY; y < verifY + boat.getSize(); y++) 
	                      
	        		{      
	                    gameGrid.setSquareContent(verifX, y, boat, false, boat.getBoatName());
	                      
	        		}
	              
	        	}  
			
        	}
        	
        	return valid;
		}




		@Override
		public void setBoats(Matrix gameGrid, boolean direction, Boats boat) {
			// TODO Auto-generated method stub
			
		}
}
