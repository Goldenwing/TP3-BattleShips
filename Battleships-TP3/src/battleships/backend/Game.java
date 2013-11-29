package battleships.backend;

import java.util.Random;

public class Game 
{

	public enum Boats
	{
		AIRCRAFT(5),
		BATTLESHIP(4),
		DESTROYER(3),
		SUBMARINE(3),
		PATROL(2);
		
		private int size = 0;
		
		private Boats(int size)
		{
			this.size = size;
		}
		
		public int getSize()
		{
			return this.size;
		}
	}

	private final int GAME_SIZE = 10;
	private Matrix playerMatrix;
	private Matrix computerMatrix;
	private Random PCBoatPlacer = new Random();
	
	public Game()
	{
		this.playerMatrix = null;
		this.computerMatrix = null;
		
		this.playerMatrix = new Matrix();
		this.computerMatrix = new Matrix(true);
		
		ComputerPlayer enemy = new ComputerPlayer();
		HumanPlayer gamer = new HumanPlayer("Link");
		
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
	 
	public void setComputerShips(Boats boat, boolean direction)
	{
		int randomX = PCBoatPlacer.nextInt(this.GAME_SIZE -1);
		int randomY = PCBoatPlacer.nextInt(this.GAME_SIZE -1);
		
		while(!this.computerMatrix.checkSpace(boat, direction, randomX, randomY))
		{
			randomX = PCBoatPlacer.nextInt(this.GAME_SIZE -1);
			randomY = PCBoatPlacer.nextInt(this.GAME_SIZE -1);
		}

		if(direction) //si horizontal
		{
			for(int x = randomX; x < randomX + boat.getSize(); x++)
			{
				this.computerMatrix.setSquareContent(x, randomY, boat.getSize(), true);
			}
		}
		else // si vertical
		{
			for(int y = randomY; y < randomY + boat.getSize(); y++)
			{
					this.computerMatrix.setSquareContent(randomX, y, boat.getSize(), true);
			}
		}
	}
	
	
	public void setPlayerShips(String boat, int size, boolean direction)
	{
		
	}
	
	public static void main(String[] args)
	{
		
		Game game = new Game();
		Matrix computerMatrix = game.getComputerMatrix();
		for(int i = 0; i < 10; i++)
		   {
		      for(int j = 0; j < 10; j++)
		      {
		         System.out.printf("%5d ", computerMatrix.getSquareContentNumber(i, j, true));
		      }
		      System.out.println();
		   }
	}
}
