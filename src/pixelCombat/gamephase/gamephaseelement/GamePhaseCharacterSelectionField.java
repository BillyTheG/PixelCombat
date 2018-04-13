package pixelCombat.gamephase.gamephaseelement;

import java.util.HashMap;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import pixelCombat.Math.Vector2d;
import pixelCombat.utils.GamePhase;
import pixelCombat.view.gamephases.GamePlayView;

public class GamePhaseCharacterSelectionField extends GamePhaseElement implements SelectionField
{
	
	private String[] 							selections; 
	private Image[]								avatars;
	private int									selections_size;
	private float								field_width					=	2*GamePlayView.FIELD_SIZE;
	private	float								field_height				=	2*GamePlayView.FIELD_SIZE;
	private Canvas								canvas;
	private GraphicsContext 					graphicsContext;
	private int 								maximum_number_per_size		=	4;
	public  HashMap<Integer,Vector2d>			pos_tables;
	
	public GamePhaseCharacterSelectionField(GamePhase gamePhase, Vector2d pos, Canvas canvas)  
	{
		super(gamePhase, pos);
		init();
		this.canvas					= canvas;
		this.graphicsContext		= this.canvas.getGraphicsContext2D();
		pos_tables 					= new HashMap<Integer,Vector2d>();
	}

	private void init() 
	{
		selections_size				= 3;	
		selections					= new String[selections_size];
		avatars						= new Image[selections_size];
		selections[0]				= "ruffy";
		avatars[0]					= GamePlayView.loadImage("/images/ruffy/IMG_Ruffy_Avatar.png");
		selections[1]				= "natsu";
		avatars[1]					= GamePlayView.loadImage("/images/natsu/natsu_avatar.png");
		selections[2]				= "zorro";
		avatars[2]					= GamePlayView.loadImage("/images/zorro/IMG_Zorro_Avatar.png");
		
	}

	@Override
	public void createSelectionPanel() 
	{
		Vector2d panel_cell_pos		= new Vector2d(3,3);
		float	x					= panel_cell_pos.x * GamePlayView.FIELD_SIZE;
		float	y					= panel_cell_pos.y * GamePlayView.FIELD_SIZE;
		int		j					= 0;
		
		float	x_					= x;
		float	y_					= y;
		
		for(int i = 0; i<selections_size;i++)
		{
			x_	=	x + field_width	*i 	+15;
			y_	=	y + field_height*j	+15;
			
			graphicsContext.fillRect(x_, y_, field_width+10, field_height+10);			
			graphicsContext.drawImage(avatars[i],x_ +5 , y_ +5);
			Vector2d avatar_pos		= new Vector2d((x_+5)/GamePlayView.FIELD_SIZE,(y_+5+field_height)/GamePlayView.FIELD_SIZE);
			
			pos_tables.put(i, avatar_pos);
			
			
			if(i != 0 && i%maximum_number_per_size == 0)
				j++;
		}
	}

	@Override
	public void update(float delta) 
	{
		
	}

	@Override
	public Image draw() 
	{
		createSelectionPanel();
		return null;
	}

	@Override
	public void dynamic() 
	{
		// TODO Auto-generated method stub

	}

	/**
	 * @return the selections
	 */
	public String[] getSelections() {
		return selections;
	}

	/**
	 * @param selections the selections to set
	 */
	public void setSelections(String[] selections) {
		this.selections = selections;
	}

	/**
	 * @return the selections_size
	 */
	public int getSelections_size() {
		return selections_size;
	}

	/**
	 * @param selections_size the selections_size to set
	 */
	public void setSelections_size(int selections_size) {
		this.selections_size = selections_size;
	}

	/**
	 * @return the pos_tables
	 */
	public HashMap<Integer, Vector2d> getPos_tables() {
		return pos_tables;
	}

	/**
	 * @param pos_tables the pos_tables to set
	 */
	public void setPos_tables(HashMap<Integer, Vector2d> pos_tables) {
		this.pos_tables = pos_tables;
	}
 
	
 
}
