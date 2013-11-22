package gameField.backend;

public class Game 
{

	public enum Boats
	{
		AIRCRAFT(5),
		BATTLESHIP(4),
		DESTROYER(3),
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
}
