package pixelCombat.xml;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import org.xml.sax.Attributes;
import org.xml.sax.ContentHandler;
import org.xml.sax.InputSource;
import org.xml.sax.Locator;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

import pixelCombat.utils.Console;
import pixelCombat.utils.WordWrapConsole;

public class StatsParser implements ContentHandler{
	
	Map<String, Object> stats = new HashMap<String, Object>();
	
	String currentElement = "";
	String statType = null;
	
	private boolean readingStats = false;
	private boolean readingName = false;
	private boolean readingInsideStat = false;

	private Console console;
	
	public StatsParser(Console console)
	{
		this.console = console;
	}
	
	public void setDocumentLocator(Locator locator) {}

	public void startDocument() throws SAXException {
		console.println("starts reading stats");
		
	}

	public void endDocument() throws SAXException {
		console.println("ends reading stats");
		
	}
	
	public static void main(String... args) 
	{
		
		

		try {
			@SuppressWarnings("unused")
			Map<String,Object> player = new HashMap<String, Object>();
			InputStream stream = StatsParser.class.getResourceAsStream("/characters_stats/" + "Ruffy.xml");
			InputSource source = new InputSource(stream);

		    XMLReader xmlreader = XMLReaderFactory.createXMLReader();
		    StatsParser ch = new StatsParser(new WordWrapConsole());
		    xmlreader.setContentHandler(ch);
		    xmlreader.parse(source);
		    
		    player = ch.stats;
		    
		    	
		    
		    
		} catch (Exception e) {
		    e.printStackTrace();
		}

		
	    }

	public void startPrefixMapping(String prefix, String uri)
			throws SAXException {}

	public void endPrefixMapping(String prefix) throws SAXException {}

	public void startElement(String uri, String localName, String qName,
			Attributes atts) throws SAXException {
		if(readingStats){
			readingInsideStat = true;
			statType = atts.getValue("type");
			if(statType == null){
				statType = "integer";
			}
		}else{
			if(localName.equals("type")){
				readingName = true;
			}else if(localName.equals("stats") && !readingName){
				readingStats = true;
			}
		}
		
	}

	public void endElement(String uri, String localName, String qName)
			throws SAXException {
		if(readingName){
			stats.put("type", currentElement);
			readingName = false;
			console.println("character type found: " + stats.get("type"));
		}else if(readingInsideStat){
			console.println("statsfound: " +localName +" "+ parseStat());
			readingInsideStat = false;
			stats.put(localName, parseStat());
		} else if(readingStats) {
			readingStats = false;
		}
	}
	
	private Object parseStat(){
		if(statType.equals("integer"))
		{
			return Integer.parseInt(currentElement);
		}else if(statType.equals("float")){
			return Float.parseFloat(currentElement);
		}else{
			return null;
		}
	}
	
	public Map<String, Object> getStats(){
		return stats;
	}

	public void characters(char[] ch, int start, int length)
			throws SAXException {
		this.currentElement = new String(ch, start, length);	
	}

	public void ignorableWhitespace(char[] ch, int start, int length)
			throws SAXException {}

	public void processingInstruction(String target, String data)
			throws SAXException {}

	public void skippedEntity(String name) throws SAXException {}

}
