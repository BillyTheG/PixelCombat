package pixelCombat.dusts;

import java.util.ArrayList;

import javafx.scene.image.Image;
import pixelCombat.Math.Vector2d;
import pixelCombat.model.Dust;
import pixelCombat.view.Animation;

public class NatsuDashDust
  extends Dust
{
  public NatsuDashDust(Vector2d pos, boolean faceRight)
  {
    super(pos, faceRight);
    ArrayList<Image> images = new ArrayList<Image>();
    
    images.add(loadImage("/dusts/IMG_Dust_Natsu_Dash_01.png"));
    images.add(loadImage("/dusts/IMG_Dust_Natsu_Dash_02.png"));
    images.add(loadImage("/dusts/IMG_Dust_Natsu_Dash_03.png"));
    images.add(loadImage("/dusts/IMG_Dust_Natsu_Dash_04.png"));
    images.add(loadImage("/dusts/IMG_Dust_Natsu_Dash_05.png"));
    
    images.add(loadImage("/dusts/IMG_Dust_Natsu_Dash_06.png"));
    images.add(loadImage("/dusts/IMG_Dust_Natsu_Dash_07.png"));
    images.add(loadImage("/dusts/IMG_Dust_Natsu_Dash_08.png"));
    images.add(loadImage("/dusts/IMG_Dust_Natsu_Dash_09.png"));
    images.add(loadImage("/dusts/IMG_Dust_Natsu_Dash_10.png"));
    
    images.add(loadImage("/dusts/IMG_Dust_Natsu_Dash_11.png"));
    images.add(loadImage("/dusts/IMG_Dust_Natsu_Dash_12.png"));
    
    ArrayList<Float> times = new ArrayList<Float>();
    times.add(Float.valueOf(0.035F));
    times.add(Float.valueOf(0.035F));
    times.add(Float.valueOf(0.035F));
    times.add(Float.valueOf(0.035F));
    times.add(Float.valueOf(0.035F));
    
    times.add(Float.valueOf(0.035F));
    times.add(Float.valueOf(0.05F));
    times.add(Float.valueOf(0.035F));
    times.add(Float.valueOf(0.035F));
    times.add(Float.valueOf(0.05F));
    
    times.add(Float.valueOf(0.05F));
    times.add(Float.valueOf(0.035F));
    
    this.dustAnimator = new Animation(images, times, true);
  }
}
