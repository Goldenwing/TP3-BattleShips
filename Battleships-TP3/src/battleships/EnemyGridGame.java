package battleships;

import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class EnemyGridGame 
{
	
	
	
	private ImageView[][] tabImage;
	
	public EnemyGridGame()
	
	{
		
	}
	
	
	public Group setGrid()
	{
		Group rootEnemy = new Group();
		
		Text name = new Text("Ordinateur");
		name.setX(385);
		name.setY(870);
		name.setFont(new Font(20));
		int nbSquare = 11;
		
		int xSquare = 50; // coordonnees x de l'image
		int ySquare = 70; // coordonnees y de l'image
				
		for(int i = 0; i < nbSquare; i++)
		{
		
			if( i > 0)
			{
				xSquare += 70;
				ySquare = 70;
			}
			for(int j = 0; j <  nbSquare; j++)
			{
				
				if(j > 0)
				{
					ySquare += 70;
				}
				ImageView waterTiles = new ImageView(new Image("file:Images/water.png"));
				waterTiles.setX(xSquare);
				waterTiles.setY(ySquare);
				waterTiles.setOnMouseClicked(new MouseListener());
				this.tabImage[i][j] = waterTiles;
				rootEnemy.getChildren().add(waterTiles);
			
			}
		}
		
		rootEnemy.getChildren().add(name);
		
		return rootEnemy;
		
	}
	
	private class MouseListener implements EventHandler<MouseEvent>
	{

		@Override
		public void handle(MouseEvent arg0) 
		{
//			if clicked && boat, if clicked != boat, not clicked, already clicked
			
			
			
			
		}
	}
}
