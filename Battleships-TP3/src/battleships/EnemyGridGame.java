package battleships;

import java.util.List;

import battleships.backend.Game;
import battleships.backend.Matrix;
import battleships.backend.MatrixTiles;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * Classe JavaFX de la grille qui contient les coordonnées des bateaux de l'ordinateur.
 * L'utilisateur doit cliquer dessus pour trouver les bateaux
 * @author Laurie
 *
 */
public class EnemyGridGame
{
        private ImageView[][] imageViewTab;
        private Game game;
        private int nbShots;
        private BattleGame battleGame;
        
        
        /**
         * Constructeur de EnemyGridGame
         * @param game
         * @param battleGame 
         */
    public EnemyGridGame(Game game, BattleGame battleGame)
    {
        this.game = game;
        this.battleGame = battleGame;
        this.nbShots = 0;
        this.imageViewTab = new ImageView[11][11];        
    }
     
    
    /**
     * Retourne le tableau d'image de la grille
     * @return
     */
    public ImageView[][] getImageTab()
    {
            return this.imageViewTab;
    }
        
    
    public int getNbShots() {
                return this.nbShots;
        }


        /**
     * Configure le visuel de  la grille de l'ordinateur en ajoutant des imageView dans le groupe.
     * @return
     */
     public Group setGrid()
     {
        Group rootEnemy = new Group();
                
        Text name = new Text(this.game.getEnemy().getPlayerName());
        name.setX(200);
        name.setY(450);
        name.setFont(new Font(20));
        int nbSquare = 11;
        
        int xSquare = 25; // coordonnees x de l'image
        int ySquare = 35; // coordonnees y de l'image
        ImageList imageList =  new ImageList();
        List<Image> imageListNumber = imageList.imageListNumbers();
        List<Image> imageListLetter = imageList.imageListLetters();
        
                                
        for(int i = 0; i < nbSquare; i++)
        {
                if( i > 0)
            {
                      xSquare += 35;
                      ySquare = 35;
            }
                
                for(int j = 0; j < nbSquare; j++)
                {
                        ImageView waterTiles = new ImageView(new Image("file:Images/water.png"));
                  
                        if(j > 0 && i > 0)
                        {
                       ySquare += 35;
                       waterTiles = new ImageView(new Image("file:Images/water.png"));
                    }
                        
                    else if(j == 0 && i > 0)
                    {
                       waterTiles = new ImageView(imageListNumber.get(i - 1));
                    }
                        
                    else if(j > 0 && i == 0)
                    {
                       ySquare += 35;
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
        
     
     
     /**
          * Classe contenant les actions lorsqu'un utilisateur clique sur la grille de l'ordinateur
          * @author Kevin
          *
          */
        private class MouseListener implements EventHandler<MouseEvent>
        {

        	@Override
            public void handle(MouseEvent arg0) 
            {
//                    if clicked && boat, if clicked != boat, not clicked, already clicked
                    Matrix gameMatrix = EnemyGridGame.this.game.getComputerMatrix();
                    
                    ImageView imageView = (ImageView) arg0.getSource();
                    
                    int quickX = EnemyGridGame.this.getSquareX(imageView);
                    int quickY = EnemyGridGame.this.getSquareY(imageView);
                    
                    if(gameMatrix.getSquareContentCheck(quickX, quickY, true) == true)
                    {
                            
                    }
                    else
                    {
                    	EnemyGridGame.this.nbShots++;
                            gameMatrix.setSquareCheck(quickX, quickY, true, true);
                            Image imageRedX = new Image("file:Images/water-red.png");
                            Image imageWhiteX = new Image("file:Images/water-white.png");
                            
                            switch(gameMatrix.getSquareContentNumber(quickX, quickY, true))
                            {
                                    case 0:
                                    {
                                            imageView.setImage(imageWhiteX);
                                            EnemyGridGame.this.game.UpHitCounter(false);
                                            
                                            break;
                                    }
                                    case 2: case 3: case 4: case 5:
                                    {
                                            imageView.setImage(imageRedX);

                                            EnemyGridGame.this.game.UpHitCounter(false);
                                            if(EnemyGridGame.this.game.DidWeWin(gameMatrix))
                                            {
                                            	EnemyGridGame.this.game.VictoryPanel();
                                            }
                                            
                                            break;
                                    }
                            }
                            
                            
                    }
                    EnemyGridGame.this.game.getEnemy().shootEnemy(EnemyGridGame.this.game.getMatrix());       
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


                public void resetImage(MatrixTiles[][] matrix)
                
                {
                        int nbSquare = 11;
                        
                        for(int i = 1; i < nbSquare; i++)
                        {
                                for(int j = 1; j <  nbSquare; j++)
                                {
                                        if(matrix[i][j].isClicked())
                                        {
                                                if(matrix[i][j].getNumber() != 0)
                                                {
                                                        this.imageViewTab[i][j].setImage(new Image("file:Images/water-red.png"));
                                                }
                                                else
                                                {
                                                        this.imageViewTab[i][j].setImage(new Image("file:Images/water-white.png"));
                                                }
                                                
                                        
                                        }
                                                
                                        
                                        else
                                        {
                                                this.imageViewTab[i][j].setImage(new Image("file:Images/water.png"));
                                        }
                                }
                        }
                }
                
        }

