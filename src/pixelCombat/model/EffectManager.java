package pixelCombat.model;

import java.util.Random;

import javafx.scene.image.Image;
import javafx.scene.image.PixelReader;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;

public class EffectManager {
	
	public EffectManager()
	{
	}
	
	
	public Image drawImage(Character character)
	{
		if(character.statusLogic.isKnockback())
		{	
			int width = (int) character.picManager.getImage().getWidth();
			int height = (int) character.picManager.getImage().getHeight();				
			WritableImage  image = new WritableImage(width, height);
			PixelWriter writer = image.getPixelWriter();
			PixelReader reader = character.picManager.getImage().getPixelReader();
			
		for(int x =0; x<width;x++)
		{
			Random random = new Random();
			for(int y = 0; y<height; y++)
			{
				if(reader.getArgb(x, y) != 0 && reader.getArgb(x, y) != 33587200)
					{
					System.out.println(reader.getArgb(x, y));
						writer.setColor(x, y, Color.RED);
						int old = image.getPixelReader().getArgb(x, y);
						writer.setArgb(x, y, old + random.nextInt(201));
					}
				else
					writer.setArgb(x, y, reader.getArgb(x, y));
				
			}
		}
			return image;
		}
		return character.picManager.getImage();
	}
}
