package pixelCombat.dusts;

import java.util.ArrayList;

import javafx.scene.image.Image;
import pixelCombat.Math.Vector2d;
import pixelCombat.model.Dust;
import pixelCombat.view.Animation;

public class OniGiriSmoke extends Dust {

	
	
	public OniGiriSmoke(Vector2d pos, boolean faceRight) {
		super(pos,faceRight);
		ArrayList<Image> images = new ArrayList<Image>();
	
		images.add(loadImage("/dusts/IMG_Dust_OnGiri_Smoke_1.png"));
		images.add(loadImage("/dusts/IMG_Dust_OnGiri_Smoke_2.png"));
		images.add(loadImage("/dusts/IMG_Dust_OnGiri_Smoke_3.png"));
		images.add(loadImage("/dusts/IMG_Dust_OnGiri_Smoke_4.png"));
		images.add(loadImage("/dusts/IMG_Dust_OnGiri_Smoke_5.png"));
		
		images.add(loadImage("/dusts/IMG_Dust_OnGiri_Smoke_6.png"));
		images.add(loadImage("/dusts/IMG_Dust_OnGiri_Smoke_7.png"));
		images.add(loadImage("/dusts/IMG_Dust_OnGiri_Smoke_8.png"));
		images.add(loadImage("/dusts/IMG_Dust_OnGiri_Smoke_9.png"));
		images.add(loadImage("/dusts/IMG_Dust_OnGiri_Smoke_10.png"));
		
		images.add(loadImage("/dusts/IMG_Dust_OnGiri_Smoke_11.png"));
		images.add(loadImage("/dusts/IMG_Dust_OnGiri_Smoke_12.png"));
		images.add(loadImage("/dusts/IMG_Dust_OnGiri_Smoke_13.png"));
		images.add(loadImage("/dusts/IMG_Dust_OnGiri_Smoke_14.png"));
		images.add(loadImage("/dusts/IMG_Dust_OnGiri_Smoke_15.png"));
		
		images.add(loadImage("/dusts/IMG_Dust_OnGiri_Smoke_16.png"));
		images.add(loadImage("/dusts/IMG_Dust_OnGiri_Smoke_17.png"));		

		
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
		
		times.add(0.025f);
		times.add(0.025f);
		times.add(0.025f);
		times.add(0.025f);
		times.add(0.025f);
		
		times.add(0.025f);
		times.add(0.025f);
		
		this.dustAnimator = new Animation(images,times,true);

	}

}
