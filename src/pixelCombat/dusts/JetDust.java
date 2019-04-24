package pixelCombat.dusts;

import java.util.ArrayList;

import javafx.scene.image.Image;
import pixelCombat.Math.Vector2d;
import pixelCombat.model.Dust;
import pixelCombat.view.animation.Animation;

public class JetDust extends Dust {

	
	
	public JetDust(Vector2d pos, boolean faceRight) {
		super(pos,faceRight);
		ArrayList<Image> images = new ArrayList<Image>();
		
		images.add(loadImage("/dusts/IMG_Jet_Pistole_Dust_1.png"));
		images.add(loadImage("/dusts/IMG_Jet_Pistole_Dust_2.png"));
		images.add(loadImage("/dusts/IMG_Jet_Pistole_Dust_3.png"));
		images.add(loadImage("/dusts/IMG_Jet_Pistole_Dust_4.png"));
		images.add(loadImage("/dusts/IMG_Jet_Pistole_Dust_5.png"));	
		images.add(loadImage("/dusts/IMG_Jet_Pistole_Dust_6.png"));
		images.add(loadImage("/dusts/IMG_Jet_Pistole_Dust_7.png"));
		images.add(loadImage("/dusts/IMG_Jet_Pistole_Dust_8.png"));
		images.add(loadImage("/dusts/IMG_Jet_Pistole_Dust_9.png"));
		images.add(loadImage("/dusts/IMG_Jet_Pistole_Dust_10.png"));
		images.add(loadImage("/dusts/IMG_Jet_Pistole_Dust_11.png"));
		
		
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

		
		
		this.dustAnimator = new Animation(images,times,true);

	}

}
