package pixelCombat.controller;
// neu -> Bilal

import java.util.HashMap;
import java.util.Map;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import pixelCombat.utils.GameEngine;
/**
 * InputController
 * 
 * 
 * @author BillyG
  * @version 0.1
 */
public class InputController implements EventHandler<Event> {
	
	private GameEngine engine;
	private Event lastEvent;
	public  Map<KeyCode, Boolean> hold;
	private Map<KeyCode, Boolean> typed;

	public InputController(GameEngine engine) {
		this.engine = engine;
		hold = new HashMap<KeyCode, Boolean>();
		typed = new HashMap<KeyCode, Boolean>();
		
		
		
	}

	/**
	 * Sets if a key is held down and not released, yet
	 * 
	 * @param key
	 *            key
	 * @param isHold
	 *            is key not released
	 */
	public void setHold(KeyCode key, boolean isHold) {
		hold.put(key, isHold);
	}

	/**
	 * Sets if a key is typed
	 * 
	 * @param key
	 *            key
	 * @param isTyped
	 *            is typed
	 */
	public void setTyped(KeyCode key, boolean isTyped) {
		typed.put(key, isTyped);
	}

	/**
	 * Checks if a key is pressed but not released, yet
	 * 
	 * @param key
	 *            key
	 * @return is key hold
	 */
	public Boolean isHold(KeyCode key) {
		return (hold.get(key) == null) ? false : hold.get(key);
	}

	/**
	 * Checks if a key is typed
	 * 
	 * @param key
	 *            key
	 * @return is key typed
	 */
	public Boolean isTyped(KeyCode key) {
		return (typed.get(key) == null) ? false : typed.get(key);
	}

	/**
	 * Called when event occured
	 * 
	 * @param e
	 *            event
	 */

	public void handle(Event e) {
		lastEvent = e;

		if (lastEvent.getEventType() == KeyEvent.KEY_PRESSED) {
			setHold(((KeyEvent) lastEvent).getCode(), true);
			setTyped(((KeyEvent) lastEvent).getCode(), false);
		} else if (lastEvent.getEventType() == KeyEvent.KEY_RELEASED) {
			setHold(((KeyEvent) lastEvent).getCode(), false);
			setTyped(((KeyEvent) lastEvent).getCode(), true);
		}

	}

	/**
	 * Called once per frame to process keys held down without the problem of
	 * key-holding-delay!
	 */

	public void update(long delta) 
	{
		for (KeyCode k : hold.keySet()) {
			if (isHold(k)) {
				engine.handleCommand(k, true);
			} else if (isTyped(k)) {
				engine.handleCommand(k, false);
				setTyped(k, false);
			}
		}
	}
}
