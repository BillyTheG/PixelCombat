package pixelCombat.dusts;

import java.util.ArrayList;

import javafx.scene.image.Image;
import pixelCombat.Math.Vector2d;
import pixelCombat.model.Dust;
import pixelCombat.view.animation.Animation;

public class StampDust extends Dust {

	
	
	public StampDust(Vector2d pos, boolean faceRight) {
		super(pos,faceRight);
		ArrayList<Image> images = new ArrayList<Image>();
	
		images.add(loadImage("/dusts/IMG_Dust_Stamp_1.png"));
		images.add(loadImage("/dusts/IMG_Dust_Stamp_2.png"));
		images.add(loadImage("/dusts/IMG_Dust_Stamp_3.png"));
		images.add(loadImage("/dusts/IMG_Dust_Stamp_4.png"));
		images.add(loadImage("/dusts/IMG_Dust_Stamp_5.png"));
		
		images.add(loadImage("/dusts/IMG_Dust_Stamp_6.png"));
		images.add(loadImage("/dusts/IMG_Dust_Stamp_7.png"));
		

		
		ArrayList<Float> times = new ArrayList<Float>();

		times.add(0.085f);
		times.add(0.085f);
		times.add(0.085f);
		times.add(0.085f);
		times.add(0.085f);
		
		times.add(0.085f);
		times.add(0.085f);
		
		this.dustAnimator = new Animation(images,times,true);

	}

}
