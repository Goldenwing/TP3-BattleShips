package battleships;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.StringReader;

import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.xml.sax.InputSource;

import battleships.backend.Game;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu; 
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.MouseEvent;

import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;

import javafx.stage.Modality;
import javafx.stage.Stage;



/**
 * Menu de l'application permettant de crÃƒÂ©er une nouvelle partie, voir ses meilleurs scores, de tricher et de quitter l'application.
 * @author Laurie
 *
 */
public class GameMenu extends Parent
{
	
	private BattleGame gameBattle;
	private Game game;
	private boolean seeBoats;
	
	
	/**
	 * CrÃ©ation d'un menu pour l'application
	 * @param game
	 */
	public GameMenu(Game game, BattleGame battleGame)
	{
		this.seeBoats = false;
		this.gameBattle = battleGame;
		this.game = game;
		MenuBar menuBar = new MenuBar();
		Menu menuGame = new Menu("Partie");
		Menu menuHelp = new Menu("?");
		
		MenuItem itemNewGame  = new MenuItem("Nouvelle Partie");
		itemNewGame.setAccelerator(KeyCombination.keyCombination("F2")); // raccourci
		itemNewGame.setOnAction(new menuListenerNewGame());
		
		MenuItem itemBestScores  = new MenuItem("Meilleurs Scores");
		itemBestScores.setOnAction(new menuListenerSeeScores());
		
		MenuItem itemSeeBoats  = new MenuItem("Tricher");
		itemSeeBoats.setOnAction(new menuListenerSeeBoats());
		
		MenuItem itemEndGame  = new MenuItem("Quitter");
		itemEndGame.setOnAction(new menuListenerEndGame());
		
		MenuItem itemHelp  = new MenuItem("Aide");
		itemHelp.setOnAction(new menuListenerHelp());
		
		MenuItem itemSeeMore  = new MenuItem("A propos");
		itemSeeMore.setOnAction(new menuListenerSeeMore());
		
		menuGame.getItems().addAll(itemNewGame,itemBestScores, itemSeeBoats, itemEndGame);
		menuHelp.getItems().addAll(itemHelp, itemSeeMore);
		
		menuBar.getMenus().addAll(menuGame, menuHelp);
		
		super.getChildren().add(menuBar);
	}
	

	/**
	 * Action lors du clique de l'utilisateur sur nouvelle partie
	 * @author Laurie
	 *
	 */
	private class menuListenerNewGame implements EventHandler<ActionEvent>
	{
		
		@Override
		public void handle(ActionEvent arg0)
		{
			Group confirmNewGame = new Group();
			Label lblConfirmQuestion = new Label("Etes-vous sur de vouloir commencer une nouvelle partie?");
			Stage confirmNewGameDialog = new Stage();
			confirmNewGameDialog.initModality(Modality.APPLICATION_MODAL);
			Scene corfirmNewGameScene = new Scene(confirmNewGame, 350,100, Color.LIGHTGRAY);
			lblConfirmQuestion.setLayoutX(25);
			lblConfirmQuestion.setLayoutY(25);
			
			Button buttonNo = new Button("Non");
				buttonNo.setLayoutX(185);
				buttonNo.setLayoutY(75);
			Button buttonYes = new Button("Oui");
				buttonYes.setLayoutX(125);
				buttonYes.setLayoutY(75);

			buttonYes.setOnAction(new ButtonYesListener());
			buttonNo.setOnAction(new ButtonNoListener());
		
			confirmNewGame.getChildren().add(lblConfirmQuestion);
			confirmNewGame.getChildren().add(buttonNo);
			confirmNewGame.getChildren().add(buttonYes);
			
			
			confirmNewGameDialog.setTitle("Confirmation nouvelle partie");
			confirmNewGameDialog.setScene(corfirmNewGameScene);
			//this.stage = confirmNewGameDialog;
			confirmNewGameDialog.setResizable(false);
			confirmNewGameDialog.show();
		}
		
	}
	/**
	 * Action lors du clique de l'utilisateur sur quitter
	 * @author Laurie
	 *
	 */
	private class menuListenerEndGame implements EventHandler<ActionEvent>
	{

