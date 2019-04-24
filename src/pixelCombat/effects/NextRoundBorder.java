package pixelCombat.effects;

import java.util.ArrayList;

import javafx.scene.image.Image;
import pixelCombat.view.animation.Animation;

public class NextRoundBorder extends SpecialBorderAnimation {

	public NextRoundBorder() {
		ArrayList<Image> images_intro = new ArrayList<Image>();
		ArrayList<Image> images_outro = new ArrayList<Image>();
		
		Image img_1 = loadImage("/effects/special_border_effect_0.png");
		Image img_2 = loadImage("/effects/special_border_effect_1.png");
		Image img_3 = loadImage("/effects/special_border_effect_2.png");
		Image img_4 = loadImage("/effects/special_border_effect_3.png");
		Image img_5 = loadImage("/effects/special_border_effect_4.png");
		Image img_6 = loadImage("/effects/special_border_effect_5.png");
		Image img_7 = loadImage("/effects/special_border_effect_6.png");
		Image img_8 = loadImage("/effects/special_border_effect_7.png");
		
		images_intro.add(img_1);
		images_intro.add(img_2);
		images_intro.add(img_3);
		images_intro.add(img_4);
		images_intro.add(img_5);
		images_intro.add(img_6);
		images_intro.add(img_7);
		images_intro.add(img_8);
		
		images_outro.add(img_8);
		images_outro.add(img_7);
		images_outro.add(img_6);
		images_outro.add(img_5);
		images_outro.add(img_4);
		images_outro.add(img_3);
		images_outro.add(img_2);
		images_outro.add(img_1);
		
		ArrayList<Float> times_intro = new ArrayList<Float>();
		ArrayList<Float> times_outro = new ArrayList<Float>();
		
		times_intro.add(2.375f);
		times_intro.add(0.075f);
		times_intro.add(0.075f);
		times_intro.add(0.075f);
		times_intro.add(0.075f);
		times_intro.add(0.075f);
		times_intro.add(0.075f);
		times_intro.add(0.075f);
		
		times_outro.add(0.375f);
		times_outro.add(0.075f);
		times_outro.add(0.075f);
		times_outro.add(0.075f);
		times_outro.add(0.075f);
		times_outro.add(0.075f);
		times_outro.add(0.075f);
		times_outro.add(0.075f);
		
		this.introAnimator = new Animation(images_intro,times_intro,true);
		this.outroAnimator = new Animation(images_outro,times_outro,true);
		introAnimator.start();
	}

}
