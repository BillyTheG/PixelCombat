package pixelCombat.dusts;

import java.util.ArrayList;

import javafx.scene.image.Image;
import pixelCombat.Math.Vector2d;
import pixelCombat.model.Dust;
import pixelCombat.view.animation.Animation;

public class TatsumakiWindExplosion extends Dust {

	
	
	public TatsumakiWindExplosion(Vector2d pos, boolean faceRight) {
		super(pos,faceRight);
		
		ArrayList<Image> images = new ArrayList<>();
		
		images.add(loadImage("/dusts/IMG_Dust_Tatsumaki_Wind_Explosion_1.png"));
		images.add(loadImage("/dusts/IMG_Dust_Tatsumaki_Wind_Explosion_2.png"));
		images.add(loadImage("/dusts/IMG_Dust_Tatsumaki_Wind_Explosion_3.png"));
		images.add(loadImage("/dusts/IMG_Dust_Tatsumaki_Wind_Explosion_4.png"));
		images.add(loadImage("/dusts/IMG_Dust_Tatsumaki_Wind_Explosion_5.png"));
		
		images.add(loadImage("/dusts/IMG_Dust_Tatsumaki_Wind_Explosion_6.png"));
		images.add(loadImage("/dusts/IMG_Dust_Tatsumaki_Wind_Explosion_7.png"));
		images.add(loadImage("/dusts/IMG_Dust_Tatsumaki_Wind_Explosion_8.png"));
		images.add(loadImage("/dusts/IMG_Dust_Tatsumaki_Wind_Explosion_9.png"));
		images.add(loadImage("/dusts/IMG_Dust_Tatsumaki_Wind_Explosion_10.png"));
		
		images.add(loadImage("/dusts/IMG_Dust_Tatsumaki_Wind_Explosion_11.png"));
		images.add(loadImage("/dusts/IMG_Dust_Tatsumaki_Wind_Explosion_12.png"));
		images.add(loadImage("/dusts/IMG_Dust_Tatsumaki_Wind_Explosion_13.png"));
		images.add(loadImage("/dusts/IMG_Dust_Tatsumaki_Wind_Explosion_14.png"));
		images.add(loadImage("/dusts/IMG_Dust_Tatsumaki_Wind_Explosion_15.png"));
		
		images.add(loadImage("/dusts/IMG_Dust_Tatsumaki_Wind_Explosion_16.png"));
		images.add(loadImage("/dusts/IMG_Dust_Tatsumaki_Wind_Explosion_17.png"));
		images.add(loadImage("/dusts/IMG_Dust_Tatsumaki_Wind_Explosion_18.png"));
		images.add(loadImage("/dusts/IMG_Dust_Tatsumaki_Wind_Explosion_19.png"));
		
		ArrayList<Float> times = new ArrayList<>();
		
		times.add(0.035f);
		times.add(0.035f);
		times.add(0.035f);
		times.add(0.035f);
		times.add(0.035f);
		
		times.add(0.035f);
		times.add(0.085f);
		times.add(0.085f);
		times.add(0.035f);
		times.add(0.035f);
		
		times.add(0.065f);
		times.add(0.065f);
		times.add(0.065f);
		times.add(0.065f);
		times.add(0.085f);
		
		times.add(0.035f);
		times.add(0.035f);
		times.add(0.035f);
		times.add(0.035f);
		
		this.dustAnimator = new Animation(images,times,true);

	}

}
