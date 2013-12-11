package battleships;

import java.util.ArrayList;
import java.util.List;

import javafx.geometry.Insets;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;


/**
 * Classe JavaFX qui affiche une fenêtre lors d'une nouvelle partie qui demande le nom et les coordonnées des bateaux
 * @author Laurie
 *
 */
public class ModalWindow 

{
	

	private List<TextField> listField;
	private Text errorText;
	private List<RadioButton> listRadioButton;

	/**
	 * Retourne le text du message d'erreur
	 * @return
	 */
	public Text getErrorText() 
	{
		return this.errorText;
	}
	
	/**
	 * Retourne la liste de boutton radio
	 * @return
	 */

	public List<RadioButton> getListRadioButton()
	{
		return this.listRadioButton;
	}
	
	/**
	 * Constructeur
	 */

	public ModalWindow()
	{
		this.listField = new ArrayList<TextField>();
		this.listRadioButton = new ArrayList<RadioButton>();	
	}
	
	
	/**
	 * Retourne un group contenant des Text, des TextField et des radio button
	 * qui permettera à l'utilisateur d'entré ses données
	 * @return
	 */
	
	public GridPane setItemModal()
	{
		 GridPane gridPosition = new GridPane();
				
		Text nameText = new Text("Veuillez entrer votre nom");
		TextField nameField = new TextField(); 
		this.listField.add(nameField);
		
		

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
		this.listRadioButton.add(rbHorizontalAircraft);
		this.listRadioButton.add(rbVerticalAircraft);
		
		Text cruiserText = new Text("Veuillez entrer les coordonnées de votre croiseur (4)");
		TextField xcruiserField = new TextField(); 
		TextField ycruiserField = new TextField(); 
		RadioButton rbVerticalCruiser = new RadioButton("Vertical");
		RadioButton rbHorizontalCruiser = new RadioButton("Horizontal");
		final ToggleGroup groupCruiser = new ToggleGroup();
		rbVerticalCruiser.setToggleGroup(groupCruiser);
		rbHorizontalCruiser.setToggleGroup(groupCruiser);
		this.listRadioButton.add(rbHorizontalCruiser);
		this.listRadioButton.add(rbVerticalCruiser);
		
		this.listField.add(xcruiserField);
		this.listField.add(ycruiserField);
		
		
		
		Text destroyerText = new Text("Veuillez entrer les coordonnées de votre contre-torpilleur (3)");
		TextField xDestroyerField = new TextField(); 
		TextField yDestroyerField = new TextField(); 
		RadioButton rbVerticalDestroyer = new RadioButton("Vertical");
		RadioButton rbHorizontalDestroyer = new RadioButton("Horizontal");
		final ToggleGroup groupDestroyer = new ToggleGroup();
		rbVerticalDestroyer.setToggleGroup(groupDestroyer);
		rbHorizontalDestroyer.setToggleGroup(groupDestroyer);
		this.listRadioButton.add(rbHorizontalDestroyer);
		this.listRadioButton.add(rbVerticalDestroyer);
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
		this.listRadioButton.add(rbHorizontalSubmarine);
		this.listRadioButton.add(rbVerticalSubmarine);
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
		this.listRadioButton.add(rbHorizontalPatrol);
		this.listRadioButton.add(rbVerticalPatrol);
		this.listField.add(xPatrolField);
		this.listField.add(yPatrolField);
		
		Text xBoats = new Text("X");
		Text yBoats = new Text("Y");
		
		

		this.errorText = new Text("Valeur(s) incorrects");
		this.errorText.setVisible(false);
		
		
		for(int i = 0; i < 9.; i++)
		{
			this.listRadioButton.get(i).setSelected(true);
			i++;
		}
		
		gridPosition.setHgap(10);
	    gridPosition.setVgap(10);
	    gridPosition.setPadding(new Insets(25, 25, 25, 25));
	     
		
		gridPosition.add(nameText, 0, 0);
		gridPosition.add(nameField, 1, 0);
		gridPosition.add(errorText,2, 0);
		
		gridPosition.add(xBoats, 1, 1);
		gridPosition.add(yBoats, 2, 1);
		
		gridPosition.add(cruiserText, 0, 3);
		gridPosition.add(xcruiserField, 1, 3);
		gridPosition.add(ycruiserField, 2, 3);
		gridPosition.add(rbHorizontalCruiser, 3, 3);
		gridPosition.add(rbVerticalCruiser, 4, 3);

		
		
		gridPosition.add(aircraftText, 0, 2);
		gridPosition.add(xAircraftField , 1, 2);
		gridPosition.add(yAircraftField, 2, 2);
		gridPosition.add(rbHorizontalAircraft, 3, 2);
		gridPosition.add(rbVerticalAircraft, 4, 2);
		
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
		
		return gridPosition;
		
	}
	
	/**
	 * Retourne une liste de TextField
	 * @return
	 */

	public List<TextField> getListField()
	{
		return this.listField;
	}
	

}
