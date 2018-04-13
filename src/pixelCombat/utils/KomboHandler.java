package pixelCombat.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import pixelCombat.controller.GamePlayController;
import pixelCombat.enums.AttackStates;
import pixelCombat.enums.GameEvent;
import pixelCombat.enums.KeyCommand;
import pixelCombat.model.Character;
import pixelCombat.model.PXMapHandler;

public class KomboHandler {
	// combos for player1
	public static ArrayList<ArrayList<String>> combos1;
	public static Map<String, Float> keyReleasedTimes1;
	public static ArrayList<String> pressedKeys1;
	public static Map<ArrayList<String>, Float> comboChain1;
	// combo for player2
	public static ArrayList<ArrayList<String>> combos2;
	public static Map<String, Float> keyReleasedTimes2;
	public static ArrayList<String> pressedKeys2;
	public static Map<ArrayList<String>, Float> comboChain2;

	public static ArrayList<ArrayList<String>> comboChainList1 = new ArrayList<ArrayList<String>>();
	public static ArrayList<ArrayList<String>> comboChainList2 = new ArrayList<ArrayList<String>>();

	public static boolean comboActive = false;
	public static Integer increment = 0;
	public static float maxDuration = 1.4f;
	private static PXMapHandler arena;	
	private static GameEngine engine;
	
	
	public KomboHandler(GameEngine engine)
	{
		KomboHandler.engine = engine;
		KomboHandler.arena = engine.arena;
		init();
	}
	
	public void init() {
		combos1 = new ArrayList<ArrayList<String>>();
		pressedKeys1 = new ArrayList<String>();
		keyReleasedTimes1 = new HashMap<String, Float>();
		comboChain1 = new HashMap<ArrayList<String>, Float>();
		comboChainList1 = new ArrayList<ArrayList<String>>();

		combos2 = new ArrayList<ArrayList<String>>();
		pressedKeys2 = new ArrayList<String>();
		keyReleasedTimes2 = new HashMap<String, Float>();
		comboChain2 = new HashMap<ArrayList<String>, Float>();
		comboChainList2 = new ArrayList<ArrayList<String>>();

		ArrayList<String> combo1 = new ArrayList<String>();
		ArrayList<String> combo2 = new ArrayList<String>();
		ArrayList<String> combo3 = new ArrayList<String>();
		ArrayList<String> combo4 = new ArrayList<String>();
		ArrayList<String> combo5 = new ArrayList<String>();
		ArrayList<String> combo6 = new ArrayList<String>();
		ArrayList<String> combo7 = new ArrayList<String>();
		ArrayList<String> combo8 = new ArrayList<String>();
		ArrayList<String> combo9 = new ArrayList<String>();
		ArrayList<String> combo10 = new ArrayList<String>();
		ArrayList<String> combo11 = new ArrayList<String>();
		ArrayList<String> combo12 = new ArrayList<String>();
		ArrayList<String> combo13 = new ArrayList<String>();
		ArrayList<String> combo14 = new ArrayList<String>();
		ArrayList<String> combo15 = new ArrayList<String>();
		ArrayList<String> combo16 = new ArrayList<String>();
		ArrayList<String> combo17 = new ArrayList<String>();
		ArrayList<String> combo18 = new ArrayList<String>();
		ArrayList<String> combo19 = new ArrayList<String>();
		
		// combo 1 : Up + Left + Up
		combo1.add("left");
		combo1.add("left");
		combo1.add("left");
		// combo 2 : Up + Down + Right
		combo2.add("right");
		combo2.add("right");
		combo2.add("right");
		// combo 3 : Left + Attack + Down
		combo3.add("attack");
		combo3.add("attack");
		combo3.add("attack");

		// combo3.add("attack");

		// combo 4 : Right + Right + Attack
		combo4.add("aa");
		combo4.add("aa");
		combo4.add("aa");

		// combo 5 : Up + Up + Attack
		combo5.add("aa");
		combo5.add("aa");
		combo5.add("aa");

		combo6.add("defend");
		combo6.add("left");
		combo6.add("attack");

		combo7.add("defend");
		combo7.add("right");
		combo7.add("attack");

		combo8.add("defend");
		combo8.add("attack");
		combo8.add("defend");
		combo8.add("right");

		combo9.add("defend");
		combo9.add("attack");
		combo9.add("defend");
		combo9.add("left");

		combo10.add("defend");
		combo10.add("attack");
		combo10.add("defend");
		combo10.add("down");
		
		combo11.add("attack1");
		combo11.add("attack1");
		combo11.add("attack1");

		combo12.add("defend");
		combo12.add("attack1");
		combo12.add("defend");
		combo12.add("right");
		
		combo13.add("defend");
		combo13.add("attack1");
		combo13.add("defend");
		combo13.add("left");
		
		combo14.add("defend");
		combo14.add("right");
		combo14.add("attack1");
		
		combo15.add("defend");
		combo15.add("left");
		combo15.add("attack1");
		
		combo16.add("defend");
		combo16.add("attack1");
		combo16.add("defend");
		combo16.add("up");
		
		combo17.add("defend");
		combo17.add("attack1");
		combo17.add("defend");
		combo17.add("up");
		
		combo18.add("defend");
		combo18.add("up");
		combo18.add("attack");
		
		combo19.add("defend");
		combo19.add("attack1");
		combo19.add("defend");
		combo19.add("down");
		
		
		// put in Chaining
		comboChain1.put(combo3, 0.0f);
		comboChain1.put(combo5, 0.0f);
		comboChain1.put(combo11, 0.0f);
		comboChainList1.add(combo3);
		comboChainList1.add(combo5);
		comboChainList1.add(combo11);
		
		comboChain2.put(combo3, 0.0f);
		comboChain2.put(combo5, 0.0f);
		comboChain2.put(combo11, 0.0f);
		comboChainList2.add(combo3);
		comboChainList2.add(combo5);
		comboChainList2.add(combo11);
		// put all in combos

		// player1
		combos1.add(combo1);
		combos1.add(combo2);
		combos1.add(combo3);
		combos1.add(combo4);
		combos1.add(combo5);
		combos1.add(combo6);
		combos1.add(combo7);
		combos1.add(combo8);
		combos1.add(combo9);
		combos1.add(combo10);
		combos1.add(combo11);
		combos1.add(combo12);
		combos1.add(combo13);
		combos1.add(combo14);
		combos1.add(combo15);
		combos1.add(combo16);
		combos1.add(combo17);
		combos1.add(combo18);
		combos1.add(combo19);
		// player2
		combos2.add(combo1);
		combos2.add(combo2);
		combos2.add(combo3);
		combos2.add(combo4);
		combos2.add(combo5);
		combos2.add(combo6);
		combos2.add(combo7);
		combos2.add(combo8);
		combos2.add(combo9);
		combos2.add(combo10);
		combos2.add(combo11);
		combos2.add(combo12);
		combos2.add(combo13);
		combos2.add(combo14);
		combos2.add(combo15);
		combos2.add(combo16);
		combos2.add(combo17);
		combos2.add(combo18);
		combos2.add(combo19);
	}
	
