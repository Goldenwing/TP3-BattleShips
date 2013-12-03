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
	
	public Game()
	{
		this.playerMatrix = null;
		this.computerMatrix = null;
		
		this.playerMatrix = new Matrix();
		this.computerMatrix = new Matrix(true);
		
		ComputerPlayer enemy = new ComputerPlayer();
		HumanPlayer gamer = new HumanPlayer("Link");

	}
	
	 public Matrix getMatrix()
	 {
		 return this.playerMatrix;
	 }
	 
	 public Matrix getComputerMatrix()
	 {
		 return this.computerMatrix;
	 }
}
