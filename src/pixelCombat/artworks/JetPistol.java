package pixelCombat.artworks;

import java.util.ArrayList;

import javafx.scene.image.Image;
import pixelCombat.Math.Vector2d;
import pixelCombat.model.PXMapHandler;
import pixelCombat.view.Animation;

public class JetPistol extends MovableArtWork {

	
	
	public JetPistol() { 
		super(new Vector2d(-PXMapHandler.X_FIELDS/2f,2f),new Vector2d(PXMapHandler.X_FIELDS/2f,2f),
				new Vector2d(PXMapHandler.X_FIELDS+PXMapHandler.X_FIELDS*3/4f,2f),	
				
				70f,0f);
		ArrayList<Image> images = new ArrayList<Image>();
		
		images.add(loadImage("/artWorks/IMG_Ruffy_Artwork_JetPistol.png"));
		images.add(loadImage("/artWorks/IMG_Ruffy_Artwork_JetPistol.png"));
		
		ArrayList<Float> times = new ArrayList<Float>();
		times.add(0.15f);
		times.add(0.15f);
		
		this.dustAnimator = new Animation(images,times,true);

	}

}
