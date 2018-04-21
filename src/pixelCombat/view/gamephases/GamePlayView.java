package pixelCombat.view.gamephases;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.transform.Rotate;
import pixelCombat.Math.Vector2d;
import pixelCombat.arenas.BackGroundEffect;
import pixelCombat.arenas.PXMap;
import pixelCombat.artworks.ArtWork;
import pixelCombat.artworks.ArtWorkCover;
import pixelCombat.dusts.Finish_BG;
import pixelCombat.dusts.WandBlow;
import pixelCombat.effects.NextRoundBorder;
import pixelCombat.effects.UpAndDownBorder;
import pixelCombat.gamephase.GamePlay;
import pixelCombat.model.Character;
import pixelCombat.model.Dust;
import pixelCombat.model.PXMapHandler;
import pixelCombat.model.Particle;
import pixelCombat.model.Spirit;
import pixelCombat.npc.NPC;
import pixelCombat.projectiles.Projectile;
import pixelCombat.utils.Console;
import pixelCombat.utils.GameEngine;
import pixelCombat.view.ImageLoader;
import pixelCombat.view.InterfaceView;
import pixelCombat.view.Renderer;

public class GamePlayView extends Renderer {

	private InterfaceView interfaceView;

	// protected Image IMG_BACKGROUND = loadImage("/images/testBackground.png");
	protected static Image IMG_BACKGROUND = loadImage("/images/2.png");
	protected Image IMG_BACKGROUND1 = loadImage("/images/1.png");
	protected Image IMG_P1_Text = loadImage("/images/p1_text.png");
	protected Image IMG_P2_Text = loadImage("/images/p2_text.png");
	public static float CAMERA_X_SPEED = 10f;
	public static float CAMERA_Y_SPEED = 15f;

	public static float CAMERA_X_SPEED_Default = 10f;
	public static float CAMERA_Y_SPEED_Default = 15f;

	// protected Image IMG_BACKGROUND2 = loadImage("/images/2.png");
	protected float levelMin = 0;
	public static float levelMax;// = (float) IMG_BACKGROUND.getWidth();
	public static float levelMaxy;// = (float) IMG_BACKGROUND.getHeight();
	protected float CX = 0;
	protected float CY = 0;
	double screenX = 0;
	double screenY = 0;

	protected float delta_Y = 0f;

	public volatile ImageLoader imageLoader;
	private Map<String, ArrayList<Image>> player1 = new HashMap<String, ArrayList<Image>>();
	private Map<String, ArrayList<Image>> player2 = new HashMap<String, ArrayList<Image>>();

	// Hilfsvariablen fuer Animatorfunktion

	int actualPicP1 = 0;
	int actualPicP2 = 0;
	ArrayList<Image> oldBufferP1;
	ArrayList<Image> oldBufferP2;
	Map<Integer, Integer> positionBuffer = new HashMap<Integer, Integer>();
	ArrayList<Integer> testBall = new ArrayList<Integer>();

	//
	private Character char_player1;
	private Character char_player2;

	// Hilsvariablen render-Funktion

	private PXMapHandler arena;
	private Finish_BG finish_bg;
	private float x;
	private float y;
	private Vector2d target = new Vector2d(0f, 0f);
	private GraphicsContext graphicsContext;
	private float shakeTimer = 0f;
	private float xOffset = 0f;
	private float yOffset = 0f;
	private float SHAKE_TIME_MS = 0.5f;
	private boolean shakeDirection = true;
	private float SHAKE_OFFSET = 0.2f;
	private float shakeSpeed = 5f;

	private boolean initialized;

	public Console console;

	private GamePlay gamePlay;

	private ColorAdjust monochrome;

	private PXMap map;

	public static final int FIELD_SIZE = 50;
	public static final int HALF_FIELD_SIZE = FIELD_SIZE / 2;
	public static final int SCREEN_WIDTH = PXMapHandler.X_FIELDS * FIELD_SIZE;
	public static final int SCREEN_HEIGHT = PXMapHandler.Y_FIELDS * FIELD_SIZE;

	public static final float SCALEFACTOR = 0.75f;
	private WandBlow wandBlow;
	private ArtWorkCover artWorkCover = new ArtWorkCover();

	// -------------------------------------------------------
	// ende Variablen

	public GamePlayView(PXMapHandler arena, Canvas canvas, GamePlay gamePlay) {
		super(canvas);
		this.console = gamePlay.console;
		this.canvas = canvas;
		this.arena = arena;
		this.gamePlay = gamePlay;
		CX = SCREEN_WIDTH / 2;
		CY = SCREEN_HEIGHT / 2;
		this.imageLoader = new ImageLoader(arena, player1, player2, console);
		this.wandBlow = new WandBlow(new Vector2d(), true);
	}

