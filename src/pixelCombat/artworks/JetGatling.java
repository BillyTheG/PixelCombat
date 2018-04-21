package pixelCombat.artworks;

import java.util.ArrayList;

import javafx.scene.image.Image;
import pixelCombat.Math.Vector2d;
import pixelCombat.model.PXMapHandler;
import pixelCombat.view.Animation;


public class JetGatling extends ArtWork {
	
	
	public JetGatling() {
		super(new Vector2d(PXMapHandler.X_FIELDS_STANDARD*3/5f,PXMapHandler.Y_FIELDS_STANDARD*5/6f),0f,0f);
		ArrayList<Image> images = new ArrayList<Image>();		
		
		images.add(loadImage("/artWorks/IMG_ArtWork_Luffy_JetGatling_01.png"));
		images.add(loadImage("/artWorks/IMG_ArtWork_Luffy_JetGatling_02.png"));		
		images.add(loadImage("/artWorks/IMG_ArtWork_Luffy_JetGatling_03.png"));
		images.add(loadImage("/artWorks/IMG_ArtWork_Luffy_JetGatling_04.png"));	
		
		images.add(loadImage("/artWorks/IMG_ArtWork_Luffy_JetGatling_05.png"));
		images.add(loadImage("/artWorks/IMG_ArtWork_Luffy_JetGatling_06.png"));	
		images.add(loadImage("/artWorks/IMG_ArtWork_Luffy_JetGatling_07.png"));
		images.add(loadImage("/artWorks/IMG_ArtWork_Luffy_JetGatling_08.png"));	
		
		images.add(loadImage("/artWorks/IMG_ArtWork_Luffy_JetGatling_09.png"));
		images.add(loadImage("/artWorks/IMG_ArtWork_Luffy_JetGatling_10.png"));	
		
		
		ArrayList<Float> times = new ArrayList<Float>();
		times.add(0.025f);	
		times.add(0.025f);	
		times.add(0.025f);	
		times.add(0.025f);
		times.add(0.025f);	
		times.add(0.025f);
		times.add(0.025f);	
		times.add(0.025f);
		times.add(0.025f);	
		times.add(0.025f);
		
		this.scaleX = 0.5f;
		this.scaleY = 0.5f;
		
		this.dustAnimator = new Animation(images,times,0,7);
		setSpecialArtWork(true);
	}

}