		@Override
		public void handle(ActionEvent arg0)
		{
			Platform.exit();
		}
		
	}
	
	/**
	 * Action lors du clique de l'utilisateur sur Tricher
	 * @author Laurie
	 *
	 */
	
	private class menuListenerSeeBoats implements EventHandler<ActionEvent>
	{

		@Override
		public void handle(ActionEvent arg0)
		{
			
			if(!GameMenu.this.seeBoats)
			{
				GameMenu.this.gameBattle.seeBoatsEnemyGrid();
				GameMenu.this.seeBoats = true;
			}
			else
			{
				GameMenu.this.gameBattle.resetEnemyGrid();
				GameMenu.this.seeBoats = false;
			}
		
		
		}
		
	}
	
	/**
	 * Action lors du clique de l'utilisateur sur meilleur score
	 * @author Laurie
	 *
	 */
	private class menuListenerSeeScores implements EventHandler<ActionEvent>
	{
		
		private GridPane scoreRoot;
		Stage stage;
		@Override
		public void handle(ActionEvent arg0)
		{
			scoreRoot = new GridPane();
			 scoreRoot.setAlignment(Pos.CENTER);
		        scoreRoot.setHgap(10);
		        scoreRoot.setVgap(10);
		        scoreRoot.setPadding(new Insets(25, 25, 25, 25));
			Stage scoreDialog = new Stage();
			scoreDialog.initModality(Modality.APPLICATION_MODAL);
			Scene scoreScene = new Scene(this.scoreRoot, 200,100, Color.LIGHTGRAY);
			int line = takeScoresInFile();
			Button buttonErase = new Button("Effacer les scores");
			buttonErase.setOnAction(new ButtonEraseListener());
			buttonErase.setLayoutX(25);
			buttonErase.setLayoutY(75);
			Button buttonAccept = new Button("Ok");
				buttonAccept.setLayoutX(150);
				buttonAccept.setLayoutY(75);
				ButtonOKListener okListener = new ButtonOKListener();
				buttonAccept.setOnMouseClicked(okListener);
				
				scoreRoot.add(buttonErase, 0, line);
				scoreRoot.add(buttonAccept, 1, line);
			
			scoreDialog.setTitle("Les demineurs les plus rapides");
			scoreDialog.setScene(scoreScene);
			this.stage = scoreDialog;
			scoreDialog.setResizable(false);
	        scoreDialog.show(); ;
		    scoreDialog.show();
			
		}
		
		private int takeScoresInFile()
		{
			 
	    	 String name = null;
	         String score = "";
	         String lineScore = "";
	         int nbLined = 0;
	         
	         Document doc = null;
	      
	         
	         try
	         {    
	            BufferedReader buff = new BufferedReader(new FileReader("bestScores.txt"));
	                
	            try
	            {
	            	
	            	lineScore = buff.readLine();
	            	while (lineScore != null)
	            	{	
	            		
	            		
	            		   XPathFactory xpathFactory = XPathFactory.newInstance();
	            	         XPath xpath = xpathFactory.newXPath();
	            		
	            		InputSource source = new InputSource(new StringReader(lineScore));
	            		
	            		
	            		 try
	                     {
	                             doc = (Document) xpath.evaluate("/", source, XPathConstants.NODE);
	                     }
	                     catch (XPathExpressionException e)
	                     {        
	                             e.printStackTrace();
	                     }
	                     
////	                    
	                    try
	                     {
	                         score = xpath.evaluate("/game/nbShots", doc);
	                         
	                                
	                     }
	                    catch (XPathExpressionException e)
	                    {
	                    		 e.printStackTrace();
	                    }
	                    
	                    try
	                     {
	                         name = xpath.evaluate("/game/name", doc);
	                         
	                                
	                     }
	                    catch (XPathExpressionException e)
	                    {
	                    		 e.printStackTrace();
	                    }
	                    
	                    System.out.println(name);
	                    System.out.println(score);
	                    
	                    
	                    Label textName = new Label(name);
	                    Label textScore = new Label(score);
	                    
	                    
	                    
	                    this.scoreRoot.add(textName, 0, nbLined);
	                    this.scoreRoot.add(textScore, 1, nbLined);
	                    
	           			nbLined++;
	                    lineScore = buff.readLine();
//	                    
	            	}
	            	
	            	
	            	buff.close();
	            } 
	            finally
	            {
	            	
	            }
	                
	         }
	         catch (IOException ioe)
	         {
	        	
	        	
	         }
	         
	         return nbLined;
	           
		}
		
