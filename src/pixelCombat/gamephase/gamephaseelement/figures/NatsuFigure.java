package pixelCombat.gamephase.gamephaseelement.figures;

import javafx.scene.canvas.Canvas;
import pixelCombat.Math.Vector2d;
import pixelCombat.enums.FigureState;
import pixelCombat.gamephase.gamephaseelement.GamePhaseFigure;
import pixelCombat.utils.GamePhase;
import pixelCombat.view.FigureAnimation;
import pixelCombat.view.gamephases.GamePlayView;

public class NatsuFigure extends GamePhaseFigure {

	public NatsuFigure(GamePhase gamePhase, Vector2d pos, Canvas canvas) {
		super(gamePhase, pos, canvas);

		this.stand.add(GamePlayView.loadImage("/images/natsu/IMG_Natsu_Stand_1_re.png"));
		this.stand.add(GamePlayView.loadImage("/images/natsu/IMG_Natsu_Stand_2_re.png"));
		this.stand.add(GamePlayView.loadImage("/images/natsu/IMG_Natsu_Stand_3_re.png"));
		this.stand.add(GamePlayView.loadImage("/images/natsu/IMG_Natsu_Stand_4_re.png"));

		this.selected.add(GamePlayView.loadImage("/images/natsu/IMG_Natsu_intro_1.png"));
		this.selected.add(GamePlayView.loadImage("/images/natsu/IMG_Natsu_intro_2.png"));
		this.selected.add(GamePlayView.loadImage("/images/natsu/IMG_Natsu_intro_3.png"));
		this.selected.add(GamePlayView.loadImage("/images/natsu/IMG_Natsu_intro_4.png"));
		this.selected.add(GamePlayView.loadImage("/images/natsu/IMG_Natsu_intro_5.png"));

		this.deselected.add(GamePlayView.loadImage("/npc/IMG_NPC_Natsu_1.png"));
		this.deselected.add(GamePlayView.loadImage("/npc/IMG_NPC_Natsu_2.png"));
		this.deselected.add(GamePlayView.loadImage("/npc/IMG_NPC_Natsu_3.png"));
		this.deselected.add(GamePlayView.loadImage("/npc/IMG_NPC_Natsu_4.png"));
		this.deselected.add(GamePlayView.loadImage("/npc/IMG_NPC_Natsu_5.png"));

		this.victory.add(GamePlayView.loadImage("/images/natsu/IMG_Natsu_intro_1.png"));
		this.victory.add(GamePlayView.loadImage("/images/natsu/IMG_Natsu_intro_2.png"));
		this.victory.add(GamePlayView.loadImage("/images/natsu/IMG_Natsu_intro_3.png"));
		this.victory.add(GamePlayView.loadImage("/images/natsu/IMG_Natsu_intro_4.png"));
		this.victory.add(GamePlayView.loadImage("/images/natsu/IMG_Natsu_intro_5.png"));

		this.stand_times.add(250f);
		this.stand_times.add(150f);
		this.stand_times.add(250f);
		this.stand_times.add(150f);

		this.selected_times.add(250f);
		this.selected_times.add(250f);
		this.selected_times.add(250f);
		this.selected_times.add(250f);
		this.selected_times.add(250f);

		this.deselected_times.add(250f);
		this.deselected_times.add(250f);
		this.deselected_times.add(250f);
		this.deselected_times.add(250f);
		this.deselected_times.add(250f);

		this.victory_times.add(250f);
		this.victory_times.add(250f);
		this.victory_times.add(250f);
		this.victory_times.add(250f);
		this.victory_times.add(250f);

		this.selectedSound = "/audio/natsu_selected_sound.wav";
		this.deselectedSound = "/audio/natsu_deselected_sound.wav";
		this.victorySound = "/audio/Natsu_Victory.wav";

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
		switcher = true;

	}

	@Override
	public void changeToVictoryAnim() {	
		//	sound(deselectedSound);
		resetStats();
		setState(FigureState.VICTORY);	
		getAnimator().setup(victory, victory_times, true);

	}

}
