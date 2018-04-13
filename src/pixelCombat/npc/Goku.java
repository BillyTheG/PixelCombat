package pixelCombat.npc;

import java.util.ArrayList;

import javafx.scene.image.Image;
import pixelCombat.Math.Vector2d;
import pixelCombat.view.Animation;

public class Goku extends NPC {

	
	
	public Goku(Vector2d pos, boolean faceRight) {
		super(pos);
		ArrayList<Image> images = new ArrayList<Image>();
		
		images.add(loadImage("/npc/IMG_NPC_Goku_1.png"));
		images.add(loadImage("/npc/IMG_NPC_Goku_2.png"));
		images.add(loadImage("/npc/IMG_NPC_Goku_3.png"));
		images.add(loadImage("/npc/IMG_NPC_Goku_4.png"));

		
		ArrayList<Float> times = new ArrayList<Float>();
		times.add(0.155f);
		times.add(0.155f);
		times.add(0.155f);
		times.add(0.155f);
		this.animator = new Animation(images,times,false);

	}

}
