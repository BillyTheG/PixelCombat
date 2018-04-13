package pixelCombat.gamephase.gamephaseelement.figures;

import javafx.scene.canvas.Canvas;
import pixelCombat.Math.Vector2d;
import pixelCombat.enums.FigureState;
import pixelCombat.gamephase.gamephaseelement.GamePhaseFigure;
import pixelCombat.utils.GamePhase;
import pixelCombat.view.FigureAnimation;
import pixelCombat.view.gamephases.GamePlayView;

public class RuffyFigure extends GamePhaseFigure {

	public RuffyFigure(GamePhase gamePhase, Vector2d pos, Canvas canvas) {
		super(gamePhase, pos, canvas);

		this.stand.add(GamePlayView.loadImage("/images/ruffy/IMG_Ruffy_Stand_1_re.png"));
		this.stand.add(GamePlayView.loadImage("/images/ruffy/IMG_Ruffy_Stand_2_re.png"));
		this.stand.add(GamePlayView.loadImage("/images/ruffy/IMG_Ruffy_Stand_3_re.png"));

		this.selected.add(GamePlayView.loadImage("/images/ruffy/IMG_Ruffy_intro_1.png"));
		this.selected.add(GamePlayView.loadImage("/images/ruffy/IMG_Ruffy_intro_2.png"));
		this.selected.add(GamePlayView.loadImage("/images/ruffy/IMG_Ruffy_intro_3.png"));
		this.selected.add(GamePlayView.loadImage("/images/ruffy/IMG_Ruffy_intro_4.png"));
		this.selected.add(GamePlayView.loadImage("/images/ruffy/IMG_Ruffy_intro_5.png"));

		this.deselected.add(GamePlayView.loadImage("/images/ruffy/IMG_Ruffy_Win_0_re.png"));
		this.deselected.add(GamePlayView.loadImage("/images/ruffy/IMG_Ruffy_Win_1_re.png"));
		this.deselected.add(GamePlayView.loadImage("/images/ruffy/IMG_Ruffy_Win_2_re.png"));

		this.victory.add(GamePlayView.loadImage("/images/ruffy/IMG_Ruffy_Victory_1_re.png"));
		this.victory.add(GamePlayView.loadImage("/images/ruffy/IMG_Ruffy_Victory_2_re.png"));
		this.victory.add(GamePlayView.loadImage("/images/ruffy/IMG_Ruffy_Victory_3_re.png"));
		this.victory.add(GamePlayView.loadImage("/images/ruffy/IMG_Ruffy_Victory_4_re.png"));
		this.victory.add(GamePlayView.loadImage("/images/ruffy/IMG_Ruffy_Victory_5_re.png"));

		this.stand_times.add(350f);
		this.stand_times.add(350f);
		this.stand_times.add(350f);

		this.selected_times.add(150f);
		this.selected_times.add(250f);
		this.selected_times.add(150f);
		this.selected_times.add(250f);
		this.selected_times.add(280f);

		this.deselected_times.add(350f);
		this.deselected_times.add(450f);
		this.deselected_times.add(750f);

		this.victory_times.add(250f);
		this.victory_times.add(250f);
		this.victory_times.add(250f);
		this.victory_times.add(250f);
		this.victory_times.add(250f);

		this.selectedSound = "/audio/ruffy_selected_sound.wav";
		this.deselectedSound = "/audio/ruffy_deselected_sound.wav";
		this.victorySound = "/audio/Ruffy_Victory.wav";

		this.animator = new FigureAnimation(stand, stand_times, false, this);
	}

	@Override
	public void updateDeselected() {
		switch (animator.getCurrFrameIndex()) {
		case 0:
			if (this.switcher) {
				sound(deselectedSound);
				switcher = false;
			}
			break;
		default:
			break;

		}

	}

	@Override
	public void updateSelected() {
		switch (animator.getCurrFrameIndex()) {
		case 0:
			if (this.switcher) {
				sound(selectedSound);
				switcher = false;
			}
			break;
		case 4:
			if (!this.switcher) {
				sound("/audio/Ruffy_Intro_Punch.wav");
				switcher = true;
			}
			break;
		default:
			break;

		}

	}

	@Override
	public void updateVictory() {
		switch (animator.getCurrFrameIndex()) {
		case 0:
			if (this.switcher) {
				sound(victorySound);
				switcher = false;
			}
			break;
		}
	}

	@Override
	public void resetStats() {
		this.switcher = true;

	}
	
	@Override
	public void changeToVictoryAnim() {	
		//	Character.sound(deselectedSound);
		resetStats();
		setState(FigureState.VICTORY);	
		getAnimator().setup(victory, victory_times, false,2);

	}

}