	/**
	 * Initializes the view
	 * 
	 * @param canvas
	 *            canvas
	 */
	public void init() {

		map = arena.getMap();
		char_player2 = arena.getPlayer2();
		char_player1 = arena.getPlayer1();
		loadNeededImagePlayers();
		this.finish_bg = new Finish_BG(new Vector2d(0, 0), true);

		canvas.setHeight(GamePlayView.SCREEN_HEIGHT);
		canvas.setWidth(GamePlayView.SCREEN_WIDTH);
		setGraphicsContext(canvas.getGraphicsContext2D());
		interfaceView = new InterfaceView(getGraphicsContext(), this);

		if (arena.getMap().getHeight() > PXMapHandler.Y_FIELDS * FIELD_SIZE)
			delta_Y = (float) (-PXMapHandler.Y_FIELDS * FIELD_SIZE + arena.getMap().getHeight());
		else
			delta_Y = (float) (PXMapHandler.Y_FIELDS * FIELD_SIZE - arena.getMap().getHeight());

		levelMaxy -= delta_Y;
		setInitialized(true);

		monochrome = new ColorAdjust();
		monochrome.setSaturation(-1);

		// debugView = new DebugView(graphicsContext, arena);
	}

	/**
	 * Safe way for loading images from resource folder without <br>
	 * crashing on invalid url
	 * 
	 * @param url
	 *            String
	 * @return Image
	 */
	public static Image loadImage(String url) {
		try {
			Image img = new Image(url);
			return img;
		} catch (Exception e) {
			return null;
		}
	}

	public void loadNeededImagePlayers() {

		Thread thread = new Thread(imageLoader);
		thread.start();

		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}

	public void render(long delta) {
		if (!this.gamePlay.isInitialized())
			return;

		List<Character> chars = new ArrayList<Character>(arena.getCharacter());
		Character player1 = arena.getPlayer1();
		Character player2 = arena.getPlayer2();

		checkCursor(delta);
		checkBorders();

		renderBackGround(delta, player1, player2);
		drawNPCs(player1, player2);
		
		//ArtWorks as Bg
		drawArtworks(new ArrayList<ArtWork>(arena.getArtWorks()),true);
		drawSpirits(arena.getSpirits());
		drawChars(chars, (float) delta / 1000000000f);
		
		drawProjectiles(new ArrayList<Projectile>(arena.getProjectiles()));
		drawParticles(new ArrayList<Particle>(arena.getParticles()));
		drawDusts(new ArrayList<Dust>(player1.getReleasedDusts()));
		drawDusts(new ArrayList<Dust>(player2.getReleasedDusts()));
		drawWeather(player1, player2);
		drawForeGrounds(player1, player2);

		if (!player1.isBorderEffecting() && !player2.isBorderEffecting())
			interfaceView.render();
		drawAvatars(player1, player2);
		
		artWorkCover.update((float) delta / 1000000000f);
		//ArtWorks as Fg
		drawArtworks(new ArrayList<ArtWork>(arena.getArtWorks()),false);

		drawBorders(player1, player2);
	}

	private void drawSpirits(List<Spirit> list) {
		Vector2d pos;
		for (Spirit spirit : list) {
			pos = spirit.getPos();
			x = (int) (pos.x * FIELD_SIZE - screenX + CX);
			y = (int) (pos.y * FIELD_SIZE - screenY + CY);
			spirit.draw(getGraphicsContext(), x, y);
		}

	}

	private void drawForeGrounds(Character player1, Character player2) {
		if (map.getForeGround() == null)
			return;
		if (player1.superAttacking || player2.superAttacking)
			return;

		if (player1.finishing || player2.finishing)
			return;

		if (this.arena.getBackGroundEffect() != BackGroundEffect.NONE)
			return;
		getGraphicsContext().drawImage(map.getForeGround(), (-screenX + CX) / 5,
				map.getHeight() - delta_Y - 80 - screenY + CY);

	}

	private void drawWeather(Character player1, Character player2) {
		if (map.getCurrentWeatherBG() == null)
			return;
		if (player1.superAttacking || player2.superAttacking)
			return;

		if (player1.finishing || player2.finishing)
			return;

		if (this.arena.getBackGroundEffect() != BackGroundEffect.NONE)
			return;

		Vector2d pos = new Vector2d(PXMapHandler.X_FIELDS / 2f, PXMapHandler.Y_FIELDS / 2);
		x = (int) (pos.x * FIELD_SIZE);
		y = (int) (pos.y * FIELD_SIZE);
		getGraphicsContext().drawImage(map.getCurrentWeatherBG(), x - map.getCurrentWeatherBG().getWidth() / 2f,
				y - map.getCurrentWeatherBG().getHeight() / 2f);
	}

