package pixelCombat.gamephase.gamephaseelement;

import java.util.HashMap;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import pixelCombat.Math.Vector2d;
import pixelCombat.utils.GamePhase;
import pixelCombat.view.gamephases.GamePlayView;

public class GamePhaseMapSelectionField
  extends GamePhaseElement
  implements SelectionField
{
  private String[] selections;
  private Image[] maps;
  private int selections_size;
  private float field_width = 250.0F;
  private float field_height = 100.0F;
  private Canvas canvas;
  private GraphicsContext graphicsContext;
  private int maximum_number_per_size = 4;
  public HashMap<Integer, Vector2d> pos_tables;
  
  public GamePhaseMapSelectionField(GamePhase gamePhase, Vector2d pos, Canvas canvas)
  {
    super(gamePhase, pos);
    init();
    this.canvas = canvas;
    this.graphicsContext = this.canvas.getGraphicsContext2D();
    this.pos_tables = new HashMap<Integer, Vector2d>();
  }
  
  private void init()
  {
    this.selections_size = 2;
    this.selections = new String[this.selections_size];
    this.maps = new Image[this.selections_size];
    this.selections[0] = "Map_1";
    this.maps[0] = GamePlayView.loadImage("/maps/Map_3.png");
    this.selections[1] = "Map_2";
    this.maps[1] = GamePlayView.loadImage("/maps/IMG_Map_Fairy_Tail_Guild_Destroyed_Icon.png");
  }
  
  public void createSelectionPanel()
  {
    Vector2d panel_cell_pos = new Vector2d(3.0F, 3.0F);
    float x = panel_cell_pos.x * 50.0F;
    float y = panel_cell_pos.y * 50.0F;
    int j = 0;
    
    float x_ = x;
    float y_ = y;
    for (int i = 0; i < this.selections_size; i++)
    {
      x_ = x + this.field_width * i + 15.0F;
      y_ = y + this.field_height * j + 15.0F;
      
      this.graphicsContext.fillRect(x_, y_, this.field_width + 10.0F, 
        this.field_height + 10.0F);
      this.graphicsContext.drawImage(this.maps[i], x_ + 5.0F, y_ + 5.0F);
      Vector2d avatar_pos = new Vector2d((x_ + 5.0F) / 50.0F, 
        (y_ + 5.0F + this.field_height) / 50.0F);
      
      this.pos_tables.put(Integer.valueOf(i), avatar_pos);
      if ((i != 0) && (i % this.maximum_number_per_size == 0)) {
        j++;
      }
    }
  }
  
  public void update(float delta) {}
  
  public Image draw()
  {
    createSelectionPanel();
    return null;
  }
  
  public void dynamic() {}
  
  public String[] getSelections()
  {
    return this.selections;
  }
  
  public void setSelections(String[] selections)
  {
    this.selections = selections;
  }
  
  public int getSelections_size()
  {
    return this.selections_size;
  }
  
  public void setSelections_size(int selections_size)
  {
    this.selections_size = selections_size;
  }
  
  public HashMap<Integer, Vector2d> getPos_tables()
  {
    return this.pos_tables;
  }
  
  public void setPos_tables(HashMap<Integer, Vector2d> pos_tables)
  {
    this.pos_tables = pos_tables;
  }
}
