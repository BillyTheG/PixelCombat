package pixelCombat.dusts;

import java.util.ArrayList;

import javafx.scene.image.Image;
import pixelCombat.Math.Vector2d;
import pixelCombat.model.Dust;
import pixelCombat.view.Animation;

public class JetPistole extends Dust {

	
	
	public JetPistole(Vector2d pos, boolean faceRight) {
		super(pos,faceRight);
		ArrayList<Image> images = new ArrayList<Image>();
		
		images.add(loadImage("/dusts/IMG_HitSpark_JetPistol_1.png"));
		images.add(loadImage("/dusts/IMG_HitSpark_JetPistol_2.png"));
		images.add(loadImage("/dusts/IMG_HitSpark_JetPistol_3.png"));
		images.add(loadImage("/dusts/IMG_HitSpark_JetPistol_4.png"));
		images.add(loadImage("/dusts/IMG_HitSpark_JetPistol_5.png"));
		images.add(loadImage("/dusts/IMG_HitSpark_JetPistol_6.png"));
		images.add(loadImage("/dusts/IMG_HitSpark_JetPistol_7.png"));
		images.add(loadImage("/dusts/IMG_HitSpark_JetPistol_8.png"));
		images.add(loadImage("/dusts/IMG_HitSpark_JetPistol_9.png"));
		images.add(loadImage("/dusts/IMG_HitSpark_JetPistol_10.png"));
		images.add(loadImage("/dusts/IMG_HitSpark_JetPistol_11.png"));


		
		ArrayList<Float> times = new ArrayList<Float>();
		times.add(0.03f);
		times.add(0.03f);
		times.add(0.03f);
		times.add(0.02f);
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
