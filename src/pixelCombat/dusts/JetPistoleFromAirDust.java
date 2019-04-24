package pixelCombat.dusts;

import java.util.ArrayList;

import javafx.scene.image.Image;
import pixelCombat.Math.Vector2d;
import pixelCombat.model.Dust;
import pixelCombat.view.animation.Animation;

public class JetPistoleFromAirDust
  extends Dust
{
  public JetPistoleFromAirDust(Vector2d pos, boolean faceRight)
  {
    super(pos, faceRight);
    ArrayList<Image> images = new ArrayList<Image>();
    
    images.add(loadImage("/dusts/IMG_Dust_JetPistoleFromAir_01.png"));
    images.add(loadImage("/dusts/IMG_Dust_JetPistoleFromAir_02.png"));
    images.add(loadImage("/dusts/IMG_Dust_JetPistoleFromAir_03.png"));
    images.add(loadImage("/dusts/IMG_Dust_JetPistoleFromAir_04.png"));
    images.add(loadImage("/dusts/IMG_Dust_JetPistoleFromAir_05.png"));
    images.add(loadImage("/dusts/IMG_Dust_JetPistoleFromAir_06.png"));
    images.add(loadImage("/dusts/IMG_Dust_JetPistoleFromAir_07.png"));
    images.add(loadImage("/dusts/IMG_Dust_JetPistoleFromAir_08.png"));
    images.add(loadImage("/dusts/IMG_Dust_JetPistoleFromAir_09.png"));
    images.add(loadImage("/dusts/IMG_Dust_JetPistoleFromAir_10.png"));
    
    ArrayList<Float> times = new ArrayList<Float>();
    times.add(Float.valueOf(0.025F));
    times.add(Float.valueOf(0.025F));
    times.add(Float.valueOf(0.025F));
    times.add(Float.valueOf(0.045F));
    times.add(Float.valueOf(0.03F));
    
    times.add(Float.valueOf(0.024F));
    times.add(Float.valueOf(0.013F));
    times.add(Float.valueOf(0.012F));
    times.add(Float.valueOf(0.012F));
    times.add(Float.valueOf(0.012F));
    
    this.dustAnimator = new Animation(images, times, true);
  }
}
