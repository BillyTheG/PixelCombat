package pixelCombat.npc;

import java.util.ArrayList;

import javafx.scene.image.Image;
import pixelCombat.Math.Vector2d;
import pixelCombat.view.animation.Animation;

public class Chopper extends NPC {

	
	
	public Chopper(Vector2d pos, boolean faceRight) {
		super(pos);
		ArrayList<Image> images = new ArrayList<Image>();
		
		images.add(loadImage("/npc/IMG_NPC_Chopper_1.png"));
		images.add(loadImage("/npc/IMG_NPC_Chopper_2.png"));
		images.add(loadImage("/npc/IMG_NPC_Chopper_3.png"));
		images.add(loadImage("/npc/IMG_NPC_Chopper_4.png"));
		
		
		ArrayList<Float> times = new ArrayList<Float>();
		times.add(0.075f);
		times.add(0.075f);
		times.add(0.075f);
		times.add(0.075f);
		
		this.animator = new Animation(images,times,false);

	}

}
