/**
* La classe qui contient toute la logique derrière le jeu de BattleShips.
* Battleships est un jeu qui se joue 1-à-1, soit contre un autre joueur, ou un joueur ordinateur.
* Chaque joueur place ses cinq bateaux de tailles différentes dans un cadre de 10x10 et le but du jeu c'est de
* réussir à deviner l'emplacement des cinq bateaux de l'ennemi avant qu'il découvre les notres. Lorsque nous découvrons
* chaque pièce des cinq bateaux (donc, quand nous les coulons), nous gagnons la bataille. Le jeu se fait chacun son tour, en devinant
* une case par tour. Chaque case à sa propre numérotation (une lettre suivit d'un chiffre).
*
* @author Kevin Tanguay
*/
package battleships.backend;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Random;

import battleships.BattleGame;
import javafx.scene.control.RadioButton;


public class Game
{

        public enum Boats
    {
            AIRCRAFT(5, "Porte-avions"),
            BATTLESHIP(4, "Croiseur"),
            DESTROYER(3, "Torpilleur"),
            SUBMARINE(3, "Sous-marin"),
            PATROL(2, "Patrouilleur");
            
            private int size = 0;
            private String nameBoat = "";
            
            private Boats(int size, String name)
            {
                    this.size = size;
                    this.nameBoat = name;
            }
            
            public int getSize()
            {
                    return this.size;
            }
            
            public String getBoatName()
            {
                    return this.nameBoat;
            }
            
           public Boats getBoatByNumber(int number)
           {
                   
                   Boats boat = null;
                   switch (number)
                   {
               case 1: boat = Boats.AIRCRAFT;
                        break;
               case 2: boat = Boats.BATTLESHIP;
                        break;
               case 3: boat = Boats.DESTROYER;
                        break;
               case 4: boat = Boats.SUBMARINE;
                        break;
               case 5: boat = Boats.PATROL;
                        break;

                   }
                   
                   return boat;
           }
    }

        private final int GAME_SIZE = 10;
        private Matrix playerMatrix;
        private Matrix computerMatrix;
        private Random PCBoatPlacer = new Random();
        private ComputerPlayer enemy;
        private HumanPlayer gamer;
        private BattleGame battleGame;
        
        public Game()
        {
            startGame();
        }
        
        /**
* La première fois que le jeu est parti, le BattleGame est passé en paramètres.
* @param interfaceGame                l'instance de l'application visuelle.
*/
        public Game(BattleGame interfaceGame)
        {
                this.battleGame = interfaceGame;
            startGame();
        }
        
        /**
* La méthode qui initialise tout les objets nécéssaires pour le début d'un jeu.
* Cette méthode créée les matrices, ainsi que les joueurs, et place les bateaux ennemis.
*/
        public void startGame()
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
        
        /**
* Les deux prochaines méthodes retournes les deux matrices présentement en jeu.
* Soit celui du joueur, ou de l'ordinateur.
* @return        la matrice joueure.
*/
        public Matrix getMatrix()
        {
                return this.playerMatrix;
        }
        
        
        public void setMatrix(Matrix matrix)
        {
                this.playerMatrix = matrix;
        }
        
                /**
                 * Retourne la matrice de l'ordinateur.
                 * @return        l'instance de la matrice ennemie.
                 */
        public Matrix getComputerMatrix()
        {
                return this.computerMatrix;
        }
        
        /**
* Retourne l'instance de l'ennemie.
* @return        l'instance de l'ennemie.
*/
        public ComputerPlayer getEnemy()
        {
                return enemy;
        }

        /**
* Initialise l'instance de l'ennemie.
* @param enemy        la nouvelle instance de l'ennemie.
*/
        public void setEnemy(ComputerPlayer enemy)
        {
                this.enemy = enemy;
        }

        /**
* retourne l'instance du joueur.
* @return        l'instance du joueur.
*/
        public HumanPlayer getGamer()
        {
                return gamer;
        }

        /**
* Initialise une nouvelle instance du joueur.
* @param gamer        la nouvelle instance du joueur.
*/
        public void setGamer(HumanPlayer gamer)
        {
                this.gamer = gamer;
        }
        
