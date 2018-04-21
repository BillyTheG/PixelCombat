package pixelCombat.view;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

import javafx.scene.image.Image;
import pixelCombat.model.Character;
import pixelCombat.model.PXMapHandler;
import pixelCombat.utils.Console;
import pixelCombat.xml.CharacterParser;
import pixelCombat.xml.StatsParser;

public class ImageLoader implements Runnable {

	private ArrayList<ArrayList<ArrayList<Image>>> IMAGES = new ArrayList<ArrayList<ArrayList<Image>>>();
//	private ArrayList<ArrayList<Image>> Player1 = new ArrayList<ArrayList<Image>>();
//	private ArrayList<ArrayList<Image>> Player2 = new ArrayList<ArrayList<Image>>();
	private Map<String,Map<String, ArrayList<Image>>> allIMAGES = new HashMap<>();
	private Map<String,Map<String, Integer>> allAirLoops = new HashMap<>();
	private Map<String,Map<String, Boolean>> allAirBools = new HashMap<>();
	private Map<String,ArrayList<Integer>> allLoops = new HashMap<>();
	private Map<String,ArrayList<Boolean>> allLoopBools = new HashMap<>();
	private Map<String,ArrayList<ArrayList<Float>>> allTimes = new HashMap<>();
	
	
	// container for loaded sprites
	private Map<String, ArrayList<Image>> player1;
	private Map<String, ArrayList<Image>> player2;

	private ArrayList<ArrayList<Float>> player1_times;
	private ArrayList<Integer> player1_loops;
	private ArrayList<Boolean> player1_loopBools;
	private Map<String, Integer> player1_airIndices;
	private Map<String, Boolean> player1_airBools;

	private ArrayList<ArrayList<Float>> player2_times;
	private ArrayList<Integer> player2_loops;
	private ArrayList<Boolean> player2_loopBools;
	private Map<String, Integer> player2_airIndices;
	private Map<String, Boolean> player2_airBools;

	private Character char_player1;
	private Character char_player2;
	private PXMapHandler arena;
	private volatile Console console;

	public ImageLoader(PXMapHandler arena, Map<String, ArrayList<Image>> player1, Map<String, ArrayList<Image>> player2, Console console) {
		this.console = console;
		this.arena = arena;
		this.player1 = player1;
		this.player2 = player2;
	}

