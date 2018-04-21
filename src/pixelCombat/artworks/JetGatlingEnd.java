package pixelCombat.artworks;

import java.util.ArrayList;

import javafx.scene.image.Image;
import pixelCombat.Math.Vector2d;
import pixelCombat.model.PXMapHandler;
import pixelCombat.view.Animation;


public class JetGatlingEnd extends ArtWork {
	
	
	public JetGatlingEnd() {
		super(new Vector2d(PXMapHandler.X_FIELDS_STANDARD/2f,PXMapHandler.Y_FIELDS_STANDARD/2f),0f,0f);
		ArrayList<Image> images = new ArrayList<Image>();		
		
		images.add(loadImage("/artWorks/IMG_ArtWork_Luffy_JetGatling_End_01.png"));
		images.add(loadImage("/artWorks/IMG_ArtWork_Luffy_JetGatling_End_02.png"));		
		images.add(loadImage("/artWorks/IMG_ArtWork_Luffy_JetGatling_End_03.png"));
		images.add(loadImage("/artWorks/IMG_ArtWork_Luffy_JetGatling_End_04.png"));	
		
		images.add(loadImage("/artWorks/IMG_ArtWork_Luffy_JetGatling_End_05.png"));
		images.add(loadImage("/artWorks/IMG_ArtWork_Luffy_JetGatling_End_06.png"));	
		
		
		
		ArrayList<Float> times = new ArrayList<Float>();
		times.add(0.075f);	
		times.add(0.075f);	
		times.add(0.075f);	
		times.add(0.075f);
		times.add(0.075f);
		
		times.add(0.075f);
		
		
		
		this.dustAnimator = new Animation(images,times,true);
		setSpecialArtWork(true);
	}

}
