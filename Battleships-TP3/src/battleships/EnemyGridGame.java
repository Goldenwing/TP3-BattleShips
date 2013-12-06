package battleships;

import java.util.List;

import battleships.backend.Game;
import battleships.backend.Matrix;

import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class EnemyGridGame
{
	private ImageView[][] imageViewTab;
	Game game;
        
    public EnemyGridGame(Game game)
    {
    	this.game = game;
        this.imageViewTab = new ImageView[11][11];        
    }
        
    public ImageView[][] getImageTab()
    {
    	return this.imageViewTab;
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
        	
        	for(int j = 0; j < nbSquare; j++)
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
	
	            this.imageViewTab[i][j] = waterTiles;
	            waterTiles.setX(xSquare);
	            waterTiles.setY(ySquare);
	            rootEnemy.getChildren().add(waterTiles);
	         }
        }
        
        for(int i = 1; i < nbSquare; i++)
        {
        	for(int j = 1; j < nbSquare; j++)
        	{
        		 this.imageViewTab[i][j].setOnMouseClicked(new MouseListener());
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
			Matrix gameMatrix = EnemyGridGame.this.game.getComputerMatrix();
			
			ImageView imageView = (ImageView) arg0.getSource();
			
			int quickX = EnemyGridGame.this.getSquareX(imageView);
			int quickY = EnemyGridGame.this.getSquareY(imageView);
			
			if(gameMatrix.getSquareContentCheck(quickX, quickY, true) == true)
			{
				
			}
			else
			{
				gameMatrix.setSquareCheck(quickX, quickY, true, true);
				Image imageRedX = new Image("file:Images/water-red.png");
				Image imageWhiteX = new Image("file:Images/water-white.png");
				
				switch(gameMatrix.getSquareContentNumber(quickX, quickY, true))
				{
					case 0:
					{
						imageView.setImage(imageWhiteX);
						
						break;
					}
					case 2: case 3: case 4: case 5:
					{
						imageView.setImage(imageRedX);
					}
				}
			}
		}
	}
	
	private int getSquareX(ImageView imageView)
	{
		int x = 0;
		
		for(int i = 0; i< 11;i++)
		{
			for(int j = 0; j< 11;j++)
			{
				if(this.imageViewTab[i][j] == imageView)
				{
					x = i;
				}
			}
		}
		return x;
	}
	
	private int getSquareY(ImageView imageView)
	{
		int y = 0;
		
		for(int i = 0; i< 11;i++)
		{
			for(int j = 0; j< 11;j++)
			{
				if(this.imageViewTab[i][j] == imageView)
				{
					y = j;
				}
			}
		}
		return y;
	}
}