	/**
	 * /** Updates the game
	 * 
	 * @param deltaTime
	 *            delta time
	 **/
	public void update(long deltaTime) {
		float delta = (float) deltaTime / 1000000000.0f;
		// check possibly released combos of player1
		
		checkCommands(delta,pressedKeys1,keyReleasedTimes1,comboChainList1,comboChain1);
		checkCommands(delta,pressedKeys2,keyReleasedTimes2,comboChainList2,comboChain2);
		
		checkTime(pressedKeys1, keyReleasedTimes1);
		checkChain();
		checkCombo(arena.getPlayer1(), pressedKeys1, combos1, keyReleasedTimes1);

		checkTime(pressedKeys2, keyReleasedTimes2);
		checkCombo(arena.getPlayer2(), pressedKeys2, combos2, keyReleasedTimes2);
	}

	
	public void checkCommands(float delta, 	ArrayList<String> pressedKeys, 
										Map<String,Float> keyReleasedTimes,
										ArrayList<ArrayList<String>> comboChainList,
										Map<ArrayList<String>,Float> comboChain)
	{
		for (int i = 0; i < pressedKeys.size(); i++) {
			try {
				String elem = pressedKeys.get(i);
				float actualTime = keyReleasedTimes.get(elem);
				keyReleasedTimes.replace(elem, actualTime + delta);
			} catch (NullPointerException e) {
				pressedKeys.clear();
			}
		}
		
		for (int i = 0; i < comboChainList.size(); i++) {
			try {
				ArrayList<String> elem = comboChainList.get(i);
				float actualTime = comboChain.get(elem);
				if (actualTime - delta > 0f)
					comboChain.replace(elem, actualTime - delta);
				else
					comboChain.replace(elem, 0f);
			} catch (NullPointerException e) {
				pressedKeys.clear();
			}
		}
	}
	
	

	/**
	 * Handles command passed from the InputController
	 * 
	 * @param kc
	 *            key code
	 * @param hold
	 *            is hold
	 **/

	public static void pressKey(String key, Character player) {
		String newKey = key + "_" + increment.toString();
		if (player.getName() == "p1") {
			pressedKeys1.add(newKey);
			keyReleasedTimes1.put(newKey, (float) 0.0);
		} else {
			pressedKeys2.add(newKey);
			keyReleasedTimes2.put(newKey, (float) 0.0);
		}
		increment = increment + 1;

		
	}

