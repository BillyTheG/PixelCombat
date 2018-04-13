package pixelCombat.dusts;

import java.util.ArrayList;

import javafx.scene.image.Image;
import pixelCombat.Math.Vector2d;
import pixelCombat.model.Dust;
import pixelCombat.view.Animation;

public class SpecialAttackSignalEffect extends Dust {

	
	
	public SpecialAttackSignalEffect(Vector2d pos, boolean faceRight) {
		super(pos, faceRight);
		ArrayList<Image> images = new ArrayList<Image>();

			images.add(loadImage("/dusts/IMG_Dust_Signal_Effect_1.png"));
			images.add(loadImage("/dusts/IMG_Dust_Signal_Effect_2.png"));
			images.add(loadImage("/dusts/IMG_Dust_Signal_Effect_3.png"));
			images.add(loadImage("/dusts/IMG_Dust_Signal_Effect_4.png"));
			images.add(loadImage("/dusts/IMG_Dust_Signal_Effect_5.png"));
			
			images.add(loadImage("/dusts/IMG_Dust_Signal_Effect_6.png"));
			
		
		
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
