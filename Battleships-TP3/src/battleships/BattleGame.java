package battleships;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

import battleships.backend.Game;
import battleships.backend.Matrix;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * Classe JavaFx principal du jeu qui contient la méthode start, les objets graphiques de l'application et une partie.
 * Cette classe lance les tests au démarrage de l'application.
 * 
 * @author Laurie Lavoie
 *
 */
public class BattleGame extends Application
{
//        private List<TextField> listField;
        private Group root;
        private Stage stagePosition;
        private Stage modalStage;
        private Scene scene;
        private ModalWindow modal;
        private String name;
        private ImageView[] tabImage;
        private Game game;
        int [][] tableXY;
        private List<RadioButton> listRbuttonChecked;
        private EnemyGridGame enemyGrid;
        private MyGameGrid myGrid;
        private Group myGroup;
        private Group enemyGroup;

        
        /**
         * Configuration du stage lors du lancement de l'application
         */
        public void start(Stage stage)
        {
                this.root = new Group();
                this.scene = new Scene(this.root, 1920, 1000, Color.LIGHTGRAY);
                
                this.game = new Game(this);
//                
                GameMenu gameMenu = new GameMenu(game, this);
                this.root.getChildren().add(gameMenu);
                stage.setTitle("Battleships  (Annie Belzile, Laurie Lavoie, Kevin Tanguay)"); 
                stage.setScene(scene); 
       
//                        stage.setResizable(false);
                stage.show(); 
                this.askPositionBoats();
        }
        

        public String getName()
        {
        	return this.name;
        }


                /**
         * Affiche une fenêtre modale qui demande le nom et les coordonnées des bateaux du joueur.
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
         * Crée un objet MyGameGrid et un objet EnemyGridGame, les deux grilles graphiques.
         * 
         * @param game2 partie du jeu courant
         */
        public void setGrids(Game game2)
        {        
                this.setPieces();
                this.myGrid = new MyGameGrid();
                this.myGroup = this.myGrid.setGrid(this.name); 
                this.game.getGamer().setPlayerName(this.name);
                this.myGrid.ifBoats(this.game.getMatrix().getGameMatrix());
                this.enemyGrid = new EnemyGridGame(this.game, this);
                this.enemyGroup = this.enemyGrid.setGrid();
                this.root.getChildren().add(this.enemyGroup);
                this.root.getChildren().add(this.myGroup);
                this.game.getEnemy().shootEnemy(this.game.getMatrix());
        }
        
        /**
         * Appelle la méthode pour révéler l'emplacement des bateaux ennemis.
         */
        public void seeBoatsEnemyGrid()
        {
                this.enemyGrid.ifBoats(this.game.getComputerMatrix().getComputerGameMatrix());
        }

        public EnemyGridGame getEnemyGrid() {
                        return this.enemyGrid;
                }


                public MyGameGrid getMyGrid() {
			return this.myGrid;
		}


				/**
         * Place deux images à  droite dans l'interface.
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
                boolean verified = this.game.checkBoatsUser(this.tableXY, this.listRbuttonChecked);
                return verified;
        }
        
        
        /**
         * Le main de l'application
         * Lance les tests lors du démarrage.
         * @param args
         */
        public static void main(String[] args)
        {
//                JUnitCore junit = new JUnitCore(); 
//                Result resultGame = junit.run(GameTest.class);
//                runTests(resultGame);
//                Result resultMatrix = junit.run(MatrixTest.class);
//                runTests(resultMatrix);
//                
//                if (runTests(resultGame) && runTests(resultMatrix))
//                {
//                        String s = new File("").getAbsolutePath();
//                        System.out.println(s);
                        Application.launch(BattleGame.class, args);
//                }   
        }
        
        /**
         * Lance les test JUnit des classes du backend
         * @param result
         * @return true si aucune erreures, faux si des erreures sont présentes.
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
         * Classe contenant les actions du bouton "Ok" de la fenêtre modale où l'utilisateur entre ses donneés.
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
                                        int textLength = BattleGame.this.modal.getListField().get(compteur).getText().length();
                                        if(textLength == 1 || textLength == 2)
                                        {
                                                char checkIfnumber = BattleGame.this.modal.getListField().get(compteur).getText().charAt(0);
                                                boolean checkSecondChar = true;
                                                if(textLength == 2)
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

        /**
         * Appelle la méthode qui remet la grille ennemie comme neuve, pour débuter une nouvelle partie.
         */
        public void resetEnemyGrid()
        { 
                this.enemyGrid.resetImage(this.game.getComputerMatrix().getComputerGameMatrix());
                
        }

        /**
         * Éfface le jeu présentement en cours pour en débuter une nouvelle.
         */
        public void resetBattleGame() 
        {
                this.root.getChildren().remove(this.myGroup);
                this.root.getChildren().remove(this.enemyGroup);
        }
        
        /**
         * Création de la fenêtre de victoire, lorsque la partie est gagnée.
         * La fenètre te demandera si tu voudra enregistrer ta partie dans les "Meilleurs scores", et
         * ensuite te demandera si tu voudra jouer une autre partie.
         */
        public void VictoryPanel(int playerHit)
        {
        	Group scoreRoot = new Group();
    		Stage scoreDialog = new Stage();
    		scoreDialog.initModality(Modality.WINDOW_MODAL);
    		Scene scoreScene = new Scene(scoreRoot, 200,200, Color.LIGHTGRAY);
    		
    		Label infoLabel = new Label("Félicitations, vous êtes le champion!! \n \n Vous avez réussit en " + playerHit + " coups! \n" +
    				" Voulez-vous enregistrer votre score?");
    			infoLabel.setLayoutX(5);
    			infoLabel.setLayoutY(10);
    			
    		Button buttonYesScore = new Button("Oui!");
    		buttonYesScore.setLayoutX(10);
    		buttonYesScore.setLayoutY(100);
    		buttonYesScore.setOnMouseClicked(new ScoreYesListener());
    		
    		Button buttonNoScore = new Button("Non");
    		buttonNoScore.setLayoutX(80);
    		buttonNoScore.setLayoutY(100);
    		buttonNoScore.setOnMouseClicked(new ScoreNoListener());
    		
    		scoreRoot.getChildren().addAll(infoLabel, buttonYesScore, buttonNoScore);
    		
    		scoreDialog.setTitle("Victoire!!");
    		scoreDialog.setScene(scoreScene);
    		scoreDialog.setResizable(false);
    		this.modalStage = scoreDialog;
    		scoreDialog.show(); ;
    			scoreDialog.show();
        }
        
