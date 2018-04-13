package pixelCombat.artworks;

import java.util.ArrayList;

import javafx.scene.image.Image;
import pixelCombat.Math.Vector2d;
import pixelCombat.view.Animation;

public class OnAir
  extends ArtWork
{
  public OnAir()
  {
    super(new Vector2d(9.0F, 5.0F), 0.0F, 0.0F);
    ArrayList<Image> images = new ArrayList<Image>();
    
    images.add(loadImage("/dusts/IMG_Dust_onAir_1.png"));
    images.add(loadImage("/dusts/IMG_Dust_onAir_2.png"));
    images.add(loadImage("/dusts/IMG_Dust_onAir_3.png"));
    images.add(loadImage("/dusts/IMG_Dust_onAir_4.png"));
    images.add(loadImage("/dusts/IMG_Dust_onAir_5.png"));
    images.add(loadImage("/dusts/IMG_Dust_onAir_6.png"));
    images.add(loadImage("/dusts/IMG_Dust_onAir_7.png"));
    images.add(loadImage("/dusts/IMG_Dust_onAir_8.png"));
    images.add(loadImage("/dusts/IMG_Dust_onAir_9.png"));
    images.add(loadImage("/dusts/IMG_Dust_onAir_10.png"));
    
    ArrayList<Float> times = new ArrayList<Float>();
    times.add(Float.valueOf(0.075F));
    times.add(Float.valueOf(0.075F));
    times.add(Float.valueOf(0.075F));
    times.add(Float.valueOf(0.075F));
    times.add(Float.valueOf(0.075F));
    
    times.add(Float.valueOf(0.075F));
    times.add(Float.valueOf(0.075F));
    times.add(Float.valueOf(0.075F));
    times.add(Float.valueOf(0.075F));
    times.add(Float.valueOf(0.075F));
    
    this.dustAnimator = new Animation(images, times, false);
  }
}
