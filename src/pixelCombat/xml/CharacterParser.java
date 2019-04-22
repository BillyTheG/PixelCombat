package pixelCombat.xml;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.xml.sax.Attributes;
import org.xml.sax.ContentHandler;
import org.xml.sax.InputSource;
import org.xml.sax.Locator;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

import javafx.scene.image.Image;
import pixelCombat.utils.Console;
import pixelCombat.utils.WordWrapConsole;


public class CharacterParser implements ContentHandler {

	// Zwischenspeicher fuer Inhalt von Elementen
	String ElementContent = null;
	private Map<String, ArrayList<Image>> character = new HashMap<String, ArrayList<Image>>();
	private ArrayList<ArrayList<Float>> times = new ArrayList<>();
	private ArrayList<Float> time = new ArrayList<>();
	private ArrayList<Boolean> loop = new ArrayList<>();
	private ArrayList<Integer> loopIndizes = new ArrayList<>();
	private Map<String, Boolean> airBools = new HashMap<String, Boolean>(); //is applicable on Air?
	private Map<String, Integer> airIndices = new HashMap<String, Integer>(); //if yes, which picture as Start-Image (varies from attacks)
	
	
	
	private int loopIndex ;
	private boolean loops;
	private int airIndex ;
	private boolean airBool;
	private float duration;
	private String  readingType = "";
	private boolean readingSprites = false;
	private boolean readingAnimation = false;
	private boolean readingIMG = false;
	private int key = 0;
	private String animation = "";
	private Console console;

	
	
	public CharacterParser(Console console) {
		this.console = console;
	}



	public static void main(String... args) {

		try {

			InputStream stream = StatsParser.class.getResourceAsStream("/characters_sprites/" + "Ruffy.xml");
			InputSource source = new InputSource(stream);

			XMLReader xmlreader = XMLReaderFactory.createXMLReader();
			CharacterParser ch = new CharacterParser(new WordWrapConsole());
			xmlreader.setContentHandler(ch);
			xmlreader.parse(source);
			System.out.println("Ruffys SpeciallAttack 3 on Air: " + ch.getAirBools().get("specialAttack3"));
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	
	
	
	public void setDocumentLocator(Locator locator) {
	}

	public void startDocument() throws SAXException {
		console.println("Initiating Parsing of Image-Elements");
	}

	public void endDocument() throws SAXException {
		console.println("Parsing of "+readingType+ " Image-Elements done");
	}

	public void startPrefixMapping(String prefix, String uri)
			throws SAXException {
	}

	public void endPrefixMapping(String prefix) throws SAXException {
	}

	public void startElement(String uri, String localName, String qName,
			Attributes atts) throws SAXException {
		if (localName.equals("sprites") && readingSprites == false) {
			readingSprites = true;
		} else if (readingSprites == true) {
			if (readingAnimation == false) {
				readingAnimation = true;
				time = new ArrayList<Float>();								
				character.put(localName, new ArrayList<Image>());
				
				animation = localName;
				
				loopIndex = Integer.parseInt(atts.getValue("loopIndex"));
				loops =  getVal(atts.getValue("loops").toString());
				
	
				airIndex = Integer.parseInt(atts.getValue("airIndex"));
				airBool =  getVal(atts.getValue("airBool").toString());
		
				
				this.airBools.put(animation, airBool);
				this.airIndices.put(animation,airIndex);	
				loop.add(loops);
				loopIndizes.add(loopIndex);
				
				
			} else {
				key = Integer.parseInt(atts.getValue("key"));
				duration = Float.parseFloat(atts.getValue("duration"));
				time.add(duration);
				readingIMG = true;
			}
		}
	}

	private boolean getVal(String value) {
		if(value.equals("true") || value == "true")
			return true;
		else
			return false;		
	}

	public void endElement(String uri, String localName, String qName)
			throws SAXException {
		if (localName.equals("type")){
			readingType =""+ ElementContent;
		}
		if (readingSprites == true) {
			if (readingAnimation == true) {
				if (readingIMG == true) {
					readingIMG = false;
					try {
						character.get(animation).add(key,
								loadImage(ElementContent));
						
					} catch (IndexOutOfBoundsException e) {
						console.println("IndexOutOfBoundsException while parsing character... CHECK KEYS!");
					}
					console.println("Loaded image key " + key + " from "
							+ ElementContent);
				} else {
					times.add(time);
					readingAnimation = false;
					console.println("Loaded animation " + localName
							+ " with " + character.get(localName).size()
							+ " images and with " + time.size() + " time units and is on Air: " + airBool);
				}
			} else {
				readingSprites = false;
			}
		}
	}

	public void characters(char[] ch, int start, int length)
			throws SAXException {
		ElementContent = new String(ch, start, length);
	}

	public void ignorableWhitespace(char[] ch, int start, int length)
			throws SAXException {
	}

	public void processingInstruction(String target, String data)
			throws SAXException {
	}

	public void skippedEntity(String name) throws SAXException {
	}

	public Image loadImage(String url) {
		try {
			Image img = new Image(url);
			return img;
		} catch (Exception e) {
			return null;
		}
	}

	public Map<String, Boolean> getAirBools() {
		return airBools;
	}

	public void setAirBools(Map<String, Boolean> airBools) {
		this.airBools = airBools;
	}

	public boolean isAirBool() {
		return airBool;
	}

	public void setAirBool(boolean airBool) {
		this.airBool = airBool;
	}
	

	public Map<String, ArrayList<Image>> getCharacter() {
		return this.character;
	}
	
	public ArrayList<ArrayList<Float>> getTimes() {
		return this.times;
	}
	
		
	public ArrayList<Boolean> getLoopBools() {
		return this.loop;
	}

	public ArrayList<Integer> getLoopIndizes() {
		return this.loopIndizes;
	}




	public Map<String, Integer> getAirIndices() {
		return airIndices;
	}




	public void setAirIndices(Map<String, Integer> airIndices) {
		this.airIndices = airIndices;
	}
	


	

}
