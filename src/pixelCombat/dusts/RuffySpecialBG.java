package pixelCombat.dusts;

import java.util.ArrayList;

import javafx.scene.image.Image;
import pixelCombat.Math.Vector2d;
import pixelCombat.model.Dust;
import pixelCombat.view.animation.Animation;

public class RuffySpecialBG extends Dust {
	
	
	public RuffySpecialBG(Vector2d pos, boolean faceRight) {
		super(pos,faceRight);
		ArrayList<Image> images = new ArrayList<Image>();
		images.add(loadImage("/special/IMG_Ruffy_Special_1.png"));
		images.add(loadImage("/special/IMG_Ruffy_Special_2.png"));
		images.add(loadImage("/special/IMG_Ruffy_Special_3.png"));
		images.add(loadImage("/special/IMG_Ruffy_Special_4.png"));
		images.add(loadImage("/special/IMG_Ruffy_Special_5.png"));
		images.add(loadImage("/special/IMG_Ruffy_Special_6.png"));
		
		
		ArrayList<Float> times = new ArrayList<Float>();
		times.add(0.03f);
		times.add(0.03f);
		times.add(0.03f);
		times.add(0.03f);
		times.add(0.03f);
		times.add(0.03f);
		
		this.dustAnimator = new Animation(images,times,false);

	}

}
