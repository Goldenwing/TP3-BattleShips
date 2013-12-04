package battleships;

import java.util.ArrayList;
import java.util.List;

import javafx.geometry.Insets;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;

public class ModalWindow 

{
	

	private List<TextField> listField;
	private Text errorText;

	

	public Text getErrorText() 
	{
		return this.errorText;
	}

	public ModalWindow()
	{
		this.listField = new ArrayList<TextField>();
	
	}
	
	
	
	
	public GridPane setItemModal()
	{
		 GridPane gridPosition = new GridPane();
				
		Text nameText = new Text("Veuillez entrer votre nom");
		TextField nameField = new TextField(); 
		this.listField.add(nameField);
		
		Text cruiserText = new Text("Veuillez entrer les coordonnées de votre croiseur (4)");
		TextField xcruiserField = new TextField(); 
		TextField ycruiserField = new TextField(); 
		RadioButton rbVerticalCruiser = new RadioButton("Vertical");
		RadioButton rbHorizontalCruiser = new RadioButton("Horizontal");
		final ToggleGroup groupCruiser = new ToggleGroup();
		rbVerticalCruiser.setToggleGroup(groupCruiser);
		rbHorizontalCruiser.setToggleGroup(groupCruiser);
		
		this.listField.add(xcruiserField);
		this.listField.add(ycruiserField);
		
		

		
		
		Text aircraftText = new Text("Veuillez entrer les coordonnées de votre porte-avions (5)");
		TextField xAircraftField = new TextField(); 
		TextField yAircraftField = new TextField(); 
		RadioButton rbVerticalAircraft = new RadioButton("Vertical");
		RadioButton rbHorizontalAircraft = new RadioButton("Horizontal");
		final ToggleGroup groupAircraft = new ToggleGroup();
		rbVerticalAircraft.setToggleGroup(groupAircraft);
		rbHorizontalAircraft.setToggleGroup(groupAircraft);
		this.listField.add(xAircraftField);
		this.listField.add(yAircraftField);
		
		
		Text destroyerText = new Text("Veuillez entrer les coordonnées de votre contre-torpilleur (3)");
		TextField xDestroyerField = new TextField(); 
		TextField yDestroyerField = new TextField(); 
		RadioButton rbVerticalDestroyer = new RadioButton("Vertical");
		RadioButton rbHorizontalDestroyer = new RadioButton("Horizontal");
		final ToggleGroup groupDestroyer = new ToggleGroup();
		rbVerticalDestroyer.setToggleGroup(groupDestroyer);
		rbHorizontalDestroyer.setToggleGroup(groupDestroyer);
		
		this.listField.add(xDestroyerField);
		this.listField.add(yDestroyerField);
		
		Text submarineText = new Text("Veuillez entrer les coordonnées de votre sous-marin (3)");
		TextField xSubmarineField = new TextField(); 
		TextField ySubmarineField = new TextField(); 
		RadioButton rbVerticalSubmarine = new RadioButton("Vertical");
		RadioButton rbHorizontalSubmarine = new RadioButton("Horizontal");
		final ToggleGroup groupSubmarine = new ToggleGroup();
		rbVerticalSubmarine.setToggleGroup(groupSubmarine);
		rbHorizontalSubmarine.setToggleGroup(groupSubmarine);
		
		this.listField.add(xSubmarineField);
		this.listField.add(ySubmarineField);
		
		Text patrolText = new Text("Veuillez entrer les coordonnées de votre patrouilleur (2)");
		TextField xPatrolField = new TextField(); 
		TextField yPatrolField = new TextField(); 
		RadioButton rbVerticalPatrol = new RadioButton("Vertical");
		RadioButton rbHorizontalPatrol = new RadioButton("Horizontal");
		final ToggleGroup groupPatrol = new ToggleGroup();
		rbVerticalPatrol.setToggleGroup(groupPatrol);
		rbHorizontalPatrol.setToggleGroup(groupPatrol);
		this.listField.add(xPatrolField);
		this.listField.add(yPatrolField);
		
		Text xBoats = new Text("X");
		Text yBoats = new Text("Y");
		
		
//		Button okBtn = new Button("Ok");
//		ActionEvent e;
//		okBtn.setOnAction(new ButtonListener());
		this.errorText = new Text("Valeur(s) incorrects");
		this.errorText.setVisible(false);
		
		gridPosition.setHgap(10);
	    gridPosition.setVgap(10);
	    gridPosition.setPadding(new Insets(25, 25, 25, 25));
	     
		
		gridPosition.add(nameText, 0, 0);
		gridPosition.add(nameField, 1, 0);
		gridPosition.add(errorText,2, 0);
		
		gridPosition.add(xBoats, 1, 1);
		gridPosition.add(yBoats, 2, 1);
		
		gridPosition.add(cruiserText, 0, 2);
		gridPosition.add(xcruiserField, 1, 2);
		gridPosition.add(ycruiserField, 2, 2);
		gridPosition.add(rbHorizontalCruiser, 3, 2);
		gridPosition.add(rbVerticalCruiser, 4, 2);

		
		
		gridPosition.add(aircraftText, 0, 3);
		gridPosition.add(xAircraftField , 1, 3);
		gridPosition.add(yAircraftField, 2, 3);
		gridPosition.add(rbHorizontalAircraft, 3, 3);
		gridPosition.add(rbVerticalAircraft, 4, 3);
		
		gridPosition.add(destroyerText, 0, 4);
		gridPosition.add(xDestroyerField, 1, 4);
		gridPosition.add(yDestroyerField, 2, 4);
		gridPosition.add(rbHorizontalDestroyer, 3, 4);
		gridPosition.add(rbVerticalDestroyer, 4, 4);
		
		gridPosition.add(submarineText, 0, 5);
		gridPosition.add(xSubmarineField , 1, 5);
		gridPosition.add(ySubmarineField, 2, 5);
		gridPosition.add(rbHorizontalSubmarine, 3, 5);
		gridPosition.add(rbVerticalSubmarine, 4, 5);
		
		
		gridPosition.add(patrolText , 0, 6);
		gridPosition.add(xPatrolField, 1, 6);
		gridPosition.add(yPatrolField, 2, 6);
		gridPosition.add(rbHorizontalPatrol, 3, 6);
		gridPosition.add(rbVerticalPatrol, 4, 6);
		
//		gridPosition.add(okBtn, 1, 7);
		return gridPosition;
		
	}

	public List<TextField> getListField()
	{
		return this.listField;
	}
	
//	private class ButtonListener implements EventHandler<ActionEvent>
//	{
//		
//		int [][] tableXY  = new int[2][5];
//		
//		@Override
//		public void handle(ActionEvent arg0)
//		{
//			
//			int compteur = 0;
//			
//			for(int j = 0; j < 5; j++)
//			{
//				for(int i = 0; i < 2; i++)
//				{
//					this.tableXY[i][j] = Integer.parseInt(ModalWindow.this.listField.get(compteur).getText());
//					compteur++;
//					
//				}
//			}
//			ModalWindow.this.okClicked = true;
//
//			
//		}
//	
//	}
}

