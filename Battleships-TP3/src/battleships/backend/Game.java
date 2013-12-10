package battleships.backend;

import java.util.Random;

public class Game 
{

	public enum Boats
    {
            AIRCRAFT(5, "Porte-avions"),
            BATTLESHIP(4, "Croiseur"),
            DESTROYER(3, "Torpilleur"),
            SUBMARINE(3, "Sous-marin"),
            PATROL(2, "Patrouilleur");
            
            private int size = 0;
            private String nameBoat = "";
            
            private Boats(int size, String name)
            {
                    this.size = size;
                    this.nameBoat = name;
            }
            
            public int getSize()
            {
                    return this.size;
            }
            
            public String getBoatName() 
            {
                    return this.nameBoat;
            }
    }

	private final int GAME_SIZE = 10;
	private Matrix playerMatrix;
	private Matrix computerMatrix;
	private Random PCBoatPlacer = new Random();
	private ComputerPlayer enemy;
	private HumanPlayer gamer;
	
	public Game()
	{
		this.playerMatrix = null;
		this.computerMatrix = null;
		
		this.playerMatrix = new Matrix();
		this.computerMatrix = new Matrix(true);
		
		this.enemy = null;
		this.gamer = null;
		
		this.enemy = new ComputerPlayer();
		this.gamer = new HumanPlayer("Link");

		
		enemy.setBoats(this.computerMatrix, PCBoatPlacer.nextBoolean(), Boats.AIRCRAFT);
		enemy.setBoats(this.computerMatrix, PCBoatPlacer.nextBoolean(), Boats.BATTLESHIP);
		enemy.setBoats(this.computerMatrix, PCBoatPlacer.nextBoolean(), Boats.DESTROYER);
		enemy.setBoats(this.computerMatrix, PCBoatPlacer.nextBoolean(), Boats.SUBMARINE);
		enemy.setBoats(this.computerMatrix, PCBoatPlacer.nextBoolean(), Boats.PATROL);
	}
	
	public Matrix getMatrix()
	{
		return this.playerMatrix;
	}
	 

	public Matrix getComputerMatrix()
	{
		return this.computerMatrix;
	}
	 
	public ComputerPlayer getEnemy() 
	{
		return enemy;
	}

	public void setEnemy(ComputerPlayer enemy) 
	{
		this.enemy = enemy;
	}

	public HumanPlayer getGamer() 
	{
		return gamer;
	}

	public void setGamer(HumanPlayer gamer) 
	{
		this.gamer = gamer;
	}

	
	public void setPlayerShips(String boat, int size, boolean direction)
	{
		//this.gamer.setBoats();
	}
	
	  public boolean checkBoatsUser(int[][] tableXY, List<RadioButton> listRbuttonChecked)
    	{
    		 int []tableSizeBoats =  {5,4,3,3,2};
    		
    		 int x;
    		 int y;
    		 String textRb;
    		 boolean verified = true;
    		 Boats boat = Boats.AIRCRAFT;
    		for(int j = 0; j < 5; j++)
    		{
    			
    			x = tableXY[0][j];
    			y = tableXY[1][j];
    			textRb = listRbuttonChecked.get(j).getText();
    	
    			if(textRb.equals("Horizontal"))
    			{
    				if ((x >= 1) && ((x + tableSizeBoats[j]) <= 11) && (y >= 1) && (y <= 10) && (verified == true))
    				{
    						
    					 verified = true;
    					 Boats boatSended = boat.getBoatByNumber(j);
    					 this.gamer.setBoats(x,  y, true, boatSended);
    				}
    				else
    				{
    					verified = false;
    				}
    			}
    			else if (textRb == "Vertical")
    			{
    				if ((y >= 1) && ((y + tableSizeBoats[j]) <= 11) && (x >= 1) && (x <= 10) && (verified == true))
    				{
    					 verified = true;
    					 this.gamer.setBoats(x,  y, false, boat);
    				}
    				else
    				{
    					verified = false;
    				}
    			}
    			
    			
    		}
    		
    		return verified;
    	}
        
}
	
//	public static void main(String[] args)
//	{
//		
//		Game game = new Game();
//		Matrix computerMatrix = game.getComputerMatrix();
//		for(int i = 0; i < 10; i++)
//		   {
//		      for(int j = 0; j < 10; j++)
//		      {
//		         System.out.printf("%5d ", computerMatrix.getSquareContentNumber(i, j, true));
//		      }
//		      System.out.println();
//		   }
//	}
