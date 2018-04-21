package pixelCombat.artworks;

import java.util.ArrayList;

import javafx.scene.image.Image;
import pixelCombat.Math.Vector2d;
import pixelCombat.model.PXMapHandler;
import pixelCombat.view.Animation;

public class NatsuSuper
  extends ArtWork
{
  public NatsuSuper()
  {
    super(new Vector2d(PXMapHandler.X_FIELDS/2f,PXMapHandler.Y_FIELDS/2), 0.0F, 0.0F);
    ArrayList<Image> images = new ArrayList<Image>();
    Image image1 = loadImage("/artWorks/IMG_ArtWork_IMG_ArtWork_Natsu_Super_1.png");
    Image image2 = loadImage("/artWorks/IMG_ArtWork_IMG_ArtWork_Natsu_Super_2.png");
    Image image3 = loadImage("/artWorks/IMG_ArtWork_IMG_ArtWork_Natsu_Super_3.png");
    
    images.add(image1);
    images.add(image2);
    images.add(image3);
    
    images.add(image1);
    images.add(image2);
    images.add(image3);
    
    images.add(image1);
    images.add(image2);
    images.add(image3);
    
    images.add(image1);
    images.add(image2);
    images.add(image3);
    
    images.add(image1);
    images.add(image2);
    images.add(image3);
    
    images.add(image1);
    images.add(image2);
    images.add(image3);
    
    images.add(image1);
    images.add(image2);
    images.add(image3);
    
    images.add(image1);
    images.add(image2);
    images.add(image3);
    
    images.add(image1);
    images.add(image2);
    images.add(image3);
    
    images.add(image1);
    images.add(image2);
    images.add(image3);
    
    ArrayList<Float> times = new ArrayList<Float>();
    
    times.add(Float.valueOf(0.1F));
    times.add(Float.valueOf(0.1F));
    times.add(Float.valueOf(0.1F));
    
    times.add(Float.valueOf(0.1F));
    times.add(Float.valueOf(0.1F));
    times.add(Float.valueOf(0.1F));
    
    times.add(Float.valueOf(0.1F));
    times.add(Float.valueOf(0.1F));
    times.add(Float.valueOf(0.1F));
    
    times.add(Float.valueOf(0.1F));
    times.add(Float.valueOf(0.1F));
    times.add(Float.valueOf(0.1F));
    
    times.add(Float.valueOf(0.1F));
    times.add(Float.valueOf(0.1F));
    times.add(Float.valueOf(0.1F));
    
    times.add(Float.valueOf(0.1F));
    times.add(Float.valueOf(0.1F));
    times.add(Float.valueOf(0.1F));
    
    times.add(Float.valueOf(0.1F));
    times.add(Float.valueOf(0.1F));
    times.add(Float.valueOf(0.1F));
    
    times.add(Float.valueOf(0.1F));
    times.add(Float.valueOf(0.1F));
    times.add(Float.valueOf(0.1F));
    
    times.add(Float.valueOf(0.1F));
    times.add(Float.valueOf(0.1F));
    times.add(Float.valueOf(0.1F));
    
    times.add(Float.valueOf(0.1F));
    times.add(Float.valueOf(0.1F));
    times.add(Float.valueOf(0.1F));
    
    scaleX = 1.25f;
    scaleY = 1.25f;
    
    this.dustAnimator = new Animation(images, times, true);
    setSpecialArtWork(true);
  }
}
