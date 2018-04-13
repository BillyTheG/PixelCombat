package pixelCombat.npc;

import java.util.ArrayList;

import javafx.scene.image.Image;
import pixelCombat.Math.Vector2d;
import pixelCombat.view.Animation;

public class Natsu extends NPC {

	
	
	public Natsu(Vector2d pos, boolean faceRight) {
		super(pos);
		ArrayList<Image> images = new ArrayList<Image>();
		
		images.add(loadImage("/npc/IMG_NPC_Natsu_1.png"));
		images.add(loadImage("/npc/IMG_NPC_Natsu_2.png"));
		images.add(loadImage("/npc/IMG_NPC_Natsu_3.png"));
		images.add(loadImage("/npc/IMG_NPC_Natsu_4.png"));
		images.add(loadImage("/npc/IMG_NPC_Natsu_5.png"));


		
		ArrayList<Float> times = new ArrayList<Float>();
		times.add(0.155f);
		times.add(0.155f);
		times.add(0.155f);
		times.add(0.155f);
		times.add(0.155f);
		
		this.animator = new Animation(images,times,false);

	}

}
