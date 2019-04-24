package pixelCombat.dusts;

import java.util.ArrayList;

import javafx.scene.image.Image;
import pixelCombat.Math.Vector2d;
import pixelCombat.model.Dust;
import pixelCombat.view.animation.Animation;

public class NatsuPumpUp
  extends Dust
{
  public NatsuPumpUp(Vector2d pos, boolean faceRight)
  {
    super(pos, faceRight);
    ArrayList<Image> images = new ArrayList<Image>();
    
    images.add(loadImage("/dusts/IMG_Dust_Natsu_Pump_Aura_1.png"));
    images.add(loadImage("/dusts/IMG_Dust_Natsu_Pump_Aura_2.png"));
    images.add(loadImage("/dusts/IMG_Dust_Natsu_Pump_Aura_3.png"));
    images.add(loadImage("/dusts/IMG_Dust_Natsu_Pump_Aura_4.png"));
    
    images.add(loadImage("/dusts/IMG_Dust_Natsu_Pump_Aura_5.png"));
    images.add(loadImage("/dusts/IMG_Dust_Natsu_Pump_Aura_6.png"));
    images.add(loadImage("/dusts/IMG_Dust_Natsu_Pump_Aura_7.png"));
    images.add(loadImage("/dusts/IMG_Dust_Natsu_Pump_Aura_8.png"));
    
    ArrayList<Float> times = new ArrayList<Float>();
    times.add(Float.valueOf(0.04F));
    times.add(Float.valueOf(0.04F));
    times.add(Float.valueOf(0.04F));
    times.add(Float.valueOf(0.04F));
    
    times.add(Float.valueOf(0.04F));
    times.add(Float.valueOf(0.04F));
    times.add(Float.valueOf(0.04F));
    times.add(Float.valueOf(0.04F));
    
    this.dustAnimator = new Animation(images, times, true);
  }
}
