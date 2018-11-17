package com.client_mail;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import sun.misc.BASE64Decoder;
public class Test1 {

	public static void main(String args[]) throws IOException{
		String sourceData = "data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAH0AAAB9AQAAAACn+1GIAAAApElEQVR42u3VMQ7DMAwDQP6A//+lxm4qxbZoPZpOtwQGnNxg2JSMoNfngRsugALQgKcYPN5TCoVqlqcTqOIFUGcwQ9/raTdB6XrN39Q3wc9saqn+HhQbZM2cg96ISYk5tMNRqRCDDkZH9Ak5AfeKztfIYTp/9oaOwcXWkqwDeOWszTGH6Xr4CpzB2jAZqNDf6gdgU/P+XuRdmJCbBDuG+x/1b3gCOSaFcLrWXbIAAAAASUVORK5CYII";

		// tokenize the data
		String[] parts = sourceData.split(",");
		String imageString = parts[1];

		// create a buffered image
		BufferedImage image = null;
		byte[] imageByte;

		BASE64Decoder decoder = new BASE64Decoder();
		imageByte = decoder.decodeBuffer(imageString);
		ByteArrayInputStream bis = new ByteArrayInputStream(imageByte);
		image = ImageIO.read(bis);
		bis.close();

		// write the image to a file
		File outputfile = new File("./image.png");
		ImageIO.write(image, "png", outputfile);
		System.out.println("KK");
	}
}
