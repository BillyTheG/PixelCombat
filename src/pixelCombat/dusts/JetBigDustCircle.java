package pixelCombat.dusts;

import java.util.ArrayList;

import javafx.scene.image.Image;
import pixelCombat.Math.Vector2d;
import pixelCombat.model.Dust;
import pixelCombat.view.Animation;

public class JetBigDustCircle extends Dust {

	
	
	public JetBigDustCircle(Vector2d pos, boolean faceRight) {
		super(pos,faceRight);
		ArrayList<Image> images = new ArrayList<Image>();
		
		images.add(loadImage("/dusts/IMG_Dust_JetBigDustCircle_1.png"));
		images.add(loadImage("/dusts/IMG_Dust_JetBigDustCircle_2.png"));
		images.add(loadImage("/dusts/IMG_Dust_JetBigDustCircle_3.png"));
		images.add(loadImage("/dusts/IMG_Dust_JetBigDustCircle_4.png"));
		images.add(loadImage("/dusts/IMG_Dust_JetBigDustCircle_5.png"));
		
		images.add(loadImage("/dusts/IMG_Dust_JetBigDustCircle_6.png"));
		images.add(loadImage("/dusts/IMG_Dust_JetBigDustCircle_7.png"));
		images.add(loadImage("/dusts/IMG_Dust_JetBigDustCircle_8.png"));
		images.add(loadImage("/dusts/IMG_Dust_JetBigDustCircle_9.png"));
		images.add(loadImage("/dusts/IMG_Dust_JetBigDustCircle_10.png"));
		
		images.add(loadImage("/dusts/IMG_Dust_JetBigDustCircle_11.png"));
		images.add(loadImage("/dusts/IMG_Dust_JetBigDustCircle_12.png"));
		images.add(loadImage("/dusts/IMG_Dust_JetBigDustCircle_13.png"));
		images.add(loadImage("/dusts/IMG_Dust_JetBigDustCircle_14.png"));
		images.add(loadImage("/dusts/IMG_Dust_JetBigDustCircle_15.png"));

		
		ArrayList<Float> times = new ArrayList<Float>();
		
		times.add(0.0125f);
		times.add(0.0125f);
		times.add(0.0125f);
		times.add(0.0125f);
		times.add(0.0125f);
		
		times.add(0.0125f);
		times.add(0.0125f);
		times.add(0.0125f);
		times.add(0.0125f);
		times.add(0.0125f);

		times.add(0.0125f);
		times.add(0.0125f);
		times.add(0.0125f);
		times.add(0.0125f);
		times.add(0.0125f);
		
		this.dustAnimator = new Animation(images,times,false);

	}

}
