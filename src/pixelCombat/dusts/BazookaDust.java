package pixelCombat.dusts;

import java.util.ArrayList;

import javafx.scene.image.Image;
import pixelCombat.Math.Vector2d;
import pixelCombat.model.Dust;
import pixelCombat.view.Animation;

public class BazookaDust extends Dust {

	
	
	public BazookaDust(Vector2d pos, boolean faceRight) {
		super(pos, faceRight);
		ArrayList<Image> images = new ArrayList<Image>();

			images.add(loadImage("/dusts/IMG_Dust_Bazooka_01.png"));
			images.add(loadImage("/dusts/IMG_Dust_Bazooka_02.png"));
			images.add(loadImage("/dusts/IMG_Dust_Bazooka_04.png"));
			images.add(loadImage("/dusts/IMG_Dust_Bazooka_05.png"));
			images.add(loadImage("/dusts/IMG_Dust_Bazooka_06.png"));
			images.add(loadImage("/dusts/IMG_Dust_Bazooka_07.png"));
			images.add(loadImage("/dusts/IMG_Dust_Bazooka_08.png"));
			images.add(loadImage("/dusts/IMG_Dust_Bazooka_09.png"));
			images.add(loadImage("/dusts/IMG_Dust_Bazooka_10.png"));
			
			
			images.add(loadImage("/dusts/IMG_Dust_Bazooka_11.png"));
			images.add(loadImage("/dusts/IMG_Dust_Bazooka_12.png"));
			images.add(loadImage("/dusts/IMG_Dust_Bazooka_13.png"));
			images.add(loadImage("/dusts/IMG_Dust_Bazooka_14.png"));
			images.add(loadImage("/dusts/IMG_Dust_Bazooka_15.png"));
			images.add(loadImage("/dusts/IMG_Dust_Bazooka_16.png"));
			images.add(loadImage("/dusts/IMG_Dust_Bazooka_17.png"));
			images.add(loadImage("/dusts/IMG_Dust_Bazooka_18.png"));
			images.add(loadImage("/dusts/IMG_Dust_Bazooka_19.png"));
			images.add(loadImage("/dusts/IMG_Dust_Bazooka_20.png"));
		
		ArrayList<Float> times = new ArrayList<Float>();
		times.add(0.035f);
		times.add(0.035f);
		times.add(0.035f);
		times.add(0.035f);
		
		times.add(0.035f);
		times.add(0.035f);
		times.add(0.035f);
		times.add(0.035f);
		times.add(0.035f);
		
		times.add(0.035f);
		times.add(0.035f);
		times.add(0.035f);
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
