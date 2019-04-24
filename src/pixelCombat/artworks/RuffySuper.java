package pixelCombat.artworks;

import java.util.ArrayList;

import javafx.scene.image.Image;
import pixelCombat.Math.Vector2d;
import pixelCombat.model.PXMapHandler;
import pixelCombat.view.animation.Animation;


public class RuffySuper extends ArtWork {

	
	
	public RuffySuper() {
		super(new Vector2d(PXMapHandler.X_FIELDS_STANDARD/2f,PXMapHandler.Y_FIELDS_STANDARD/2),0f,-2f);
		ArrayList<Image> images = new ArrayList<Image>();		
		images.add(loadImage("/artWorks/IMG_Artwork_Ruffy_SuperAttack_1.png"));
		images.add(loadImage("/artWorks/IMG_Artwork_Ruffy_SuperAttack_2.png"));
		images.add(loadImage("/artWorks/IMG_Artwork_Ruffy_SuperAttack_3.png"));
		images.add(loadImage("/artWorks/IMG_Artwork_Ruffy_SuperAttack_4.png"));
		images.add(loadImage("/artWorks/IMG_Artwork_Ruffy_SuperAttack_5.png"));
		images.add(loadImage("/artWorks/IMG_Artwork_Ruffy_SuperAttack_6.png"));
		images.add(loadImage("/artWorks/IMG_Artwork_Ruffy_SuperAttack_7.png"));
		images.add(loadImage("/artWorks/IMG_Artwork_Ruffy_SuperAttack_8.png"));
		images.add(loadImage("/artWorks/IMG_Artwork_Ruffy_SuperAttack_9.png"));
		images.add(loadImage("/artWorks/IMG_Artwork_Ruffy_SuperAttack_10.png"));
		images.add(loadImage("/artWorks/IMG_Artwork_Ruffy_SuperAttack_11.png"));
		images.add(loadImage("/artWorks/IMG_Artwork_Ruffy_SuperAttack_12.png"));
		images.add(loadImage("/artWorks/IMG_Artwork_Ruffy_SuperAttack_13.png"));
		images.add(loadImage("/artWorks/IMG_Artwork_Ruffy_SuperAttack_14.png"));
		images.add(loadImage("/artWorks/IMG_Artwork_Ruffy_SuperAttack_15.png"));
		images.add(loadImage("/artWorks/IMG_Artwork_Ruffy_SuperAttack_16.png"));
		images.add(loadImage("/artWorks/IMG_Artwork_Ruffy_SuperAttack_17.png"));
		images.add(loadImage("/artWorks/IMG_Artwork_Ruffy_SuperAttack_18.png"));


		
		
		
		
		ArrayList<Float> times = new ArrayList<Float>();
		
		times.add(0.05f);	
		times.add(0.05f);	
		times.add(0.05f);	
		times.add(0.05f);
		times.add(0.05f);	
		times.add(0.05f);
		times.add(0.05f);	
		times.add(0.05f);
		times.add(0.05f);	
		times.add(0.05f);
		
		times.add(0.05f);	
		times.add(0.05f);
		times.add(0.05f);	
		times.add(0.05f);
		times.add(0.05f);	
		times.add(0.05f);
		times.add(0.05f);	
		times.add(0.05f);
		
		
		
		this.dustAnimator = new Animation(images,times,true);
		setSpecialArtWork(true);
	}

}