	private void drawBorders(Character player1, Character player2) {
		if (player1.isBorderEffecting()) {
			UpAndDownBorder upAndDownBorder = player1.getUpAndDownBorder();
			getGraphicsContext().drawImage(upAndDownBorder.draw(), 0, 0,
					upAndDownBorder.draw().getWidth() * GamePlayView.SCALEFACTOR,
					upAndDownBorder.draw().getHeight() * GamePlayView.SCALEFACTOR);
		}
		if (player2.isBorderEffecting()) {
			UpAndDownBorder upAndDownBorder = player2.getUpAndDownBorder();
			getGraphicsContext().drawImage(upAndDownBorder.draw(), 0, 0,
					upAndDownBorder.draw().getWidth() * GamePlayView.SCALEFACTOR,
					upAndDownBorder.draw().getHeight() * GamePlayView.SCALEFACTOR);
		}

		if (gamePlay.roundIsEnd()) {
			NextRoundBorder nextRoundBorder = gamePlay.nextRoundBorder;
			getGraphicsContext().drawImage(nextRoundBorder.draw(), 0, 0,
					nextRoundBorder.draw().getWidth() * GamePlayView.SCALEFACTOR,
					nextRoundBorder.draw().getHeight() * GamePlayView.SCALEFACTOR);

		}

	}

	public void drawAvatars(Character player1, Character player2) {

		if (player1.isBorderEffecting() || player2.isBorderEffecting())
			return;

		Vector2d pos;
		pos = player1.getPos();
		x = (int) (pos.x * FIELD_SIZE);
		y = (int) (pos.y * FIELD_SIZE);
		getGraphicsContext().drawImage(IMG_P1_Text, x - IMG_P1_Text.getWidth() * 1 / 4 - screenX + CX,
				y - 150 - screenY + CY);

		pos = player2.getPos();
		x = (int) (pos.x * FIELD_SIZE);
		y = (int) (pos.y * FIELD_SIZE);
		getGraphicsContext().drawImage(IMG_P2_Text, x - IMG_P2_Text.getWidth() * 1 / 4 - screenX + CX,
				y - 150 - screenY + CY);

	}

	public void drawArtworks(List<ArtWork> artWorks, boolean behind) {
		Vector2d pos;

		if (artWorks.size() > 0) {
			
			boolean isSpecial = false;
			for (ArtWork artWork : artWorks)
				isSpecial = isSpecial || artWork.isSpecialArtWork();

			if (!isSpecial) {
				pos = artWorkCover.getPos();
				x = (int) (pos.x * FIELD_SIZE*SCALEFACTOR);
				y = (int) (pos.y * FIELD_SIZE*SCALEFACTOR);
				getGraphicsContext().drawImage(artWorkCover.draw(), (double)x, (double)y,
						artWorkCover.draw().getWidth()*SCALEFACTOR,artWorkCover.draw().getHeight()*SCALEFACTOR);
			}
		}
		
		
		for (ArtWork o : artWorks) {

			pos = o.getPos();
			x = (int) (pos.x * FIELD_SIZE);
			y = (int) (pos.y * FIELD_SIZE);

			float 	scaleX 				= o.getScaleX();
			float 	scaleY 				= o.getScaleY();
			double 	opacity 			= o.getOPACITY();
			boolean shouldBeDrawnBehind = o.shouldBeDrawBehind();
			
			if(shouldBeDrawnBehind == behind)
			{
				graphicsContext.setGlobalAlpha(opacity);
				if(o.isSpecialArtWork())
					getGraphicsContext().drawImage(o.draw(), (x-o.draw().getWidth()/2f)*SCALEFACTOR, (y-o.draw().getHeight()/2f)*SCALEFACTOR,
						o.draw().getWidth()*SCALEFACTOR * scaleX, o.draw().getHeight()*SCALEFACTOR * scaleY);
				else
					getGraphicsContext().drawImage(o.draw(), x, y-o.draw().getHeight()/2f,
							o.draw().getWidth()*SCALEFACTOR * scaleX, o.draw().getHeight()*SCALEFACTOR * scaleY);
				
				graphicsContext.setGlobalAlpha(1);
			}
			
			
		}
		graphicsContext.setGlobalAlpha(1);
	}

	public void drawDusts(List<Dust> dusts) {
		Vector2d pos;
		for (Dust o : dusts) {
			pos = o.getPos();
			x = (int) (pos.x * FIELD_SIZE);
			y = (int) (pos.y * FIELD_SIZE);
			if (o.faceRight)
				getGraphicsContext().drawImage(o.draw(), x - screenX + CX - o.draw().getWidth() / 2f,
						y - o.draw().getHeight() / 2f - fixPic(o.draw()) - screenY + CY);
			else
				getGraphicsContext().drawImage(o.draw(), x - screenX + CX + o.draw().getWidth() / 2f,
						y - o.draw().getHeight() / 2f - fixPic(o.draw()) - screenY + CY, -o.draw().getWidth(),
						o.draw().getHeight());
		}
	}

