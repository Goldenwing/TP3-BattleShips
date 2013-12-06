package battleships;



import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.layout.GridPane;

public class MyGameGrid
{
	GridPane gameGrid;
	
	public MyGameGrid()
	
	
	{
		
	}
	
	
	public void setBoats()
	{
		
	}
	
	public GridPane setGrid(String nom)
	{
		this.gameGrid = new GridPane();

		Text name = new Text(nom);
		name.setX(1240);
		name.setY(870);
		name.setFont(new Font(20));

		int nbSquare = 11;
		
//		int xSquare = 875; // coordonnees x de l'image
//		int ySquare = 70; // coordonnees y de l'image
				
//		for(int i = 0; i < nbSquare; i++)
//		{
//			if( i > 0)
//			{
//				xSquare += 70;
//				ySquare = 70;
//			}
//			for(int j = 0; j <  nbSquare; j++)
//			{
//				
//				if(j > 0)
//				{
//					ySquare += 70;
//				}
//				ImageView waterTiles = new ImageView(new Image("file:Images/water.png"));
//				waterTiles.setX(xSquare);
//				waterTiles.setY(ySquare);
//				myRoot.getChildren().add(waterTiles);
//			}
//		}
//		myRoot.getChildren().add(name);
		
		for(int i = 0; i < nbSquare; i++)
		{
			for(int j = 0; j < nbSquare; j++)
			{
				Image image = new Image("file:Images/water.png");
				ImageView imageView = new ImageView();
				imageView.setImage(image);
				
				ImageViewClickListener imageViewClick = new ImageViewClickListener();
					imageView.setOnMouseClicked(imageViewClick);
					
				this.gameGrid.add(imageView, i, j);
				
//				this.imageBoard[i][j] = imageView;
			}
		}
		
		return this.gameGrid;
	}
		
		private class ImageViewClickListener implements EventHandler<MouseEvent>
		{

			@Override
			public void handle(MouseEvent arg0) 
			{
				// TODO Auto-generated method stub
				
			}
			
		}
	
	
}
