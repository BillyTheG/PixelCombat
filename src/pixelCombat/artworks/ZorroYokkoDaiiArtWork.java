package pixelCombat.artworks;

import java.util.ArrayList;

import javafx.scene.image.Image;
import pixelCombat.Math.Vector2d;
import pixelCombat.model.PXMapHandler;
import pixelCombat.view.Animation;


public class ZorroYokkoDaiiArtWork extends ArtWork {

		
	public ZorroYokkoDaiiArtWork() {
		super(new Vector2d(PXMapHandler.X_FIELDS_STANDARD/2f -6f,PXMapHandler.Y_FIELDS_STANDARD/2),0f,0f);
		ArrayList<Image> images = new ArrayList<Image>();		
		images.add(loadImage("/artWorks/IMG_ArtWork_Zorro_Yokkodaii_01.png"));
		images.add(loadImage("/artWorks/IMG_ArtWork_Zorro_Yokkodaii_02.png"));
		images.add(loadImage("/artWorks/IMG_ArtWork_Zorro_Yokkodaii_03.png"));
		images.add(loadImage("/artWorks/IMG_ArtWork_Zorro_Yokkodaii_04.png"));
		images.add(loadImage("/artWorks/IMG_ArtWork_Zorro_Yokkodaii_05.png"));
		
		images.add(loadImage("/artWorks/IMG_ArtWork_Zorro_Yokkodaii_06.png"));
		images.add(loadImage("/artWorks/IMG_ArtWork_Zorro_Yokkodaii_07.png"));
		images.add(loadImage("/artWorks/IMG_ArtWork_Zorro_Yokkodaii_08.png"));
		images.add(loadImage("/artWorks/IMG_ArtWork_Zorro_Yokkodaii_09.png"));
		images.add(loadImage("/artWorks/IMG_ArtWork_Zorro_Yokkodaii_10.png"));
		
		images.add(loadImage("/artWorks/IMG_ArtWork_Zorro_Yokkodaii_11.png"));
		images.add(loadImage("/artWorks/IMG_ArtWork_Zorro_Yokkodaii_12.png"));
		images.add(loadImage("/artWorks/IMG_ArtWork_Zorro_Yokkodaii_13.png"));
		images.add(loadImage("/artWorks/IMG_ArtWork_Zorro_Yokkodaii_14.png"));
		images.add(loadImage("/artWorks/IMG_ArtWork_Zorro_Yokkodaii_15.png"));
		
		images.add(loadImage("/artWorks/IMG_ArtWork_Zorro_Yokkodaii_16.png"));
		images.add(loadImage("/artWorks/IMG_ArtWork_Zorro_Yokkodaii_17.png"));
		images.add(loadImage("/artWorks/IMG_ArtWork_Zorro_Yokkodaii_18.png"));
		images.add(loadImage("/artWorks/IMG_ArtWork_Zorro_Yokkodaii_19.png"));
		images.add(loadImage("/artWorks/IMG_ArtWork_Zorro_Yokkodaii_20.png"));
		
		images.add(loadImage("/artWorks/IMG_ArtWork_Zorro_Yokkodaii_21.png"));
		images.add(loadImage("/artWorks/IMG_ArtWork_Zorro_Yokkodaii_22.png"));
		images.add(loadImage("/artWorks/IMG_ArtWork_Zorro_Yokkodaii_23.png"));
		images.add(loadImage("/artWorks/IMG_ArtWork_Zorro_Yokkodaii_24.png"));
		images.add(loadImage("/artWorks/IMG_ArtWork_Zorro_Yokkodaii_25.png"));
		
		images.add(loadImage("/artWorks/IMG_ArtWork_Zorro_Yokkodaii_26.png"));
		images.add(loadImage("/artWorks/IMG_ArtWork_Zorro_Yokkodaii_27.png"));
		images.add(loadImage("/artWorks/IMG_ArtWork_Zorro_Yokkodaii_28.png"));
		images.add(loadImage("/artWorks/IMG_ArtWork_Zorro_Yokkodaii_29.png"));
		images.add(loadImage("/artWorks/IMG_ArtWork_Zorro_Yokkodaii_30.png"));
	
		images.add(loadImage("/artWorks/IMG_ArtWork_Zorro_Yokkodaii_31.png"));
		

		
		
		
		
		ArrayList<Float> times = new ArrayList<Float>();
		
		times.add(0.075f);	
		times.add(0.075f);	
		times.add(0.075f);	
		times.add(0.075f);
		times.add(0.075f);
		
		times.add(0.075f);
		times.add(0.075f);	
		times.add(0.075f);
		times.add(0.075f);	
		times.add(0.075f);
		
		times.add(0.075f);	
		times.add(0.075f);	
		times.add(0.075f);	
		times.add(0.075f);
		times.add(0.075f);
		
		times.add(0.075f);
		times.add(0.075f);	
		times.add(0.075f);
		times.add(0.075f);	
		times.add(0.075f);
		
		times.add(0.075f);	
		times.add(0.075f);	
		times.add(0.075f);	
		times.add(0.075f);
		times.add(0.075f);
		
		times.add(0.075f);
		times.add(0.075f);	
		times.add(0.075f);
		times.add(0.075f);	
		times.add(0.075f);
		
		times.add(0.075f);	
		
				
		
		this.dustAnimator = new Animation(images,times,true);
		setSpecialArtWork(true);
		setOPACITY(0.75);
	}

}