	public void drawParticles(List<Particle> particles) {
		Vector2d pos;
		for (Particle o : particles) {
			Color color = o.getColor();
			pos = o.getPos();
			x = (int) (pos.x * FIELD_SIZE);
			y = (int) (pos.y * FIELD_SIZE);
			float radius = o.getRadius();
			getGraphicsContext().setFill(color);
			getGraphicsContext().fillOval(x - screenX + CX, y - screenY + CY, (int) radius, (int) radius);
		}
	}

	public void drawProjectiles(List<Projectile> pl2) {
		Vector2d pos;
		for (Projectile o : pl2) {
			pos = o.getPos();
			x = (int) (pos.x * FIELD_SIZE);
			y = (int) (pos.y * FIELD_SIZE);

			if (o.dir == 1f) {
				if (o.isRotable()) {
					graphicsContext.save();
					o.rotate(graphicsContext, o.calculateDirection(), x - screenX + CX, y - screenY + CY);
				}
				getGraphicsContext().drawImage(o.draw(), x - screenX + CX - o.draw().getWidth() / 2f,
						y - o.draw().getHeight() / 2f - fixPic(o.draw()) - screenY + CY);
			} else {
				if (o.isRotable()) {
					graphicsContext.save();
					o.rotate(graphicsContext, o.calculateDirection() - 180, x - screenX + CX, y - screenY + CY);
				}
				getGraphicsContext().drawImage(o.draw(), x - screenX + CX + o.draw().getWidth() / 2f,
						y - o.draw().getHeight() / 2f - fixPic(o.draw()) - screenY + CY, -o.draw().getWidth(),
						o.draw().getHeight());
			}
			graphicsContext.restore();

		}
	}

	public void drawChars(List<Character> cl, float delta) {
		Vector2d pos;

		Character importantImplayer = null;

		for (Character p : cl) {
			pos = p.getPos();
			x = (int) (pos.x * FIELD_SIZE - screenX + CX);
			y = (int) (pos.y * FIELD_SIZE - screenY + CY);
			draw(p);

			if (p.statusLogic.isImportant() || p.statusLogic.isWinning())
				importantImplayer = p;

		}

		if (importantImplayer != null) {
			pos = importantImplayer.getPos();
			x = (int) (pos.x * FIELD_SIZE - screenX + CX);
			y = (int) (pos.y * FIELD_SIZE - screenY + CY);
			draw(importantImplayer);
		}

	}

	private void draw(Character player) {
		if (player.statusLogic.isRight())
			drawRight(x, player, y);
		else
			drawLeft(x, player, y);
	}

	public void drawNPCs(Character player1, Character player2) {
		Vector2d pos;
		if (!player1.superAttacking && !player2.superAttacking && !player1.finishing && !player2.finishing) {
			for (NPC o : arena.getNpc()) {
				pos = o.getPos();
				x = (int) (pos.x * FIELD_SIZE);
				y = (int) (pos.y * FIELD_SIZE);
				float factor = pos.y / 7.5F;

				getGraphicsContext().drawImage(o.draw(),
						(this.x - this.screenX + this.CX - o.draw().getWidth() / 2.0D) / 1.0750000476837158D,
						this.y - o.draw().getHeight() / 2.0D - fixPic2(o.draw()) - this.screenY + this.CY,
						o.draw().getWidth() * factor, o.draw().getHeight() * factor);
			}
		}
	}

