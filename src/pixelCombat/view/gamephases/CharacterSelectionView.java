package pixelCombat.view.gamephases;

import javafx.scene.canvas.Canvas;
import javafx.scene.image.Image;
import pixelCombat.Math.Vector2d;
import pixelCombat.gamephase.CharacterSelection;
import pixelCombat.gamephase.gamephaseelement.GamePhaseFigure;
import pixelCombat.model.EffectManager;
import pixelCombat.view.Renderer;

public class CharacterSelectionView extends Renderer 
{

	public 				CharacterSelection	characterSelection;
	public				EffectManager		effectManager 		= new EffectManager();
	
	protected 			Image 				CHARACTER_SELECTION = GamePlayView.loadImage("/gamePhase/backgrounds/Character_Selection.png");
	protected 			Image				player1_cursor		= GamePlayView.loadImage("/images/player_1_cursor.png");
	protected 			Image				player2_cursor		= GamePlayView.loadImage("/images/player_2_cursor.png");
	private 			float 				figure1_x 			= 3.75f;
	private 			float 				figure2_x 			= 14.5f;
	private 			float 				figure1_y 			= 8.25f;
	private 			float 				figure2_y 			= figure1_y;
	private 			float 				SCALEFACTOR			= -0.2f;
	private 	static 	float 				BOTTOM_LINE			= 12f;

    public CharacterSelectionView(CharacterSelection characterSelection,Canvas canvas) 
    {
    	super(canvas);
    	this.characterSelection = characterSelection;
    }

    public void render(long delta) 
    {
    	GamePhaseFigure figure1;
    	GamePhaseFigure figure2;
    	Vector2d pos 									= new Vector2d();
    	float 	 x										= 0;	
    	float 	 y										= 0;
    	    	
    	// render background
    	if (MENU_BACKGROUND != null) 
    		graphicsContext.drawImage(MENU_BACKGROUND,0,0, GamePlayView.SCREEN_WIDTH, GamePlayView.SCREEN_HEIGHT);
    	
    	if (CHARACTER_SELECTION 		!= null) 
    		graphicsContext.drawImage(CHARACTER_SELECTION,0,0, GamePlayView.SCREEN_WIDTH, GamePlayView.SCREEN_HEIGHT);
    	
    	//draw selection field
    	characterSelection.getSelectionField().createSelectionPanel();
    	
    	int player1_selection = characterSelection.getPlayer1_pointer();
    	int player2_selection = characterSelection.getPlayer2_pointer();
    	
    	// drawing cursors
    	double offset_x = player1_cursor.getWidth()/2 - 65f;
    	double offset_y = player1_cursor.getHeight()/2 - 30f;
    	
    	pos	= characterSelection.getSelectionField().pos_tables.get(player1_selection);
    	
    	x	= pos.x*GamePlayView.FIELD_SIZE;
    	y   = pos.y*GamePlayView.FIELD_SIZE;
    	
    	graphicsContext.drawImage(player1_cursor,x-offset_x,y-offset_y,player1_cursor.getWidth()*GamePlayView.SCALEFACTOR,player1_cursor.getHeight()*GamePlayView.SCALEFACTOR);
    	
    	pos	= characterSelection.getSelectionField().pos_tables.get(player2_selection);
    	   	
    	y = pos.y*GamePlayView.FIELD_SIZE;
    	if(x == pos.x*GamePlayView.FIELD_SIZE)
    		 y+= 10;
    	x	= pos.x*GamePlayView.FIELD_SIZE;    	
    	
    	graphicsContext.drawImage(player2_cursor,x-offset_x,y-offset_y,player2_cursor.getWidth()*GamePlayView.SCALEFACTOR,player2_cursor.getHeight()*GamePlayView.SCALEFACTOR);
    	    	    	
    	figure1 = characterSelection.getPlayer1_selection(); 
    	figure2 = characterSelection.getPlayer2_selection(); 
    	
    	x	= figure1_x*GamePlayView.FIELD_SIZE;
    	y   = figure1_y*GamePlayView.FIELD_SIZE;    	
        rightDraw(figure1.draw(),x,y);
    	    	
    	x	= figure2_x*GamePlayView.FIELD_SIZE;
    	y   = figure2_y*GamePlayView.FIELD_SIZE;
    	leftDraw(figure2.draw(),x,y);
    	
    }
    
    
    public void leftDraw(Image p, float x, float y) {
		graphicsContext.drawImage(p,x +fixPic2(p), 
	    			y+ fixBottomOfPictures((float) (p.getHeight()*(GamePlayView.SCALEFACTOR+SCALEFACTOR))),
		    		-p.getWidth()	*(GamePlayView.SCALEFACTOR+SCALEFACTOR),
		    		 p.getHeight()	*(GamePlayView.SCALEFACTOR+SCALEFACTOR)
		    		);
	}
    	
    public void rightDraw(Image p,float x, float y) {
		graphicsContext.drawImage(p,x-fixPic2(p), 
	    		y + fixBottomOfPictures((float) (p.getHeight()*(GamePlayView.SCALEFACTOR+SCALEFACTOR))),
	    		p.getWidth()*(GamePlayView.SCALEFACTOR+SCALEFACTOR),
	    		p.getHeight()*(GamePlayView.SCALEFACTOR+SCALEFACTOR)
	    		);
	}

   
    public float fixPic2(Image i) {
    	
    	float halfHeight = (float) (i.getWidth() / 2)*(GamePlayView.SCALEFACTOR+SCALEFACTOR);
    	float diff = 0f;
//    	if(halfHeight >= 300f*(GamePlayView.SCALEFACTOR+SCALEFACTOR))
//    		return 0f;
    	if (100*(GamePlayView.SCALEFACTOR+SCALEFACTOR) < halfHeight)
    	    diff = halfHeight - 100*(GamePlayView.SCALEFACTOR+SCALEFACTOR);
    	if (100*(GamePlayView.SCALEFACTOR+SCALEFACTOR) > halfHeight)
    	    diff = halfHeight - 100*(GamePlayView.SCALEFACTOR+SCALEFACTOR);
    	return diff;
        }
    
    public float fixBottomOfPictures(float y)
    {
    	float diff = BOTTOM_LINE-y;
    	return diff;
    }
    
    
}
