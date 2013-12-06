package battleships;



import java.util.List;

import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class MyGameGrid

{
	private ImageView[][] imageViewTab;
	
	public MyGameGrid()
	
	
	{
		this.imageViewTab = new ImageView[11][11];	
	}
	
	public ImageView[][] getImageTabViewMyGrid()
	{
		return this.imageViewTab;
	}
	
	public void setBoats()
	{
		
	}
	
	public Group setGrid(String nom)
	{
		Group myRoot = new Group();
		
		Text name = new Text(nom);
		name.setX(1240);
		name.setY(870);
		name.setFont(new Font(20));

		int nbSquare = 11;
		
		int xSquare = 875; // coordonnees x de l'image
		int ySquare = 70; // coordonnees y de l'image
		ImageList imageList =  new ImageList();
		
		List<Image> imageListNumber = imageList.imageListNumbers();
        List<Image> imageListLetter = imageList.imageListLetters();
                           
				
		for(int i = 0; i < nbSquare; i++)
		{
			if( i > 0)
			{
				xSquare += 70;
				ySquare = 70;
			}
			for(int j = 0; j <  nbSquare; j++)
			{
				
				ImageView waterTiles = new ImageView(new Image("file:Images/water.png"));
                
                if(j > 0 && i > 0)
                {
                        ySquare += 70;
                        waterTiles = new ImageView(new Image("file:Images/water.png"));
                }
                else if(j == 0 && i > 0)
                {
                	 waterTiles = new ImageView(imageListNumber.get(i - 1));
                }
                
                else if(j > 0 && i == 0)
                {
                	 ySquare += 70;
                	 waterTiles = new ImageView(imageListLetter.get(j - 1));
                }
				waterTiles.setX(xSquare);
				waterTiles.setY(ySquare);
				

				this.imageViewTab[i][j] = waterTiles;

				myRoot.getChildren().add(waterTiles);
			

			}
		}
		
		
		
		myRoot.getChildren().add(name);
		
		return myRoot;
		
	}
	
	
}
