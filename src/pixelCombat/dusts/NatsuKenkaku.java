package pixelCombat.dusts;

import java.util.ArrayList;

import javafx.scene.image.Image;
import pixelCombat.Math.Vector2d;
import pixelCombat.model.Dust;
import pixelCombat.view.Animation;

public class NatsuKenkaku extends Dust {

	
	
	public NatsuKenkaku(Vector2d pos, boolean faceRight) {
		super(pos, faceRight);
		ArrayList<Image> images = new ArrayList<Image>();

			images.add(loadImage("/dusts/IMG_Dust_Kenkaku_1.png"));
			images.add(loadImage("/dusts/IMG_Dust_Kenkaku_2.png"));
			images.add(loadImage("/dusts/IMG_Dust_Kenkaku_3.png"));
			images.add(loadImage("/dusts/IMG_Dust_Kenkaku_4.png"));
			images.add(loadImage("/dusts/IMG_Dust_Kenkaku_5.png"));
		
			images.add(loadImage("/dusts/IMG_Dust_Kenkaku_6.png"));
			
		
		ArrayList<Float> times = new ArrayList<Float>();
		times.add(0.025f);
		times.add(0.025f);
		times.add(0.025f);
		times.add(0.025f);
		times.add(0.025f);
		
		times.add(0.025f);
		

		
		this.dustAnimator = new Animation(images,times,false);

	}

}
