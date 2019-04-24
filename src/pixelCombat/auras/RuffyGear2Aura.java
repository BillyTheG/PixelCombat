package pixelCombat.auras;

import java.util.ArrayList;

import javafx.scene.image.Image;
import pixelCombat.Math.Vector2d;
import pixelCombat.model.Dust;
import pixelCombat.view.animation.Animation;

public class RuffyGear2Aura extends Dust {

	
	
	public RuffyGear2Aura(Vector2d pos, boolean faceRight) {
		super(pos,faceRight);
		ArrayList<Image> images = new ArrayList<Image>();
	
		images.add(loadImage("/auras/IMG_Aura_Gear2_1.png"));
		images.add(loadImage("/auras/IMG_Aura_Gear2_2.png"));
		images.add(loadImage("/auras/IMG_Aura_Gear2_3.png"));
		images.add(loadImage("/auras/IMG_Aura_Gear2_4.png"));
		images.add(loadImage("/auras/IMG_Aura_Gear2_5.png"));
		
		images.add(loadImage("/auras/IMG_Aura_Gear2_6.png"));
		images.add(loadImage("/auras/IMG_Aura_Gear2_7.png"));
		images.add(loadImage("/auras/IMG_Aura_Gear2_8.png"));
		images.add(loadImage("/auras/IMG_Aura_Gear2_9.png"));
		images.add(loadImage("/auras/IMG_Aura_Gear2_10.png"));
		
		images.add(loadImage("/auras/IMG_Aura_Gear2_11.png"));
		images.add(loadImage("/auras/IMG_Aura_Gear2_12.png"));
		images.add(loadImage("/auras/IMG_Aura_Gear2_13.png"));
		images.add(loadImage("/auras/IMG_Aura_Gear2_14.png"));
		images.add(loadImage("/auras/IMG_Aura_Gear2_15.png"));
		
		images.add(loadImage("/auras/IMG_Aura_Gear2_16.png"));
		images.add(loadImage("/auras/IMG_Aura_Gear2_17.png"));
		images.add(loadImage("/auras/IMG_Aura_Gear2_18.png"));
		images.add(loadImage("/auras/IMG_Aura_Gear2_19.png"));
		images.add(loadImage("/auras/IMG_Aura_Gear2_20.png"));
		
		images.add(loadImage("/auras/IMG_Aura_Gear2_21.png"));
		images.add(loadImage("/auras/IMG_Aura_Gear2_22.png"));
		images.add(loadImage("/auras/IMG_Aura_Gear2_23.png"));
		
		
		
		ArrayList<Float> times = new ArrayList<Float>();
		
		times.add(0.025f);
		times.add(0.025f);
		times.add(0.015f);
		times.add(0.025f);
		times.add(0.015f);
		times.add(0.025f);
		times.add(0.025f);
		times.add(0.025f);
		times.add(0.015f);
		times.add(0.025f);
		
		times.add(0.025f);
		times.add(0.015f);
		times.add(0.025f);
		times.add(0.025f);
		times.add(0.025f);
		times.add(0.025f);
		times.add(0.025f);
		times.add(0.015f);
		times.add(0.025f);
		times.add(0.015f);
		
		times.add(0.025f);
		times.add(0.025f);
		times.add(0.025f);
		
		
		
		this.dustAnimator = new Animation(images,times,false);

	}

}
