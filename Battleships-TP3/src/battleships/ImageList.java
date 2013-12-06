package battleships;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.image.Image;

public class ImageList 

{
	public ImageList()
	{
		
	}
	public List<Image> imageListNumbers()
	{
		List<Image> imageListNumbers = new ArrayList<Image>();
		
		for(int i = 0; i < 10; i++)
		{
			Image imageToAdd = new Image("file:Images/" + (i + 1) + ".png");
			imageListNumbers.add(imageToAdd);
			
		}
		
		
		return imageListNumbers;
		
	}
	public List<Image> imageListLetters()
	{
		List<Image> imageListLetters = new ArrayList<Image>();
		
		for(char i = 65; i < 75; i++)
		{
		
			
			
			Image imageToAdd = new Image("file:Images/" + (i) + ".png");
			imageListLetters.add(imageToAdd);
			
		}
		
		return imageListLetters;
		
	}
}
