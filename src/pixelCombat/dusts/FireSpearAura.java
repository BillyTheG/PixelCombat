package pixelCombat.dusts;

import java.util.ArrayList;

import javafx.scene.image.Image;
import pixelCombat.Math.Vector2d;
import pixelCombat.model.Dust;
import pixelCombat.view.Animation;

public class FireSpearAura
  extends Dust
{
  public FireSpearAura(Vector2d pos, boolean faceRight)
  {
    super(pos, faceRight);
    ArrayList<Image> images = new ArrayList<Image>(); 
    //test
    
    images.add(loadImage("/dusts/IMG_Dust_Fire_Spear_Aura_1.png"));
    images.add(loadImage("/dusts/IMG_Dust_Fire_Spear_Aura_2.png"));
    
    ArrayList<Float> times = new ArrayList<Float>();
    times.add(Float.valueOf(0.075F));
    times.add(Float.valueOf(0.075F));
    
    this.dustAnimator = new Animation(images, times, false);
  }
}
