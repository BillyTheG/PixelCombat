package pixelCombat.view.effectManager;

import java.util.HashMap;
import java.util.Random;

import javafx.scene.image.Image;
import javafx.scene.image.PixelReader;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;
import pixelCombat.model.Character;

public class EffectManager {

	public int current_index = -1;
	public int new_index = -1;
	private int anim= -1;
	public HashMap<Integer, Integer> color_pallette;
	public volatile Thread colorizerWorker;
	public volatile Colorizer colorizer;
	
	
	public EffectManager() {
		this.color_pallette = new HashMap<Integer, Integer>();
		init();
		colorizer = new Colorizer(this);
		colorizerWorker = new Thread(colorizer);
		
		// TODO edit effects like fire, define filters and image editing
		// tools...
		//
	}

	private void init() {
		color_pallette.put(16631642, -16603888);
		color_pallette.put(16631383, -16603119);
		color_pallette.put(16566359, -16603888);
		color_pallette.put(16167225, -16136688);

		color_pallette.put(Integer.parseInt("ff8ae33", 16),
				Integer.parseInt("ff833f5", 16));
		color_pallette.put(Integer.parseInt("ffbb335", 16),
				Integer.parseInt("ffb35f5", 16));
		color_pallette.put(Integer.parseInt("fffbc40", 16),
				Integer.parseInt("fff40f5", 16));
		color_pallette.put(Integer.parseInt("fffc34c", 16),
				Integer.parseInt("fff4cf1", 16));

		color_pallette.put(Integer.parseInt("ffab540", 16),
				Integer.parseInt("ffa40f6", 16));

	}

	public Image drawImage(Character character) {
		if (character.statusLogic.isKnockback()) {
			int width = (int) character.picManager.getImage().getWidth();
			int height = (int) character.picManager.getImage().getHeight();
			WritableImage image = new WritableImage(width, height);
			PixelWriter writer = image.getPixelWriter();
			PixelReader reader = character.picManager.getImage()
					.getPixelReader();

			for (int x = 0; x < width; x++) {
				Random random = new Random();
				for (int y = 0; y < height; y++) {
					if (reader.getArgb(x, y) != 0
							&& reader.getArgb(x, y) != 33587200) {
						writer.setColor(x, y, Color.RED);
						int old = image.getPixelReader().getArgb(x, y);
						writer.setArgb(x, y, old + random.nextInt(201));
					} else
						writer.setArgb(x, y, reader.getArgb(x, y));

				}
			}
			return image;
		}
		return character.picManager.getImage();
	}

	public synchronized Image drawImage(Image image, int new_index, int anim) throws InterruptedException {
		boolean notSameAnim = false;
		if(this.colorizer.getCurrentImage() == null)
		{
			this.colorizer.setUpImage(image);
			this.colorizer.setUpImage2(image);
		}
		
		if(this.anim == anim)
		{
			if(current_index == new_index)
			{
				if(this.colorizer.getLastImage() != null)			
					return colorizer.getLastImage();
				else 
					return image;
			}
	
		}
		else
		{
			this.anim = anim;
			notSameAnim = true;
		}
		
		
		if(this.current_index != new_index || notSameAnim)
			this.current_index = new_index;
		else
		if(this.colorizer.getLastImage() != null)
			return colorizer.getLastImage();
		else 
			return image;
		
		if(!colorizer.done)
		{
			this.colorizer.setUpImage(image);
			this.colorizerWorker = new Thread(colorizer);
			this.colorizerWorker.start();	
		}
		else
		{			
			colorizerWorker.join();
			colorizer.done = false;
			return colorizer.getLastImage();
		}
		
		
		 return colorizer.getLastImage();
	}

	private boolean checkColor(int color) {

		int blue = color & 0xff;
		int green = (color & 0xff00) >> 8;
		int red = (color & 0xff0000) >> 16;

		int[] lab1 = new int[3];
		int[] lab2 = new int[3];
		rgb2lab(red, green, blue, lab1);
		rgb2lab(240, 149, 25, lab2);

		float distance1 = (float) Math.sqrt((lab1[0] - lab2[0])
				* (lab1[0] - lab2[0]) + (lab1[1] - lab2[1])
				* (lab1[1] - lab2[1]) + (lab1[2] - lab2[2])
				* (lab1[2] - lab2[2]));

		// float distance2 = (float)
		// Math.sqrt((247-red)*(247-red)+(90-green)*(90-green)+(223-blue)*(223-blue));

		if (distance1 <= 37 || (red >= 245 && distance1 <= 59))
			return true;
		return false;
	}

