package pixelCombat.dusts;

import java.util.ArrayList;

import javafx.scene.image.Image;
import pixelCombat.Math.Vector2d;
import pixelCombat.model.Dust;
import pixelCombat.view.Animation;

public class NatsuPunch
  extends Dust
{
  public NatsuPunch(Vector2d pos, boolean faceRight)
  {
    super(pos, faceRight);
    ArrayList<Image> images = new ArrayList<Image>();
    
    images.add(loadImage("/dusts/IMG_Dust_Natsu_Punch_(1).png"));
    images.add(loadImage("/dusts/IMG_Dust_Natsu_Punch_(2).png"));
    images.add(loadImage("/dusts/IMG_Dust_Natsu_Punch_(3).png"));
    images.add(loadImage("/dusts/IMG_Dust_Natsu_Punch_(4).png"));
    images.add(loadImage("/dusts/IMG_Dust_Natsu_Punch_(5).png"));
    
    images.add(loadImage("/dusts/IMG_Dust_Natsu_Punch_(6).png"));
    images.add(loadImage("/dusts/IMG_Dust_Natsu_Punch_(7).png"));
    images.add(loadImage("/dusts/IMG_Dust_Natsu_Punch_(8).png"));
    images.add(loadImage("/dusts/IMG_Dust_Natsu_Punch_(9).png"));
    images.add(loadImage("/dusts/IMG_Dust_Natsu_Punch_(10).png"));
    
    images.add(loadImage("/dusts/IMG_Dust_Natsu_Punch_(11).png"));
    images.add(loadImage("/dusts/IMG_Dust_Natsu_Punch_(12).png"));
    
    ArrayList<Float> times = new ArrayList<Float>();
    times.add(Float.valueOf(0.02F));
    times.add(Float.valueOf(0.02F));
    times.add(Float.valueOf(0.02F));
    times.add(Float.valueOf(0.02F));
    times.add(Float.valueOf(0.02F));
    
    times.add(Float.valueOf(0.02F));
    times.add(Float.valueOf(0.02F));
    times.add(Float.valueOf(0.02F));
    times.add(Float.valueOf(0.02F));
    times.add(Float.valueOf(0.02F));
    
    times.add(Float.valueOf(0.02F));
    times.add(Float.valueOf(0.02F));
    
    this.dustAnimator = new Animation(images, times, true);
  }
}
