package pixelCombat.arenas;

import java.util.ArrayList;

import javafx.scene.image.Image;
import pixelCombat.Math.Vector2d;
import pixelCombat.npc.Erza;
import pixelCombat.npc.Gray;
import pixelCombat.npc.Lucy;
import pixelCombat.utils.GameEngine;
import pixelCombat.view.animation.Animation;
import pixelCombat.view.gamephases.GamePlayView;

public class FairyTailGuildDestroyed
  extends PXMap
{
  public FairyTailGuildDestroyed()
  {
    ArrayList<Image> images = new ArrayList<>();
    
    images.add(GamePlayView.loadImage("/maps/IMG_Map_Fairy_Tail_Guild_Destroyed.png"));
    
    ArrayList<Float> times = new ArrayList<>();
    times.add(Float.valueOf(0.35F));
    
    this.bgAnimator = new Animation(images, times, false);
    this.npcs.add(new Gray(new Vector2d(6.2F, 6.0F), true));
    this.npcs.add(new Lucy(new Vector2d(7.5F, 6.0F), true));
    this.npcs.add(new Erza(new Vector2d(9.25F, 6.0F), true));
  }
  
  public void updateFurther(float delta) {}
  
  public void playMusic(GameEngine engine)
  {
    engine.musicMP3("/audio/Fairy_Tail_GuildI.mp3");
  }
}
