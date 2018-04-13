package pixelCombat.auras;

import java.util.ArrayList;

import javafx.scene.image.Image;
import pixelCombat.Math.Vector2d;
import pixelCombat.model.Dust;
import pixelCombat.view.Animation;

public class Natsu_Aura extends Dust {

	
	
	public Natsu_Aura(Vector2d pos, boolean faceRight) {
		super(pos,faceRight);
		ArrayList<Image> images = new ArrayList<Image>();
	
		images.add(loadImage("/auras/fire_aura_1.png"));
		images.add(loadImage("/auras/fire_aura_2.png"));
		images.add(loadImage("/auras/fire_aura_3.png"));
		images.add(loadImage("/auras/fire_aura_4.png"));
		images.add(loadImage("/auras/fire_aura_5.png"));
		
		images.add(loadImage("/auras/fire_aura_6.png"));
		images.add(loadImage("/auras/fire_aura_7.png"));
		images.add(loadImage("/auras/fire_aura_8.png"));
		images.add(loadImage("/auras/fire_aura_9.png"));
		images.add(loadImage("/auras/fire_aura_10.png"));
		
		images.add(loadImage("/auras/fire_aura_11.png"));
		images.add(loadImage("/auras/fire_aura_12.png"));
		images.add(loadImage("/auras/fire_aura_13.png"));
		images.add(loadImage("/auras/fire_aura_14.png"));
		images.add(loadImage("/auras/fire_aura_15.png"));
		
		images.add(loadImage("/auras/fire_aura_16.png"));
		images.add(loadImage("/auras/fire_aura_17.png"));
		images.add(loadImage("/auras/fire_aura_18.png"));
		images.add(loadImage("/auras/fire_aura_19.png"));
		images.add(loadImage("/auras/fire_aura_20.png"));
		
		images.add(loadImage("/auras/fire_aura_21.png"));
		
		
		ArrayList<Float> times = new ArrayList<Float>();
		
		times.add(0.085f);
		times.add(0.085f);
		times.add(0.085f);
		times.add(0.085f);
		times.add(0.075f);
		times.add(0.075f);
		times.add(0.075f);
		times.add(0.075f);
		times.add(0.075f);
		times.add(0.075f);
		
		times.add(0.075f);
		times.add(0.075f);
		times.add(0.095f);
		times.add(0.095f);
		times.add(0.075f);
		times.add(0.075f);
		times.add(0.075f);
		times.add(0.095f);
		times.add(0.095f);
		times.add(0.095f);
		
		times.add(0.075f);

		
		
		this.dustAnimator = new Animation(images,times,true);

	}

}
