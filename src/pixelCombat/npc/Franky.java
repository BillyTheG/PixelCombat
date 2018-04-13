package pixelCombat.npc;

import java.util.ArrayList;

import javafx.scene.image.Image;
import pixelCombat.Math.Vector2d;
import pixelCombat.view.Animation;

public class Franky extends NPC {

	
	
	public Franky(Vector2d pos, boolean faceRight) {
		super(pos);
		ArrayList<Image> images = new ArrayList<Image>();
		
		images.add(loadImage("/npc/IMG_NPC_Franky_1.png"));
		images.add(loadImage("/npc/IMG_NPC_Franky_2.png"));
		images.add(loadImage("/npc/IMG_NPC_Franky_3.png"));
		images.add(loadImage("/npc/IMG_NPC_Franky_4.png"));
		
		
		ArrayList<Float> times = new ArrayList<Float>();
		times.add(0.15f);
		times.add(0.15f);
		times.add(0.15f);
		times.add(0.15f);
		
		this.animator = new Animation(images,times,false);

	}

}
