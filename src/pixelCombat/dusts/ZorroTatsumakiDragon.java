package pixelCombat.dusts;

import java.util.ArrayList;

import javafx.scene.image.Image;
import pixelCombat.Math.Vector2d;
import pixelCombat.model.Dust;
import pixelCombat.view.Animation;

public class ZorroTatsumakiDragon
  extends Dust
{
  public ZorroTatsumakiDragon(Vector2d pos, boolean faceRight)
  {
    super(pos, faceRight);
    
    ArrayList<Image> images = new ArrayList<Image>();
    
    images.add(loadImage("/dusts/IMG_Dust_Zorro_Tatsumaki_Dragonflame_01.png"));
    images.add(loadImage("/dusts/IMG_Dust_Zorro_Tatsumaki_Dragonflame_02.png"));
    images.add(loadImage("/dusts/IMG_Dust_Zorro_Tatsumaki_Dragonflame_03.png"));
    images.add(loadImage("/dusts/IMG_Dust_Zorro_Tatsumaki_Dragonflame_04.png"));
    images.add(loadImage("/dusts/IMG_Dust_Zorro_Tatsumaki_Dragonflame_05.png"));
    
    images.add(loadImage("/dusts/IMG_Dust_Zorro_Tatsumaki_Dragonflame_06.png"));
    images.add(loadImage("/dusts/IMG_Dust_Zorro_Tatsumaki_Dragonflame_07.png"));
    images.add(loadImage("/dusts/IMG_Dust_Zorro_Tatsumaki_Dragonflame_08.png"));
    images.add(loadImage("/dusts/IMG_Dust_Zorro_Tatsumaki_Dragonflame_09.png"));
    images.add(loadImage("/dusts/IMG_Dust_Zorro_Tatsumaki_Dragonflame_10.png"));
    
    images.add(loadImage("/dusts/IMG_Dust_Zorro_Tatsumaki_Dragonflame_11.png"));
    images.add(loadImage("/dusts/IMG_Dust_Zorro_Tatsumaki_Dragonflame_12.png"));
    images.add(loadImage("/dusts/IMG_Dust_Zorro_Tatsumaki_Dragonflame_13.png"));
    images.add(loadImage("/dusts/IMG_Dust_Zorro_Tatsumaki_Dragonflame_14.png"));
    images.add(loadImage("/dusts/IMG_Dust_Zorro_Tatsumaki_Dragonflame_15.png"));
    
    images.add(loadImage("/dusts/IMG_Dust_Zorro_Tatsumaki_Dragonflame_16.png"));
    images.add(loadImage("/dusts/IMG_Dust_Zorro_Tatsumaki_Dragonflame_17.png"));
    images.add(loadImage("/dusts/IMG_Dust_Zorro_Tatsumaki_Dragonflame_18.png"));
    images.add(loadImage("/dusts/IMG_Dust_Zorro_Tatsumaki_Dragonflame_19.png"));
    images.add(loadImage("/dusts/IMG_Dust_Zorro_Tatsumaki_Dragonflame_20.png"));
    
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
    times.add(Float.valueOf(0.035F));
    times.add(Float.valueOf(0.035F));
    
    this.dustAnimator = new Animation(images, times, true);
  }
}
