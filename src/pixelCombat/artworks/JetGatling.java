package pixelCombat.artworks;

import java.util.ArrayList;

import javafx.scene.image.Image;
import pixelCombat.Math.Vector2d;
import pixelCombat.model.PXMapHandler;
import pixelCombat.view.Animation;


public class JetGatling extends ArtWork {
	
	
	public JetGatling() {
		super(new Vector2d(PXMapHandler.X_FIELDS+10f,PXMapHandler.Y_FIELDS/2),-30f,0f);
		ArrayList<Image> images = new ArrayList<Image>();		
		images.add(loadImage("/artWorks/IMG_ArtWork_Ruffy_Jet_Gatling.png"));
		images.add(loadImage("/artWorks/IMG_ArtWork_Ruffy_Jet_Gatling.png"));		
		
		
		ArrayList<Float> times = new ArrayList<Float>();
		times.add(0.35f);	
		times.add(0.35f);	
		
		
		
		this.dustAnimator = new Animation(images,times,true);

	}

}
