package pixelCombat.arenas;


import javafx.scene.image.Image;
import pixelCombat.view.Animation;

public abstract class Weather {

	protected Animation weatherAnimator;
	
	public void update(float delta){
		weatherAnimator.update(delta);
		updateFurther(delta);
	}
	
	abstract public void updateFurther(float delta);
	
	
	public Image getCurrentBG(){
		return weatherAnimator.getImage();
	}
	
}
