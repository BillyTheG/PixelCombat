package pixelCombat.arenas;

import java.util.ArrayList;

import javafx.scene.image.Image;
import pixelCombat.arenas.weather.Snow;
import pixelCombat.utils.GameEngine;
import pixelCombat.view.animation.Animation;
import pixelCombat.view.gamephases.GamePlayView;

public class ColdWinter extends PXMap {

	public ColdWinter() {
		super(new Snow());
		
		ArrayList<Image> images = new ArrayList<Image>();

		images.add(GamePlayView.loadImage("/maps/IMG_Map_Cold_Winter_1.png"));
		images.add(GamePlayView.loadImage("/maps/IMG_Map_Cold_Winter_2.png"));
		images.add(GamePlayView.loadImage("/maps/IMG_Map_Cold_Winter_3.png"));
		images.add(GamePlayView.loadImage("/maps/IMG_Map_Cold_Winter_4.png"));
		images.add(GamePlayView.loadImage("/maps/IMG_Map_Cold_Winter_5.png"));
		
		images.add(GamePlayView.loadImage("/maps/IMG_Map_Cold_Winter_6.png"));
		images.add(GamePlayView.loadImage("/maps/IMG_Map_Cold_Winter_7.png"));
		images.add(GamePlayView.loadImage("/maps/IMG_Map_Cold_Winter_8.png"));
		images.add(GamePlayView.loadImage("/maps/IMG_Map_Cold_Winter_9.png"));
		images.add(GamePlayView.loadImage("/maps/IMG_Map_Cold_Winter_10.png"));
		
		images.add(GamePlayView.loadImage("/maps/IMG_Map_Cold_Winter_11.png"));
		images.add(GamePlayView.loadImage("/maps/IMG_Map_Cold_Winter_12.png"));
		images.add(GamePlayView.loadImage("/maps/IMG_Map_Cold_Winter_13.png"));

	
		ArrayList<Float> times = new ArrayList<Float>();
		times.add(0.35f);
		times.add(0.25f);
		times.add(0.35f);
		times.add(0.25f);
		times.add(0.35f);
		
		times.add(0.35f);
		times.add(0.25f);
		times.add(0.35f);
		times.add(0.25f);
		times.add(0.35f);
		
		times.add(0.35f);
		times.add(0.25f);
		times.add(0.35f);

		
		this.bgAnimator = new Animation(images,times,false);
//		this.foreGround = GamePlayView.loadImage("/maps/IMG_Map_ForeGround_Layer_Grass.png");
	}

	
	
	@Override
	public void updateFurther(float delta) {
		
	}



	@Override
	public void playMusic(GameEngine engine) {
		engine.musicMP3("/audio/ColdWinter.mp3");		
	}

}
