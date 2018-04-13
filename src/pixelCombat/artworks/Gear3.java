package pixelCombat.artworks;

import java.util.ArrayList;

import javafx.scene.image.Image;
import pixelCombat.Math.Vector2d;
import pixelCombat.model.PXMapHandler;
import pixelCombat.view.Animation;


public class Gear3 extends ArtWork {

	
	
	public Gear3() {
		super(new Vector2d(PXMapHandler.X_FIELDS/2f,PXMapHandler.Y_FIELDS/2),0f,-2f);
		ArrayList<Image> images = new ArrayList<Image>();		
		images.add(loadImage("/artWorks/IMG_Ruffy_Artwork_Gear3.png"));
		images.add(loadImage("/artWorks/IMG_Ruffy_Artwork_Gear3.png"));
		
		
		
		
		ArrayList<Float> times = new ArrayList<Float>();
		times.add(0.15f);	
		times.add(0.05f);	
		
		
		
		this.dustAnimator = new Animation(images,times,true);

	}

}
