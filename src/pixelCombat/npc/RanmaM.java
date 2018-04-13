package pixelCombat.npc;

import java.util.ArrayList;

import javafx.scene.image.Image;
import pixelCombat.Math.Vector2d;
import pixelCombat.view.Animation;

public class RanmaM extends NPC {

	
	
	public RanmaM(Vector2d pos, boolean faceRight) {
		super(pos);
		ArrayList<Image> images = new ArrayList<Image>();
		
		images.add(loadImage("/npc/IMG_NPC_RanmaM_1.png"));
		images.add(loadImage("/npc/IMG_NPC_RanmaM_2.png"));
		images.add(loadImage("/npc/IMG_NPC_RanmaM_3.png"));
		images.add(loadImage("/npc/IMG_NPC_RanmaM_4.png"));
		images.add(loadImage("/npc/IMG_NPC_RanmaM_5.png"));
		images.add(loadImage("/npc/IMG_NPC_RanmaM_6.png"));

		
		ArrayList<Float> times = new ArrayList<Float>();
		times.add(0.155f);
		times.add(0.155f);
		times.add(0.155f);
		times.add(0.155f);
		times.add(0.155f);
		times.add(0.155f);
		
		this.animator = new Animation(images,times,false);

	}

}
