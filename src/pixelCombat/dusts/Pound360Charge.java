package pixelCombat.dusts;

import java.util.ArrayList;

import javafx.scene.image.Image;
import pixelCombat.Math.Vector2d;
import pixelCombat.model.Dust;
import pixelCombat.view.Animation;

public class Pound360Charge extends Dust {
	
	
	public Pound360Charge(Vector2d pos, boolean faceRight) {
		super(pos,faceRight);
		ArrayList<Image> images = new ArrayList<Image>();
	
		images.add(loadImage("/dusts/IMG_Dust_360Pound_Charge_1.png"));
		images.add(loadImage("/dusts/IMG_Dust_360Pound_Charge_2.png"));
		images.add(loadImage("/dusts/IMG_Dust_360Pound_Charge_3.png"));
	
		

		
		ArrayList<Float> times = new ArrayList<Float>();

		times.add(0.085f);
		times.add(0.085f);
		times.add(0.085f);
		
		
		this.dustAnimator = new Animation(images,times,false);

	}

}
