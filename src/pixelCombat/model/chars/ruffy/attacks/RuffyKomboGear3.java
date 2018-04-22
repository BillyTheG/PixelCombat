package pixelCombat.model.chars.ruffy.attacks;

import pixelCombat.Math.Vector2d;
import pixelCombat.dusts.JetPistoleBlueHit;
import pixelCombat.enums.ActionStates;
import pixelCombat.enums.AttackStates;
import pixelCombat.enums.GlobalStates;
import pixelCombat.model.Attack;
import pixelCombat.model.chars.Ruffy;

public class RuffyKomboGear3 extends Attack {
	private Ruffy user;

	public RuffyKomboGear3(Ruffy user, int id) {
		super(user, id);
		this.user = user;
		setRequiredEnergy(30.0F);
	}

	public void process() {
		switch (getUser().picManager.getCurrFrameIndex()) {
		case 0:
			if (getUser().isSwitcher()) {
				if (checkGear()) {
					return;
				}
				checkIfIsOnAir();
				getUser().setSwitcher(false);
			}
			break;
		case 3:
			if (!getUser().isSwitcher()) {
				this.user.getJetDust().reset(new Vector2d(this.user.pos.x, this.user.getGroundLevel() + 0.25F),
						this.user.statusLogic.isRight());
				this.user.releasedDusts.add(this.user.getJetDust());
				user.freeze = false;
				user.freeze_loop = false;
				this.user.statusLogic.setAHitDelay(false);
				getUser().sound("/audio/teleport.wav");
				this.user.pos = new Vector2d(this.user.pos.x + this.user.getDir() * 8.0F, this.user.pos.y);
				this.user.statusLogic.swapDirection();
				getUser().setSwitcher(true);
			}
			break;
		case 9:
			if (getUser().isSwitcher()) {
				getUser().sound("/audio/jet_pistole_sound.wav");
				this.user.getDashGatlingDust().reset(
						new Vector2d(this.user.pos.x + -this.user.getDir() * 2.25F, this.user.getGroundLevel()),
						this.user.statusLogic.isRight());
				this.user.releasedDusts.add(this.user.getDashGatlingDust());
				getUser().setSwitcher(false);
			}
			break;
		case 10:
			if (!getUser().isSwitcher()) {
				this.user.getJetDust().reset(new Vector2d(this.user.pos.x, this.user.getGroundLevel() + 0.25F),
						this.user.statusLogic.isRight());
				this.user.releasedDusts.add(this.user.getJetDust());

				this.user.statusLogic.setAHitDelay(false);
				getUser().sound("/audio/teleport.wav");
				getUser().sound("/audio/Gear Third 00.wav");

				this.user.pos = new Vector2d(this.user.pos.x + this.user.getDir() * 8.0F, this.user.pos.y);
				this.user.statusLogic.swapDirection();

				this.user.getDashGatlingDust().reset(
						new Vector2d(this.user.pos.x + -this.user.getDir() * 2.25F, this.user.getGroundLevel()),
						this.user.statusLogic.isRight());
				this.user.releasedDusts.add(this.user.getDashGatlingDust());
				this.user.secondTeleport = true;
				getUser().setSwitcher(true);
			}
			break;
		case 15:
			if (getUser().isSwitcher()) {
				getUser().sound("/audio/bonefusen_sound.wav");
				getUser().setSwitcher(false);
			}
			break;
		case 21:
			if (!getUser().isSwitcher()) {
				getUser().sound("/audio/gum_gum.wav");
				getUser().setSwitcher(true);
			}
			break;
		case 27:
			if (getUser().isSwitcher()) {
				this.user.getDashGatlingDust().reset(
						new Vector2d(this.user.pos.x + -this.user.getDir() * 2.25F, this.user.getGroundLevel()),
						this.user.statusLogic.isRight());
				this.user.releasedDusts.add(this.user.getDashGatlingDust());
				getUser().sound("/audio/gigant_sound.wav");
				getUser().sound("/audio/Gigant Pistol 00.wav");
				getUser().setSwitcher(false);
			}
			break;
		}
	}

	private void checkIfIsOnAir() {
		if (this.user.isAttackOnAir()) {
			int airIndex = ((Integer) this.user.getAirIndices().get("specialAttack4")).intValue();
			this.user.picManager.setCurrFrameIndex(airIndex);
			this.user.picManager.setAnimTime(this.user.picManager.getFrame(airIndex - 1).getEndTime());
		} else {
			user.superAttacking = true;
			user.freeze = true;
			user.freeze_loop = true;
			getUser().sound("/audio/Yell 01.wav");
		}
	}

	public void checkContent() {
		this.user.enemy.damage(this.user.getStrength() * 2.0F);
		getUser().sound("/audio/meleehit2.wav");
		getUser().sound(this.user.enemy.cry());

		JetPistoleBlueHit currentBlueHit = (JetPistoleBlueHit) this.user.getJetPistole_Blues()
				.get(this.user.getJetPistoleBlue_Id());
		this.user.setJetPistoleBlue_Id((this.user.getJetPistoleBlue_Id() + 1) % this.user.getJetPistole_Blues().size());

		currentBlueHit.dustAnimator.start();
		currentBlueHit.pos.x = this.user.enemy.pos.x;
		currentBlueHit.pos.y = (this.user.enemy.pos.y + 2.0F);
		currentBlueHit.dead = false;
		this.user.releasedDusts.add(currentBlueHit);
		if (!this.user.shaking) {
			this.user.shaking = true;
		}
		if (!this.user.enemy.statusLogic.isKnockback()) {
			this.user.enemy.setKnockbackRange(1.0F);
			this.user.enemy.setKnockbackHeight(-37.0F);
			this.user.enemy.checkOnAir();
			this.user.enemy.statusLogic.setActionStates(ActionStates.STAND);
			this.user.enemy.statusLogic.setGlobalStatus(GlobalStates.KNOCKBACK);
			if (this.user.secondTeleport) {
				this.user.shaking = true;
				this.user.enemy.damage(this.user.getStrength() * 15.0F);
				this.user.enemy.setKnockbackHeight(-37.0F);
				this.user.enemy.setKnockbackRange(50.0F);
			}
		} else if (this.user.secondTeleport) {
			this.user.enemy.damage(this.user.getStrength() * 15.0F);
			this.user.comboTouch(this.user.enemy, -17.0F, 50.0F);
			this.user.shaking = true;
		} else {
			this.user.comboTouch(this.user.enemy, -37.0F, 45.0F);
		}
		this.user.statusLogic.setAHitDelay(true);
	}

	private boolean checkGear() {
		if (!this.user.isGear2On()) {
			return false;
		}
		this.user.giveEnergyBack(this);
		this.user.attackLogic.setAttackStatus(AttackStates.notAttacking);

		return true;
	}

	public void checkFinished() {
		this.user.setAttackOnAir(false);
		resetStats();
	}

	public boolean isAttacking() {
		return this.user.getAttackLogic().isSpecialAttacking4();
	}

	public void resetStats() {
		user.superAttacking = false;
		user.freeze = false;
		user.freeze_loop = false;
		this.user.releasedDusts.remove(this.user.getDashGatlingDust());
		this.user.secondTeleport = false;
	}
}
