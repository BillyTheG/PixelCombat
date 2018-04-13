package pixelCombat.dusts;

import java.util.ArrayList;

import javafx.scene.image.Image;
import pixelCombat.Math.Vector2d;
import pixelCombat.model.Dust;
import pixelCombat.view.Animation;

public class ZorroBasicAttack13Dust extends Dust {

	
	
	public ZorroBasicAttack13Dust(Vector2d pos, boolean faceRight) {
		super(pos,faceRight);
		ArrayList<Image> images = new ArrayList<Image>();
			images.add(loadImage("/dusts/IMG_Dust_Zorro_BasicAttack13_1.png"));
			images.add(loadImage("/dusts/IMG_Dust_Zorro_BasicAttack13_2.png"));
			images.add(loadImage("/dusts/IMG_Dust_Zorro_BasicAttack13_3.png"));
			images.add(loadImage("/dusts/IMG_Dust_Zorro_BasicAttack13_4.png"));
			images.add(loadImage("/dusts/IMG_Dust_Zorro_BasicAttack13_5.png"));
		
		
		ArrayList<Float> times = new ArrayList<Float>();
		times.add(0.03f);
		times.add(0.037f);
		times.add(0.03f);
		times.add(0.03f);
		times.add(0.03f);
		
		this.dustAnimator = new Animation(images,times,true);

	}

}
