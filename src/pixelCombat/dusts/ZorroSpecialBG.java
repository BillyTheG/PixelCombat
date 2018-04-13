package pixelCombat.dusts;

import java.util.ArrayList;

import javafx.scene.image.Image;
import pixelCombat.Math.Vector2d;
import pixelCombat.model.Dust;
import pixelCombat.view.Animation;

public class ZorroSpecialBG extends Dust {
	
	
	public ZorroSpecialBG(Vector2d pos, boolean faceRight) {
		super(pos,faceRight);
		ArrayList<Image> images = new ArrayList<Image>();
		images.add(loadImage("/special/Zorro_SuperAttack_1.png"));
		images.add(loadImage("/special/Zorro_SuperAttack_2.png"));
		images.add(loadImage("/special/Zorro_SuperAttack_3.png"));
		images.add(loadImage("/special/Zorro_SuperAttack_4.png"));
		
		ArrayList<Float> times = new ArrayList<Float>();
		times.add(0.13f);
		times.add(0.13f);
		times.add(0.13f);
		times.add(0.13f);

		
		this.dustAnimator = new Animation(images,times,true);

	}
	
	

}
