package pixelCombat.dusts;

import java.util.ArrayList;

import javafx.scene.image.Image;
import pixelCombat.Math.Vector2d;
import pixelCombat.model.Dust;
import pixelCombat.view.Animation;

public class RashomonDust extends Dust{

	
	public RashomonDust(Vector2d pos, boolean faceRight) {
		super(pos,faceRight);
		ArrayList<Image> images = new ArrayList<Image>();
		
		images.add(loadImage("/dusts/IMG_Dust_Rashomon_1.png"));
		images.add(loadImage("/dusts/IMG_Dust_Rashomon_2.png"));
		images.add(loadImage("/dusts/IMG_Dust_Rashomon_3.png"));
		images.add(loadImage("/dusts/IMG_Dust_Rashomon_4.png"));
		images.add(loadImage("/dusts/IMG_Dust_Rashomon_5.png"));
		
		images.add(loadImage("/dusts/IMG_Dust_Rashomon_6.png"));
		images.add(loadImage("/dusts/IMG_Dust_Rashomon_7.png"));
		images.add(loadImage("/dusts/IMG_Dust_Rashomon_8.png"));
		images.add(loadImage("/dusts/IMG_Dust_Rashomon_9.png"));


		
		ArrayList<Float> times = new ArrayList<Float>();
		
		times.add(0.095f);
		times.add(0.085f);
		times.add(0.075f);
		times.add(0.025f);
		times.add(0.025f);
		
		times.add(0.025f);
		times.add(0.025f);
		times.add(0.025f);
		times.add(0.025f);
		
		this.dustAnimator = new Animation(images,times,true);
	}
}
