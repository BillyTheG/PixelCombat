package pixelCombat.auras;

import java.util.ArrayList;

import javafx.scene.image.Image;
import pixelCombat.Math.Vector2d;
import pixelCombat.model.Dust;
import pixelCombat.view.animation.Animation;

public class Natsu_Aurabeam extends Dust {

	
	
	public Natsu_Aurabeam(Vector2d pos, boolean faceRight) {
		super(pos,faceRight);
		ArrayList<Image> images = new ArrayList<Image>();
	
		images.add(loadImage("/auras/aura_column_1.png"));
		images.add(loadImage("/auras/aura_column_2.png"));
		images.add(loadImage("/auras/aura_column_3.png"));


		
		ArrayList<Float> times = new ArrayList<Float>();
		
		times.add(0.1225f);
		times.add(0.1125f);
		times.add(0.0525f);
		
		
		
		this.dustAnimator = new Animation(images,times,true);

	}

}
