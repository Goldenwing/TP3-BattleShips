package battleships;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

import battleships.backend.Game;
import battleships.backend.Matrix;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.MenuBar;
import javafx.scene.control.RadioButton;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * Classe JavaFx principal du jeu qui contient la méthode start, les objets graphiques de l'application et une partie.
 * Cette classe lance les tests au démarrage de l'application
 * 
 * @author Laurie
 *
 */
public class BattleGame extends Application
{
//	private List<TextField> listField;
	private Group root;
	private Stage stagePosition;
	private Scene scene;
	private ModalWindow modal;
	private String name;
	private ImageView[] tabImage;
	private Game game;
	int [][] tableXY;
	private List<RadioButton> listRbuttonChecked;
	private EnemyGridGame enemyGrid;
	
	/**
	 * Configuration du stage lors du lancement de l'application
	 */
	public void start(Stage stage)
	{
		this.root = new Group();
		this.scene = new Scene(this.root, 1920, 1000, Color.LIGHTGRAY);
		
		this.game = new Game();
//		
		GameMenu gameMenu = new GameMenu(game, this);
		this.root.getChildren().add(gameMenu);
		stage.setTitle("Jeu de simulation de combat en territoire navale.  (Annie Belzile, Laurie Lavoie, Kevin Tanguay)"); 
        stage.setScene(scene); 
       
//        stage.setResizable(false);
        stage.show(); 
		this.askPositionBoats();

	
	}
	
	/**
	 * Affiche une fenêtre modale qui demande le nom et les coordonnées des bateaux
	 */
	public void askPositionBoats()
	{
		
		GridPane gridPosition = new GridPane();
		this.modal = new ModalWindow();
		gridPosition = modal.setItemModal();
		this.stagePosition = new Stage();
		this.tableXY  = new int[2][5];
		Button okBtn = new Button("Ok");
		okBtn.setOnAction(new ButtonListener());
		gridPosition.add(okBtn, 1, 7);
		this.stagePosition.initModality(Modality.APPLICATION_MODAL);
        Scene scenePosition = new Scene(gridPosition, Color.WHITE);
        this.stagePosition.setTitle("Nouvelle Partie");
        this.stagePosition.setScene(scenePosition);
        this.stagePosition.show();
	}

	/**
	 * Crée un objet MyGameGrid et un objet EnemyGridGame qui sont les grilles graphiques
	 * des matrices des joueurs.
	 * 
	 * @param game2 partie du jeu courant
	 */
	public void setGrids(Game game2)
	{	
		this.setPieces();
		MyGameGrid myGrid = new MyGameGrid();
		Group myGroup = myGrid.setGrid(this.name);
		myGrid.ifBoats(this.game.getMatrix().getGameMatrix());
		this.enemyGrid = new EnemyGridGame(this.game);
		this.root.getChildren().add(this.enemyGrid.setGrid());
		this.root.getChildren().add(myGroup);
	}
	
	
	public void seeBoatsEnemyGrid()
	{
		this.enemyGrid.ifBoats(this.game.getComputerMatrix().getComputerGameMatrix());
	}

	/**
	 * Place deux images à droite dans l'interface
	 */
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
	

	
	/**
	 * Appelle une fonction de Game qui vérifie si les données entrées par l'utilisateur sont correctes.
	 * @return verified vrai, si les données de l'utilisateurs sont correctes ou sinon faux
	 */
	public boolean verifiedSettingBoats()
	{
//		boolean verified = this.game.checkBoatsUser(this.tableXY, this.listRbuttonChecked);
		boolean verified = this.game.checkBoatsUser(this.tableXY, this.listRbuttonChecked);
		return verified;
	}
	
	
	/**
	 * Le main de l'application
	 * Lance les tests lors du démarrage
	 * @param args
	 */
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
	
	/**
	 * Lance les test JUnit des classes du backend
	 * @param result
	 * @return
	 */
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

	/**
	 * Classe contenant les actions du bouton ok de la fenêtre modal où l'utilisateur entre ses donneés
	 * @author Laurie
	 *
	 */
	private class ButtonListener implements EventHandler<ActionEvent>
	{
		

		
		@Override
		public void handle(ActionEvent arg0)
		{
			boolean verified = true;
			int compteur = 1;
			BattleGame.this.listRbuttonChecked = new ArrayList<RadioButton>();
			
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
					if(textLenght == 1 || textLenght == 2)
					{
						char checkIfnumber = BattleGame.this.modal.getListField().get(compteur).getText().charAt(0);
						boolean checkSecondChar = true;
						if(textLenght == 2)
						{
							char checkIfSecondNumber = BattleGame.this.modal.getListField().get(compteur).getText().charAt(1);
							if(checkIfSecondNumber == 48)
							{
								checkSecondChar = true;
							}
							else
							{
								checkSecondChar = false;
							}
						}
						
						if(checkIfnumber > 47 && checkIfnumber < 58 && checkSecondChar)
						{
							
							BattleGame.this.tableXY[i][j] = Integer.parseInt(BattleGame.this.modal.getListField().get(compteur).getText());
							boolean checked = BattleGame.this.modal.getListRadioButton().get(compteur - 1).isSelected();
							if(checked == true)
							{
								BattleGame.this.listRbuttonChecked.add(BattleGame.this.modal.getListRadioButton().get(compteur - 1));
							}
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
			
			if(!BattleGame.this.verifiedSettingBoats())
			{
				
				BattleGame.this.modal.getErrorText().setVisible(true);
				if(BattleGame.this.name != null)
				{
					BattleGame.this.game.setMatrix(new Matrix()) ;
				}
				
				verified = false;
				
			}
			
			
			if(verified == true)
			{
				
				
				 BattleGame.this.stagePosition.close();
				 BattleGame.this.modal.getErrorText().setVisible(false);
				 
				 BattleGame.this.setGrids(BattleGame.this.game);
					
			}
			
			
		}
	
	}

	public void resetEnemyGrid()
	{ 
		this.enemyGrid.resetImage(this.game.getComputerMatrix().getComputerGameMatrix());
		
	}
}
