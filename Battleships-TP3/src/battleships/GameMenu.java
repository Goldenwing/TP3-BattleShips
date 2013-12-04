package battleships;


import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.input.KeyCombination;

public class GameMenu 
{
	
	
	public GameMenu()
	{
		
		
		
	}
	
	public MenuBar setMenu()
	{
		
		MenuBar menuBar = new MenuBar();
		Menu menuGame = new Menu("Partie");
		Menu menuHelp = new Menu("?");
		
		MenuItem itemNewGame  = new MenuItem("Nouvelle Partie");
		itemNewGame.setAccelerator(KeyCombination.keyCombination("F2")); // raccourci
		itemNewGame.setOnAction(new menuListenerNewGame());
		
		MenuItem itemBestScores  = new MenuItem("Meilleurs Scores");
		itemBestScores.setOnAction(new menuListenerEndGame());
		
		MenuItem itemSeeBoats  = new MenuItem("Tricher");
		itemSeeBoats.setOnAction(new menuListenerSeeBoats());
		
		MenuItem itemEndGame  = new MenuItem("Quitter");
		itemEndGame.setOnAction(new menuListenerSeeScores());
		
		MenuItem itemHelp  = new MenuItem("Aide");
		itemHelp.setOnAction(new menuListenerHelp());
		
		MenuItem itemSeeMore  = new MenuItem("À propos");
		itemSeeMore.setOnAction(new menuListenerSeeMore());
		
		menuGame.getItems().addAll(itemNewGame,itemBestScores, itemSeeBoats, itemEndGame);
		menuHelp.getItems().addAll(itemHelp, itemSeeMore);
		
		menuBar.getMenus().addAll(menuGame, menuHelp);
		
		return menuBar;
		
	}
	

	
	private class menuListenerNewGame implements EventHandler<ActionEvent>
	{

		@Override
		public void handle(ActionEvent arg0)
		{
			// TODO Auto-generated method stub
			
		}
		
	}
	
	private class menuListenerEndGame implements EventHandler<ActionEvent>
	{

		@Override
		public void handle(ActionEvent arg0)
		{
			// TODO Auto-generated method stub
			
		}
		
	}
	
	private class menuListenerSeeBoats implements EventHandler<ActionEvent>
	{

		@Override
		public void handle(ActionEvent arg0)
		{
			// TODO Auto-generated method stub
			
		}
		
	}
	
	private class menuListenerSeeScores implements EventHandler<ActionEvent>
	{

		@Override
		public void handle(ActionEvent arg0)
		{
			// TODO Auto-generated method stub
			
		}
		
	}
	
	
	private class menuListenerHelp implements EventHandler<ActionEvent>
	{

		@Override
		public void handle(ActionEvent arg0)
		{
			// TODO Auto-generated method stub
			
		}
		
	}
	
	private class menuListenerSeeMore implements EventHandler<ActionEvent>
	{

		@Override
		public void handle(ActionEvent arg0)
		{
			// TODO Auto-generated method stub
			
		}
		
	}
}
