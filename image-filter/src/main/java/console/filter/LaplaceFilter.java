package console.filter;

import java.awt.image.BufferedImage;

public class LaplaceFilter implements IFilter {

	@Override
	public BufferedImage filter(BufferedImage image) {
		int[][] mask = {
				{0, -1, 0},
				{-1, 4, -1},
				{0, -1, 0}};
		return FilterAssembler.filterByMask(image, mask);
	}

}
