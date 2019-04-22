package pixelCombat.dusts;

import java.util.ArrayList;

import javafx.scene.image.Image;
import pixelCombat.Math.Vector2d;
import pixelCombat.model.Dust;
import pixelCombat.view.Animation;

public class JetBigDustBackDust extends Dust {

	public JetBigDustBackDust(Vector2d pos, boolean faceRight) {
		super(pos,faceRight);
		ArrayList<Image> images = new ArrayList<Image>();
		
		images.add(loadImage("/dusts/IMG_Dust_JetBigDustBackDust_1.png"));
		images.add(loadImage("/dusts/IMG_Dust_JetBigDustBackDust_2.png"));
		images.add(loadImage("/dusts/IMG_Dust_JetBigDustBackDust_3.png"));
		images.add(loadImage("/dusts/IMG_Dust_JetBigDustBackDust_4.png"));
		images.add(loadImage("/dusts/IMG_Dust_JetBigDustBackDust_5.png"));
		
		images.add(loadImage("/dusts/IMG_Dust_JetBigDustBackDust_6.png"));
		images.add(loadImage("/dusts/IMG_Dust_JetBigDustBackDust_7.png"));
		images.add(loadImage("/dusts/IMG_Dust_JetBigDustBackDust_8.png"));
		images.add(loadImage("/dusts/IMG_Dust_JetBigDustBackDust_9.png"));
		images.add(loadImage("/dusts/IMG_Dust_JetBigDustBackDust_10.png"));
		
		images.add(loadImage("/dusts/IMG_Dust_JetBigDustBackDust_11.png"));
		images.add(loadImage("/dusts/IMG_Dust_JetBigDustBackDust_12.png"));
		images.add(loadImage("/dusts/IMG_Dust_JetBigDustBackDust_13.png"));

		
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

		
		this.dustAnimator = new Animation(images,times,false);

	}

}