	public void renderBackGround(long delta, Character player1, Character player2) {
		if (map.getCurrentBG() != null) {

			// ColorAdjust colorAdjust = new ColorAdjust();
			// colorAdjust.setContrast(0.1);
			// colorAdjust.setHue(-0.05);
			// colorAdjust.setBrightness(-0.86);
			// colorAdjust.setSaturation(0.2);

			//
			// ImageView imageView = new ImageView(IMG_BACKGROUND);
			// imageView.setEffect(colorAdjust);
			// imageView.setCache(true);
			// imageView.setCacheHint(CacheHint.SPEED);

			// getGraphicsContext().drawImage(map.getCurrentBG(), (0 - screenX +
			// CX), -1000 - screenY + CY, IMG_BACKGROUND1.getWidth() * 10,
			// IMG_BACKGROUND1.getHeight() * 10);
			getGraphicsContext().setEffect(null);
			checkEffect();
			// getGraphicsContext().setEffect(monochrome);

			// getGraphicsContext().setEffect(colorAdjust);

			getGraphicsContext().drawImage(map.getCurrentBG(), (0 - screenX + CX), -this.delta_Y - screenY + CY);
			getGraphicsContext().setEffect(null);
			// specials
			if (player1.superAttacking) {
				Dust p1Sp = player1.getSpecialBG();
				getGraphicsContext().drawImage(p1Sp.draw(), 0, 0, p1Sp.draw().getWidth() * 1.15f,
						p1Sp.draw().getHeight() * 1.15f);
			}
			if (player2.superAttacking) {
				Dust p2Sp = player2.getSpecialBG();
				getGraphicsContext().drawImage(p2Sp.draw(), 0, 0, p2Sp.draw().getWidth() * 1.15f,
						p2Sp.draw().getHeight() * 1.15f);
			}

			if (player1.finishing || player2.finishing) {
				getGraphicsContext().drawImage(finish_bg.draw(), -65, 0, finish_bg.draw().getWidth() * .85f,
						finish_bg.draw().getHeight() * 0.85f);
				finish_bg.update((float) delta / 1000000000.0f);
			}

		} else {

			getGraphicsContext().setFill(Color.BLACK);
			getGraphicsContext().fillRect(0, 0, GamePlayView.SCREEN_WIDTH, GamePlayView.SCREEN_HEIGHT);
		}
	}

	private void checkEffect() {

		switch (this.arena.getBackGroundEffect()) {
		case MONOCHROME:
			monochrome = new ColorAdjust();
			monochrome.setSaturation(-1);
			getGraphicsContext().setEffect(monochrome);
			break;
		default:
			getGraphicsContext().setEffect(null);
			break;
		}

	}

