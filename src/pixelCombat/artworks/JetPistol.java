package pixelCombat.artworks;

import java.util.ArrayList;

import javafx.scene.image.Image;
import pixelCombat.Math.Vector2d;
import pixelCombat.model.PXMapHandler;
import pixelCombat.view.Animation;

public class JetPistol extends ArtWork {

	
	
	public JetPistol() {
		super(new Vector2d(PXMapHandler.X_FIELDS/2f,PXMapHandler.Y_FIELDS/2),0f,-5f);
		ArrayList<Image> images = new ArrayList<Image>();
		
		images.add(loadImage("/artWorks/IMG_Ruffy_Artwork_JetPistol.png"));
		images.add(loadImage("/artWorks/IMG_Ruffy_Artwork_JetPistol.png"));
		
		ArrayList<Float> times = new ArrayList<Float>();
		times.add(0.15f);
		times.add(0.15f);
		
		this.dustAnimator = new Animation(images,times,true);

	}

}
