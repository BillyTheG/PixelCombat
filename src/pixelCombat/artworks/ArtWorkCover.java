package pixelCombat.artworks;

import java.util.ArrayList;

import javafx.scene.image.Image;
import pixelCombat.Math.Vector2d;
import pixelCombat.view.animation.Animation;

public class ArtWorkCover
  extends ArtWork
{
  public ArtWorkCover()
  {
    super(new Vector2d(0F, 0.0F), 0.0F, 0.0F);
    ArrayList<Image> images = new ArrayList<Image>();
    
    images.add(loadImage("/artWorks/IMG_ArtWork_Cover_01.png"));
    images.add(loadImage("/artWorks/IMG_ArtWork_Cover_02.png"));
    images.add(loadImage("/artWorks/IMG_ArtWork_Cover_03.png"));
    images.add(loadImage("/artWorks/IMG_ArtWork_Cover_04.png"));
    images.add(loadImage("/artWorks/IMG_ArtWork_Cover_05.png"));
    
    images.add(loadImage("/artWorks/IMG_ArtWork_Cover_06.png"));
    images.add(loadImage("/artWorks/IMG_ArtWork_Cover_07.png"));
    images.add(loadImage("/artWorks/IMG_ArtWork_Cover_08.png"));
    images.add(loadImage("/artWorks/IMG_ArtWork_Cover_09.png"));
    images.add(loadImage("/artWorks/IMG_ArtWork_Cover_10.png"));
    
    images.add(loadImage("/artWorks/IMG_ArtWork_Cover_11.png"));
    images.add(loadImage("/artWorks/IMG_ArtWork_Cover_12.png"));
    
    ArrayList<Float> times = new ArrayList<Float>();
    times.add(Float.valueOf(0.035f));
    times.add(Float.valueOf(0.035f));
    times.add(Float.valueOf(0.035f));
    times.add(Float.valueOf(0.035f));
    times.add(Float.valueOf(0.035f));
    
    times.add(Float.valueOf(0.035f));
    times.add(Float.valueOf(0.035f));
    times.add(Float.valueOf(0.035f));
    times.add(Float.valueOf(0.035f));
    times.add(Float.valueOf(0.035f));
    
    times.add(Float.valueOf(0.035f));
    times.add(Float.valueOf(0.035f));
    
    this.dustAnimator = new Animation(images, times, false);
  }
}