	private void checkCursor(long delta) {
		Character player1 = arena.getPlayer1();
		Character player2 = arena.getPlayer2();

		// helper variable for operations working with positions
		Vector2d pos_1 = player1.getPos();
		Vector2d pos_2 = player2.getPos();

		if (player1.statusLogic.isWinning()) {
			GamePlayView.CAMERA_X_SPEED = 35f;
			x = Math.abs(pos_1.x);
			y = pos_1.y;

		} else if (player2.statusLogic.isWinning()) {
			GamePlayView.CAMERA_X_SPEED = 35f;
			x = Math.abs(pos_2.x);
			y = pos_2.y;
		} else if (player1.statusLogic.isImportant()) {
			GamePlayView.CAMERA_X_SPEED = 25f;
			if ((!player1.isAlive() || player1.statusLogic.isFocused())) {
				x = Math.abs(pos_1.x);
				y = pos_1.y;
			} else if (player2.getPos().x < 0.25f + getTarget().x - CX / 50f
					|| player2.getPos().x > getTarget().x + CX / 50f - 0.25f) {
				x = Math.abs(pos_1.x);
				y = pos_1.y;
			} else {
				x = Math.abs(pos_1.x + pos_2.x) / 2f;
				y = pos_1.y;
			}
		} else if (player2.statusLogic.isImportant()) {
			GamePlayView.CAMERA_X_SPEED = 25f;
			if ((!player2.isAlive() || player2.statusLogic.isFocused())) {
				x = Math.abs(pos_2.x);
				y = pos_2.y;
			} else if (player1.getPos().x < 0.25f + getTarget().x - CX / 50f
					|| player1.getPos().x > getTarget().x + CX / 50f - 0.25f) {
				x = Math.abs(pos_2.x);
				y = pos_2.y;
			} else {
				x = Math.abs(pos_1.x + pos_2.x) / 2f;
				y = pos_2.y;
			}
		} else {
			x = Math.abs(pos_1.x + pos_2.x) / 2f;
			y = (pos_1.y + pos_2.y) / 2f;

			if (getTarget().x != 0 && Math.abs(pos_1.x - pos_2.x) > CX / 50f)
				return;

		}

		if (!player1.statusLogic.isImportant() && !player2.statusLogic.isImportant()
				&& !player1.statusLogic.isDead() && !player2.statusLogic.isDead())
			GamePlayView.CAMERA_X_SPEED = CAMERA_X_SPEED_Default;

		if (player1.shaking || player2.shaking) {
			if (pos_1.distance(pos_2) <= GamePlayView.SCREEN_HEIGHT / GamePlayView.FIELD_SIZE
					&& getTarget().y <= (pos_1.y + pos_2.y) / 2f - 1f)
				updateShake(delta);
			else {
				player1.shaking = false;
				player2.shaking = false;
			}
		}
		// else if (!player1.statusLogic.isWinning()
		// && !player2.statusLogic.isWinning()
		// && (player1.getPos().x < 1.5f + target.x - CX / 50f
		// || player2.getPos().x > target.x + CX / 50f - 1.5f
		// || player2.getPos().x < 1.5f + target.x - CX / 50f || player1
		// .getPos().x > target.x + CX / 50f - 1.5f)
		// && !((player1.getPos().x > 1.5f + target.x - CX / 50f && player2
		// .getPos().x > target.x + CX / 50f - 1.5f) || (player2
		// .getPos().x > 1.5f + target.x - CX / 50f && player1
		// .getPos().x > target.x + CX / 50f - 1.5f))
		// && !((player2.getPos().x < 1.5f + target.x - CX / 50f && player1
		// .getPos().x < target.x + CX / 50f - 1.5f) || (player1
		// .getPos().x < 1.5f + target.x - CX / 50f && player2
		// .getPos().x < target.x + CX / 50f - 1.5f))
		//
		// )
		// return;

		else {
			if (getTarget().x == 0f) {
				getTarget().x = x;
			}
			float dir_x = Math.signum(x - getTarget().x);
			// Bringe den Cursor auf die Mitte falls Target schon erreicht
			// (Juristierung)
			if ((getTarget().x <= x && dir_x < 0f) || (getTarget().x >= x && dir_x > 0f)) {
				getTarget().x = x;
			} else // Bewege den Cursor zur neuen Mitte des Bildschirs
			if ((getTarget().x >= x && dir_x < 0f) || (getTarget().x <= x && dir_x > 0f)) { // Kamera
				// viel
				// zu
				// langsam,
				// muss
				// also
				// schneller
				// verfolgen
				if ((!player1.isAlive() && CAMERA_X_SPEED < player1.physics.VX)
						|| (!player2.isAlive() && CAMERA_X_SPEED < player2.physics.VX))
					getTarget().x += dir_x * CAMERA_X_SPEED * 10f * (float) delta / 1000000000.0f;
				else
					getTarget().x += dir_x * CAMERA_X_SPEED * (float) delta / 1000000000.0f;
				if (dir_x < 0f && getTarget().x <= x)
					getTarget().x = x;
				if (dir_x > 0f && getTarget().x >= x)
					getTarget().x = x;
			}

			// Rände dürfen nicht passiert werden, dabei wird Bildschirmhälfte
			// als Maß genommen
			if (getTarget().x > levelMax / 50f - CX / 50f)
				getTarget().x = levelMax / 50f - CX / 50f;
			if (getTarget().x < CX / 50f)
				getTarget().x = CX / 50f;

			// GLEICHES für Y
			if (getTarget().y == 0f) {
				getTarget().y = y;
			}
			float dir_y = Math.signum(y - getTarget().y);
			// Bringe den Cursor auf die Mitte falls Target schon erreicht
			// (Juristierung)
			if ((getTarget().y <= y && dir_y < 0f) || (getTarget().y >= y && dir_y > 0f)) {
				getTarget().y = y;

			} else // Bewege den Cursor zur neuen Mitte des Bildschirs
			if ((getTarget().y >= y && dir_y < 0f) || (getTarget().y <= y && dir_y > 0f)) {

				getTarget().y += dir_y * CAMERA_Y_SPEED * (float) delta / 1000000000.0f;
				if (dir_y < 0f && getTarget().y <= y)
					getTarget().y = y;
				if (dir_y > 0f && getTarget().y >= y)
					getTarget().y = y;
			}

			// Rände dürfen nicht passiert werden, dabei wird Bildschirmhälfte
			// als Maß genommen
			if (getTarget().y < -delta_Y / 50f + CY / 50f)
				getTarget().y = -delta_Y / 50f + CY / 50f;
			if (getTarget().y > CY / 50f)
				getTarget().y = CY / 50f;

		}

		// Virtuellen Bildverschiebung X
		if (getTarget().x * GamePlayView.FIELD_SIZE < CX) // Cursor befindet
															// sich in
															// der
															// ersten(linken)
															// Bildschirmhaelfte
		{
			screenX = CX;
		} else if (getTarget().x * GamePlayView.FIELD_SIZE > levelMax - CX) // Cursor
		// befindet
		// sich
		// an
		// der
		// rechtesten
		// Bildschirmhaelfte
		{
			screenX = levelMax - CX;
		} else // Charakter befindet sich dazwischen
		{
			screenX = getTarget().x * GamePlayView.FIELD_SIZE;
		}

		// Virtuellen Bildverschiebung Y

		// Cursor befindet sich in der ersten(obersten) Bildschirmhaelfte
		if (getTarget().y * GamePlayView.FIELD_SIZE > CY) {
			screenY = CY;
		}

		else if (getTarget().y * GamePlayView.FIELD_SIZE < -delta_Y + CY) // Cursor
																			// befindet
																			// sich
																			// an
																			// der
																			// untersten
																			// Bildschirmhaelfte
		{
			screenY = -levelMaxy + CY;
		} else // Charakter befindet sich dazwischen
		{
			screenY = getTarget().y * GamePlayView.FIELD_SIZE;
		}

	}

