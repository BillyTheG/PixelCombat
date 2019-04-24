package pixelCombat.dusts;

import java.util.ArrayList;
import javafx.scene.image.Image;
import pixelCombat.Math.Vector2d;
import pixelCombat.model.Dust;
import pixelCombat.view.animation.Animation;

public class WandBlow
  extends Dust
{
  public WandBlow(Vector2d pos, boolean faceRight)
  {
    super(pos, faceRight);
    
    ArrayList<Image> images = new ArrayList<Image>();
    
    images.add(loadImage("/dusts/IMG_Dust_Wand_Blow_(0).png"));
    images.add(loadImage("/dusts/IMG_Dust_Wand_Blow_(1).png"));
    images.add(loadImage("/dusts/IMG_Dust_Wand_Blow_(2).png"));
    images.add(loadImage("/dusts/IMG_Dust_Wand_Blow_(3).png"));
    images.add(loadImage("/dusts/IMG_Dust_Wand_Blow_(4).png"));
    
    images.add(loadImage("/dusts/IMG_Dust_Wand_Blow_(5).png"));
    images.add(loadImage("/dusts/IMG_Dust_Wand_Blow_(6).png"));
    images.add(loadImage("/dusts/IMG_Dust_Wand_Blow_(7).png"));
    images.add(loadImage("/dusts/IMG_Dust_Wand_Blow_(8).png"));
    images.add(loadImage("/dusts/IMG_Dust_Wand_Blow_(9).png"));
    
    images.add(loadImage("/dusts/IMG_Dust_Wand_Blow_(10).png"));
    images.add(loadImage("/dusts/IMG_Dust_Wand_Blow_(11).png"));
    images.add(loadImage("/dusts/IMG_Dust_Wand_Blow_(12).png"));
    
    ArrayList<Float> times = new ArrayList<Float>();
    
    times.add(Float.valueOf(0.035F));
    times.add(Float.valueOf(0.035F));
    times.add(Float.valueOf(0.035F));
    times.add(Float.valueOf(0.035F));
    times.add(Float.valueOf(0.035F));
    
    times.add(Float.valueOf(0.035F));
    times.add(Float.valueOf(0.035F));
    times.add(Float.valueOf(0.035F));
    times.add(Float.valueOf(0.035F));
    times.add(Float.valueOf(0.035F));
    
    times.add(Float.valueOf(0.035F));
    times.add(Float.valueOf(0.035F));
    times.add(Float.valueOf(0.035F));
    
    this.dustAnimator = new Animation(images, times, true);
  }
}
