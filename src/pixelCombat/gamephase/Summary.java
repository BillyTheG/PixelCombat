package pixelCombat.gamephase;

import javafx.scene.canvas.Canvas;
import javafx.scene.input.KeyCode;
import pixelCombat.Math.Vector2d;
import pixelCombat.controller.SummaryController;
import pixelCombat.enums.FigureState;
import pixelCombat.enums.KeyCommand;
import pixelCombat.gamephase.gamephaseelement.GamePhaseElement;
import pixelCombat.gamephase.gamephaseelement.GamePhaseFigure;
import pixelCombat.gamephase.gamephaseelement.PressEnterButton;
import pixelCombat.gamephase.gamephaseelement.figures.NatsuFigure;
import pixelCombat.gamephase.gamephaseelement.figures.RuffyFigure;
import pixelCombat.gamephase.gamephaseelement.figures.ZorroFigure;
import pixelCombat.model.PXMapHandler;
import pixelCombat.utils.Console;
import pixelCombat.utils.GameEngine;
import pixelCombat.utils.GamePhase;
import pixelCombat.utils.GamePhaseVisitor;
import pixelCombat.view.gamephases.SummaryView;

public class Summary extends GamePhase {

	private SummaryView summarySelectionView;
	private SummaryController summarySelectionController;
	private GamePhaseFigure winner_figure;
	private Canvas canvas;
	private boolean player1Wins;
	private PressEnterButton	enter_button;
	
	// all figures

	public Summary(GameEngine engine, Canvas canvas, Console console) {
		super(engine, console);
		this.summarySelectionView = new SummaryView(this, canvas);
		this.summarySelectionController = new SummaryController(engine, summarySelectionView, this);
		this.canvas = canvas;
		this.render = summarySelectionView;
		this.controller = summarySelectionController;
		this.enter_button				= new PressEnterButton(this,new Vector2d(),true);
		this.enter_button.getPos().x	= PXMapHandler.X_FIELDS*	1f/2f;				
		this.enter_button.getPos().y	= PXMapHandler.Y_FIELDS* 	6f/7f;
		this.elements.add(enter_button);
		
		
		// init(canvas);
	}

	public void init(Canvas canvas, String characterName, boolean player1Wins) {
		this.setPlayer1Wins(player1Wins);
		matchCharacter(characterName);
		winner_figure.changeToVictoryAnim();
		engine.stopMP3();
		engine.startMenuMusic();
	}

	@Override
	public void update(long deltaTime) {
		float delta = ((float) deltaTime) / 1000000000.0f;

		winner_figure.update(delta);

		for (GamePhaseElement el : this.elements)
			el.update(delta);

		if (summarySelectionController.enter_pressed) {
			summarySelectionController.enter_pressed = false;
			accept(engine.getVisitor(), false);
		}
	}

	@Override
	public void handleCommand(KeyCode kc, boolean hold) {
		switch (kc) {
		case ENTER:
			controller.onKey(KeyCommand.ENTER, hold);
			break;
		case BACK_SPACE:
			controller.onKey(KeyCommand.BACK_SLASH, hold);
			break;
		case F9:
			this.engine.getGameStage().setFullScreen(false);
			break;
		case F10:
			this.engine.getGameStage().setFullScreen(true);
			break;
		default:
			break;
		}

	}

	public void matchCharacter(String selected_char) {
		switch (selected_char) {
		case "Ruffy":
			winner_figure = new RuffyFigure(this, new Vector2d(0, 0), canvas);
			break;
		case "Natsu":
			winner_figure = new NatsuFigure(this, new Vector2d(0, 0), canvas);
			break;
		case "Zorro":
			winner_figure = new ZorroFigure(this, new Vector2d(0, 0), canvas);
			break;
		default:
			break;

		}
	}

	@Override
	public void accept(GamePhaseVisitor visitor, boolean forward) {
		engine.setGamePhase(visitor.visit(this));
	}

	/**
	 * @return the player2_selection
	 */
	public GamePhaseFigure getWinnerFigure() {
		return winner_figure;
	}

	/**
	 * @param player2_selection
	 *            the player2_selection to set
	 */
	public void setWinnerFigure(GamePhaseFigure player2_selection) {
		this.winner_figure = player2_selection;
	}

	@Override
	public void reset() {
		this.winner_figure.setReady(false);
		this.winner_figure.changeToStandAnim();
		this.winner_figure.setState(FigureState.STAND);
	}

	public boolean isPlayer1Wins() {
		return player1Wins;
	}

	public void setPlayer1Wins(boolean player1Wins) {
		this.player1Wins = player1Wins;
	}

}
