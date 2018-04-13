package pixelCombat.view;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;

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
