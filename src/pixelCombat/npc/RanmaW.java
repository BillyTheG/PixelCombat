package pixelCombat.npc;

import java.util.ArrayList;

import javafx.scene.image.Image;
import pixelCombat.Math.Vector2d;
import pixelCombat.view.animation.Animation;

public class RanmaW extends NPC {

	
	
	public RanmaW(Vector2d pos, boolean faceRight) {
		super(pos);
		ArrayList<Image> images = new ArrayList<Image>();
		
		images.add(loadImage("/npc/IMG_NPC_RanmaW_1.png"));
		images.add(loadImage("/npc/IMG_NPC_RanmaW_2.png"));
		images.add(loadImage("/npc/IMG_NPC_RanmaW_3.png"));
		images.add(loadImage("/npc/IMG_NPC_RanmaW_4.png"));
		images.add(loadImage("/npc/IMG_NPC_RanmaW_5.png"));
		images.add(loadImage("/npc/IMG_NPC_RanmaW_6.png"));
		images.add(loadImage("/npc/IMG_NPC_RanmaW_7.png"));
		
		ArrayList<Float> times = new ArrayList<Float>();
		times.add(0.155f);
		times.add(0.155f);
		times.add(0.155f);
		times.add(0.155f);
		times.add(0.155f);
		times.add(0.155f);
		times.add(0.155f);

		
		this.animator = new Animation(images,times,false);

	}

}
