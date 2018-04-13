package pixelCombat.view.gamephases;


import javafx.scene.canvas.Canvas;
import javafx.scene.image.Image;
import pixelCombat.Math.Vector2d;
import pixelCombat.gamephase.MapSelection;
import pixelCombat.view.Renderer;

public class MapSelectionView extends Renderer
{
	protected 	Image 				MAP_SELECTION 		= GamePlayView.loadImage("/gamePhase/backgrounds/Map_Selection.png");
	protected 	Image				player1_cursor		= GamePlayView.loadImage("/images/player_1_cursor.png");
	private 	MapSelection 		mapSelection;
	  
	
    public MapSelectionView(MapSelection mapSelection, Canvas canvas) 
    {
    	super(canvas);
    	this.mapSelection = mapSelection;
    }



    public void render(long delta) 
    {
    	Vector2d pos 				= new Vector2d();
    	float 	 x					= 0;	
    	float 	 y					= 0;
    	    	
    	// render background
    	if (MENU_BACKGROUND != null) 
    		graphicsContext.drawImage(MENU_BACKGROUND,0,0, GamePlayView.SCREEN_WIDTH, GamePlayView.SCREEN_HEIGHT);
    	
    	if (MAP_SELECTION 		!= null) 
    		graphicsContext.drawImage(MAP_SELECTION,0,0, GamePlayView.SCREEN_WIDTH, GamePlayView.SCREEN_HEIGHT);
    	
    	//draw selection field
    	mapSelection.getSelectionField().createSelectionPanel();
    	
    	
    	
    	// drawing cursor
    	int    currentMapID 		= mapSelection.getCurrentMapID();
    	double offset_x 			= player1_cursor.getWidth()/2 - 2.5f*GamePlayView.FIELD_SIZE;
    	double offset_y 			= player1_cursor.getHeight()/2 - 20f;
    	
    	pos	= mapSelection.getSelectionField().pos_tables.get(currentMapID);
    	
    	x	= pos.x*GamePlayView.FIELD_SIZE;
    	y   = pos.y*GamePlayView.FIELD_SIZE;
    	
    	graphicsContext.drawImage(player1_cursor,x-offset_x,y-offset_y);
   
    	
    	
    }

}