	private void checkBorders() {
		Character player1 = this.arena.getPlayer1();
		Character player2 = this.arena.getPlayer2();
		if ((player1.isBorderEffecting()) || (player2.isBorderEffecting())
			|| player1.statusLogic.isFocused() || player2.statusLogic.isFocused()) {
			return;
		}
		checkLeftBorder(player1, player2);
		checkLeftBorder(player2, player1);
		checkRightBorder(player1, player2);
		checkRightBorder(player2, player1);
		if ((player1.statusLogic.isMoving()) && (player1.physics.VX == 0.0F)) {
			player1.physics.VX = (player1.getDir() * 1.0F);
		}
		if ((player2.statusLogic.isMoving()) && (player2.physics.VX == 0.0F)) {
			player2.physics.VX = (player2.getDir() * 1.0F);
		}
	}

	private void checkLeftBorder(Character player1, Character player2) {
		if (((player2.getPos().x < 1.5F + getTarget().x - this.CX / 50.0F) && (player1.isAlive())
				&& (player2.isAlive())) || (player2.getPos().x < 1.5F)) {
			if (player2.getPos().x < 1.5F) {
				player2.getPos().x = 1.5F;
			} else {
				player2.getPos().x = (1.5F + getTarget().x - this.CX / 50.0F);
			}
			if (player2.statusLogic.isKnockback()) {
				this.wandBlow.reset(new Vector2d(player2.pos.x, player2.pos.y), false);
				player2.releasedDusts.add(this.wandBlow);
				pixelCombat.physics.PlayerPhysics tmp170_167 = player2.physics;
				tmp170_167.VX = ((float) (tmp170_167.VX * -1.05D));
				player2.physics.update(player2.delta);
				player2.statusLogic.swapDirection();
				if (this.shakeTimer == 0.0F) {
					player2.sound("/audio/Ruffy_Stamp.wav");
				}
				if (!player2.shaking) {
					player2.shaking = true;
				}
			}
		}
	}

	private void checkRightBorder(Character player1, Character player2) {
		if (((player2.getPos().x > getTarget().x + this.CX / 50.0F - 1.5F) && (player1.isAlive())
				&& (player2.isAlive())) || (player2.getPos().x > levelMax / 50.0F - 1.5F)) {
			if (player2.getPos().x > levelMax / 50.0F - 1.5F) {
				player2.getPos().x = (levelMax / 50.0F - 1.5F);
			} else {
				player2.getPos().x = (getTarget().x + this.CX / 50.0F - 1.5F);
			}
			if (player2.statusLogic.isKnockback()) {
				this.wandBlow.reset(new Vector2d(player2.pos.x, player2.pos.y), true);
				player2.releasedDusts.add(this.wandBlow);
				pixelCombat.physics.PlayerPhysics tmp194_191 = player2.physics;
				tmp194_191.VX = ((float) (tmp194_191.VX * -1.05D));
				player2.physics.update(player2.delta);
				player2.statusLogic.swapDirection();
				if (this.shakeTimer == 0.0F) {
					player2.sound("/audio/Ruffy_Stamp.wav");
				}
				if (!player2.shaking) {
					player2.shaking = true;
				}
			}
		}
	}

	// Shaker
	private void updateShake(long delta) {

		float deltaF = (float) delta / 1000000000f;

		if (shakeTimer == 0) {
			// If its the first run from the current shake, initialise x and y
			// offset to 0
			xOffset = 0;
			yOffset = 0;
		}

		// Add passed milliseconds to timer... If timer exceeds configuration,
		// shaking ends
		shakeTimer += deltaF;
		if (shakeTimer > SHAKE_TIME_MS) {
			// Shaking ends
			shakeTimer = 0;
			this.char_player1.shaking = false;
			this.char_player2.shaking = false;
			xOffset = 0;
			yOffset = 0;
		} else {
			applyScreenShake(deltaF);
		}
	}

