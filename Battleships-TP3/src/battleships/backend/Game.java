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
		this.gamer.setBoats();
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
