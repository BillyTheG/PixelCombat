package pixelCombat.dusts;

import java.util.ArrayList;

import javafx.scene.image.Image;
import pixelCombat.Math.Vector2d;
import pixelCombat.model.Dust;
import pixelCombat.view.Animation;

public class GroundSweep
  extends Dust
{
  public GroundSweep(Vector2d pos, boolean faceRight)
  {
    super(pos, faceRight);
    ArrayList<Image> images = new ArrayList<Image>();
    
    images.add(loadImage("/dusts/IMG_Dust_Ground_Sweep_1.png"));
    images.add(loadImage("/dusts/IMG_Dust_Ground_Sweep_2.png"));
    images.add(loadImage("/dusts/IMG_Dust_Ground_Sweep_3.png"));
    images.add(loadImage("/dusts/IMG_Dust_Ground_Sweep_4.png"));
    images.add(loadImage("/dusts/IMG_Dust_Ground_Sweep_5.png"));
    
    ArrayList<Float> times = new ArrayList<Float>();
    times.add(Float.valueOf(0.035F));
    times.add(Float.valueOf(0.025F));
    times.add(Float.valueOf(0.015F));
    times.add(Float.valueOf(0.015F));
    times.add(Float.valueOf(0.015F));
    
    this.dustAnimator = new Animation(images, times, true);
  }
}
