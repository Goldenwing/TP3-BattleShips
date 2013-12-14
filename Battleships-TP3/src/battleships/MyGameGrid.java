package battleships;



import java.util.List;

import battleships.backend.Game;

import battleships.backend.MatrixTiles;

import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import javafx.scene.text.Font;
import javafx.scene.text.Text;


/**
 * Grille JavaFX de l'utilisateur contenant ses bateaux
 * @author Laurie
 *
 */
public class MyGameGrid

{
	private ImageView[][] imageViewTab;
	private Group myRoot;
	private Game game;
	
	/**
	 * Constructeur, initialise le tableau d'images
	 */
	public MyGameGrid()
	
	
	{
		this.imageViewTab = new ImageView[11][11];	
	}
	
	/**
	 * retourne le tableau d'images
	 * @return
	 */
	public ImageView[][] getImageTabViewMyGrid()
	{
		return this.imageViewTab;
	}
	

	/**
	 * Parcours le tableau pour vÃ©rifier s'il y a des bateaux dans la matrice. S'il en a, il a un changement d'image
	 * @param matrix Tableau de MatrixTiles du joueur utilisateur
	 */
	public void ifBoats(MatrixTiles matrix[][])
	{
		
		int nbSquare = 11;
	
		for(int i = 1; i < nbSquare; i++)
		{
			for(int j = 1; j <  nbSquare; j++)
			{
				if(matrix[i][j].getNumber() != 0)
				{
					this.imageViewTab[i][j].setImage(new Image("file:Images/boatPiece.png"));
				}
			}
		} 
				
	}
	/**
     * Configure le visuel de  la grille de l'ordinateur en ajoutant des imageView dans le groupe.
     * @return le group contenant les Ã©lÃ©ments graphiques de la grille
     */
	public Group setGrid(String nom)
	{
		this.myRoot = new Group();
		
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

				this.myRoot.getChildren().add(waterTiles);
			

			}
		}
		
		
		
		this.myRoot.getChildren().add(name);
		
		return this.myRoot;
		
	}
	
	/**
	 * Methode appelee par ComputerPlayer permettant de placer soit un point blanc ou un point
	 * rouge sur la grille du joueur humain selon si c'est un coup reussi ou rate 
	 * @param isHit booleen, true est un coup reussi, false est un coup rate
	 * @param x la coordonnee en X de la case a changer
	 * @param y la coordonnee en Y de la case a changer
	 */
	public void changeStateSquare(Boolean isHit, int x, int y) {
		if (!isHit) {
			this.imageViewTab[x][y].setImage(new Image("file:Images/water-white.png"));
		}
		else {
			this.imageViewTab[x][y].setImage(new Image("file:Images/water-red.png"));
		}	
	}	
}
