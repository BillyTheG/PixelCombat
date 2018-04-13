package pixelCombat.dusts;

import java.util.ArrayList;

import javafx.scene.image.Image;
import pixelCombat.Math.Vector2d;
import pixelCombat.model.Dust;
import pixelCombat.view.Animation;

public class HardLand
  extends Dust
{
  public HardLand(Vector2d pos, boolean faceRight)
  {
    super(pos, faceRight);
    ArrayList<Image> images = new ArrayList<Image>();
    
    images.add(loadImage("/dusts/IMG_Dust_Hard_Land_1.png"));
    images.add(loadImage("/dusts/IMG_Dust_Hard_Land_2.png"));
    images.add(loadImage("/dusts/IMG_Dust_Hard_Land_3.png"));
    images.add(loadImage("/dusts/IMG_Dust_Hard_Land_4.png"));
    images.add(loadImage("/dusts/IMG_Dust_Hard_Land_5.png"));
    
    images.add(loadImage("/dusts/IMG_Dust_Hard_Land_6.png"));
    images.add(loadImage("/dusts/IMG_Dust_Hard_Land_7.png"));
    images.add(loadImage("/dusts/IMG_Dust_Hard_Land_8.png"));
    images.add(loadImage("/dusts/IMG_Dust_Hard_Land_9.png"));
    images.add(loadImage("/dusts/IMG_Dust_Hard_Land_10.png"));
    
    images.add(loadImage("/dusts/IMG_Dust_Hard_Land_11.png"));
    
    ArrayList<Float> times = new ArrayList<Float>();
    times.add(Float.valueOf(0.07F));
    times.add(Float.valueOf(0.07F));
    times.add(Float.valueOf(0.07F));
    times.add(Float.valueOf(0.07F));
    times.add(Float.valueOf(0.07F));
    
    times.add(Float.valueOf(0.07F));
    times.add(Float.valueOf(0.07F));
    times.add(Float.valueOf(0.07F));
    times.add(Float.valueOf(0.07F));
    times.add(Float.valueOf(0.07F));
    
    times.add(Float.valueOf(0.07F));
    
    this.dustAnimator = new Animation(images, times, true);
  }
}
