package pixelCombat.view.effectManager;

import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.awt.image.WritableRaster;

import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;

public class ColorizerUtils 
{
	public static float r, g, b, X, Y, Z, fx, fy, fz, xr, yr, zr;
	public static float Ls, as, bs;
	public static float eps = 216.f / 24389.f;
	public static float k = 24389.f / 27.f;
	public static int[] lab1 = new int[3];
	public static int[] lab2 = new int[3];
	
	
	public static float Xr = 0.964221f; // reference white D50
	public static float Yr = 1.0f;
	public static float Zr = 0.825211f;
	
	
	public static BufferedImage toBufferedImage(Image image)
	{
		BufferedImage image2 = new BufferedImage((int)image.getWidth(),(int)image.getHeight(),BufferedImage.TYPE_INT_ARGB);
		return SwingFXUtils.fromFXImage(image, image2);		
	}
	
	public static Image toJavaFXImage(BufferedImage image)
	{
		return SwingFXUtils.toFXImage(image, null);				
	}
	
	
	public static BufferedImage toBufferedImageFromArray(BufferedImage img, int[] myRgbArray,int width, int height)
	{	
		BufferedImage image = new BufferedImage(width,height,BufferedImage.TYPE_INT_ARGB);
		WritableRaster raster = (WritableRaster)img.getData();		
		raster.setPixels(0, 0, img.getWidth(), img.getHeight(), myRgbArray);
		image.setData(raster);
		return image;
	}
	
	@SuppressWarnings("unused")
	public static int[] convertTo2DWithoutUsingGetRGB(BufferedImage image3) {
			
		BufferedImage image2 = new BufferedImage(image3.getWidth(), image3.getHeight(), BufferedImage.TYPE_4BYTE_ABGR); 
		image2.getGraphics().drawImage(image3, 0, 0, null); 
	      final byte[] pixels = ((DataBufferByte) image2.getRaster().getDataBuffer()).getData();	     
	      final boolean hasAlphaChannel = image2.getAlphaRaster() != null;

	      int[] result = new int[pixels.length];
	      if (hasAlphaChannel) {
	         final int pixelLength = 4;
	   
	         for (int pixel = 0, i= 0;pixel < pixels.length; pixel +=pixelLength) {
	            
	        	 int[] arg = new int[3];
	        	 arg[0] = ((0xff &((int) pixels[pixel+3]  >> 0) ));
	        	 arg[1] = (0xff &((int) pixels[pixel+2]   >>  0));
	        	 arg[2] = (0xff &((int) pixels[pixel+1]   >> 0) );
	            if (checkColor(arg))// && argb[1]+argb[2]+argb[3] != 33587200)
	        	{
	            	 result[i+3]  	= (0xff &((int) pixels[pixel]   >> 0));// alpha
	        		 result[i+2]	= 180;//238; //(0xff &((int) pixels[pixel+1]   >> 0) ); // blue
	        		 result[i+1]	=105;//130;//(0xff &((int) pixels[pixel+2]   >>  0)); // green
	        		 result[i]	=  255;//238; //;((0xff &((int) pixels[pixel+3]  >> 0) )); // red
	        		 
	        	}
	        	else
	        	{
	        		 result[i+3]  	= (0xff &((int) pixels[pixel]   >> 0));// alpha
	        		 result[i+2]	= (0xff &((int) pixels[pixel+1]   >> 0) ); // blue
	        		 result[i+1]	= (0xff &((int) pixels[pixel+2]   >>  0)); // green
	        		 result[i]	= ((0xff &((int) pixels[pixel+3]  >> 0) )); // red     		
	        		  
	        	}
	            i+=pixelLength;	            
	         }
	      } else {
	         final int pixelLength = 3;
	       //  result= new int[width*height];
	         for (int pixel = 0, i = 0; pixel < pixels.length; pixel += pixelLength) {
	        	 
		        	
	        	 if (false)//checkColor(pixels[pixel]))// && argb[1]+argb[2]+argb[3] != 33587200)
		        	{
		        		 result[i] 	= 100;
		        		 result[i+1] =    238;//(int) Color.VIOLET.getBlue();
		        		 result[i+2] =    130;//(int) Color.VIOLET.getGreen();
		        		 result[i+3] =    238;//(int) Color.VIOLET.getRed();
		        		 
		        		 
		        	}
		        	else
		        	{	        		 
		        		 result[i] = 100 | pixels[pixel] << 16 | pixels[pixel] << 8 | pixels[pixel]; // all values must be 0-255    		 
		        	}
	        	 i++;
	         }
	      }

	      return result;
	   }
	
	
	
	private static boolean checkColor(int[] color) {
		int blue = color[2];
		int green = color[1];
		int red = color[0];
		
		if(red <= 150 && green <= 255 && blue <= 255)
			return false;	
		
		lab1 = new int[3];
		lab2 = new int[3];
		
		rgb2lab(red, green, blue, lab1);
		rgb2lab(240, 149, 25, lab2)	;
				

		float distance1 = (float) Math.sqrt((lab1[0] - lab2[0])
				* (lab1[0] - lab2[0]) + (lab1[1] - lab2[1])
				* (lab1[1] - lab2[1]) + (lab1[2] - lab2[2])
				* (lab1[2] - lab2[2]));

		if (distance1 <= 37 || (red>= 245 && distance1 <= 59))
			return true;
		return false;
	}
	
	
	public static void rgb2lab(int R, int G, int B, int[] lab) {
		// http://www.brucelindbloom.com

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
	
}
