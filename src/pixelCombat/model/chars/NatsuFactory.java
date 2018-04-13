package pixelCombat.model.chars;

import java.util.Map;

import pixelCombat.Math.Vector2d;
import pixelCombat.model.Character;
import pixelCombat.model.chars.natsu.NatsuAttackLogic;
import pixelCombat.model.chars.natsu.NatsuBoxLogic;
import pixelCombat.model.chars.natsu.NatsuStatusLogic;
import pixelCombat.model.chars.natsu.NatsuViewLogic;
import pixelCombat.model.factory.PCCharacterFactoryInterface;
import pixelCombat.projectiles.ProjectileManager;

public class NatsuFactory implements PCCharacterFactoryInterface<Natsu> {

	@Override
	public Natsu createCharacter(Map<String, Object> stats,ProjectileManager projectile_manager) {
		Vector2d pos = (Vector2d)stats.get("pos");
		String playerName = (String)stats.get("name");
		Vector2d direction = (Vector2d)stats.get("dir");
		Integer hitPoints = (Integer)stats.get("hp");
		
		Integer maxHitPoints = (Integer)stats.get("mHP");
//		Float movementSpeed = (Float)stats.get("movementSpeed");
		
		Natsu u = new Natsu(playerName, hitPoints, pos, direction, maxHitPoints,projectile_manager);
		

		NatsuStatusLogic statusLogic = new NatsuStatusLogic(u);
		NatsuAttackLogic attackLogic = new NatsuAttackLogic(u);;
		NatsuBoxLogic 	boxLogic = new NatsuBoxLogic(u,projectile_manager.getConsole());
		NatsuViewLogic  viewLogic = new NatsuViewLogic(u);
		
		u.statusLogic = statusLogic;
		u.attackLogic = attackLogic;
		u.boxLogic = boxLogic;
		u.viewLogic = viewLogic;
		
		
//		Integer magicPoints = (Integer)stats.get("mp");
//		Integer maxMagicPoints = (Integer)stats.get("mMP");
//		Float attackSpeed = (Float)stats.get("attackSpeed");
		

		
		if(stats.containsKey(Character.STAT_ATTACKSPEED)) {
			u.setAttackSpeed((Float)stats.get(Character.STAT_ATTACKSPEED));
		}
		if(stats.containsKey(Character.STAT_MAXLIFEPOINTS)){
			u.setMaxLifepoints((Integer)stats.get(Character.STAT_MAXLIFEPOINTS));
		}
		if(stats.containsKey(Character.STAT_ACTUALLIFEPOINTS)){
			u.setActualLifepoints((Integer)stats.get(Character.STAT_ACTUALLIFEPOINTS));
		}
		if(stats.containsKey(Character.STAT_STRENGTH)){
			u.setStrength((Integer)stats.get(Character.STAT_STRENGTH));
		}
		if(stats.containsKey(Character.STAT_REGENERATEMAGICTIME)){
			u.setRegenerateMagicTime((Float)stats.get(Character.STAT_REGENERATEMAGICTIME));
		}
		if(stats.containsKey(Character.STAT_MOVEMENTSPEED)){
			u.physics.setMaximumSpeed((Float)stats.get(Character.STAT_MOVEMENTSPEED));
		}
//		if(stats.containsKey(Character.STAT_ACTUALMAGICPOINTS)){
//			u.setActualMagicpoints((Integer)stats.get(Character.STAT_ACTUALMAGICPOINTS));
//		}
		if(stats.containsKey(Character.STAT_MAXMAGICPOINTS)){
			u.setMaxMagicpoints((Integer)stats.get(Character.STAT_MAXMAGICPOINTS));
		}
		return u;
	}
}