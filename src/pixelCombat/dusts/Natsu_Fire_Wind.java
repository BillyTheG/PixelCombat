package pixelCombat.dusts;

import java.util.ArrayList;

import javafx.scene.image.Image;
import pixelCombat.Math.Vector2d;
import pixelCombat.model.Dust;
import pixelCombat.view.animation.Animation;

public class Natsu_Fire_Wind extends Dust {

	
	
	public Natsu_Fire_Wind(Vector2d pos, boolean faceRight) {
		super(pos,faceRight);
		ArrayList<Image> images = new ArrayList<Image>();
			
			images.add(loadImage("/dusts/natsu_basicAttack23_fire_1_re.png"));
			images.add(loadImage("/dusts/natsu_basicAttack23_fire_2_re.png"));
			images.add(loadImage("/dusts/natsu_basicAttack23_fire_3_re.png"));
			images.add(loadImage("/dusts/natsu_basicAttack23_fire_4_re.png"));
			images.add(loadImage("/dusts/natsu_basicAttack23_fire_5_re.png"));
			
			images.add(loadImage("/dusts/natsu_basicAttack23_fire_6_re.png"));
			images.add(loadImage("/dusts/natsu_basicAttack23_fire_7_re.png"));
			images.add(loadImage("/dusts/natsu_basicAttack23_fire_8_re.png"));
			images.add(loadImage("/dusts/natsu_basicAttack23_fire_9_re.png"));
			images.add(loadImage("/dusts/natsu_basicAttack23_fire_10_re.png"));

		
		
		ArrayList<Float> times = new ArrayList<Float>();
		times.add(0.05f);
		times.add(0.05f);
		times.add(0.05f);
		times.add(0.05f);
		times.add(0.05f);
		
		times.add(0.05f);
		times.add(0.05f);
		times.add(0.05f);
		times.add(0.05f);
		times.add(0.05f);
		
		
		
		this.dustAnimator = new Animation(images,times,true);

	}

}
