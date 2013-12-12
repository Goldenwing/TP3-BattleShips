package battleships;


import battleships.backend.Game;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
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
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * Menu de l'application permettant de crÃ©er une nouvelle partie, voir ses meilleurs scores, de tricher et de quitter l'application.
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
		Stage stage;
		@Override
		public void handle(ActionEvent arg0)
		{
			Group confirmNewGame = new Group();
			Stage confirmNewGameDialog = new Stage();
			confirmNewGameDialog.initModality(Modality.APPLICATION_MODAL);
			Scene corfirmNewGameScene = new Scene(confirmNewGame, 350,100, Color.LIGHTGRAY);
			
			Button buttonCancel = new Button("Annuler");
				buttonCancel.setLayoutX(185);
				buttonCancel.setLayoutY(75);
			Button buttonOk = new Button("Ok");
				buttonOk.setLayoutX(125);
				buttonOk.setLayoutY(75);
			//ButtonOKListener okListener = new ButtonOKListener();
			//buttonAccept.setOnMouseClicked(okListener);
				
			confirmNewGame.getChildren().add(buttonCancel);
			confirmNewGame.getChildren().add(buttonOk);
			
			
			confirmNewGameDialog.setTitle("Confirmation nouvelle partie");
			confirmNewGameDialog.setScene(corfirmNewGameScene);
			this.stage = confirmNewGameDialog;
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
		Stage stage;
		@Override
		public void handle(ActionEvent arg0)
		{
			Group scoreRoot = new Group();
			Stage scoreDialog = new Stage();
			scoreDialog.initModality(Modality.APPLICATION_MODAL);
			Scene scoreScene = new Scene(scoreRoot, 200,100, Color.LIGHTGRAY);
			
			Button buttonErase = new Button("Effacer les scores");
				buttonErase.setLayoutX(25);
				buttonErase.setLayoutY(75);
			Button buttonAccept = new Button("Ok");
				buttonAccept.setLayoutX(150);
				buttonAccept.setLayoutY(75);
				ButtonOKListener okListener = new ButtonOKListener();
				buttonAccept.setOnMouseClicked(okListener);
				
				scoreRoot.getChildren().add(buttonErase);
				scoreRoot.getChildren().add(buttonAccept);
			
			scoreDialog.setTitle("Les demineurs les plus rapides");
			scoreDialog.setScene(scoreScene);
			this.stage = scoreDialog;
			scoreDialog.setResizable(false);
	        scoreDialog.show(); ;
		    	scoreDialog.show();
			
		}
		
		
		
		private class ButtonOKListener implements EventHandler<MouseEvent>
		{
			@Override
			public void handle(MouseEvent arg0)
			{
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
			
			Label infoLabel = new Label("Cette application a  ete realisee par \n Annie Belzile, Laurie Lavoie et Kevin Tanguay \n dans le cadre du cours de Programmation III!");
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
}
