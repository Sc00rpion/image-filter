package console.filter;

import java.awt.image.BufferedImage;

public class MeanRemovalFilter implements IFilter {

	@Override
	public BufferedImage filter(BufferedImage image) {
		int[][] mask = {
				{-1, -1, -1},
				{-1, 9, -1},
				{-1, -1, -1}};
		return FilterAssembler.filterByMask(image, mask);
	}

}
