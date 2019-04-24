package pixelCombat.dusts;

import java.util.ArrayList;

import javafx.scene.image.Image;
import pixelCombat.Math.Vector2d;
import pixelCombat.model.Dust;
import pixelCombat.view.animation.Animation;

public class DashWind
  extends Dust
{
  public DashWind(Vector2d pos, boolean faceRight)
  {
    super(pos, faceRight);
    
    ArrayList<Image> images = new ArrayList<Image>();
    
    images.add(loadImage("/dusts/IMG_Dust_Zorro_Dash_Wind_1.png"));
    images.add(loadImage("/dusts/IMG_Dust_Zorro_Dash_Wind_2.png"));
    images.add(loadImage("/dusts/IMG_Dust_Zorro_Dash_Wind_3.png"));
    
    ArrayList<Float> times = new ArrayList<Float>();
    
    times.add(Float.valueOf(0.025F));
    times.add(Float.valueOf(0.025F));
    times.add(Float.valueOf(0.025F));
    
    this.dustAnimator = new Animation(images, times, false);
  }
}
