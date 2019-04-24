package pixelCombat.dusts;

import java.util.ArrayList;

import javafx.scene.image.Image;
import pixelCombat.Math.Vector2d;
import pixelCombat.model.Dust;
import pixelCombat.view.animation.Animation;

public class BloodSplash2 extends Dust {

	
	
	public BloodSplash2(Vector2d pos, boolean faceRight) {
		super(pos,faceRight);
		ArrayList<Image> images = new ArrayList<Image>();
		images.add(loadImage("/dusts/IMG_Dust_BloodSplash2_1.png"));
		images.add(loadImage("/dusts/IMG_Dust_BloodSplash2_2.png"));
		images.add(loadImage("/dusts/IMG_Dust_BloodSplash2_3.png"));
		images.add(loadImage("/dusts/IMG_Dust_BloodSplash2_4.png"));
		images.add(loadImage("/dusts/IMG_Dust_BloodSplash2_5.png"));
	
		images.add(loadImage("/dusts/IMG_Dust_BloodSplash2_6.png"));
		images.add(loadImage("/dusts/IMG_Dust_BloodSplash2_7.png"));
		images.add(loadImage("/dusts/IMG_Dust_BloodSplash2_8.png"));
		images.add(loadImage("/dusts/IMG_Dust_BloodSplash2_9.png"));
		images.add(loadImage("/dusts/IMG_Dust_BloodSplash2_10.png"));
		
		images.add(loadImage("/dusts/IMG_Dust_BloodSplash2_11.png"));
		images.add(loadImage("/dusts/IMG_Dust_BloodSplash2_12.png"));
		images.add(loadImage("/dusts/IMG_Dust_BloodSplash2_13.png"));
		images.add(loadImage("/dusts/IMG_Dust_BloodSplash2_14.png"));
		images.add(loadImage("/dusts/IMG_Dust_BloodSplash2_15.png"));
		
		images.add(loadImage("/dusts/IMG_Dust_BloodSplash2_16.png"));
		images.add(loadImage("/dusts/IMG_Dust_BloodSplash2_17.png"));
		images.add(loadImage("/dusts/IMG_Dust_BloodSplash2_18.png"));
		images.add(loadImage("/dusts/IMG_Dust_BloodSplash2_19.png"));
		images.add(loadImage("/dusts/IMG_Dust_BloodSplash2_20.png"));
		
		
		
		ArrayList<Float> times = new ArrayList<Float>();
		times.add(0.03f);
		times.add(0.03f);
		times.add(0.03f);
		times.add(0.03f);
		times.add(0.03f);
		
		times.add(0.03f);
		times.add(0.03f);
		times.add(0.03f);
		times.add(0.03f);
		times.add(0.03f);
		
		times.add(0.03f);
		times.add(0.03f);
		times.add(0.03f);
		times.add(0.03f);
		times.add(0.03f);
		
		times.add(0.03f);
		times.add(0.03f);
		times.add(0.03f);
		times.add(0.03f);
		times.add(0.03f);
		
		this.dustAnimator = new Animation(images,times,true);

	}

}