	public int checkColor2(Color color, int old) {

		int blue = (int) color.getBlue();
		int green = (int) color.getGreen();
		int red = (int) color.getRed();

		for (int key : color_pallette.keySet()) {
			int blue2 = key & 0xff;
			int green2 = (key & 0xff00) >> 8;
			int red2 = (key & 0xff0000) >> 16;
			//int[] lab1 = new int[3];
		    //int[] lab2 = new int[3];
			// rgb2lab(red,green,blue,lab1);
			// rgb2lab(red2,green2,blue2,lab2);

			float distance2 = (float) Math.sqrt((red2 - red) * (red2 - red)
					+ (green2 - green) * (green2 - green) + (blue2 - blue)
					* (blue2 - blue));
			// float distance1 = (float)
			// Math.sqrt((lab1[0]-lab2[0])*(lab1[0]-lab2[0])+(lab1[1]-lab2[1])*(lab1[1]-lab2[1])+(lab1[2]-lab2[2])*(lab1[2]-lab2[2]));

			if (distance2 < 5)
				return this.color_pallette.get(key);
		}

		return old;

	}

	public void rgb2lab(int R, int G, int B, int[] lab) {
		// http://www.brucelindbloom.com

		float r, g, b, X, Y, Z, fx, fy, fz, xr, yr, zr;
		float Ls, as, bs;
		float eps = 216.f / 24389.f;
		float k = 24389.f / 27.f;

		float Xr = 0.964221f; // reference white D50
		float Yr = 1.0f;
		float Zr = 0.825211f;

		// RGB to XYZ
		r = R / 255.f; // R 0..1
		g = G / 255.f; // G 0..1
		b = B / 255.f; // B 0..1

		// assuming sRGB (D65)
		if (r <= 0.04045)
			r = r / 12;
		else
			r = (float) Math.pow((r + 0.055) / 1.055, 2.4);

		if (g <= 0.04045)
			g = g / 12;
		else
			g = (float) Math.pow((g + 0.055) / 1.055, 2.4);

		if (b <= 0.04045)
			b = b / 12;
		else
			b = (float) Math.pow((b + 0.055) / 1.055, 2.4);

		X = 0.436052025f * r + 0.385081593f * g + 0.143087414f * b;
		Y = 0.222491598f * r + 0.71688606f * g + 0.060621486f * b;
		Z = 0.013929122f * r + 0.097097002f * g + 0.71418547f * b;

		// XYZ to Lab
		xr = X / Xr;
		yr = Y / Yr;
		zr = Z / Zr;

		if (xr > eps)
			fx = (float) Math.pow(xr, 1 / 3.);
		else
			fx = (float) ((k * xr + 16.) / 116.);

		if (yr > eps)
			fy = (float) Math.pow(yr, 1 / 3.);
		else
			fy = (float) ((k * yr + 16.) / 116.);

		if (zr > eps)
			fz = (float) Math.pow(zr, 1 / 3.);
		else
			fz = (float) ((k * zr + 16.) / 116);

		Ls = (116 * fy) - 16;
		as = 500 * (fx - fy);
		bs = 200 * (fy - fz);

		lab[0] = (int) (2.55 * Ls + .5);
		lab[1] = (int) (as + .5);
		lab[2] = (int) (bs + .5);
	}

	public Image getBlueChannel(Image sourceImage) {
	    PixelReader pr = sourceImage.getPixelReader();
	    int width = (int) sourceImage.getWidth();
	    int height = (int) sourceImage.getHeight();

	    WritableImage result = new WritableImage(width, height);
	    PixelWriter pw = result.getPixelWriter();
	    for (int x = 0;  x < width;  x++) {
	        for (int y = 0; y < height;  y++) {
	            
	            int old = pr.getArgb(x, y);
	            if (checkColor(old) && pr.getArgb(x, y) != 33587200)
	            pw.setColor(x, y, new Color(Color.VIOLET.getRed(), Color.VIOLET.getGreen(), Color.VIOLET.getBlue(), 1.0)); 
	           // pw.setColor(x, y, new Color(Color.DARKGRAY.getRed(), Color.DARKGRAY.getGreen(), Color.DARKGRAY.getBlue(), 1.0)); 
	            
	            else
	            	pw.setArgb(x, y, pr.getArgb(x, y));
	        }
	    }

	    return result;
	}

}
