package pixelCombat.dusts;

import java.util.ArrayList;

import javafx.scene.image.Image;
import pixelCombat.Math.Vector2d;
import pixelCombat.model.Dust;
import pixelCombat.view.animation.Animation;

public class ZorroDemonAura
  extends Dust
{
  public ZorroDemonAura(Vector2d pos, boolean faceRight)
  {
    super(pos, faceRight);
    
    ArrayList<Image> images = new ArrayList<Image>();
    
    images.add(loadImage("/dusts/IMG_Dust_Zorro_Demon_Aura_(18).png"));
    images.add(loadImage("/dusts/IMG_Dust_Zorro_Demon_Aura_(1).png"));
    images.add(loadImage("/dusts/IMG_Dust_Zorro_Demon_Aura_(2).png"));
    images.add(loadImage("/dusts/IMG_Dust_Zorro_Demon_Aura_(3).png"));
    images.add(loadImage("/dusts/IMG_Dust_Zorro_Demon_Aura_(4).png"));
    
    images.add(loadImage("/dusts/IMG_Dust_Zorro_Demon_Aura_(5).png"));
    images.add(loadImage("/dusts/IMG_Dust_Zorro_Demon_Aura_(6).png"));
    images.add(loadImage("/dusts/IMG_Dust_Zorro_Demon_Aura_(7).png"));
    images.add(loadImage("/dusts/IMG_Dust_Zorro_Demon_Aura_(8).png"));
    images.add(loadImage("/dusts/IMG_Dust_Zorro_Demon_Aura_(9).png"));
    
    images.add(loadImage("/dusts/IMG_Dust_Zorro_Demon_Aura_(10).png"));
    images.add(loadImage("/dusts/IMG_Dust_Zorro_Demon_Aura_(11).png"));
    images.add(loadImage("/dusts/IMG_Dust_Zorro_Demon_Aura_(12).png"));
    images.add(loadImage("/dusts/IMG_Dust_Zorro_Demon_Aura_(13).png"));
    images.add(loadImage("/dusts/IMG_Dust_Zorro_Demon_Aura_(14).png"));
    
    images.add(loadImage("/dusts/IMG_Dust_Zorro_Demon_Aura_(15).png"));
    images.add(loadImage("/dusts/IMG_Dust_Zorro_Demon_Aura_(16).png"));
    images.add(loadImage("/dusts/IMG_Dust_Zorro_Demon_Aura_(17).png"));
    
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
    times.add(Float.valueOf(0.035F));
    times.add(Float.valueOf(0.035F));
    
    times.add(Float.valueOf(0.035F));
    times.add(Float.valueOf(0.035F));
    times.add(Float.valueOf(0.035F));
    
    this.dustAnimator = new Animation(images, times, true);
  }
}