	private void applyScreenShake(float delta) {
		// Depending on shake direction, the screen is moved either to the top
		// left, or the bottom right
		if (shakeDirection) {
			xOffset -= shakeSpeed * delta;
			if (xOffset < -SHAKE_OFFSET) {
				// SWITCH DIRECTION
				xOffset = -SHAKE_OFFSET;
				shakeDirection = !shakeDirection;
			}
			yOffset = xOffset;
		} else {
			xOffset += shakeSpeed * delta;
			if (xOffset > SHAKE_OFFSET) {
				// SWITCH DIRECTION
				xOffset = SHAKE_OFFSET;
				shakeDirection = !shakeDirection;
			}
			yOffset = xOffset;
		}

		Vector2d new_target = new Vector2d();
		new_target.x = getTarget().x + xOffset;
		new_target.y = getTarget().y + yOffset;

		if (getTarget().distance(new_target) < 1f) {
			getTarget().x = getTarget().x + xOffset;
			if (getTarget().x > levelMax / 50f - CX / 50f)
				getTarget().x = levelMax / 50f - CX / 50f;
			if (getTarget().x < CX / 50f)
				getTarget().x = CX / 50f;
			getTarget().y = getTarget().y + yOffset;
			if (getTarget().y < -delta_Y / 50f + CY / 50f)
				getTarget().y = -delta_Y / 50f + CY / 50f;
			if (getTarget().y > CY / 50f)
				getTarget().y = CY / 50f;
		} else {
			char_player1.shaking = false;
			char_player2.shaking = false;
		}

		Vector2d middle_point = new Vector2d();
		middle_point.x = (char_player1.pos.x + char_player2.pos.x) / 2f;
		middle_point.y = (char_player1.pos.y + char_player2.pos.y) / 2f;

		if (getTarget().distance(middle_point) > 10f) {
			char_player1.shaking = false;
			char_player2.shaking = false;
		}

	}

	private void drawLeft(float x, Character p, float y) {

		getGraphicsContext().save();
		getGraphicsContext().translate((2 * x), 0);
		getGraphicsContext().scale(-1, 1);
		getGraphicsContext().drawImage(p.draw(), x - (float) p.draw().getWidth() / 2f,
				y - (float) p.draw().getHeight() / 2f - fixPic(p.draw()));
		getGraphicsContext().restore();

	}

	public void rotate(GraphicsContext gc, double angle, double px, double py) {
		Rotate r = new Rotate(angle, px, py);
		gc.setTransform(r.getMxx(), r.getMyx(), r.getMxy(), r.getMyy(), r.getTx(), r.getTy());
	}

	private void drawRight(float x, Character p, float y) {
		getGraphicsContext().drawImage(p.draw(), x - (float) p.draw().getWidth() / 2f,
				y - (float) p.draw().getHeight() / 2f - fixPic(p.draw()));
	}

	private float fixPic(Image i) {
		float halfHeight = (float) (i.getHeight() / 2);
		float diff = 0f;
		if (halfHeight >= 300f)
			return 0f;
		if (100 < halfHeight)
			diff = halfHeight - 100;
		if (100 > halfHeight)
			diff = halfHeight - 100;
		return diff;
	}

	private float fixPic2(Image i) {
		float halfHeight = (float) (i.getHeight() / 2);
		float diff = 0f;
		if (halfHeight >= 800f)
			return 0f;
		if (100 < halfHeight)
			diff = halfHeight - 100;
		if (100 > halfHeight)
			diff = halfHeight - 100;
		return diff;
	}

	public Character getChar_player1() {
		return char_player1;
	}

	public Character getChar_player2() {
		return char_player2;
	}

	public Map<String, ArrayList<Image>> getPlayer1() {
		return imageLoader.getMapPlayer1();
	}

	public Map<String, ArrayList<Image>> getPlayer2() {
		return imageLoader.getMapPlayer2();
	}

	public boolean isInitialized() {
		return initialized;
	}

	public void setInitialized(boolean initialized) {
		this.initialized = initialized;
	}

	public GraphicsContext getGraphicsContext() {
		return graphicsContext;
	}

	public void setGraphicsContext(GraphicsContext graphicsContext) {
		this.graphicsContext = graphicsContext;
	}

	public void reset() {
		finish_bg.reset(new Vector2d(0, 0), true);
		x = Math.abs(char_player1.pos.x + char_player2.pos.x) / 2f;
		y = char_player1.pos.y;
		getTarget().x = 0;
		getTarget().y = 0;
	}

	/**
	 * @return the target
	 */
	public Vector2d getTarget() {
		return target;
	}

	/**
	 * @param target
	 *            the target to set
	 */
	public void setTarget(Vector2d target) {
		this.target = target;
	}

	/**
	 * Method for the sounding effects.
	 * 
	 * 
	 * @param url
	 *            , Url
	 */
	public void sound(Clip clip, String url) {

		if (!GameEngine.TOGGLE_SOUND)
			return;

		try {
			if (clip != null)
				clip.stop();
			clip = AudioSystem.getClip();
			AudioInputStream inputStream1 = AudioSystem.getAudioInputStream(Character.class.getResource(url));
			clip.open(inputStream1);
			clip.start();

		} catch (Exception e) {
		}
	}

	public void kill() {
		this.initialized = false;
		finish_bg.reset(new Vector2d(0, 0), true);
		getTarget().x = 0;
		getTarget().y = 0;

	}
}
