package pixelCombat.dusts;

import java.util.ArrayList;

import javafx.scene.image.Image;
import pixelCombat.Math.Vector2d;
import pixelCombat.model.Dust;
import pixelCombat.view.animation.Animation;

public class FireSpark extends Dust {

	
	
	public FireSpark(Vector2d pos, boolean faceRight) {
		super(pos,faceRight);
		ArrayList<Image> images = new ArrayList<Image>();
		
		images.add(loadImage("/dusts/fire_spark_1.png"));
		images.add(loadImage("/dusts/fire_spark_2.png"));
		images.add(loadImage("/dusts/fire_spark_3.png"));
		images.add(loadImage("/dusts/fire_spark_4.png"));
		images.add(loadImage("/dusts/fire_spark_5.png"));
		
		images.add(loadImage("/dusts/fire_spark_6.png"));
		images.add(loadImage("/dusts/fire_spark_7.png"));
		images.add(loadImage("/dusts/fire_spark_8.png"));
		images.add(loadImage("/dusts/fire_spark_9.png"));
		images.add(loadImage("/dusts/fire_spark_10.png"));
		
		images.add(loadImage("/dusts/fire_spark_11.png"));
		images.add(loadImage("/dusts/fire_spark_12.png"));
		images.add(loadImage("/dusts/fire_spark_13.png"));
		images.add(loadImage("/dusts/fire_spark_14.png"));
		
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
		
		times.add(0.05f);
		times.add(0.05f);
		times.add(0.05f);
		times.add(0.05f);
		
		this.dustAnimator = new Animation(images,times,true);

	}

}
