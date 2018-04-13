package pixelCombat.dusts;

import java.util.ArrayList;

import javafx.scene.image.Image;
import pixelCombat.Math.Vector2d;
import pixelCombat.model.Dust;
import pixelCombat.view.Animation;

public class DashDustAttack extends Dust {

	
	
	public DashDustAttack(Vector2d pos, boolean faceRight) {
		super(pos,faceRight);
		ArrayList<Image> images = new ArrayList<Image>();

			images.add(loadImage("/dusts/IMG_Dust_Attack_1.png"));
			images.add(loadImage("/dusts/IMG_Dust_Attack_2.png"));
			images.add(loadImage("/dusts/IMG_Dust_Attack_3.png"));
			images.add(loadImage("/dusts/IMG_Dust_Attack_4.png"));
			images.add(loadImage("/dusts/IMG_Dust_Attack_5.png"));
			images.add(loadImage("/dusts/IMG_Dust_Attack_6.png"));
			images.add(loadImage("/dusts/IMG_Dust_Attack_7.png"));
			images.add(loadImage("/dusts/IMG_Dust_Attack_8.png"));
			images.add(loadImage("/dusts/IMG_Dust_Attack_9.png"));
			images.add(loadImage("/dusts/IMG_Dust_Attack_10.png"));
		
		
		ArrayList<Float> times = new ArrayList<Float>();
		times.add(0.03f);
		times.add(0.03f);
		times.add(0.03f);
		times.add(0.03f);
		times.add(0.03f);
		times.add(0.03f);
		times.add(0.03f);
		times.add(0.03f);
		times.add(0.03f);
		times.add(0.03f);
		

		
		this.dustAnimator = new Animation(images,times,true);

	}

}
