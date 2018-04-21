package pixelCombat.dusts;

import java.util.ArrayList;

import javafx.scene.image.Image;
import pixelCombat.Math.Vector2d;
import pixelCombat.model.Dust;
import pixelCombat.view.Animation;

public class Fire_Dragon extends Dust {
	
	public Fire_Dragon(Vector2d pos, boolean faceRight) {
		super(pos,faceRight);
		ArrayList<Image> images = new ArrayList<Image>();
		
		images.add(loadImage("/dusts/dragon_1.png"));
		images.add(loadImage("/dusts/dragon_2.png"));
		images.add(loadImage("/dusts/dragon_3.png"));
		images.add(loadImage("/dusts/dragon_4.png"));
		images.add(loadImage("/dusts/dragon_5.png"));
		
		images.add(loadImage("/dusts/dragon_6.png"));
		images.add(loadImage("/dusts/dragon_7.png"));
		images.add(loadImage("/dusts/dragon_8.png"));
		images.add(loadImage("/dusts/dragon_9.png"));
		images.add(loadImage("/dusts/dragon_10.png"));

		images.add(loadImage("/dusts/dragon_11.png"));
		images.add(loadImage("/dusts/dragon_12.png"));
		images.add(loadImage("/dusts/dragon_13.png"));
		images.add(loadImage("/dusts/dragon_14.png"));
		images.add(loadImage("/dusts/dragon_15.png"));
		
		images.add(loadImage("/dusts/dragon_16.png"));
		images.add(loadImage("/dusts/dragon_17.png"));
		images.add(loadImage("/dusts/dragon_18.png"));
		images.add(loadImage("/dusts/dragon_19.png"));
		images.add(loadImage("/dusts/dragon_20.png"));
		
		images.add(loadImage("/dusts/dragon_21.png"));
		images.add(loadImage("/dusts/dragon_22.png"));
		images.add(loadImage("/dusts/dragon_23.png"));
		images.add(loadImage("/dusts/dragon_24.png"));
		images.add(loadImage("/dusts/dragon_25.png"));
		
		
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
		times.add(0.05f);
		
		this.dustAnimator = new Animation(images,times,true);

	}

}
