package pixelCombat.dusts;

import java.util.ArrayList;

import javafx.scene.image.Image;
import pixelCombat.Math.Vector2d;
import pixelCombat.model.Dust;
import pixelCombat.view.Animation;

public class Lightning extends Dust {

	
	
	public Lightning(Vector2d pos, boolean faceRight) {
		super(pos,faceRight);
		
		ArrayList<Image> images = new ArrayList<Image>();
		
		images.add(loadImage("/dusts/lightning_1.png"));
		images.add(loadImage("/dusts/lightning_2.png"));
		images.add(loadImage("/dusts/lightning_3.png"));
		images.add(loadImage("/dusts/lightning_4.png"));
		images.add(loadImage("/dusts/lightning_5.png"));
		
		images.add(loadImage("/dusts/lightning_6.png"));
	
		
		ArrayList<Float> times = new ArrayList<Float>();
		times.add(0.05f);
		times.add(0.05f);
		times.add(0.05f);
		times.add(0.05f);
		times.add(0.05f);
		
		times.add(0.05f);
		
		
		this.dustAnimator = new Animation(images,times,true);

	}

}