		private class ButtonOKListener implements EventHandler<MouseEvent>
		{
			@Override
			public void handle(MouseEvent arg0)
			{
				menuListenerSeeScores.this.stage.close();
			}
		}
		
		private class ButtonEraseListener implements  EventHandler<ActionEvent>
		{
			@Override
			public void handle(ActionEvent arg0)
			{
				
				File file = new File("bestScores.txt");
				if(file.isFile())
				{
					file.delete();
				}
				
				menuListenerSeeScores.this.stage.close();
			}
		}
		
	}
	
	/**
	 * Action lors du clique de l'utilisateur sur Aide
	 * @author Laurie
	 *
	 */
	
	private class menuListenerHelp implements EventHandler<ActionEvent>
	{

		@Override
		public void handle(ActionEvent arg0)
		{
			Group proposRoot = new Group();
			Stage proposDialog = new Stage();
			proposDialog.initModality(Modality.APPLICATION_MODAL);
			Scene scoreScene = new Scene(proposRoot, 300,200, Color.LIGHTGRAY);
			
			Image image = new Image("file:Images/battleship.gif");
			ImageView imageView = new ImageView();
				imageView.setImage(image);
				imageView.setLayoutX(50);
				imageView.setLayoutY(60);
			proposRoot.getChildren().add(imageView);

			Label infoLabel = new Label("Cette application a  ete realisee par \n Annie Belzile, Laurie Lavoie et Kevin Tanguay \n dans le cadre du cours de Programmation III!");

				infoLabel.setLayoutY(5);
			proposRoot.getChildren().add(infoLabel);
			
			proposDialog.setTitle("A propos...");
			proposDialog.setScene(scoreScene);
			proposDialog.setResizable(false);
	        proposDialog.show(); ;
		    	proposDialog.show();
			
		}
		
	}
	
	/**
	 * Action lors du clique de l'utilisateur sur a propos
	 * @author Laurie
	 *
	 */
	private class menuListenerSeeMore implements EventHandler<ActionEvent>
	{

		@Override
		public void handle(ActionEvent arg0)
		{
			Group scoreRoot = new Group();
			Stage scoreDialog = new Stage();
			scoreDialog.initModality(Modality.APPLICATION_MODAL);
			Scene scoreScene = new Scene(scoreRoot, 300,200, Color.LIGHTGRAY);
			
			Label infoLabel = new Label("");
				infoLabel.setLayoutX(5);
				infoLabel.setLayoutY(10);
			
			scoreRoot.getChildren().add(infoLabel);
			
			scoreDialog.setTitle("Comment jouer?");
			scoreDialog.setScene(scoreScene);
			scoreDialog.setResizable(false);
			scoreDialog.show(); ;
				scoreDialog.show();
			
		}
		
	}
	
	private class ButtonYesListener implements EventHandler<ActionEvent> {

		@Override
		public void handle(ActionEvent arg0) {
			Node source = (Node) arg0.getSource();
		    Stage stage  = (Stage) source.getScene().getWindow();
		    stage.close();
			
		    //Reset
		    gameBattle.resetBattleGame();
		    GameMenu.this.game = new Game();
		    gameBattle.askPositionBoats();
		}
		
	}
	
	private class ButtonNoListener implements EventHandler<ActionEvent> {

		@Override
		public void handle(ActionEvent arg0) {
			Node source = (Node) arg0.getSource();
		    Stage stage  = (Stage) source.getScene().getWindow();
		    
		    stage.close();
		}
		
	}
}
