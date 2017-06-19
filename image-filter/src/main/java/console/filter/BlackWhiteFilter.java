package console.filter;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;

import console.filter.interfaces.IFilter;

public class BlackWhiteFilter implements IFilter {

	@Override
	public BufferedImage filter(BufferedImage image) {
		BufferedImage result = new BufferedImage( image.getWidth(), image.getHeight(), BufferedImage.TYPE_BYTE_GRAY );  
		Graphics g = result.getGraphics();  
		g.drawImage( image, 0, 0, null );  
		g.dispose();
		
		WritableRaster raster = result.getRaster(); 
		int width = result.getWidth();
		int height = result.getHeight();
		int pixels[] = new int[1];
		int i, j;
		for (i = 0; i < width; i++) {
			for (j = 0; j < height; j++) {
				raster.getPixel(i, j, pixels);

					if (pixels[0] >= 127){
						pixels[0] = 255;
					}else{
						pixels[0] = 0;
					}

				raster.setPixel(i, j, pixels);
			}
		}
		return result;
	}

}
