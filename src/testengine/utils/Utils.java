package testengine.utils;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.imageio.ImageIO;

public class Utils {
	
	private static InputStream loadFile(String path) {
			System.out.println("loading file: " + path);
			InputStream is = System.class.getResourceAsStream(path);
			if (is == null) {
				System.out.println("file not found: " + path);
				System.exit(-1);
				return null;
			}
			return is;
	}
	
	public static String loadFileAsString(String path) {
		StringBuilder builder = new StringBuilder();
		BufferedReader br = new BufferedReader(new InputStreamReader(loadFile(path)));
		String line;
		try {
			while ((line = br.readLine()) != null) {
				builder.append(line + "\n");
			}
			br.close();
			return builder.toString();
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(-1);
			return null;
		}
	}
	
	public static int parseInt(String number) {
		try {
			return Integer.parseInt(number);
		}
		catch (NumberFormatException e) {
			e.printStackTrace();
			return 0;
		}
	}

	public static BufferedImage loadFileAsImage(String path) {
		try {
			return ImageIO.read(loadFile(path));
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(-1);
			return null;
		}
	}

	/**@author Nick
	 * creates a copy of an array of images and applies a 90 degree clockwise rotation to each
	 * 
	 * @param imagesIn
	 * an array of images
	 * 
	 * @return
	 * a new array that contains copies of all images in imagesIn, all rotated clockwise by 90 degrees
	 */
	public static BufferedImage[] rotateImageArray(BufferedImage[] imagesIn) {
		int size = imagesIn.length;
		BufferedImage[] imagesOut = new BufferedImage[size];
		
		for (int i = 0; i < size; i++) {
			if (imagesIn[i] == null)
				imagesOut[i] = null;
			else
				imagesOut[i] = rotateImageClockwise(imagesIn[i]);
		}
		return imagesOut;
	}

	/**@author Nick
	 * creates a copy of an image and applies a 90 degree clockwise rotation to the copy
	 * 
	 * @param imageIn 
	 * an image
	 * 
	 * @return 
	 * a copy of imageIn, rotated clockwise by 90 degrees
	 */
	public static BufferedImage rotateImageClockwise(BufferedImage imageIn) {
	    int width = imageIn.getWidth();
	    int height = imageIn.getHeight();	
	    BufferedImage imageOut = new BufferedImage(height, width, imageIn.getType());
	    
	    for(int i=0 ; i < width ; i++) {
	    	for(int j=0 ;j < height ;j++) {
	    		imageOut.setRGB(height-1-j, i, imageIn.getRGB(i,j));
	    	}
	    }
	    return imageOut;
	}
}
