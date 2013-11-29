package battleships;

import java.io.File;
import java.util.List;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

import battleships.backend.tests.GameTest;
import battleships.backend.tests.MatrixTest;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.MenuBar;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class BattleGame extends Application
{
	
	private Group root;
	
	
	public void start(Stage stage)
	{
		this.root = new Group();
		Scene scene = new Scene(this.root, 1920, 1000, Color.LIGHTGRAY);
		this.setGrids();
		this.setPieces();
		this.setMenu();
		stage.setTitle("Jeu de simulation de combat en territoire navale.  (Annie Belzile, Laurie Lavoie, Kevin Tanguay)"); 
        stage.setScene(scene); 
       
//        stage.setResizable(false);
        stage.show(); 
		this.askPositionBoats();

	
	}
	
	public void askPositionBoats()
	{
		Stage stagePosition = new Stage();
		Group groupPosition = new Group();
		
//		AIRCRAFT(5), Aircraft carrier  , porte-avions
//		BATTLESHIP(4), cruiser, croiseur
//		DESTROYER(3), , destroyer, contre-torpilleur
//		SUBMARINE(3),
//		PATROL(2);
		
		
		Text nameText = new Text("Veuillez entrer votre nom");
		TextField nameField = new TextField(); 
		
		Text cruiserText = new Text("Veuillez entrer les coordonnées de votre croiseur (4)");
		TextField xcruiserField = new TextField(); 
		TextField ycruiserField = new TextField(); 
		
		Text aircraftText = new Text("Veuillez entrer les coordonnées de votre porte-avions (5)");
		TextField xAircraftField = new TextField(); 
		TextField yAircraftField = new TextField(); 
		
		Text destroyerText = new Text("Veuillez entrer les coordonnées de votre contre-torpilleur (3)");
		TextField xDestroyerField = new TextField(); 
		TextField yDestroyerField = new TextField(); 
		
		Text submarineText = new Text("Veuillez entrer les coordonnées de votre sous-marin (3)");
		TextField xSubmarineField = new TextField(); 
		TextField ySubmarineField = new TextField(); 
		
		
		Text patrolText = new Text("Veuillez entrer les coordonnées de votre patrouilleur (2)");
		TextField xPatrolField = new TextField(); 
		TextField yPatrolField = new TextField(); 
		
		
		
		Button okBtn = new Button("Ok");
//		okBtn.setOnAction(new ButtonListenerBoard(9));
		
		
		stagePosition.initModality(Modality.WINDOW_MODAL);
        Scene scenePosition = new Scene(groupPosition, 400, 400, Color.WHITE);
        stagePosition.setTitle("Nouvelle Partie");
        stagePosition.setScene(scenePosition);
        stagePosition.show();
	}
	
	public void setGrids()
	{
		MyGameGrid myGrid = new MyGameGrid();
		
		EnemyGridGame enemyGrid = new EnemyGridGame();
		this.root.getChildren().add(enemyGrid.setGrid());
		this.root.getChildren().add(myGrid.setGrid("Joueur"));
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
		GameMenu gameMenu = new GameMenu();
		MenuBar menuBar = new MenuBar();
		menuBar = gameMenu.setMenu();
		this.root.getChildren().add(menuBar);
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
}
