package pixelCombat.npc;

import java.util.ArrayList;

import javafx.scene.image.Image;
import pixelCombat.Math.Vector2d;
import pixelCombat.view.animation.Animation;

public class Happy extends NPC {

	
	
	public Happy(Vector2d pos, boolean faceRight) {
		super(pos);
		ArrayList<Image> images = new ArrayList<Image>();
		
		images.add(loadImage("/npc/IMG_NPC_Happy_1.png"));
		images.add(loadImage("/npc/IMG_NPC_Happy_2.png"));
		images.add(loadImage("/npc/IMG_NPC_Happy_3.png"));
		images.add(loadImage("/npc/IMG_NPC_Happy_4.png"));
		images.add(loadImage("/npc/IMG_NPC_Happy_5.png"));
		images.add(loadImage("/npc/IMG_NPC_Happy_6.png"));
		
		
		ArrayList<Float> times = new ArrayList<Float>();
		times.add(0.15f);
		times.add(0.15f);
		times.add(0.15f);
		times.add(0.15f);
		times.add(0.15f);
		times.add(0.15f);
		
		this.animator = new Animation(images,times,false);

	}

}
