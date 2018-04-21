package pixelCombat.artworks;

import java.util.ArrayList;

import javafx.scene.image.Image;
import pixelCombat.Math.Vector2d;
import pixelCombat.model.PXMapHandler;
import pixelCombat.view.Animation;


public class RuffyEpic extends ArtWork {
	
	
	public RuffyEpic() {
		super(new Vector2d(PXMapHandler.X_FIELDS_STANDARD/3f,PXMapHandler.Y_FIELDS_STANDARD*3/4f),0f,0f);
		ArrayList<Image> images = new ArrayList<Image>();		
		
		images.add(loadImage("/artWorks/IMG_ArtWork_Ruffy_Epic_01.png"));
		images.add(loadImage("/artWorks/IMG_ArtWork_Ruffy_Epic_02.png"));		
		images.add(loadImage("/artWorks/IMG_ArtWork_Ruffy_Epic_03.png"));
		images.add(loadImage("/artWorks/IMG_ArtWork_Ruffy_Epic_04.png"));	
		images.add(loadImage("/artWorks/IMG_ArtWork_Ruffy_Epic_05.png"));
		
		images.add(loadImage("/artWorks/IMG_ArtWork_Ruffy_Epic_06.png"));
		images.add(loadImage("/artWorks/IMG_ArtWork_Ruffy_Epic_07.png"));		
		images.add(loadImage("/artWorks/IMG_ArtWork_Ruffy_Epic_08.png"));
		images.add(loadImage("/artWorks/IMG_ArtWork_Ruffy_Epic_09.png"));	
		images.add(loadImage("/artWorks/IMG_ArtWork_Ruffy_Epic_10.png"));
		
		images.add(loadImage("/artWorks/IMG_ArtWork_Ruffy_Epic_11.png"));
		images.add(loadImage("/artWorks/IMG_ArtWork_Ruffy_Epic_12.png"));		
		images.add(loadImage("/artWorks/IMG_ArtWork_Ruffy_Epic_13.png"));
		images.add(loadImage("/artWorks/IMG_ArtWork_Ruffy_Epic_14.png"));	
		images.add(loadImage("/artWorks/IMG_ArtWork_Ruffy_Epic_15.png"));
		
		images.add(loadImage("/artWorks/IMG_ArtWork_Ruffy_Epic_16.png"));
		images.add(loadImage("/artWorks/IMG_ArtWork_Ruffy_Epic_17.png"));		
		images.add(loadImage("/artWorks/IMG_ArtWork_Ruffy_Epic_18.png"));
		images.add(loadImage("/artWorks/IMG_ArtWork_Ruffy_Epic_19.png"));	
		images.add(loadImage("/artWorks/IMG_ArtWork_Ruffy_Epic_20.png"));
		
		
		
		ArrayList<Float> times = new ArrayList<Float>();
		times.add(0.1f);	
		times.add(0.1f);	
		times.add(0.1f);	
		times.add(0.1f);
		times.add(0.1f);
		
		times.add(0.1f);
		times.add(0.1f);	
		times.add(0.1f);
		times.add(0.1f);	
		times.add(0.1f);
		
		times.add(0.1f);	
		times.add(0.1f);	
		times.add(0.1f);	
		times.add(0.1f);
		times.add(0.1f);
		
		times.add(0.1f);
		times.add(0.1f);	
		times.add(0.1f);
		times.add(0.1f);	
		times.add(0.1f);
		
		this.scaleX = 0.5f;
		this.scaleY = 0.5f;
		
		this.dustAnimator = new Animation(images,times,0,2);
		setSpecialArtWork(true);
	}

}
