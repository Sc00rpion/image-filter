package console.filter;


import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import console.filter.interfaces.IFilter;



public class MedianFilter implements IFilter {

	@Override
	public BufferedImage filter(BufferedImage image) {
		WritableRaster raster = image.getRaster(); 
		int width = image.getWidth();
		int height = image.getHeight();
		double tmp[] = new double[3];
		int i, j;
		BufferedImage resultImage = new BufferedImage(width, height, image.getType());
		WritableRaster resultRaster = resultImage.getRaster(); 
		for (i = 1; i < width - 1; i++) {
			for (j = 1; j < height - 1; j++) {
				List<Integer> r = new ArrayList<>();
				List<Integer> g = new ArrayList<>();
				List<Integer> b = new ArrayList<>();

				for (int k = i-1; k <= i+1; k++) {
					for (int l = j-1; l <= j+1; l++) {
						int[] p = new int[3];
						raster.getPixel(k, l, p);
						r.add(p[0]);
						g.add(p[1]);
						b.add(p[2]);
					}
				}
				Collections.sort(r);
				Collections.sort(g);
				Collections.sort(b);
				tmp[0] = r.get(r.size()/2 + 1 );
				tmp[1] = g.get(g.size()/2 + 1 );
				tmp[2] = b.get(b.size()/2 + 1 );

				resultRaster.setPixel(i, j, tmp);
			}
		}

		return resultImage;
	}

}
