package pixelCombat.artworks;

import java.util.ArrayList;

import javafx.scene.image.Image;
import pixelCombat.Math.Vector2d;
import pixelCombat.model.PXMapHandler;
import pixelCombat.view.animation.Animation;


public class RashomonArtWork extends MovableArtWork {

	
	
	public RashomonArtWork() {
		super(new Vector2d(-PXMapHandler.X_FIELDS/2f,2f),new Vector2d(PXMapHandler.X_FIELDS/2f,2f),
				new Vector2d(PXMapHandler.X_FIELDS+PXMapHandler.X_FIELDS/2f,2f),	
				
				70f,0f);
		ArrayList<Image> images = new ArrayList<Image>();		
		images.add(loadImage("/artWorks/IMG_ArtWork_Zorro_Rashomon.png"));
		images.add(loadImage("/artWorks/IMG_ArtWork_Zorro_Rashomon.png"));
		
		ArrayList<Float> times = new ArrayList<Float>();
		times.add(0.35f);	
		times.add(0.35f);	
		
		setOPACITY(0.7);
		this.dustAnimator = new Animation(images,times,true);

	}

}
