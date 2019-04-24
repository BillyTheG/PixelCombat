package pixelCombat.dusts;

import java.util.ArrayList;

import javafx.scene.image.Image;
import pixelCombat.Math.Vector2d;
import pixelCombat.model.Dust;
import pixelCombat.view.animation.Animation;

public class DashDust extends Dust {
	
	
	public DashDust(Vector2d pos, boolean faceRight) {
		super(pos,faceRight);
		ArrayList<Image> images = new ArrayList<>();

		images.add(loadImage("/dusts/IMG_DashDust_1_re.png"));
		images.add(loadImage("/dusts/IMG_DashDust_2_re.png"));
		images.add(loadImage("/dusts/IMG_DashDust_3_re.png"));
		images.add(loadImage("/dusts/IMG_DashDust_4_re.png"));
		images.add(loadImage("/dusts/IMG_DashDust_5_re.png"));
		images.add(loadImage("/dusts/IMG_DashDust_6_re.png"));
		
		
		ArrayList<Float> times = new ArrayList<Float>();
		times.add(0.035f);
		times.add(0.035f);
		times.add(0.035f);
		times.add(0.035f);
		times.add(0.035f);
		times.add(0.035f);

		
		this.dustAnimator = new Animation(images,times,true);

	}

}