	public static void checkCombo(Character player, ArrayList<String> pressedKeys, ArrayList<ArrayList<String>> combos,
			Map<String, Float> keyReleasedTimes) {

		// Take off the increment to check combos,
		// increments are not important, when you have to check
		// the key order, especially after the time control where keys
		// will be deleted if time surpassed

		ArrayList<String> pressedKeysWithoutId = new ArrayList<String>();
		for (int i = 0; i < pressedKeys.size(); i++) {
			String keyWOId = pressedKeys.get(i);
			int subIndex = keyWOId.indexOf("_");
			keyWOId = keyWOId.substring(0, subIndex);
			pressedKeysWithoutId.add(keyWOId);
		}

		// search for combo within the pressedKeys

		ArrayList<Integer> resultList = new ArrayList<Integer>();
		for (ArrayList<String> combo : combos) {
			resultList = containsCombo(player, combos, pressedKeysWithoutId, combo);
			if (resultList.size() == combo.size()) {
				break;
			}
		}

		// if combo found
		// delete all pressedKeys
		// and drop from the map
		if (resultList.size() != 0) {
			for (int i = 0; i < resultList.size(); i++) {
				int index = resultList.get(i);
				String elem = pressedKeys.get(index);
				keyReleasedTimes.remove(elem);
			}
			comboActive = true;
			pressedKeys.clear();
		}

	}

	// checks if a list is within another list
	public static ArrayList<Integer> containsCombo(Character player, ArrayList<ArrayList<String>> combos, ArrayList<String> list,
			ArrayList<String> sublist) {
		ArrayList<Integer> result = new ArrayList<Integer>();
		for (int i = 0; i < list.size(); i++) {
			if (i + sublist.size() <= list.size()) {
				// System.out.println("Compare Keyelement " + list.get(i) +
				// " at position " + i + " :");
				for (int j = 0; j < sublist.size(); j++) {
					// System.out.println("With compare object " +
					// sublist.get(j) + " at position " + j +".");
					int index = i + j;
					// System.out.println("comparing at " + index);
					// System.out.println();
					String element = list.get(index);
					// System.out.println(element + "  " + sublist.get(j));
					if (element.equals(sublist.get(j))) {
						result.add(index);
						// System.out.println("Elements are equal, putting index in result list ... ");
					} else {
						// System.out.println("Elements are not equal, dropping loop ... ");
						break;
					}
				}
				// System.out.println();
				// System.out.println();
				if (result.size() == sublist.size()) {
					// System.out.println("result list complete, combo found ... ");

					releaseCombo(player, combos, sublist);
					break;
				} else {
					result.clear();
				}
			}

		}
		return result;
	}

	private static void releaseCombo(Character player, ArrayList<ArrayList<String>> combos, ArrayList<String> result) {
		if (combos.get(3) == result) {
			player.stopActing();

			if (player.getName() == "p1")
				engine.getController().onKey(KeyCommand.P1COMBOATTACK, false);
			else
				engine.getController().onKey(KeyCommand.P2COMBOATTACK, false);
		} else if (combos.get(2) == result ) {
			player.stopActing();
			if (player.getName() == "p1") {
				engine.getController().onKey(KeyCommand.P1BASICATTACK2, false);
				comboChain1.replace(combos.get(2), 1.0f);

			} else {
				engine.getController().onKey(KeyCommand.P2BASICATTACK2, false);
				comboChain2.replace(combos.get(2), 1.0f);

			}
		}
			else if (combos.get(10) == result ) {
					player.stopActing();
				if (player.getName() == "p1") {
					engine.getController().onKey(KeyCommand.P1BASICATTACK22, false);
					comboChain1.replace(combos.get(10), 1.5f);

				} else {
					engine.getController().onKey(KeyCommand.P2BASICATTACK22, false);
					comboChain2.replace(combos.get(10), 1.5f);

				}

		}
		
			else if (combos.get(6) == result  || combos.get(5) == result ) {

			if (player.getName() == "p1")
				engine.getController().onKey(KeyCommand.P1SPECIALATTACK1, true);
			else
				engine.getController().onKey(KeyCommand.P2SPECIALATTACK1, true);

		} else if (combos.get(7) == result  || combos.get(8) == result ) {

			if (player.getName() == "p1")
				engine.getController().onKey(KeyCommand.P1SPECIALATTACK3, true);
			else
				engine.getController().onKey(KeyCommand.P2SPECIALATTACK3, true);

		} else if (combos.get(9) == result ) {

			if (player.getName() == "p1")
				engine.getController().onKey(KeyCommand.P1SPECIALATTACK4, true);
			else
				engine.getController().onKey(KeyCommand.P2SPECIALATTACK4, true);

		}
		else if (combos.get(11) == result  || combos.get(12) == result ) {

			if (player.getName() == "p1")
				engine.getController().onKey(KeyCommand.P1SPECIALATTACK5, true);
			else
				engine.getController().onKey(KeyCommand.P2SPECIALATTACK5, true);

		} 
		else if (combos.get(13) == result  || combos.get(14) == result ) {


			if (player.getName() == "p1")
				engine.getController().onKey(KeyCommand.P1SPECIALATTACK6, true);
			else
				engine.getController().onKey(KeyCommand.P2SPECIALATTACK6, true);

		} 

		else if (combos.get(15) == result  || combos.get(16) == result ) {
			if (player.getName() == "p1")
				engine.getController().onKey(KeyCommand.P1SPECIALATTACK3, true);
			else
				engine.getController().onKey(KeyCommand.P2SPECIALATTACK3, true);

		} 
		else if (combos.get(17) == result  && player.statusLogic.isJumping()) {
			player.stopActing();
			if (player.getName() == "p1")
				engine.getController().onKey(KeyCommand.P1AIRSPECIALATTACK1, true);
			else
				engine.getController().onKey(KeyCommand.P2AIRSPECIALATTACK1, true);

		} 
		else
			player.checkFurtherCombos(combos,result);
			
		
	}

