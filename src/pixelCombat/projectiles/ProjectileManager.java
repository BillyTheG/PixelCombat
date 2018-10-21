package pixelCombat.projectiles;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

import javafx.scene.image.Image;
import pixelCombat.Math.BoundingRectangle;
import pixelCombat.utils.Console;
import pixelCombat.utils.WordWrapConsole;
import pixelCombat.xml.CharacterParser;
import pixelCombat.xml.StatsParser;
import pixelCombat.xml.XML_Box_Reader;
/**
 * 
 * @author bilal_000
 *
 *	This Manager contains all pictures and boxes for all projectiles within a game.
 *  Due to effectiveness and savings in memory data, any picture and box will be only load once.
 *   
 *  
 */
public class ProjectileManager 
{
	//all pictures hold in map
	public HashMap<String,ArrayList<ArrayList<Image>>> pictures;	
	
	//all boxes hold in map
	public HashMap<String, ArrayList<ArrayList<ArrayList<BoundingRectangle>>>> boxes;

	//all additional variabels
	public HashMap<String,ArrayList<ArrayList<Float>>> times;
	public HashMap<String,ArrayList<Integer>> loopVariabels;
	public HashMap<String,ArrayList<Boolean>> loopBools;

	private static Console console;	

	
	public ProjectileManager(Console console)
	{
		ProjectileManager.console = console;
		init();
	}

	private void init() 
	{
		//create dummy lists for pics/boxes
		this.pictures = new HashMap<String,ArrayList<ArrayList<Image>>>();
		this.loopVariabels = new HashMap<String,ArrayList<Integer>> ();
		this.loopBools = new HashMap<String,ArrayList<Boolean>> ();
		this.times = new HashMap<String,ArrayList<ArrayList<Float>>>();
		this.boxes = new HashMap<String,ArrayList<ArrayList<ArrayList<BoundingRectangle>>>>();

		
		//load Pics and Boxes
		loadAll();
	}	
	


	public static void main(String... args) 
	{
		ProjectileManager manager = new ProjectileManager(console);
		try {
			ArrayList<String> files = new ArrayList<>();
			
			files.add("Dragon_Breath.xml");
			files.add("TatsumakiWind.xml");
			files.add("360Pound.xml");
			files.add("Yakkoudori.xml");
			for(String file: files)
			{
				String filename = file.split(".xml")[0];
				System.out.println("Now, the file " + filename + " will be loaded.");
				
				
				
				InputStream stream1 = ProjectileManager.class.getResourceAsStream("/projectiles_sprites/" + filename + ".xml");
				InputSource source1 = new InputSource(stream1);
			    XMLReader xmlreader1 = XMLReaderFactory.createXMLReader();
			    CharacterParser ch = new CharacterParser(new WordWrapConsole());
			    xmlreader1.setContentHandler(ch);
			    xmlreader1.parse(source1);
			   			    
			    Map<String, ArrayList<Image>> pictures 						= ch.getCharacter();
			    ArrayList<Boolean> projectile_loopBools 					= ch.getLoopBools();
			    ArrayList<Integer> projectile_loopIndices 					= ch.getLoopIndizes();
			    ArrayList<ArrayList<Float>> projectile_times 				= ch.getTimes();
			    ArrayList<ArrayList<Image>> pictures_all = new ArrayList<ArrayList<Image>>();
			    pictures_all.add(0, pictures.get("creation"));
			    pictures_all.add(1, pictures.get("move"));
			    pictures_all.add(2, pictures.get("explosion"));
			    
			    
			    manager.pictures.put(filename, pictures_all);
				manager.loopBools.put(filename, projectile_loopBools);
				manager.loopVariabels.put(filename, projectile_loopIndices);
				manager.times.put(filename, projectile_times);
			    
				
			    InputStream stream2 = ProjectileManager.class.getResourceAsStream("/projectile/boxes/" + filename + ".xml");
				InputSource source2 = new InputSource(stream2);
				XMLReader xmlreader2 = XMLReaderFactory.createXMLReader();
				XML_Box_Reader bo = new XML_Box_Reader(console);
				xmlreader2.setContentHandler(bo);
				xmlreader2.parse(source2);
				Map<String, ArrayList<ArrayList<BoundingRectangle>>> boxes	= bo.getBoxes();
				
				ArrayList<ArrayList<ArrayList<BoundingRectangle>>> boxes_all = new ArrayList<ArrayList<ArrayList<BoundingRectangle>>>();
				boxes_all.add(0, boxes.get("creation"));
				boxes_all.add(1, boxes.get("move"));
				boxes_all.add(2, boxes.get("explosion"));
				manager.boxes.put(filename, boxes_all);
			    
				//Datentypen fixen...
				//...
				//..
				
//				xxx.add(0, yyy.get("create"));
//				xxx.add(1, yyy.get("move"));
//				xxx.add(2, yyy.get("explosion"));

			}
		} 
		catch (IOException | SAXException e) 
		{
			e.printStackTrace();
		}
		
	}
	
	
	
