package pixelCombat.dusts;

import java.util.ArrayList;

import javafx.scene.image.Image;
import pixelCombat.Math.Vector2d;
import pixelCombat.model.Dust;
import pixelCombat.view.Animation;

public class UlToraGariWind extends Dust {

	
	
	public UlToraGariWind(Vector2d pos, boolean faceRight) {
		super(pos,faceRight);
		
		ArrayList<Image> images = new ArrayList<Image>();
		
		images.add(loadImage("/dusts/IMG_Dust_Tora_Gari_Wind_1.png"));
		images.add(loadImage("/dusts/IMG_Dust_Tora_Gari_Wind_2.png"));
		images.add(loadImage("/dusts/IMG_Dust_Tora_Gari_Wind_3.png"));
		images.add(loadImage("/dusts/IMG_Dust_Tora_Gari_Wind_4.png"));
		images.add(loadImage("/dusts/IMG_Dust_Tora_Gari_Wind_5.png"));
		
		images.add(loadImage("/dusts/IMG_Dust_Tora_Gari_Wind_6.png"));
		images.add(loadImage("/dusts/IMG_Dust_Tora_Gari_Wind_7.png"));
		images.add(loadImage("/dusts/IMG_Dust_Tora_Gari_Wind_8.png"));
		images.add(loadImage("/dusts/IMG_Dust_Tora_Gari_Wind_9.png"));
		images.add(loadImage("/dusts/IMG_Dust_Tora_Gari_Wind_10.png"));
		
		images.add(loadImage("/dusts/IMG_Dust_Tora_Gari_Wind_11.png"));
		images.add(loadImage("/dusts/IMG_Dust_Tora_Gari_Wind_12.png"));
		images.add(loadImage("/dusts/IMG_Dust_Tora_Gari_Wind_13.png"));
		images.add(loadImage("/dusts/IMG_Dust_Tora_Gari_Wind_14.png"));
		images.add(loadImage("/dusts/IMG_Dust_Tora_Gari_Wind_15.png"));
		
		ArrayList<Float> times = new ArrayList<Float>();
		
		times.add(0.075f);
		times.add(0.075f);
		times.add(0.075f);
		times.add(0.075f);
		times.add(0.075f);
		
		times.add(0.075f);
		times.add(0.075f);
		times.add(0.075f);
		times.add(0.075f);
		times.add(0.075f);
		
		times.add(0.075f);
		times.add(0.075f);
		times.add(0.075f);
		times.add(0.075f);
		times.add(0.075f);
		
		this.dustAnimator = new Animation(images,times,true);

	}

}