	public static void checkTime(ArrayList<String> pressedKeys, Map<String, Float> keyReleasedTimes) {
		// copy PressedKeys
		ArrayList<String> copyList = new ArrayList<String>();
		for (int i = 0; i < pressedKeys.size(); i++) {
			String elem = pressedKeys.get(i);
			copyList.add(elem);
		}

		for (int i = 0; i < pressedKeys.size(); i++) {
			String elem = copyList.get(i);
			try {
				if (keyReleasedTimes.get(elem) >= maxDuration) {
					pressedKeys.remove(i);
					keyReleasedTimes.remove(elem);

				}
			} catch (NullPointerException e) {
				
			}
		}
		copyList.clear();
	}

	// Chaining
	public static void checkChain() {
		
		Character player1 = arena.getPlayer1();
		Character player2 = arena.getPlayer2();
		
		doCeckChain(player1, pressedKeys1, combos1, keyReleasedTimes1, comboChainList1, comboChain1);
		doCeckChain(player2, pressedKeys2, combos2, keyReleasedTimes2, comboChainList2, comboChain2);
	
	}
	
	public static void doCeckChain(Character player, 
			ArrayList<String> pressedKeys, 
			ArrayList<ArrayList<String>> combos, 
			Map<String,Float> keyReleasedTimes,
			ArrayList<ArrayList<String>> comboChainList,
			Map<ArrayList<String>,Float> comboChain)
	{
		try {
		//wird noch ausgeführt, obwohl introduction phase.. muss geändert werden über 
		if(((GamePlayController)engine.getController()).event == GameEvent.INTRODUCTION)
			return;
		
		float time_combo1 = comboChain.get(combos.get(2));

		String lastKey = pressedKeys.get(pressedKeys.size() - 1);
		int subIndex = lastKey.indexOf("_");
		lastKey = lastKey.substring(0, subIndex);

		if (time_combo1 > 0f && lastKey.equals("attack")) {
			
			if (!player.isJumpAttacking()
					&& !player.attackLogic.isSpecialAttacking()
					&& !player.attackLogic.isRunAttacking()
					&& !player.statusLogic.isJumping()) {
				player.cancelAction();
				player.physics.VX = 0f;
				player.setBufferTime(0f);
				player.attackLogic.setAttackStatus(AttackStates.isComboAttacking);
			}
	
			pressedKeys.clear();
			comboChain.replace(combos.get(2), 0f);
		}
		
		time_combo1 = comboChain.get(combos.get(10));
		lastKey = pressedKeys.get(pressedKeys.size() - 1);
		subIndex = lastKey.indexOf("_");
		lastKey = lastKey.substring(0, subIndex);
		if (time_combo1 > 0f && lastKey.equals("attack1")) 
		{
			if (!arena.getPlayer1().isJumpAttacking() && !player.statusLogic.isJumping()) 
			{
				player.cancelAction();
				player.physics.VX = 0f;
				player.setBufferTime(0f);
				player.attackLogic.setAttackStatus(AttackStates.isBasicAttacking23);
			}
	
			pressedKeys.clear();
			comboChain.replace(combos.get(10), 0f);
		}
	} catch (ArrayIndexOutOfBoundsException e) {

	}
	}
	
}
