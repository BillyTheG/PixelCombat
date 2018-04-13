package pixelCombat.auras;

import java.util.ArrayList;

import javafx.scene.image.Image;
import pixelCombat.Math.Vector2d;
import pixelCombat.model.Dust;
import pixelCombat.view.Animation;

public class Ruffy_Aura extends Dust {

	
	
	public Ruffy_Aura(Vector2d pos, boolean faceRight) {
		super(pos,faceRight);
		ArrayList<Image> images = new ArrayList<Image>();
	
		images.add(loadImage("/auras/IMG_Dust_Luffy_Aura_1.png"));
		images.add(loadImage("/auras/IMG_Dust_Luffy_Aura_2.png"));
		images.add(loadImage("/auras/IMG_Dust_Luffy_Aura_3.png"));
		images.add(loadImage("/auras/IMG_Dust_Luffy_Aura_4.png"));
		images.add(loadImage("/auras/IMG_Dust_Luffy_Aura_5.png"));
		images.add(loadImage("/auras/IMG_Dust_Luffy_Aura_6.png"));
		images.add(loadImage("/auras/IMG_Dust_Luffy_Aura_7.png"));
		images.add(loadImage("/auras/IMG_Dust_Luffy_Aura_8.png"));
		images.add(loadImage("/auras/IMG_Dust_Luffy_Aura_9.png"));
		images.add(loadImage("/auras/IMG_Dust_Luffy_Aura_10.png"));
		
		images.add(loadImage("/auras/IMG_Dust_Luffy_Aura_11.png"));
		images.add(loadImage("/auras/IMG_Dust_Luffy_Aura_12.png"));
		images.add(loadImage("/auras/IMG_Dust_Luffy_Aura_13.png"));
		images.add(loadImage("/auras/IMG_Dust_Luffy_Aura_14.png"));
		images.add(loadImage("/auras/IMG_Dust_Luffy_Aura_15.png"));
		images.add(loadImage("/auras/IMG_Dust_Luffy_Aura_16.png"));
		images.add(loadImage("/auras/IMG_Dust_Luffy_Aura_17.png"));
		images.add(loadImage("/auras/IMG_Dust_Luffy_Aura_18.png"));
		images.add(loadImage("/auras/IMG_Dust_Luffy_Aura_19.png"));
		images.add(loadImage("/auras/IMG_Dust_Luffy_Aura_20.png"));
		
		images.add(loadImage("/auras/IMG_Dust_Luffy_Aura_21.png"));
		images.add(loadImage("/auras/IMG_Dust_Luffy_Aura_22.png"));
		images.add(loadImage("/auras/IMG_Dust_Luffy_Aura_23.png"));
		images.add(loadImage("/auras/IMG_Dust_Luffy_Aura_24.png"));
		images.add(loadImage("/auras/IMG_Dust_Luffy_Aura_25.png"));
		images.add(loadImage("/auras/IMG_Dust_Luffy_Aura_26.png"));
		images.add(loadImage("/auras/IMG_Dust_Luffy_Aura_27.png"));
		
		
		ArrayList<Float> times = new ArrayList<Float>();
		
		times.add(0.0125f);
		times.add(0.0125f);
		times.add(0.0125f);
		times.add(0.0125f);
		times.add(0.0125f);
		times.add(0.0125f);
		times.add(0.0125f);
		times.add(0.0125f);
		times.add(0.0125f);
		times.add(0.0125f);
		
		times.add(0.0125f);
		times.add(0.0125f);
		times.add(0.0125f);
		times.add(0.0125f);
		times.add(0.0125f);
		times.add(0.0125f);
		times.add(0.0125f);
		times.add(0.0125f);
		times.add(0.0125f);
		times.add(0.0125f);
		
		times.add(0.0125f);
		times.add(0.0125f);
		times.add(0.0125f);
		times.add(0.0125f);
		times.add(0.0125f);
		times.add(0.0125f);
		times.add(0.0125f);
		
		
		this.dustAnimator = new Animation(images,times,true);

	}

}
