package pixelCombat.auras;

import java.util.ArrayList;

import javafx.scene.image.Image;
import pixelCombat.Math.Vector2d;
import pixelCombat.model.Dust;
import pixelCombat.view.Animation;

public class PumpItAura extends Dust {

	public PumpItAura(Vector2d pos, boolean faceRight) {
		super(pos,faceRight);
		ArrayList<Image> images = new ArrayList<Image>();
	
		images.add(loadImage("/auras/natsu_aura_pump_1.png"));
		images.add(loadImage("/auras/natsu_aura_pump_2.png"));
		images.add(loadImage("/auras/natsu_aura_pump_3.png"));
		images.add(loadImage("/auras/natsu_aura_pump_4.png"));
		images.add(loadImage("/auras/natsu_aura_pump_5.png"));
		
		images.add(loadImage("/auras/natsu_aura_pump_6.png"));
		images.add(loadImage("/auras/natsu_aura_pump_7.png"));
		images.add(loadImage("/auras/natsu_aura_pump_8.png"));
		images.add(loadImage("/auras/natsu_aura_pump_9.png"));

		
		
		ArrayList<Float> times = new ArrayList<Float>();
		
		times.add(0.125f);
		times.add(0.085f);
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
