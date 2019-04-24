package pixelCombat.dusts;

import java.util.ArrayList;

import javafx.scene.image.Image;
import pixelCombat.Math.Vector2d;
import pixelCombat.model.Dust;
import pixelCombat.view.animation.Animation;

public class JetPistoleBlueHit extends Dust {

	
	
	public JetPistoleBlueHit(Vector2d pos, boolean faceRight) {
		super(pos,faceRight);
		ArrayList<Image> images = new ArrayList<Image>();
		
		images.add(loadImage("/dusts/jetpistole_bluehit_1.png"));
		images.add(loadImage("/dusts/jetpistole_bluehit_2.png"));
		images.add(loadImage("/dusts/jetpistole_bluehit_3.png"));
		images.add(loadImage("/dusts/jetpistole_bluehit_4.png"));
		images.add(loadImage("/dusts/jetpistole_bluehit_5.png"));
		
		images.add(loadImage("/dusts/jetpistole_bluehit_6.png"));
		images.add(loadImage("/dusts/jetpistole_bluehit_7.png"));



		
		ArrayList<Float> times = new ArrayList<Float>();
		
		times.add(0.02f);
		times.add(0.02f);
		times.add(0.02f);
		times.add(0.02f);
		times.add(0.02f);
		
		times.add(0.02f);
		times.add(0.02f);
	

		
		this.dustAnimator = new Animation(images,times,true);

	}

}
