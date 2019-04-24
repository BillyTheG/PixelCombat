package pixelCombat.dusts;

import java.util.ArrayList;

import javafx.scene.image.Image;
import pixelCombat.Math.Vector2d;
import pixelCombat.model.Dust;
import pixelCombat.view.animation.Animation;

public class JumpDust extends Dust {

	
	
	public JumpDust(Vector2d pos, boolean faceRight) {
		super(pos,faceRight);
		ArrayList<Image> images = new ArrayList<Image>();
		
		images.add(loadImage("/dusts/IMG_JumpDust_1.png"));
		images.add(loadImage("/dusts/IMG_JumpDust_2.png"));
		images.add(loadImage("/dusts/IMG_JumpDust_3.png"));
		images.add(loadImage("/dusts/IMG_JumpDust_4.png"));
		images.add(loadImage("/dusts/IMG_JumpDust_5.png"));

		
		ArrayList<Float> times = new ArrayList<Float>();
		times.add(0.04f);
		times.add(0.04f);
		times.add(0.04f);
		times.add(0.04f);
		times.add(0.04f);

		
		this.dustAnimator = new Animation(images,times,true);

	}

}
