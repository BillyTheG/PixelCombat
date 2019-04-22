package pixelCombat.view;

import java.util.HashMap;
import java.util.Map;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import pixelCombat.Math.Vector2d;
import pixelCombat.model.Character;
import pixelCombat.model.PXMapHandler;
import pixelCombat.view.gamephases.GamePlayView;

public class InterfaceView {
	private int x;
	private int y;
	private Vector2d pos;
	private GraphicsContext graphicsContext;
	private GamePlayView gamePlayView;
	private Character player1;
	private Character player2;
	private Image interface_player1 = GamePlayView.loadImage("/images/Interface_prot_left.png");
	private Image interface_player2 = GamePlayView.loadImage("/images/Interface_prot_right.png");
	private Image winPoint			= GamePlayView.loadImage("/images/interfaceelements/IMG_Misc_Win_Point_Icon.png");
	 private Image comboWriting = GamePlayView.loadImage("/images/interfaceelements/Interface_combo_Writing.png");
	public Map<String,ComboNumber> comboNumber_leaders = new HashMap<>();
	public Map<String,ComboNumber> comboNumber_successors = new HashMap<>();
	
	
	
	public InterfaceView(GraphicsContext graphicsContext, GamePlayView gamePlayView) {
		this.graphicsContext = graphicsContext;
		this.gamePlayView = gamePlayView;
		init();
	}

	public void init() {
		this.player1 = gamePlayView.getChar_player1();
		this.player2 = gamePlayView.getChar_player2();		
		initComboNumbers();		
	}

	private void initComboNumbers() {
		comboNumber_leaders.put("1", new ComboNumber(this.graphicsContext,"/images/interfaceelements/Interface_combo_leader_1.png"));
		comboNumber_leaders.put("2", new ComboNumber(this.graphicsContext,"/images/interfaceelements/Interface_combo_leader_2.png"));
		comboNumber_leaders.put("3", new ComboNumber(this.graphicsContext,"/images/interfaceelements/Interface_combo_leader_3.png"));
		comboNumber_leaders.put("4", new ComboNumber(this.graphicsContext,"/images/interfaceelements/Interface_combo_leader_4.png"));		
		comboNumber_leaders.put("5", new ComboNumber(this.graphicsContext,"/images/interfaceelements/Interface_combo_leader_5.png"));		
		comboNumber_leaders.put("6", new ComboNumber(this.graphicsContext,"/images/interfaceelements/Interface_combo_leader_6.png"));
		comboNumber_leaders.put("7", new ComboNumber(this.graphicsContext,"/images/interfaceelements/Interface_combo_leader_7.png"));
		comboNumber_leaders.put("8", new ComboNumber(this.graphicsContext,"/images/interfaceelements/Interface_combo_leader_8.png"));
		comboNumber_leaders.put("9", new ComboNumber(this.graphicsContext,"/images/interfaceelements/Interface_combo_leader_9.png"));
		
		comboNumber_successors.put("0", new ComboNumber(this.graphicsContext,"/images/interfaceelements/Interface_combo_successor_0.png"));
		comboNumber_successors.put("1", new ComboNumber(this.graphicsContext,"/images/interfaceelements/Interface_combo_successor_1.png"));
		comboNumber_successors.put("2", new ComboNumber(this.graphicsContext,"/images/interfaceelements/Interface_combo_successor_2.png"));
		comboNumber_successors.put("3", new ComboNumber(this.graphicsContext,"/images/interfaceelements/Interface_combo_successor_3.png"));
		comboNumber_successors.put("4", new ComboNumber(this.graphicsContext,"/images/interfaceelements/Interface_combo_successor_4.png"));		
		comboNumber_successors.put("5", new ComboNumber(this.graphicsContext,"/images/interfaceelements/Interface_combo_successor_5.png"));		
		comboNumber_successors.put("6", new ComboNumber(this.graphicsContext,"/images/interfaceelements/Interface_combo_successor_6.png"));
		comboNumber_successors.put("7", new ComboNumber(this.graphicsContext,"/images/interfaceelements/Interface_combo_successor_7.png"));
		comboNumber_successors.put("8", new ComboNumber(this.graphicsContext,"/images/interfaceelements/Interface_combo_successor_8.png"));
		comboNumber_successors.put("9", new ComboNumber(this.graphicsContext,"/images/interfaceelements/Interface_combo_successor_9.png"));		
	}

