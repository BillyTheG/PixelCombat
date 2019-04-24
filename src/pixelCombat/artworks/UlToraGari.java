package pixelCombat.artworks;

import java.util.ArrayList;

import javafx.scene.image.Image;
import pixelCombat.Math.Vector2d;
import pixelCombat.model.PXMapHandler;
import pixelCombat.view.animation.Animation;


public class UlToraGari extends ArtWork {

	
	
	public UlToraGari() {
		super(new Vector2d(PXMapHandler.X_FIELDS/2f,PXMapHandler.Y_FIELDS/2),0f,0f);
		
		ArrayList<Image> images = new ArrayList<Image>();		
		images.add(loadImage("/artWorks/IMG_Zorro_Artwork_UlToraGari.png"));
		
		ArrayList<Float> times = new ArrayList<Float>();	
		times.add(0.05f);	
		
		this.dustAnimator = new Animation(images,times,false);
		scaleX = 1.5f;
		scaleY = 1.5f;
		setSpecialArtWork(true);
	}

}
