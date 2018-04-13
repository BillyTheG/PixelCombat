package pixelCombat.arenas;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.image.Image;
import pixelCombat.npc.NPC;
import pixelCombat.utils.GameEngine;
import pixelCombat.view.Animation;

public abstract class PXMap
{
  protected Animation bgAnimator;
  protected Image foreGround;
  private Weather weather;
  protected ArrayList<NPC> npcs = new ArrayList<>();
  
  public PXMap(Weather weather)
  {
    this.weather = weather;
  }
  
  public PXMap() {}
  
  public void update(float delta)
  {
    this.bgAnimator.update(delta);
    if (this.weather != null) {
      this.weather.update(delta);
    }
    updateFurther(delta);
    for (NPC npc : this.npcs) {
      npc.update(delta);
    }
  }
  
  abstract void updateFurther(float paramFloat);
  
  public abstract void playMusic(GameEngine paramGameEngine);
  
  public Image getCurrentBG()
  {
    return this.bgAnimator.getImage();
  }
  
  public Image getCurrentWeatherBG()
  {
    if (this.weather != null) {
      return this.weather.getCurrentBG();
    }
    return null;
  }
  
  public Image getForeGround()
  {
    return this.foreGround;
  }
  
  public float getWidth()
  {
    return (float)this.bgAnimator.getImage().getWidth();
  }
  
  public float getHeight()
  {
    return (float)this.bgAnimator.getImage().getHeight();
  }
  
  public List<NPC> getNPCs()
  {
    return this.npcs;
  }
}
