package pixelCombat.dusts;

import java.util.ArrayList;

import javafx.scene.image.Image;
import pixelCombat.Math.Vector2d;
import pixelCombat.model.Dust;
import pixelCombat.view.Animation;

public class BloodSplash1 extends Dust {

	
	
	public BloodSplash1(Vector2d pos, boolean faceRight) {
		super(pos,faceRight);
		ArrayList<Image> images = new ArrayList<Image>();
		
		images.add(loadImage("/dusts/IMG_Dust_Blood_Splash1_1.png"));
		images.add(loadImage("/dusts/IMG_Dust_Blood_Splash1_2.png"));
		images.add(loadImage("/dusts/IMG_Dust_Blood_Splash1_3.png"));
		images.add(loadImage("/dusts/IMG_Dust_Blood_Splash1_4.png"));
		images.add(loadImage("/dusts/IMG_Dust_Blood_Splash1_5.png"));
		
		images.add(loadImage("/dusts/IMG_Dust_Blood_Splash1_6.png"));
		images.add(loadImage("/dusts/IMG_Dust_Blood_Splash1_7.png"));
		images.add(loadImage("/dusts/IMG_Dust_Blood_Splash1_8.png"));
		
		ArrayList<Float> times = new ArrayList<Float>();
		times.add(0.05f);
		times.add(0.05f);
		times.add(0.05f);
		times.add(0.05f);
		times.add(0.05f);
		
		times.add(0.05f);
		times.add(0.05f);
		times.add(0.05f);
		
		this.dustAnimator = new Animation(images,times,true);

	}

}