	private void loadAll() 
	{
		//All Projectiles
		String path = "";
		try {
			ArrayList<String> files = new ArrayList<>();
			
			files.add("Dragon_Breath.xml");
			files.add("TatsumakiWind.xml");
			files.add("360Pound.xml");
			files.add("Yakkoudori.xml");
			for(String file: files)
			{
				String filename = file.split(".xml")[0];
				System.out.println("Now, the file " + filename + " will be loaded.");
				
				URL url  = getClass().getClassLoader().getResource("projectiles_sprites/" + filename + ".xml");
				path = url.getPath();
				InputStream imageStream = new FileInputStream(new File(path));
								
			//	path = "/projectiles_sprites/" + filename + ".xml";
			//	InputStream stream1 = getClass().getResourceAsStream(path);
				InputSource source1 = new InputSource(imageStream);
			    XMLReader xmlreader1 = XMLReaderFactory.createXMLReader();
			    CharacterParser ch = new CharacterParser(console);
			    xmlreader1.setContentHandler(ch);
			    xmlreader1.parse(source1);
			   			    
			    Map<String, ArrayList<Image>> pictures 						= ch.getCharacter();
			    ArrayList<Boolean> projectile_loopBools 					= ch.getLoopBools();
			    ArrayList<Integer> projectile_loopIndices 					= ch.getLoopIndizes();
			    ArrayList<ArrayList<Float>> projectile_times 				= ch.getTimes();
			    ArrayList<ArrayList<Image>> pictures_all = new ArrayList<ArrayList<Image>>();
			    pictures_all.add(0, pictures.get("creation"));
			    pictures_all.add(1, pictures.get("move"));
			    pictures_all.add(2, pictures.get("explosion"));
			    
			    System.out.println("Now, the file " + filename.toLowerCase() + " will be inserted in.");
			    
			   
			    
			    this.pictures.put(filename.toLowerCase(), pictures_all);
			    this.loopBools.put(filename.toLowerCase(), projectile_loopBools);
				this.loopVariabels.put(filename.toLowerCase(), projectile_loopIndices);
				this.times.put(filename.toLowerCase(), projectile_times);
			    
				URL boxUrl  = getClass().getClassLoader().getResource("projectile/boxes/" + filename + ".xml");
				path = boxUrl.getPath();
				InputStream boxStream = new FileInputStream(new File(path));
	
				
			//  InputStream stream2 = getClass().getResourceAsStream("/projectile/boxes/" + filename + ".xml");
				InputSource source2 = new InputSource(boxStream);
				XMLReader xmlreader2 = XMLReaderFactory.createXMLReader();
				XML_Box_Reader bo = new XML_Box_Reader(console);
				xmlreader2.setContentHandler(bo);
				xmlreader2.parse(source2);
				Map<String, ArrayList<ArrayList<BoundingRectangle>>> boxes	= bo.getBoxes();
				
				ArrayList<ArrayList<ArrayList<BoundingRectangle>>> boxes_all = new ArrayList<ArrayList<ArrayList<BoundingRectangle>>>();
				boxes_all.add(0, boxes.get("creation"));
				boxes_all.add(1, boxes.get("move"));
				boxes_all.add(2, boxes.get("explosion"));
				this.boxes.put(filename, boxes_all);
			    
				//Datentypen fixen...
				//...
				//..
				
//				xxx.add(0, yyy.get("create"));
//				xxx.add(1, yyy.get("move"));
//				xxx.add(2, yyy.get("explosion"));

			}
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			System.out.println("The file: "+path +" does not exist. Check your resources");
		}
		
	}
	
	
	
	
	
//------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
	
	
	//XML Reader for Images
	public Map<String, ArrayList<Image>> loadProjectileImages(String name) {

		Map<String, ArrayList<Image>> projectile = new HashMap<String, ArrayList<Image>>();

		try {

			InputStream stream = StatsParser.class.getResourceAsStream("/projectiles_sprites/" + name + ".xml");
			InputSource source = new InputSource(stream);
		    XMLReader xmlreader = XMLReaderFactory.createXMLReader();
		    CharacterParser ch = new CharacterParser(console);
		    xmlreader.setContentHandler(ch);
		    xmlreader.parse(source);
		    
		    projectile = ch.getCharacter();
		    
		    
		} catch (Exception e) {
		    e.printStackTrace();
		}

		return projectile;
	    }
	
	
	
	//XML Reader for Boxes
	public Map<String, ArrayList<ArrayList<BoundingRectangle>>> loadProjectileBoxes(String name) {

		Map<String, ArrayList<ArrayList<BoundingRectangle>>> boxes = new HashMap<String, ArrayList<ArrayList<BoundingRectangle>>>();

		try {
			InputStream stream = StatsParser.class.getResourceAsStream("/projectile/boxes/" + name + ".xml");
			InputSource source = new InputSource(stream);

			XMLReader xmlreader = XMLReaderFactory.createXMLReader();
			XML_Box_Reader ch = new XML_Box_Reader(console);
			xmlreader.setContentHandler(ch);
			xmlreader.parse(source);

			boxes = ch.getBoxes();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return boxes;
	}

	public Console getConsole() {
		return console;
	}
	
	
}
;