package pixelCombat.model.factory.chars;

import java.util.Map;

import pixelCombat.Math.Vector2d;
import pixelCombat.model.Character;
import pixelCombat.model.chars.Zorro;
import pixelCombat.model.chars.zorro.ZorroAttackLogic;
import pixelCombat.model.chars.zorro.ZorroBoxLogic;
import pixelCombat.model.chars.zorro.ZorroStatusLogic;
import pixelCombat.model.chars.zorro.ZorroViewLogic;
import pixelCombat.model.factory.PCCharacterFactoryInterface;
import pixelCombat.projectiles.ProjectileManager;

public class ZorroFactory implements PCCharacterFactoryInterface<Zorro> {

	@Override
	public Zorro createCharacter(Map<String, Object> stats,ProjectileManager projectile_manager) {
		Vector2d pos = (Vector2d)stats.get("pos");
		String playerName = (String)stats.get("name");
		Vector2d direction = (Vector2d)stats.get("dir");
		Integer hitPoints = (Integer)stats.get("hp");
		
		Integer maxHitPoints = (Integer)stats.get("mHP");
//		Float movementSpeed = (Float)stats.get("movementSpeed");
//		Integer magicPoints = (Integer)stats.get("mp");
//		Integer maxMagicPoints = (Integer)stats.get("mMP");
//		Float attackSpeed = (Float)stats.get("attackSpeed");
		
		Zorro u = new Zorro(playerName, hitPoints, pos, direction, maxHitPoints,projectile_manager);
		
		ZorroStatusLogic statusLogic 	= new ZorroStatusLogic(u);
		ZorroAttackLogic attackLogic 	= new ZorroAttackLogic(u);;
		ZorroBoxLogic 	boxLogic 		= new ZorroBoxLogic(u,projectile_manager.getConsole());
		ZorroViewLogic  viewLogic 		= new ZorroViewLogic(u);
		
		u.statusLogic = statusLogic;
		u.attackLogic = attackLogic;
		u.boxLogic = boxLogic;
		u.viewLogic = viewLogic;
		
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
			u.setmovementspeed((Float)stats.get(Character.STAT_MOVEMENTSPEED));
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