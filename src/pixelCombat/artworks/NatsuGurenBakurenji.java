package pixelCombat.artworks;

import java.util.ArrayList;

import javafx.scene.image.Image;
import pixelCombat.Math.Vector2d;
import pixelCombat.view.Animation;

public class NatsuGurenBakurenji
  extends ArtWork
{
  public NatsuGurenBakurenji()
  {
    super(new Vector2d(9.0F, 5.0F), 0.0F, 0.0F);
    ArrayList<Image> images = new ArrayList<Image>();
    images.add(loadImage("/artWorks/IMG_ArtWork_Natsu_Guren_Bakurenji_01.png"));
    images.add(loadImage("/artWorks/IMG_ArtWork_Natsu_Guren_Bakurenji_02.png"));
    images.add(loadImage("/artWorks/IMG_ArtWork_Natsu_Guren_Bakurenji_03.png"));
    images.add(loadImage("/artWorks/IMG_ArtWork_Natsu_Guren_Bakurenji_04.png"));
    images.add(loadImage("/artWorks/IMG_ArtWork_Natsu_Guren_Bakurenji_05.png"));
    images.add(loadImage("/artWorks/IMG_ArtWork_Natsu_Guren_Bakurenji_06.png"));
    images.add(loadImage("/artWorks/IMG_ArtWork_Natsu_Guren_Bakurenji_07.png"));
    images.add(loadImage("/artWorks/IMG_ArtWork_Natsu_Guren_Bakurenji_08.png"));
    images.add(loadImage("/artWorks/IMG_ArtWork_Natsu_Guren_Bakurenji_09.png"));
    images.add(loadImage("/artWorks/IMG_ArtWork_Natsu_Guren_Bakurenji_10.png"));
    
    images.add(loadImage("/artWorks/IMG_ArtWork_Natsu_Guren_Bakurenji_11.png"));
    images.add(loadImage("/artWorks/IMG_ArtWork_Natsu_Guren_Bakurenji_12.png"));
    images.add(loadImage("/artWorks/IMG_ArtWork_Natsu_Guren_Bakurenji_13.png"));
    images.add(loadImage("/artWorks/IMG_ArtWork_Natsu_Guren_Bakurenji_14.png"));
    images.add(loadImage("/artWorks/IMG_ArtWork_Natsu_Guren_Bakurenji_15.png"));
    images.add(loadImage("/artWorks/IMG_ArtWork_Natsu_Guren_Bakurenji_16.png"));
    images.add(loadImage("/artWorks/IMG_ArtWork_Natsu_Guren_Bakurenji_17.png"));
    images.add(loadImage("/artWorks/IMG_ArtWork_Natsu_Guren_Bakurenji_18.png"));
    images.add(loadImage("/artWorks/IMG_ArtWork_Natsu_Guren_Bakurenji_19.png"));
    images.add(loadImage("/artWorks/IMG_ArtWork_Natsu_Guren_Bakurenji_20.png"));
    
    images.add(loadImage("/artWorks/IMG_ArtWork_Natsu_Guren_Bakurenji_21.png"));
    images.add(loadImage("/artWorks/IMG_ArtWork_Natsu_Guren_Bakurenji_22.png"));
    images.add(loadImage("/artWorks/IMG_ArtWork_Natsu_Guren_Bakurenji_23.png"));
    images.add(loadImage("/artWorks/IMG_ArtWork_Natsu_Guren_Bakurenji_24.png"));
    images.add(loadImage("/artWorks/IMG_ArtWork_Natsu_Guren_Bakurenji_25.png"));
    images.add(loadImage("/artWorks/IMG_ArtWork_Natsu_Guren_Bakurenji_26.png"));
    images.add(loadImage("/artWorks/IMG_ArtWork_Natsu_Guren_Bakurenji_27.png"));
    images.add(loadImage("/artWorks/IMG_ArtWork_Natsu_Guren_Bakurenji_28.png"));
    images.add(loadImage("/artWorks/IMG_ArtWork_Natsu_Guren_Bakurenji_29.png"));
    images.add(loadImage("/artWorks/IMG_ArtWork_Natsu_Guren_Bakurenji_30.png"));
    
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
    
    this.dustAnimator = new Animation(images, times, true);
  }
}
