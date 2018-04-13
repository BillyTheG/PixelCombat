package pixelCombat.dusts;

import java.util.ArrayList;

import javafx.scene.image.Image;
import pixelCombat.Math.Vector2d;
import pixelCombat.model.Dust;
import pixelCombat.view.Animation;

public class TatsumakiWindAura extends Dust {

	
	
	public TatsumakiWindAura(Vector2d pos, boolean faceRight) {
		super(pos,faceRight);
		
		ArrayList<Image> images = new ArrayList<>();
		
		images.add(loadImage("/dusts/IMG_Dust_Tatsumaki_Wind_Aura_1.png"));
		images.add(loadImage("/dusts/IMG_Dust_Tatsumaki_Wind_Aura_2.png"));
		images.add(loadImage("/dusts/IMG_Dust_Tatsumaki_Wind_Aura_3.png"));
		images.add(loadImage("/dusts/IMG_Dust_Tatsumaki_Wind_Aura_4.png"));
		images.add(loadImage("/dusts/IMG_Dust_Tatsumaki_Wind_Aura_5.png"));
		
		images.add(loadImage("/dusts/IMG_Dust_Tatsumaki_Wind_Aura_6.png"));
		images.add(loadImage("/dusts/IMG_Dust_Tatsumaki_Wind_Aura_7.png"));
		images.add(loadImage("/dusts/IMG_Dust_Tatsumaki_Wind_Aura_8.png"));
		images.add(loadImage("/dusts/IMG_Dust_Tatsumaki_Wind_Aura_9.png"));
		images.add(loadImage("/dusts/IMG_Dust_Tatsumaki_Wind_Aura_10.png"));
		
		images.add(loadImage("/dusts/IMG_Dust_Tatsumaki_Wind_Aura_11.png"));
		images.add(loadImage("/dusts/IMG_Dust_Tatsumaki_Wind_Aura_12.png"));
		images.add(loadImage("/dusts/IMG_Dust_Tatsumaki_Wind_Aura_13.png"));

		
		ArrayList<Float> times = new ArrayList<>();
		
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
