package pixelCombat.view.effectManager;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Map;

import javax.imageio.ImageIO;

import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;
import pixelCombat.view.animation.PositionedImage;
import pixelCombat.xml.CharacterParser;

public class Colorizer implements Runnable {

	public volatile EffectManager effectManager;
	private volatile Image currentImage;
	private volatile Image lastImage;
	public volatile boolean done = false;
	public volatile WritableImage result;
	public volatile ArrayList<Thread> colorizers;
	
	public Colorizer(EffectManager effectManager) {
		this.effectManager = effectManager;
		colorizers = new ArrayList<Thread>();
		
	}

	public static void main(String [] args) throws SAXException, IOException {
		
		
		String target_url = "images/ruffy/temp";
		
		InputStream imageStream = Colorizer.class.getClassLoader().getResourceAsStream("characters_sprites/" + "Ruffy" + ".xml");
		
		
		//	InputStream stream = StatsParser.class.getResourceAsStream("/characters_sprites/" + name + ".xml");
		InputSource source = new InputSource(imageStream);

		XMLReader xmlreader = XMLReaderFactory.createXMLReader();
			CharacterParser ch = new CharacterParser(null);
		xmlreader.setContentHandler(ch);
		xmlreader.parse(source);

		Map<String,ArrayList<PositionedImage>> player = ch.getCharacter();
		
		for(String animation : player.keySet()) {
			for(PositionedImage image : player.get(animation)) {
				String[] suffixes = image.getUrl().split("/images/ruffy/");
				String new_url = target_url+"/"+suffixes[0];				
				BufferedImage bufferedImage = ColorizerUtils.toBufferedImage(image);
				int[] pixels = ColorizerUtils.convertTo2DWithoutUsingGetRGB(bufferedImage);
				Image convertedImage = ColorizerUtils.toJavaFXImage(ColorizerUtils.toBufferedImageFromArray(bufferedImage,pixels,bufferedImage.getWidth(),bufferedImage.getHeight()));
				saveToFile(convertedImage,new_url);
			}
		}
		
		
		
	}
	
	public  static void saveToFile(Image image, String name) {
	    File outputFile = new File(name);
	    BufferedImage bImage = SwingFXUtils.fromFXImage(image, null);
	    try {
	      ImageIO.write(bImage, "png", outputFile);
	    } catch (IOException e) {
	      throw new RuntimeException(e);
	    }
	  }
	

	public synchronized void setUpImage(Image image) {
		this.currentImage = image;
	}

	public synchronized void setUpImage2(Image image) {
		this.lastImage = image;
	}
	
	@Override
	public synchronized void run() 
	{
		BufferedImage bufferedImage = ColorizerUtils.toBufferedImage(currentImage);
		int[] pixels = ColorizerUtils.convertTo2DWithoutUsingGetRGB(bufferedImage);
		lastImage = ColorizerUtils.toJavaFXImage(ColorizerUtils.toBufferedImageFromArray(bufferedImage,pixels,bufferedImage.getWidth(),bufferedImage.getHeight()));
	}



	public synchronized Image getCurrentImage() {
	
		return currentImage;
	}

	public synchronized Image getLastImage() {
		
		return lastImage;
	}
	
}
