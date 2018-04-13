package pixelCombat.dusts;

import java.util.ArrayList;

import javafx.scene.image.Image;
import pixelCombat.Math.Vector2d;
import pixelCombat.model.Dust;
import pixelCombat.view.Animation;

public class BazookaBackDust extends Dust {

	public BazookaBackDust(Vector2d pos, boolean faceRight) {
		super(pos, faceRight);
		ArrayList<Image> images = new ArrayList<Image>();

		images.add(loadImage("/dusts/IMG_Dust_Bazooka_Back_01.png"));
		images.add(loadImage("/dusts/IMG_Dust_Bazooka_Back_02.png"));
		images.add(loadImage("/dusts/IMG_Dust_Bazooka_Back_03.png"));

		ArrayList<Float> times = new ArrayList<Float>();
		times.add(0.075f);
		times.add(0.075f);
		times.add(0.075f);

		this.dustAnimator = new Animation(images, times, true);

	}

}