	/**
	 * render the interface of both players with avatar, life,magic, points.
	 * 
	 */
	public void render() {

		if(!player1.isAlive() || !player2.isAlive())
			return;
		// render lifepoints player1
		pos = new Vector2d(5f, 0.82f);
		float barWidth = 323f;
		int barHeight = 35;
		
		float scale = ((float) player1.getActualLifepoints() / (float) player1.getMaxLifepoints()) * barWidth
				* GamePlayView.SCALEFACTOR;
		drawLiveEnergyBar(barWidth, barHeight, scale,Color.RED);

		// render magicpoints player1
		pos = new Vector2d(5f, 1.55f);
		scale = player1.getActualMagicpoints() / (float) player1.getMaxMagicpoints() * barWidth * GamePlayView.SCALEFACTOR;
		drawLiveEnergyBar(barWidth, barHeight, scale,Color.BLUE);


		// render lifepoints player2
		pos = new Vector2d(12.4f, 0.82f);
		scale = ((float) player2.getActualLifepoints() / (float) player2.getMaxLifepoints()) * barWidth
				* GamePlayView.SCALEFACTOR;
		drawLiveEnergyBar(barWidth, barHeight, scale,Color.RED);

		// render magicpoints player2
		pos = new Vector2d(12.4f, 1.55f);
		scale = player2.getActualMagicpoints() / (float) player2.getMaxMagicpoints() * barWidth * GamePlayView.SCALEFACTOR;
		drawLiveEnergyBar(barWidth, barHeight, scale,Color.BLUE);


		// draw interface tabs of both chars
		pos = new Vector2d(0, 0);
		drawInterfaceElement(pos, interface_player1);
		pos = new Vector2d(12, 0f);
		drawInterfaceElement(pos, interface_player2);

		// render avatar player 1
		pos = new Vector2d(0.5f, 0.5f);
		drawInterfaceElement(pos, gamePlayView.getPlayer1().get("avatar").get(0));

		// render avatar player 2
		pos = new Vector2d(24 - 2.5f, 0.5f);
		drawInterfaceElement(pos, gamePlayView.getPlayer2().get("avatar").get(0));


		drawComboNumbers(player1,true);
		drawComboNumbers(player2,false);
		
		drawWinPoints(player1,true);
		drawWinPoints(player2,false);
		graphicsContext.setFill(Color.BLACK);
	}

	private void drawWinPoints(Character player, boolean isPlayer1) {
		
		if(player.wins < 0)
			return;
		
		if(isPlayer1)
		{
			pos.x = 0.4f  * GamePlayView.FIELD_SIZE * GamePlayView.SCALEFACTOR;
			pos.y = 3.25f * GamePlayView.FIELD_SIZE * GamePlayView.SCALEFACTOR;
		}
		else
		{
			pos.x = 1f * (float)PXMapHandler.X_FIELDS* (float)GamePlayView.FIELD_SIZE  - 2f  * (float)GamePlayView.FIELD_SIZE * (float)GamePlayView.SCALEFACTOR;
			pos.y =  3.25f 						   * (float)GamePlayView.FIELD_SIZE  * (float)GamePlayView.SCALEFACTOR;
		}
		
		for(int i = 0; i< player.wins;i++)
		{
		   int factor = (isPlayer1) ? 1 : -1;
		   graphicsContext.drawImage(winPoint, pos.x + ((float)factor*i*winPoint.getWidth()*GamePlayView.SCALEFACTOR), pos.y, winPoint.getWidth() * GamePlayView.SCALEFACTOR,
				   winPoint.getHeight() * GamePlayView.SCALEFACTOR);
		}
		
	}

	private void drawComboNumbers(Character player,boolean IsPlayer1) {
		if(player.getCombos() <= 1)
			return;
		
		if (IsPlayer1) {
		      this.pos.x = 37.5F;
		    } else {
		      this.pos.x = 750.0F;
		    }
		    this.pos.y = 150.0F;
		    
		    this.graphicsContext.drawImage(this.comboWriting, this.pos.x, this.pos.y, this.comboWriting.getWidth() * 0.75D, 
		      this.comboWriting.getHeight() * 0.75D);
		
		String comboNumberAsString = ""+player.getCombos();
		char[] ciphers = comboNumberAsString.toCharArray();
		
		float offset = 0;
		
		//first cipher is a leading Digit with a different design
		
		//we draw the digits from left to right if we are player1
		if(IsPlayer1){	
			ComboNumber comboNumberLeading = comboNumber_leaders.get(""+ciphers[0]);
			comboNumberLeading.draw(IsPlayer1, offset);
		}
		
		//the succeeding digits will be drawn after/before the leading number
		for(int i = 1; i< ciphers.length;i++)
		{			
			ComboNumber comboNumberSuccessor = comboNumber_successors.get(""+ciphers[i]);
			if(IsPlayer1)
			{
				offset		= i*comboNumberSuccessor.getWidth();	
				comboNumberSuccessor.draw(IsPlayer1, offset);
			}
			if(!IsPlayer1){
				offset	= (ciphers.length-i)*comboNumberSuccessor.getWidth();
				comboNumberSuccessor.draw(IsPlayer1, offset);
			}
		}
		
		//we draw the digits from right to left if we are player2
		if(!IsPlayer1){	
			ComboNumber comboNumberLeading = comboNumber_leaders.get(""+ciphers[0]);
			offset	= (ciphers.length)*comboNumberLeading.getWidth();
			comboNumberLeading.draw(IsPlayer1, offset);
		}
		
	}

	private void drawLiveEnergyBar(float barWidth, int barHeight, float scale,Color color) {
		x = (int) (pos.x * GamePlayView.FIELD_SIZE * GamePlayView.SCALEFACTOR);
		y = (int) (pos.y * GamePlayView.FIELD_SIZE * GamePlayView.SCALEFACTOR);
		graphicsContext.setFill(Color.GREY);
		graphicsContext.fillRect(x, y, barWidth, barHeight * GamePlayView.SCALEFACTOR);
		graphicsContext.setFill(color);
		graphicsContext.fillRect(x, y, scale, barHeight * GamePlayView.SCALEFACTOR);
	}

	private void drawInterfaceElement(Vector2d pos, Image interface_player) {
		x = (int) (pos.x * GamePlayView.FIELD_SIZE * GamePlayView.SCALEFACTOR);
		y = (int) (pos.y * GamePlayView.FIELD_SIZE * GamePlayView.SCALEFACTOR);

		graphicsContext.drawImage(interface_player, x, y, interface_player.getWidth() * GamePlayView.SCALEFACTOR,
				interface_player.getHeight() * GamePlayView.SCALEFACTOR);

	}

}