package pixelCombat.auras;

import java.util.ArrayList;

import javafx.scene.image.Image;
import pixelCombat.Math.Vector2d;
import pixelCombat.model.Dust;
import pixelCombat.view.Animation;

public class Natsu_LightningII extends Dust {

	
	
	public Natsu_LightningII(Vector2d pos, boolean faceRight) {
		super(pos,faceRight);
		ArrayList<Image> images = new ArrayList<Image>();
	
		images.add(loadImage("/auras/lightning_rl_1.png"));
		images.add(loadImage("/auras/lightning_rl_2.png"));
		images.add(loadImage("/auras/lightning_rl_3.png"));
		images.add(loadImage("/auras/lightning_rl_4.png"));
		images.add(loadImage("/auras/lightning_rl_5.png"));
		
		images.add(loadImage("/auras/lightning_rl_6.png"));

		
		
		images.add(loadImage("/auras/lightning_ru_1.png"));
		images.add(loadImage("/auras/lightning_ru_2.png"));
		images.add(loadImage("/auras/lightning_ru_3.png"));
		images.add(loadImage("/auras/lightning_ru_4.png"));
		images.add(loadImage("/auras/lightning_ru_5.png"));
		
		images.add(loadImage("/auras/lightning_ru_6.png"));
		
		
		
		ArrayList<Float> times = new ArrayList<Float>();
		
		times.add(0.035f);
		times.add(0.035f);
		times.add(0.035f);
		times.add(0.035f);
		times.add(0.035f);
		
		times.add(0.035f);
		
		times.add(0.035f);
		times.add(0.035f);
		times.add(0.035f);
		times.add(0.035f);
		times.add(0.035f);
		
		times.add(0.035f);
		
		this.dustAnimator = new Animation(images,times,true);

	}

}
