package console.filter;

import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;

public class NegativeFilter implements IFilter {

	@Override
	public BufferedImage filter(BufferedImage image) {
		WritableRaster raster = image.getRaster(); 
		int width = image.getWidth();
		int height = image.getHeight();
		int pixels[] = new int[3];
		int i, j;
		double tmp[] = new double[3];
		BufferedImage resultImage = new BufferedImage(width, height, image.getType());
		WritableRaster resultRaster = resultImage.getRaster(); 
		for (i = 0; i < width; i++) {
			for (j = 0; j < height; j++) {
				raster.getPixel(i, j, pixels);
				tmp[0] = 255 - pixels[0];
				tmp[1] = 255 - pixels[1];
				tmp[2] = 255 - pixels[2];
				resultRaster.setPixel(i, j, tmp);
			}
		}

		return resultImage;
	}

}
