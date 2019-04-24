package pixelCombat.artworks;

import java.util.ArrayList;

import javafx.scene.image.Image;
import pixelCombat.Math.Vector2d;
import pixelCombat.model.PXMapHandler;
import pixelCombat.view.animation.Animation;


public class NigiriArtwork extends ArtWork {

	
	
	public NigiriArtwork() {
		super(new Vector2d(PXMapHandler.X_FIELDS_STANDARD/2f,PXMapHandler.Y_FIELDS_STANDARD/2 -2f),0f,0f);
			
		ArrayList<Image> images = new ArrayList<Image>();		
		images.add(loadImage("/artWorks/IMG_ArtWork_Zorro_Nigiri.png"));
		images.add(loadImage("/artWorks/IMG_ArtWork_Zorro_Nigiri.png"));
		
		ArrayList<Float> times = new ArrayList<Float>();
		times.add(0.35f);	
		times.add(0.35f);	
		setSpecialArtWork(true);
		setOPACITY(0.7);
		scaleX = 1.25f;
		scaleY = 1.25f;
		this.dustAnimator = new Animation(images,times,true);

	}

}
