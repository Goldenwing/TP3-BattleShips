package battleships;

import java.io.File;
import java.util.List;

import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

import battleships.backend.Game;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.MenuBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class BattleGame extends Application
{
//	private List<TextField> listField;
	private Group root;
	private Stage stagePosition;
	private ModalWindow modal;
	private String name;
	private ImageView[] tabImage;
	private Game game;
	
	public void start(Stage stage)
	{
		this.root = new Group();
		Scene scene = new Scene(this.root, 1920, 1000, Color.LIGHTGRAY);
		
		this.game = new Game();
		
		GameMenu gameMenu = new GameMenu(game);
		this.root.getChildren().add(gameMenu);
		
		stage.setTitle("BattleShips  (Annie Belzile, Laurie Lavoie, Kevin Tanguay)"); 
        stage.setScene(scene); 
       
//        stage.setResizable(false);
        stage.show(); 
		this.askPositionBoats();

	
	}
	
	public void askPositionBoats()
	{
		
		GridPane gridPosition = new GridPane();
		this.modal = new ModalWindow();
		gridPosition = modal.setItemModal();
		this.stagePosition = new Stage();
	
		Button okBtn = new Button("Ok");
		okBtn.setOnAction(new ButtonListener());
		gridPosition.add(okBtn, 1, 7);
		this.stagePosition.initModality(Modality.WINDOW_MODAL);
        Scene scenePosition = new Scene(gridPosition, Color.WHITE);
        this.stagePosition.setTitle("Nouvelle Partie");
        this.stagePosition.setScene(scenePosition);
        this.stagePosition.show();
	}
	
	public void setGrids(Game game)
	{	
		this.setPieces();
		MyGameGrid myGrid = new MyGameGrid();
		
		EnemyGridGame enemyGrid = new EnemyGridGame(game);
		this.root.getChildren().add(enemyGrid.setGrid());
		this.root.getChildren().add(myGrid.setGrid(this.name));
	}
	
	public void setPieces()
	{
		ImageView whitePieces = new ImageView(new Image("file:Images/white.png"));
		whitePieces.setX(1675);
		whitePieces.setY(250);
		
		this.root.getChildren().add(whitePieces);
		ImageView redPieces = new ImageView(new Image("file:Images/red.png"));
		redPieces.setX(1675);
		redPieces.setY(500);
		this.root.getChildren().add(redPieces);
		
	}
	
	public void setMenu()
	{
		
	}
	public static void main(String[] args)
	{
//		JUnitCore junit = new JUnitCore(); 
//		Result resultGame = junit.run(GameTest.class);
//		runTests(resultGame);
//		Result resultMatrix = junit.run(MatrixTest.class);
//		runTests(resultMatrix);
//		
//		if (runTests(resultGame) && runTests(resultMatrix))
//		{
//			String s = new File("").getAbsolutePath();
//			System.out.println(s);
			Application.launch(BattleGame.class, args);
//		}   
	}
	
	public static boolean runTests(Result result)
	{			
		if (result.getFailureCount() == 0)
		{
			String s = new File("").getAbsolutePath();
			System.out.println(s);
			return true;
		}
		else
		{
			List<Failure> listeEchecs = result.getFailures();
			System.out.println("Voici les tests qui échouent: ");
			
			for (Failure f: listeEchecs)
			{
				System.out.println(f.toString());
			}		    
			return false;
		}     	
	}

	private class ButtonListener implements EventHandler<ActionEvent>
	{
		
		int [][] tableXY  = new int[2][5];
		
		@Override
		public void handle(ActionEvent arg0)
		{
			boolean verified = true;
			int compteur = 1;
			
			BattleGame.this.modal.getErrorText().setVisible(false);
			if(BattleGame.this.modal.getListField().get(0).getText().isEmpty())
			{
				BattleGame.this.modal.getErrorText().setVisible(true);
				verified = false;
			}
			else
			{
					
			BattleGame.this.name = BattleGame.this.modal.getListField().get(0).getText();
		
			
			for(int j = 0; j < 5; j++)
			{
				for(int i = 0; i < 2; i++)
				{
					int textLenght = BattleGame.this.modal.getListField().get(compteur).getText().length();
					if(textLenght == 1 )
					{
						char checkIfnumber = BattleGame.this.modal.getListField().get(compteur).getText().charAt(0);
						if(checkIfnumber > 47 && checkIfnumber < 58)
						{
							this.tableXY[i][j] = Integer.parseInt(BattleGame.this.modal.getListField().get(compteur).getText());
							compteur++;
							
				;
						}
						else
						{
							BattleGame.this.modal.getErrorText().setVisible(true);
							verified = false;
						}
					}
					else
					{
						BattleGame.this.modal.getErrorText().setVisible(true);
						verified = false;
					}
				}
			}
			
			}
			
			if(verified == true)
			{
				 BattleGame.this.stagePosition.close();
				 BattleGame.this.modal.getErrorText().setVisible(false);
				
				 BattleGame.this.setGrids(BattleGame.this.game);
					
			}
			
			
		}
	
	}
}