        /**
* Vérifie que le bateau désiré à l'endroit voulu convient à l'espacement disponible.
* Si le bateau est trop gros pour l'espace désiré, le bateau ne sera pas placé.
*
* @param tableXY                                Un tableau contenant les choix de l'utilisateur pour les placements sur la grille.
* @param listRbuttonChecked        Une liste contenant les choix de l'utilsateur pour les placements verticaux/horizontaux.
* @return                                                Vrai si possible, faux si impossible.
*/
        public boolean checkBoatsUser(int[][] tableXY, List<RadioButton> listRbuttonChecked)
            {
                     int []tableSizeBoats = {5,4,3,3,2};
                    
                     int x;
                     int y;
                     String textRb;
                     boolean verified = true;
                     Boats boat = Boats.AIRCRAFT;
                    for(int j = 0; j < 5; j++)
                    {
                            
                            x = tableXY[0][j];
                            y = tableXY[1][j];
                            textRb = listRbuttonChecked.get(j).getText();
            
                            if(textRb.equals("Horizontal"))
                            {
                                    if ((x >= 1) && ((x + tableSizeBoats[j]) <= 11) && (y >= 1) && (y <= 10) && (verified == true))
                                    {
                                                    
                                            
                                             Boats boatSent = boat.getBoatByNumber(j + 1);
                                             
                                             verified = this.gamer.setBoats(this.playerMatrix, x, y, true, boatSent);
                                    }
                                    else
                                    {
                                            verified = false;
                                    }
                            }
                            else if (textRb == "Vertical")
                            {
                                    if ((y >= 1) && ((y + tableSizeBoats[j]) <= 11) && (x >= 1) && (x <= 10) && (verified == true))
                                    {
                                            
                                             Boats boatSent = boat.getBoatByNumber(j + 1);
                                             verified = this.gamer.setBoats(this.playerMatrix,x, y, false, boatSent);
                                    }
                                    else
                                    {
                                            verified = false;
                                    }
                            }
                            
                            
                    }
                    
                    return verified;
            }

        /**
* Méthode qui vérifie si tous les bateaux ennemis on été coulés, ce qui veux dire qu'on remporte la partie.
*
* @param matrix l'instance de la matrice logique.
* @return        vrai si la victoire est remportée! Faux si pas encore.
*/
        public boolean DidWeWin(Matrix matrix)
        {
                int redCounter = 0;
                boolean victory = false;
                                
                for(int i = 1; i<11; i++)
                {
                        for(int j = 1; j<11; j++)
                        {
                                if(matrix.getSquareContentCheck(i, j, true) == true && matrix.getSquareContentNumber(i, j, true) != 0)
                                {
                                        redCounter++;
                                }
                        }
                }
                                
                if(redCounter == 17)
                {
                        victory = true;
                }
                                
                return victory;
       }
                
        /**
* Appelle la méthode qui affiche la fenètre modale pour nous féliciter.
*/
       public void VictoryPanel()
       {
             this.battleGame.VictoryPanel();
       }
                
                
     
       
      public void checkIfFile()
      {
    		 File file = new File("bestScores.txt");
    		 int nbLine = 0;
        	 if (!file.exists())
        	 {
        		 try
                 {
                     BufferedWriter writer = new BufferedWriter(new FileWriter(new File("bestScores.txt")));
                     writer.write("<game><name>" + this.battleGame.getName() + "</name><nbShots> " + this.battleGame.getEnemyGrid().getNbShots() + "</nbShots></game> \r\n");
                     writer.close();
       
                 }
                
                 catch (IOException e)
                 {
                	 e.printStackTrace();
                 }
        	 }
        	 else
        	 {
        		  try
                  {    
                     BufferedReader buff = new BufferedReader(new FileReader("bestScores.txt"));
                         
                     try
                     {
                     	String line;
                 
                     	while ((line = buff.readLine()) != null)
                     	{
                             nbLine++;
                     	}
                     } 
                     finally
                     {
                     	buff.close();
                     }
                         
                  }
                  catch (IOException ioe)
                  {
                 	 System.out.println("Erreur --" + ioe.toString());
                 	
                  }
        			 writeInFile(nbLine);
        			 
        	 }
        	 
        
        	 
      }

      
    private boolean readBestScores()
    {
    	
    	
    	
    	return true;
    }
      
	private void writeInFile(int nbLine)
	{
		if(nbLine <= 3)
		{
			if(readBestScores())
			{
				BufferedWriter bfw;
				BufferedReader bfr;
				try 
				{
					bfw = new BufferedWriter(new FileWriter("bestScores.txt", true));
					bfr = new BufferedReader(new FileReader("bestScores.txt"));
					bfr.readLine();
					bfr.close();
					bfw.write("<game><name>" + this.battleGame.getName() + "</name><nbShots> " + this.battleGame.getEnemyGrid().getNbShots() + "</nbShots></game>\r\n");
					bfw.flush();
					bfw.close();
				}
				catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				}
			
			}
			else
			{
				
			}
		}
//		else if(nbLine == 3)
//		{
//			while(!readBestScores())
//			{
//				
//			}
//		}
		
	}
       
        	
     }

