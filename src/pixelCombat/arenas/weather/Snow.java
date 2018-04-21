package pixelCombat.arenas.weather;

import java.util.ArrayList;

import javafx.scene.image.Image;
import pixelCombat.arenas.Weather;
import pixelCombat.view.Animation;
import pixelCombat.view.gamephases.GamePlayView;

public class Snow extends Weather {

	
	public Snow() {
		super();
		ArrayList<Image> images = new ArrayList<Image>();

		images.add(GamePlayView.loadImage("/maps/weather/IMG_Weather_Snow_1.png"));
		images.add(GamePlayView.loadImage("/maps/weather/IMG_Weather_Snow_2.png"));
		images.add(GamePlayView.loadImage("/maps/weather/IMG_Weather_Snow_3.png"));
		images.add(GamePlayView.loadImage("/maps/weather/IMG_Weather_Snow_4.png"));
		images.add(GamePlayView.loadImage("/maps/weather/IMG_Weather_Snow_5.png"));
		
		images.add(GamePlayView.loadImage("/maps/weather/IMG_Weather_Snow_6.png"));
		images.add(GamePlayView.loadImage("/maps/weather/IMG_Weather_Snow_7.png"));
		images.add(GamePlayView.loadImage("/maps/weather/IMG_Weather_Snow_8.png"));
		images.add(GamePlayView.loadImage("/maps/weather/IMG_Weather_Snow_9.png"));
		images.add(GamePlayView.loadImage("/maps/weather/IMG_Weather_Snow_10.png"));
		
		images.add(GamePlayView.loadImage("/maps/weather/IMG_Weather_Snow_11.png"));
		images.add(GamePlayView.loadImage("/maps/weather/IMG_Weather_Snow_12.png"));
		images.add(GamePlayView.loadImage("/maps/weather/IMG_Weather_Snow_13.png"));
		images.add(GamePlayView.loadImage("/maps/weather/IMG_Weather_Snow_14.png"));
		images.add(GamePlayView.loadImage("/maps/weather/IMG_Weather_Snow_15.png"));
		
		images.add(GamePlayView.loadImage("/maps/weather/IMG_Weather_Snow_16.png"));
		images.add(GamePlayView.loadImage("/maps/weather/IMG_Weather_Snow_17.png"));
		images.add(GamePlayView.loadImage("/maps/weather/IMG_Weather_Snow_18.png"));
		images.add(GamePlayView.loadImage("/maps/weather/IMG_Weather_Snow_19.png"));

		

	
		ArrayList<Float> times = new ArrayList<Float>();
		times.add(0.035f);
		times.add(0.025f);
		times.add(0.035f);
		times.add(0.025f);
		times.add(0.035f);
		
		times.add(0.035f);
		times.add(0.025f);
		times.add(0.035f);
		times.add(0.025f);
		times.add(0.035f);
		
		times.add(0.035f);
		times.add(0.025f);
		times.add(0.035f);
		times.add(0.025f);
		times.add(0.035f);
		
		times.add(0.035f);
		times.add(0.025f);
		times.add(0.035f);
		times.add(0.025f);
		
		
		this.weatherAnimator = new Animation(images,times,false);
	}
	
	@Override
	public void updateFurther(float delta) {
		
	}

}
