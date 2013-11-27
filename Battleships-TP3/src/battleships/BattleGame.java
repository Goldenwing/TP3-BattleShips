package battleships;

import java.io.File;
import java.util.List;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

import battleships.backend.tests.GameTest;
import battleships.backend.tests.MatrixTest;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class BattleGame extends Application
{
	public void start(Stage stage)
	{
		Group root = new Group();
		Scene scene = new Scene(root, 600, 600, Color.LIGHTGRAY);
		
		
		stage.setTitle("Jeu de simulation de combat en territoire navale.  (Annie Belzile, Laurie Lavoie, Kevin Tanguay)"); 
        stage.setScene(scene); 
        stage.setResizable(false);
        stage.show(); ;
	    	stage.show();
	}
	
	public static void main(String[] args)
	{
		JUnitCore junit = new JUnitCore(); 
		Result resultGame = junit.run(GameTest.class);
		runTests(resultGame);
		Result resultMatrix = junit.run(MatrixTest.class);
		runTests(resultMatrix);
		
		if (runTests(resultGame) && runTests(resultMatrix))
		{
			String s = new File("").getAbsolutePath();
			System.out.println(s);
			Application.launch(BattleGame.class, args);
		}   
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
