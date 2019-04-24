package pixelCombat.dusts;

import java.util.ArrayList;

import javafx.scene.image.Image;
import pixelCombat.Math.Vector2d;
import pixelCombat.model.Dust;
import pixelCombat.view.animation.Animation;

public class ShiShinShinSonSmoke extends Dust {

	
	
	public ShiShinShinSonSmoke(Vector2d pos, boolean faceRight) {
		super(pos,faceRight);
		ArrayList<Image> images = new ArrayList<Image>();
	
		images.add(loadImage("/dusts/IMG_Dust_Shin_Son_Son_Smoke_001.png"));
		images.add(loadImage("/dusts/IMG_Dust_Shin_Son_Son_Smoke_002.png"));
		images.add(loadImage("/dusts/IMG_Dust_Shin_Son_Son_Smoke_003.png"));
		images.add(loadImage("/dusts/IMG_Dust_Shin_Son_Son_Smoke_004.png"));
		images.add(loadImage("/dusts/IMG_Dust_Shin_Son_Son_Smoke_005.png"));
		
		images.add(loadImage("/dusts/IMG_Dust_Shin_Son_Son_Smoke_006.png"));
		images.add(loadImage("/dusts/IMG_Dust_Shin_Son_Son_Smoke_007.png"));
		images.add(loadImage("/dusts/IMG_Dust_Shin_Son_Son_Smoke_008.png"));
		images.add(loadImage("/dusts/IMG_Dust_Shin_Son_Son_Smoke_009.png"));
		images.add(loadImage("/dusts/IMG_Dust_Shin_Son_Son_Smoke_010.png"));
		
		images.add(loadImage("/dusts/IMG_Dust_Shin_Son_Son_Smoke_011.png"));
		images.add(loadImage("/dusts/IMG_Dust_Shin_Son_Son_Smoke_012.png"));
		
		
		ArrayList<Float> times = new ArrayList<Float>();

		times.add(0.025f);
		times.add(0.025f);
		times.add(0.025f);
		times.add(0.025f);
		times.add(0.025f);
		
		times.add(0.025f);
		times.add(0.025f);
		times.add(0.025f);
		times.add(0.025f);
		times.add(0.025f);
		
		times.add(0.025f);
		times.add(0.025f);
		
		
		this.dustAnimator = new Animation(images,times,true);

	}

}
