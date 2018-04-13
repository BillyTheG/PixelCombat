package pixelCombat.xml;

import java.io.FileInputStream;
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
import pixelCombat.Math.BoundingRectangle;
import pixelCombat.Math.Vector2d;
import pixelCombat.utils.Console;
import pixelCombat.utils.WordWrapConsole;



public class XML_Box_Reader implements ContentHandler {
	
	// Zwischenspeicher fuer Inhalt von Elementen
	String ElementContent = null;
	private Map<String,  ArrayList<ArrayList<BoundingRectangle>>> boxes = new HashMap<String, ArrayList<ArrayList<BoundingRectangle>>>();
	private boolean readingBox = false;
	private boolean readingBoxList = false;
	private boolean readingIMG = false;
	private boolean readingAnimation = false;
	private int picture = 0;
	private int id = 0;
	private float x = 0;
	private float y = 0;
	private float width = 0;
	private float height = 0;
	private boolean hurts = false;
	
	private String animation = "";
	private Console console;
	
	
	
	public XML_Box_Reader(Console console)
	{
		this.console = console;
	}
	
	public Map<String,  ArrayList<ArrayList<BoundingRectangle>>> getBoxes() {
		return this.boxes;
	}

	public static void main(String... args) 
	{
		
		try {

			InputStream stream = new FileInputStream("resources/chars_boxes/" + "Ruffy.xml");
			InputSource source = new InputSource(stream);

			
			XMLReader xmlreader = XMLReaderFactory.createXMLReader();
			XML_Box_Reader ch = new XML_Box_Reader(new WordWrapConsole());
			xmlreader.setContentHandler(ch);
			xmlreader.parse(source);
			System.out.println();
			System.out.println(ch.getBoxes().get("attack").get(0).size());

		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}



	public void setDocumentLocator(Locator locator) {
	}

	public void startDocument() throws SAXException {
		console.println("Starte Charakter parsen");
	}

	public void endDocument() throws SAXException {
		console.println("Ende Charakter parsen");
	}

	public void startPrefixMapping(String prefix, String uri)
			throws SAXException {
	}

	public void endPrefixMapping(String prefix) throws SAXException {
	}

	public void startElement(String uri, String localName, String qName,
			Attributes atts) throws SAXException {
		if (localName.equals("boxes") && readingAnimation == false) 
		{
			readingAnimation = true;
		} 
		else 
			if (readingAnimation == true) {
	
				if (readingIMG == false) {
					boxes.put(localName, new ArrayList<ArrayList<BoundingRectangle>>());
					animation = localName;
					readingIMG = true;				
			}  else {
				if(readingBoxList == false)
				{
					readingBoxList = true;
					boxes.get(animation).add(new ArrayList<BoundingRectangle>());
					picture = boxes.get(animation).size()-1;
					//picture = Integer.parseInt(atts.getValue("picture"));
				}
				else
					if (readingBox == false) 
					{
						id = Integer.parseInt(atts.getValue("id"));
						x =  Float.parseFloat(atts.getValue("x"));
						y =  Float.parseFloat(atts.getValue("y"));
						height =  Float.parseFloat(atts.getValue("height"));
						width =  Float.parseFloat(atts.getValue("width"));
						hurts = parseHurts(atts.getValue("hurts"));
						readingBox = true;
					}
			}
		}
	}

	

	public void endElement(String uri, String localName, String qName)
			throws SAXException {
		if (readingAnimation == true) {
			if(readingIMG == true){
			if (readingBoxList == true) {
				if (readingBox == true) {
					readingBox = false;
					try {				
						BoundingRectangle newBox = new BoundingRectangle(height,new Vector2d(x,y),width);
						newBox.setHurts(hurts);
						boxes.get(animation).get(picture).add(newBox);
					} catch (IndexOutOfBoundsException e) {
						console.println("IndexOutOfBoundsException while parsing character... CHECK KEYS!");
					}
					console.println("Loaded BoundaryRectangle with id " + id + " with height: " + height  + " width: " +
					width + " and coords: (" + x +"," +y + ")");
				} else {
					readingBoxList = false;
					console.println("Loaded BoxList " + localName
							+ " with " + boxes.get(animation).get(picture).size()
							+ " boxes");
				}
			}
			else
				readingIMG = false;
		}
			else
				readingAnimation = false;
			
		}
	}
	
	private boolean parseHurts(String value) {
		if(value.equals("true"))
			return true;
		return false;
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
			console.println("picture found");
			return img;
		} catch (Exception e) {
			console.println("no picture found");
			return null;
		}
	}

}