        /**
         * Contient tout les commandes pour les boutons de la fenètre modale.
         * @author Kevin
         *
         */
        private class ScoreYesListener implements EventHandler<MouseEvent>
            {

                        @Override
                        public void handle(MouseEvent arg0) 
                        {                           
                                BattleGame.this.game.checkIfFile();
                                BattleGame.this.modalStage.close();
                        }
                    
            }
            
            private class ScoreNoListener implements EventHandler<MouseEvent>
            {

                 @Override
                 public void handle(MouseEvent arg0) 
                 {
	                   BattleGame.this.modalStage.close();
	                   Group playAgainRoot = new Group();
	                   Stage playAgainDialog = new Stage();
	                   playAgainDialog.initModality(Modality.WINDOW_MODAL);
	                   Scene scoreScene = new Scene(playAgainRoot, 200,100, Color.LIGHTGRAY);
	                        
	                   Label infoLabel = new Label("Voulez-vous rejouer?");
	                         infoLabel.setLayoutX(5);
	                         infoLabel.setLayoutY(10);
	                                
	                   Button buttonYesPlay = new Button("Oui!");
	                   buttonYesPlay.setLayoutX(10);
	                   buttonYesPlay.setLayoutY(80);
	                   buttonYesPlay.setOnMouseClicked(new PlayYesListener());
	                        
	                   Button buttonNoPlay = new Button("Non");
	                   buttonNoPlay.setLayoutX(80);
	                   buttonNoPlay.setLayoutY(80);
	                   buttonNoPlay.setOnMouseClicked(new PlayNoListener());
	                        
	                   playAgainRoot.getChildren().addAll(infoLabel, buttonYesPlay, buttonNoPlay);
	                        
	                   playAgainDialog.setTitle("Victoire!!");
	                   playAgainDialog.setScene(scoreScene);
	                   playAgainDialog.setResizable(false);
	                   playAgainDialog.show(); 
	                   playAgainDialog.show();
                                
                 }
                    
	            private class PlayYesListener implements EventHandler<MouseEvent>
	            {
	            	@Override
	                public void handle(MouseEvent arg0) 
	                {
		                  Node source = (Node) arg0.getSource();
		                  Stage stage  = (Stage) source.getScene().getWindow();
		                  stage.close();
		                        
		                  //Reset
		                  resetBattleGame();
		                  BattleGame.this.game = new Game();
		                  askPositionBoats();
	                }
	                        
	             }
                        
                    private class PlayNoListener implements EventHandler<MouseEvent>
                    {
                    	@Override
                        public void handle(MouseEvent arg0) 
                         {
                    		Platform.exit();
                         }
                    }
            }
            
        /**
        * Création de la fenêtre de défaite, lorsque la partie est perdue.
        * La fenètre te demandera si tu voudra jouer une autre partie.
        */
        public void DefeatPanel(int computerHit)
        {
        	Group scoreRoot = new Group();
        	Stage scoreDialog = new Stage();
        	scoreDialog.initModality(Modality.WINDOW_MODAL);
        	Scene scoreScene = new Scene(scoreRoot, 200,200, Color.LIGHTGRAY);
        		
        	Label infoLabel = new Label("Malheureusement, l'adversaire vous à vaincu. Il à réussit en " + computerHit + "coups. \n " +
        			"Meilleur chance la prochaine fois! \n \n \n  Voulez vous rejouer?");
        	infoLabel.setLayoutX(5);
        	infoLabel.setLayoutY(10);
        			
        	Button buttonYesPlay = new Button("Oui!");
        	buttonYesPlay.setLayoutX(10);
        	buttonYesPlay.setLayoutY(100);
        	buttonYesPlay.setOnMouseClicked(new PlayYesListener());
        		
        	Button buttonNoPlay = new Button("Non");
        	buttonNoPlay.setLayoutX(80);
        	buttonNoPlay.setLayoutY(100);
        	buttonNoPlay.setOnMouseClicked(new PlayNoListener());
        		
        	scoreRoot.getChildren().addAll(infoLabel, buttonYesPlay, buttonNoPlay);
        		
        	scoreDialog.setTitle("Défaite...");
        	scoreDialog.setScene(scoreScene);
        	scoreDialog.setResizable(false);
        	this.modalStage = scoreDialog;
        	scoreDialog.show(); ;
        		scoreDialog.show();
        	}
        	
        	private class  PlayYesListener implements EventHandler<MouseEvent>
        	{

    			@Override
    			public void handle(MouseEvent arg0) 
    			{
    				Node source = (Node) arg0.getSource();
                	Stage stage  = (Stage) source.getScene().getWindow();
                	stage.close();
                        
                    resetBattleGame();
                    BattleGame.this.game = new Game();
                    askPositionBoats();
    				
    			}
        	}
        	
        	private class  PlayNoListener implements EventHandler<MouseEvent>
        	{

    			@Override
    			public void handle(MouseEvent arg0) 
    			{
    				Platform.exit();
    			}
        	}
}
