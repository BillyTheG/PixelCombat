package pixelCombat.dusts;

import java.util.ArrayList;

import javafx.scene.image.Image;
import pixelCombat.Math.Vector2d;
import pixelCombat.model.Dust;
import pixelCombat.view.Animation;

public class NatsuLightningFist extends Dust {

	
	
	public NatsuLightningFist(Vector2d pos, boolean faceRight) {
		super(pos, faceRight);
		ArrayList<Image> images = new ArrayList<Image>();

			images.add(loadImage("/dusts/lightning_punch_1_re.png"));
			images.add(loadImage("/dusts/lightning_punch_2_re.png"));
			images.add(loadImage("/dusts/lightning_punch_3_re.png"));
			images.add(loadImage("/dusts/lightning_punch_4_re.png"));
			images.add(loadImage("/dusts/lightning_punch_5_re.png"));
			
			images.add(loadImage("/dusts/lightning_punch_6_re.png"));
			images.add(loadImage("/dusts/lightning_punch_7_re.png"));
			images.add(loadImage("/dusts/lightning_punch_8_re.png"));
			images.add(loadImage("/dusts/lightning_punch_9_re.png"));
			images.add(loadImage("/dusts/lightning_punch_10_re.png"));
			
			images.add(loadImage("/dusts/lightning_punch_11_re.png"));
		
		
		ArrayList<Float> times = new ArrayList<Float>();
		times.add(0.015f);
		times.add(0.015f);
		times.add(0.025f);
		times.add(0.065f);
		times.add(0.055f);
		times.add(0.025f);
		times.add(0.025f);
		times.add(0.015f);
		times.add(0.015f);
		times.add(0.015f);
		
		times.add(0.035f);
		
		this.dustAnimator = new Animation(images,times,true);

	}

}
