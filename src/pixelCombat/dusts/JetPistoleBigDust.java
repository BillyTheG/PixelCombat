package pixelCombat.dusts;

import java.util.ArrayList;

import javafx.scene.image.Image;
import pixelCombat.Math.Vector2d;
import pixelCombat.model.Dust;
import pixelCombat.view.animation.Animation;

public class JetPistoleBigDust extends Dust {

	
	
	public JetPistoleBigDust(Vector2d pos, boolean faceRight) {
		super(pos,faceRight);
		ArrayList<Image> images = new ArrayList<Image>();
		
		images.add(loadImage("/dusts/IMG_Dust_JetBigDust_1.png"));
		images.add(loadImage("/dusts/IMG_Dust_JetBigDust_2.png"));
		images.add(loadImage("/dusts/IMG_Dust_JetBigDust_3.png"));
		images.add(loadImage("/dusts/IMG_Dust_JetBigDust_4.png"));
		images.add(loadImage("/dusts/IMG_Dust_JetBigDust_5.png"));
		
		images.add(loadImage("/dusts/IMG_Dust_JetBigDust_6.png"));
		images.add(loadImage("/dusts/IMG_Dust_JetBigDust_7.png"));
		images.add(loadImage("/dusts/IMG_Dust_JetBigDust_8.png"));



		
		ArrayList<Float> times = new ArrayList<Float>();
		
		times.add(0.025f);
		times.add(0.025f);
		times.add(0.025f);
		times.add(0.025f);
		times.add(0.025f);
		
		times.add(0.025f);
		times.add(0.025f);
		times.add(0.025f);

		
		this.dustAnimator = new Animation(images,times,true);

	}

}
