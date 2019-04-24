package pixelCombat.auras;

import java.util.ArrayList;

import javafx.scene.image.Image;
import pixelCombat.Math.Vector2d;
import pixelCombat.model.Dust;
import pixelCombat.view.animation.Animation;

public class Natsu_LightningI extends Dust {

	
	
	public Natsu_LightningI(Vector2d pos, boolean faceRight) {
		super(pos,faceRight);
		ArrayList<Image> images = new ArrayList<Image>();
	
		images.add(loadImage("/auras/lightning_lr_1.png"));
		images.add(loadImage("/auras/lightning_lr_2.png"));
		images.add(loadImage("/auras/lightning_lr_3.png"));
		images.add(loadImage("/auras/lightning_lr_4.png"));
		images.add(loadImage("/auras/lightning_lr_5.png"));
		
		images.add(loadImage("/auras/lightning_lr_6.png"));

		
		
		images.add(loadImage("/auras/lightning_lu_1.png"));
		images.add(loadImage("/auras/lightning_lu_2.png"));
		images.add(loadImage("/auras/lightning_lu_3.png"));
		images.add(loadImage("/auras/lightning_lu_4.png"));
		images.add(loadImage("/auras/lightning_lu_5.png"));
		
		images.add(loadImage("/auras/lightning_lu_6.png"));
		
		
		
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
		
		this.dustAnimator = new Animation(images,times,true);

	}

}
