package pixelCombat.npc;

import java.util.ArrayList;

import javafx.scene.image.Image;
import pixelCombat.Math.Vector2d;
import pixelCombat.view.animation.Animation;

public class Usopp extends NPC {

	
	
	public Usopp(Vector2d pos, boolean faceRight) {
		super(pos);
		ArrayList<Image> images = new ArrayList<Image>();
		
		images.add(loadImage("/npc/IMG_NPC_Usopp_1.png"));
		images.add(loadImage("/npc/IMG_NPC_Usopp_2.png"));
		images.add(loadImage("/npc/IMG_NPC_Usopp_3.png"));
		images.add(loadImage("/npc/IMG_NPC_Usopp_4.png"));
		images.add(loadImage("/npc/IMG_NPC_Usopp_5.png"));
		images.add(loadImage("/npc/IMG_NPC_Usopp_6.png"));
		images.add(loadImage("/npc/IMG_NPC_Usopp_7.png"));
		images.add(loadImage("/npc/IMG_NPC_Usopp_8.png"));
		
		ArrayList<Float> times = new ArrayList<Float>();
		times.add(0.155f);
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
