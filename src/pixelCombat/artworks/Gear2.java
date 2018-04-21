package pixelCombat.artworks;

import java.util.ArrayList;

import javafx.scene.image.Image;
import pixelCombat.Math.Vector2d;
import pixelCombat.model.PXMapHandler;
import pixelCombat.view.Animation;


public class Gear2 extends MovableArtWork {

	
	
	public Gear2() {
		super(new Vector2d(-PXMapHandler.X_FIELDS/2f,2f),new Vector2d(PXMapHandler.X_FIELDS/2f,2f),
				new Vector2d(PXMapHandler.X_FIELDS+PXMapHandler.X_FIELDS/2f,2f),	
				
				70f,0f);
		ArrayList<Image> images = new ArrayList<Image>();		
		images.add(loadImage("/artWorks/IMG_ArtWork_Luffy_Jet_Pistole.png"));
		images.add(loadImage("/artWorks/IMG_ArtWork_Luffy_Jet_Pistole.png"));
		
		
		
		
		ArrayList<Float> times = new ArrayList<Float>();
		times.add(0.35f);	
		times.add(0.35f);	
		
		
		
		this.dustAnimator = new Animation(images,times,true);

	}

}
