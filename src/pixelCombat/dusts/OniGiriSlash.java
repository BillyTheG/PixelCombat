package pixelCombat.dusts;

import java.util.ArrayList;

import javafx.scene.image.Image;
import pixelCombat.Math.Vector2d;
import pixelCombat.model.Dust;
import pixelCombat.view.Animation;

public class OniGiriSlash extends Dust {

	
	
	public OniGiriSlash(Vector2d pos, boolean faceRight) {
		super(pos,faceRight);
		ArrayList<Image> images = new ArrayList<Image>();
		
		images.add(loadImage("/dusts/IMG_Dust_OniGiriSlash_1.png"));
		images.add(loadImage("/dusts/IMG_Dust_OniGiriSlash_2.png"));
		images.add(loadImage("/dusts/IMG_Dust_OniGiriSlash_3.png"));
		images.add(loadImage("/dusts/IMG_Dust_OniGiriSlash_4.png"));
		
		
		ArrayList<Float> times = new ArrayList<Float>();
		times.add(0.05f);
		times.add(0.05f);
		times.add(0.05f);
		times.add(0.05f);
		
		
		this.dustAnimator = new Animation(images,times,true);

	}

}
