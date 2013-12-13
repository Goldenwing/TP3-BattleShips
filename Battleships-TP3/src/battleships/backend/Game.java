package battleships.backend;

import java.util.List;
import java.util.Random;

import battleships.BattleGame;

import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;

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
               case 1:  boat = Boats.AIRCRAFT;
                        break;
               case 2:  boat =  Boats.BATTLESHIP;
                        break;
               case 3:  boat =  Boats.DESTROYER;
                        break;
               case 4:  boat =  Boats.SUBMARINE;
                        break;
               case 5:  boat =  Boats.PATROL;
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
        
        public Game(BattleGame interfaceGame)
        {
        	this.battleGame = interfaceGame;
            startGame();
        }
        
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
                //this.gamer.setBoats();
        }
        
        public boolean checkBoatsUser(int[][] tableXY, List<RadioButton> listRbuttonChecked)
            {
                     int []tableSizeBoats =  {5,4,3,3,2};
                    
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
                                                    
                                            
                                             Boats boatSended = boat.getBoatByNumber(j + 1);
                                             
                                             verified = this.gamer.setBoats(this.playerMatrix, x,  y, true, boatSended);
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
                                            
                                             Boats boatSended = boat.getBoatByNumber(j + 1);
                                             verified = this.gamer.setBoats(this.playerMatrix,x,  y, false, boatSended);
                                    }
                                    else
                                    {
                                            verified = false;
                                    }
                            }
                            
                            
                    }
                    
                    return verified;
            }

                public void setMatrix(Matrix matrix) 
                {
                        this.playerMatrix = matrix;
                }
        
//        public static void main(String[] args)
//        {
//                
//                Game game = new Game();
//                Matrix computerMatrix = game.getComputerMatrix();
//                for(int i = 0; i < 10; i++)
//                 {
//                 for(int j = 0; j < 10; j++)
//                 {
//                 System.out.printf("%5d ", computerMatrix.getSquareContentNumber(i, j, true));
//                 }
//                 System.out.println();
//                 }
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
        	
        	public void VictoryPanel()
        	{
        		this.battleGame.VictoryPanel();
        	}
        	
        	
        }

