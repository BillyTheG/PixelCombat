package pixelCombat.dusts;

import java.util.ArrayList;

import javafx.scene.image.Image;
import pixelCombat.Math.Vector2d;
import pixelCombat.model.Dust;
import pixelCombat.view.animation.Animation;

public class DefendBubble
  extends Dust
{
  public DefendBubble(Vector2d pos, boolean faceRight)
  {
    super(pos, faceRight);
    ArrayList<Image> images = new ArrayList<Image>();
    
    images.add(loadImage("/dusts/IMG_Dust_Defend_Bubble_(0).png"));
    images.add(loadImage("/dusts/IMG_Dust_Defend_Bubble_(1).png"));
    images.add(loadImage("/dusts/IMG_Dust_Defend_Bubble_(2).png"));
    images.add(loadImage("/dusts/IMG_Dust_Defend_Bubble_(3).png"));
    images.add(loadImage("/dusts/IMG_Dust_Defend_Bubble_(4).png"));
    
    images.add(loadImage("/dusts/IMG_Dust_Defend_Bubble_(5).png"));
    images.add(loadImage("/dusts/IMG_Dust_Defend_Bubble_(6).png"));
    images.add(loadImage("/dusts/IMG_Dust_Defend_Bubble_(7).png"));
    images.add(loadImage("/dusts/IMG_Dust_Defend_Bubble_(8).png"));
    images.add(loadImage("/dusts/IMG_Dust_Defend_Bubble_(9).png"));
    
    ArrayList<Float> times = new ArrayList<Float>();
    times.add(Float.valueOf(0.05F));
    times.add(Float.valueOf(0.05F));
    times.add(Float.valueOf(0.05F));
    times.add(Float.valueOf(0.05F));
    times.add(Float.valueOf(0.05F));
    
    times.add(Float.valueOf(0.05F));
    times.add(Float.valueOf(0.05F));
    times.add(Float.valueOf(0.05F));
    times.add(Float.valueOf(0.05F));
    times.add(Float.valueOf(0.05F));
    
    this.dustAnimator = new Animation(images, times, true);
  }
}
