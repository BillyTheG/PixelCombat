package pixelCombat.dusts;

import java.util.ArrayList;

import javafx.scene.image.Image;
import pixelCombat.Math.Vector2d;
import pixelCombat.model.Dust;
import pixelCombat.view.animation.Animation;

public class ZorroBasicAttack21Dust extends Dust {

	
	
	public ZorroBasicAttack21Dust(Vector2d pos, boolean faceRight) {
		super(pos,faceRight);
		ArrayList<Image> images = new ArrayList<Image>();
			images.add(loadImage("/dusts/IMG_Dust_Zorro_BasicAttack21_1.png"));
			images.add(loadImage("/dusts/IMG_Dust_Zorro_BasicAttack21_2.png"));
			images.add(loadImage("/dusts/IMG_Dust_Zorro_BasicAttack21_3.png"));
			images.add(loadImage("/dusts/IMG_Dust_Zorro_BasicAttack21_4.png"));
			images.add(loadImage("/dusts/IMG_Dust_Zorro_BasicAttack21_5.png"));
		
		
		ArrayList<Float> times = new ArrayList<Float>();
		times.add(0.03f);
		times.add(0.037f);
		times.add(0.03f);
		times.add(0.03f);
		times.add(0.03f);
		
		this.dustAnimator = new Animation(images,times,true);

	}

}
