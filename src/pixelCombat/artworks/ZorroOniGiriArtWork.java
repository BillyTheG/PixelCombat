package pixelCombat.artworks;

import java.util.ArrayList;

import javafx.scene.image.Image;
import pixelCombat.Math.Vector2d;
import pixelCombat.model.PXMapHandler;
import pixelCombat.view.animation.Animation;


public class ZorroOniGiriArtWork extends ArtWork {

	
	
	public ZorroOniGiriArtWork() {
		super(new Vector2d(PXMapHandler.X_FIELDS_STANDARD/2f,PXMapHandler.Y_FIELDS_STANDARD/2 -2.5f),0f,0f);
		ArrayList<Image> images = new ArrayList<Image>();		
		images.add(loadImage("/artWorks/IMG_ArtWork_Zorro_OniGiri_01.png"));
		images.add(loadImage("/artWorks/IMG_ArtWork_Zorro_OniGiri_02.png"));
		images.add(loadImage("/artWorks/IMG_ArtWork_Zorro_OniGiri_03.png"));
		images.add(loadImage("/artWorks/IMG_ArtWork_Zorro_OniGiri_04.png"));
		images.add(loadImage("/artWorks/IMG_ArtWork_Zorro_OniGiri_05.png"));
		
		images.add(loadImage("/artWorks/IMG_ArtWork_Zorro_OniGiri_06.png"));
		images.add(loadImage("/artWorks/IMG_ArtWork_Zorro_OniGiri_07.png"));
		images.add(loadImage("/artWorks/IMG_ArtWork_Zorro_OniGiri_08.png"));
		images.add(loadImage("/artWorks/IMG_ArtWork_Zorro_OniGiri_09.png"));
		images.add(loadImage("/artWorks/IMG_ArtWork_Zorro_OniGiri_10.png"));
		
		images.add(loadImage("/artWorks/IMG_ArtWork_Zorro_OniGiri_11.png"));
		images.add(loadImage("/artWorks/IMG_ArtWork_Zorro_OniGiri_12.png"));
	


		
		
		
		
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
				
		
		this.dustAnimator = new Animation(images,times,true);
		setSpecialArtWork(true);
		setOPACITY(0.5);
	}

}
