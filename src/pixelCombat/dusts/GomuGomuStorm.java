package pixelCombat.dusts;

import java.util.ArrayList;

import javafx.scene.image.Image;
import pixelCombat.Math.Vector2d;
import pixelCombat.model.Dust;
import pixelCombat.view.Animation;

public class GomuGomuStorm extends Dust {

	
	
	public GomuGomuStorm(Vector2d pos, boolean faceRight) {
		super(pos,faceRight);
		ArrayList<Image> images = new ArrayList<Image>();

			images.add(loadImage("/dusts/IMG_Dust_Ruffy_Air_Special_1_1.png"));
			images.add(loadImage("/dusts/IMG_Dust_Ruffy_Air_Special_1_2.png"));
			images.add(loadImage("/dusts/IMG_Dust_Ruffy_Air_Special_1_3.png"));
			images.add(loadImage("/dusts/IMG_Dust_Ruffy_Air_Special_1_4.png"));
			
	
		
		ArrayList<Float> times = new ArrayList<Float>();
		times.add(0.035f);
		times.add(0.025f);
		times.add(0.035f);
		times.add(0.025f);
		this.dustAnimator = new Animation(images,times,false);

	}

}