	@Override
	public void run() {
		init();

		List<Character> cl = new ArrayList<Character>(arena.getCharacter());
		ArrayList<ArrayList<Image>> Player1 = new ArrayList<ArrayList<Image>>();
		ArrayList<ArrayList<Image>> Player2 = new ArrayList<ArrayList<Image>>();
		
		ArrayList<Thread> threads = new ArrayList<Thread>();

		
		for (Character c : cl) {

			 if(c == arena.getPlayer1()) {
				Thread thread = new Thread(new Runnable(){

					@Override
					public void run() {
						if(allIMAGES.get(c.getClass().getSimpleName())!= null)					
						{
							player1 = allIMAGES.get(c.getClass().getSimpleName());
							player1_times = allTimes.get(c.getClass().getSimpleName());
							player1_loops = allLoops.get(c.getClass().getSimpleName());
							player1_loopBools = allLoopBools.get(c.getClass().getSimpleName());
							player1_airIndices = allAirLoops.get(c.getClass().getSimpleName());;
							player1_airBools = allAirBools.get(c.getClass().getSimpleName());
						}
						else
						{
							player1 = loadCharacter(c.getClass().getSimpleName(), 0);
							allIMAGES.put(c.getClass().getSimpleName(),player1);
							allTimes.put(c.getClass().getSimpleName(),player1_times);
							allLoops.put(c.getClass().getSimpleName(),player1_loops);
							allLoopBools.put(c.getClass().getSimpleName(),player1_loopBools);
							allAirLoops.put(c.getClass().getSimpleName(),player1_airIndices);
							allAirBools.put(c.getClass().getSimpleName(),player1_airBools);
					
						}
						Player1.add(0, player1.get("stand"));
						Player1.add(1, player1.get("move"));
						Player1.add(2, player1.get("jumping"));
						Player1.add(3, player1.get("basicAttack"));
						Player1.add(4, player1.get("specialAttack1"));
						Player1.add(5, player1.get("specialAttack2"));
						Player1.add(6, player1.get("specialAttack3"));
						Player1.add(7, player1.get("isHit"));
						Player1.add(8, player1.get("knockBack"));
						Player1.add(9, player1.get("knockedOut"));
						Player1.add(10, player1.get("avatar"));
						Player1.add(11, player1.get("basicAttack1"));
						Player1.add(12, player1.get("jumpAttack"));
						Player1.add(13, player1.get("retreating"));
						Player1.add(14, player1.get("dashing"));
						Player1.add(15, player1.get("defend"));
						Player1.add(16, player1.get("specialAttack4"));
						Player1.add(17, player1.get("specialAttack5"));
						Player1.add(18, player1.get("basicAttack21"));
						Player1.add(19, player1.get("basicAttack22"));
						Player1.add(20, player1.get("basicAttack23"));
						Player1.add(21, player1.get("specialAttack6"));
						Player1.add(22, player1.get("specialAttack7"));
						Player1.add(23, player1.get("intro"));
						Player1.add(24, player1.get("win"));
						Player1.add(25, player1.get("dead"));
						Player1.add(26, player1.get("runAttack1"));
						Player1.add(27, player1.get("runAttack2"));
						Player1.add(28, player1.get("jumpRecover"));
						Player1.add(29, player1.get("airDefend"));
						Player1.add(30, player1.get("airSpecialAttack1"));
						Player1.add(31, player1.get("knockBackRecover"));
						char_player1.loadFurtherImages(Player1,player1);
					}
					
				});
				threads.add(thread);
				thread.start();
			}
			
			if (c == arena.getPlayer2()) {
				Thread thread = new Thread(new Runnable(){

					@Override
					public void run() {
						if(allIMAGES.get(c.getClass().getSimpleName())!= null)					
						{
							player2 = allIMAGES.get(c.getClass().getSimpleName());
							player2_times = allTimes.get(c.getClass().getSimpleName());
							player2_loops = allLoops.get(c.getClass().getSimpleName());
							player2_loopBools = allLoopBools.get(c.getClass().getSimpleName());
							player2_airIndices = allAirLoops.get(c.getClass().getSimpleName());;
							player2_airBools = allAirBools.get(c.getClass().getSimpleName());
						}
						else
						{
							player2 = loadCharacter(c.getClass().getSimpleName(), 1);
							allIMAGES.put(c.getClass().getSimpleName(),player2);
							allTimes.put(c.getClass().getSimpleName(),player2_times);
							allLoops.put(c.getClass().getSimpleName(),player2_loops);
							allLoopBools.put(c.getClass().getSimpleName(),player2_loopBools);
							allAirLoops.put(c.getClass().getSimpleName(),player2_airIndices);
							allAirBools.put(c.getClass().getSimpleName(),player2_airBools);
						}
						Player2.add(0, player2.get("stand"));
						Player2.add(1, player2.get("move"));
						Player2.add(2, player2.get("jumping"));
						Player2.add(3, player2.get("basicAttack"));
						Player2.add(4, player2.get("specialAttack1"));
						Player2.add(5, player2.get("specialAttack2"));
						Player2.add(6, player2.get("specialAttack3"));
						Player2.add(7, player2.get("isHit"));
						Player2.add(8, player2.get("knockBack"));
						Player2.add(9, player2.get("knockedOut"));
						Player2.add(10, player2.get("avatar"));
						Player2.add(11, player2.get("basicAttack1"));
						Player2.add(12, player2.get("jumpAttack"));
						Player2.add(13, player2.get("retreating"));
						Player2.add(14, player2.get("dashing"));
						Player2.add(15, player2.get("defend"));
						Player2.add(16, player2.get("specialAttack4"));
						Player2.add(17, player2.get("specialAttack5"));
						Player2.add(18, player2.get("basicAttack21"));
						Player2.add(19, player2.get("basicAttack22"));
						Player2.add(20, player2.get("basicAttack23"));
						Player2.add(21, player2.get("specialAttack6"));
						Player2.add(22, player2.get("specialAttack7"));
						Player2.add(23, player2.get("intro"));
						Player2.add(24, player2.get("win"));
						Player2.add(25, player2.get("dead"));
						Player2.add(26, player2.get("runAttack1"));
						Player2.add(27, player2.get("runAttack2"));
						Player2.add(28, player2.get("jumpRecover"));
						Player2.add(29, player2.get("airDefend"));
						Player2.add(30, player2.get("airSpecialAttack1"));
						Player2.add(31, player2.get("knockBackRecover"));
						char_player2.loadFurtherImages(Player2,player2);
					}
					
				});
				threads.add(thread);
				thread.start();
			}
			
		}
		
		for(Thread thread: threads)
			try {
				thread.join();
				arena.getEngine().loading.setLoading(arena.getEngine().loading.getLoading()+20);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		
				

		
		
		
		
		// Liste aller Bilder zusammenfÃ¼gen
		IMAGES.clear();
		IMAGES.add(0, Player1);
		IMAGES.add(1, Player2);


		
		// PicManager Bilder übergeben
		char_player1.setPicManager(new PlayerPicManager(char_player1, Player1, player1_times, player1_loops, player1_loopBools));
		char_player2.setPicManager(new PlayerPicManager(char_player2, Player2, player2_times, player2_loops, player2_loopBools));
		char_player1.setAirAttackConfig(this.player1_airIndices, this.player1_airBools);
		char_player2.setAirAttackConfig(this.player2_airIndices, this.player2_airBools);

		
		
		
	}

	private void init() {
		char_player2 = arena.getPlayer2();
		char_player1 = arena.getPlayer1();

	}

	public Map<String, ArrayList<Image>> loadCharacter(String name, int number) {

		Map<String, ArrayList<Image>> player = new HashMap<String, ArrayList<Image>>();

		try {

			InputStream stream = StatsParser.class.getResourceAsStream("/characters_sprites/" + name + ".xml");
			InputSource source = new InputSource(stream);

			XMLReader xmlreader = XMLReaderFactory.createXMLReader();
			CharacterParser ch = new CharacterParser(console);
			xmlreader.setContentHandler(ch);
			xmlreader.parse(source);

			player = ch.getCharacter();
			if (number == 0) {
				player1_times = ch.getTimes();
				player1_loops = ch.getLoopIndizes();
				player1_loopBools = ch.getLoopBools();
				player1_airIndices = ch.getAirIndices();
				player1_airBools = ch.getAirBools();
			} else {
				player2_times = ch.getTimes();
				player2_loops = ch.getLoopIndizes();
				player2_loopBools = ch.getLoopBools();
				player2_airIndices = ch.getAirIndices();
				player2_airBools = ch.getAirBools();

			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return player;
	}

	public ArrayList<ArrayList<ArrayList<Image>>> getIMAGES() {
		return IMAGES;
	}

	public void setIMAGES(ArrayList<ArrayList<ArrayList<Image>>> iMAGES) {
		IMAGES = iMAGES;
	}

	public Map<String, ArrayList<Image>> getMapPlayer1() {
		return player1;
	}

	public Map<String, ArrayList<Image>> getMapPlayer2() {
		return player2;
	}

	
	
}
