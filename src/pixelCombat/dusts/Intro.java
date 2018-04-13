package pixelCombat.dusts;

import java.util.ArrayList;

import javafx.scene.image.Image;
import pixelCombat.Math.Vector2d;
import pixelCombat.model.Dust;
import pixelCombat.view.Animation;

public class Intro extends Dust {

	
	
	public Intro(Vector2d pos) {
		super(pos,true);
		ArrayList<Image> images = new ArrayList<Image>();
		
		images.add(loadImage("/dusts/Intro_1.png"));
		images.add(loadImage("/dusts/Intro_2.png"));
		images.add(loadImage("/dusts/Intro_3.png"));
		images.add(loadImage("/dusts/Intro_4.png"));
		images.add(loadImage("/dusts/Intro_5.png"));
		
		images.add(loadImage("/dusts/Intro_6.png"));
		images.add(loadImage("/dusts/Intro_7.png"));
		images.add(loadImage("/dusts/Intro_8.png"));
		images.add(loadImage("/dusts/Intro_9.png"));
		images.add(loadImage("/dusts/Intro_10.png"));

		images.add(loadImage("/dusts/Intro_11.png"));
		images.add(loadImage("/dusts/Intro_12.png"));
		images.add(loadImage("/dusts/Intro_13.png"));
		images.add(loadImage("/dusts/Intro_14.png"));
		images.add(loadImage("/dusts/Intro_15.png"));
		
		images.add(loadImage("/dusts/Intro_16.png"));
		images.add(loadImage("/dusts/Intro_17.png"));
		images.add(loadImage("/dusts/Intro_18.png"));
		images.add(loadImage("/dusts/Intro_19.png"));
		images.add(loadImage("/dusts/Intro_20.png"));
		
		images.add(loadImage("/dusts/Intro_21.png"));
		images.add(loadImage("/dusts/Intro_22.png"));
		images.add(loadImage("/dusts/Intro_23.png"));
		images.add(loadImage("/dusts/Intro_24.png"));
		images.add(loadImage("/dusts/Intro_25.png"));
		
		images.add(loadImage("/dusts/Intro_26.png"));
		images.add(loadImage("/dusts/Intro_27.png"));
		
		ArrayList<Float> times = new ArrayList<Float>();
		times.add(0.25f);
		times.add(0.25f);
		times.add(0.25f);
		times.add(0.25f);
		times.add(0.25f);
		
		times.add(0.45f);
		times.add(0.45f);
		times.add(0.45f);
		
		
		times.add(.75f);
		times.add(0.02f);
		times.add(0.02f);
		
		times.add(0.02f);
		times.add(0.02f);
		times.add(0.02f);
		times.add(0.02f);
		times.add(0.02f);
		times.add(0.02f);
		
		times.add(0.02f);
		times.add(0.02f);
		times.add(0.02f);
		times.add(0.02f);
		times.add(0.02f);
		times.add(0.02f);
		
		times.add(0.02f);
		times.add(0.02f);
		times.add(0.02f);
		times.add(0.02f);
		times.add(0.02f);
		
		times.add(0.02f);
		times.add(0.02f);
		
		this.dustAnimator = new Animation(images,times,true,10);

	}

}
