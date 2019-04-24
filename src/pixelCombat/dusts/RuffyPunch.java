package pixelCombat.dusts;

import java.util.ArrayList;

import javafx.scene.image.Image;
import pixelCombat.Math.Vector2d;
import pixelCombat.model.Dust;
import pixelCombat.view.animation.Animation;

public class RuffyPunch extends Dust {

	
	
	public RuffyPunch(Vector2d pos, boolean faceRight) {
		super(pos,faceRight);
		ArrayList<Image> images = new ArrayList<Image>();
		
		images.add(loadImage("/dusts/IMG_Dust_RuffyPunch_1.png"));
		images.add(loadImage("/dusts/IMG_Dust_RuffyPunch_2.png"));
		images.add(loadImage("/dusts/IMG_Dust_RuffyPunch_3.png"));
		images.add(loadImage("/dusts/IMG_Dust_RuffyPunch_4.png"));
		images.add(loadImage("/dusts/IMG_Dust_RuffyPunch_5.png"));

		
		ArrayList<Float> times = new ArrayList<Float>();
		times.add(0.02f);
		times.add(0.02f);
		times.add(0.02f);
		times.add(0.02f);
		times.add(0.02f);

		
		this.dustAnimator = new Animation(images,times,true);

	}

}
