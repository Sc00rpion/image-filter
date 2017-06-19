package console.assembler;

import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;

public class FilterAssembler {

	public static BufferedImage filterByMask(BufferedImage image, int[][] mask) {
		WritableRaster raster = image.getRaster(); 
		int width = image.getWidth();
		int height = image.getHeight();
		int pixels[] = new int[3];
		int margin = (mask.length - 1) / 2;
		int i, j, sumMask;
		double tmp[] = new double[3];
		BufferedImage resultImage = new BufferedImage(width, height, image.getType());
		WritableRaster resultRaster = resultImage.getRaster(); 
		for (i = margin; i < width - margin; i++) {
			for (j = margin; j < height - margin; j++) {
				tmp[0] = 0;
				tmp[1] = 0;
				tmp[2] = 0;
				sumMask = 0;
				raster.getPixel(i, j, pixels);

				for (int k = 0; k < mask.length; k++) {
					for (int l = 0; l < mask.length; l++) {
						int[] p = new int[3];
						sumMask += mask[k][l];
						raster.getPixel(i - margin + k, j - margin + l, p);
						for (int xx = 0; xx < 3; xx++) {
							tmp[xx] += mask[k][l] * p[xx];
						}
					}
				}
				if (sumMask > 0){
					tmp[0] /= sumMask;
					tmp[1] /= sumMask;
					tmp[2] /= sumMask;
				}
				for (int xx = 0; xx < 3; xx++) {
					tmp[xx] = tmp[xx] > 255 ? 255 : tmp[xx];
					tmp[xx] = tmp[xx] < 0 ? 0 : tmp[xx];
				}
					
					

				resultRaster.setPixel(i, j, tmp);
			}
		}

		return resultImage;
	}
}
