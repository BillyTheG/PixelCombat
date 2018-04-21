package pixelCombat.artworks;

import java.util.ArrayList;

import javafx.scene.image.Image;
import pixelCombat.Math.Vector2d;
import pixelCombat.model.PXMapHandler;
import pixelCombat.view.Animation;


public class TatsumakiDragon extends ArtWork {

		
	public TatsumakiDragon() {
		super(new Vector2d(PXMapHandler.X_FIELDS/2f,PXMapHandler.Y_FIELDS/2f-5f),
				new Vector2d(PXMapHandler.X_FIELDS/2f,PXMapHandler.Y_FIELDS/2f),0f,5f);
		
		ArrayList<Image> images = new ArrayList<Image>();		
		images.add(loadImage("/artWorks/IMG_Zorro_Artwork_Tatsumaki.png"));
		
		ArrayList<Float> times = new ArrayList<Float>();	
		times.add(0.05f);	
		
		this.dustAnimator = new Animation(images,times,false);
		setSpecialArtWork(true);
		setDrawBehind(true);
		setOPACITY(0.25d);
		scaleX = 1.25f;
		scaleY = 1.25f;
				
		
	}

}
