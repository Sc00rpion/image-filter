package console.filter;

import java.awt.image.BufferedImage;

import console.assembler.FilterAssembler;
import console.interfaces.IFilter;

public class AverageFilter implements IFilter {

	@Override
	public BufferedImage filter(BufferedImage image) {
		int[][] mask = {
				{1, 1, 1},
				{1, 1, 1},
				{1, 1, 1}};
		return FilterAssembler.filterByMask(image, mask);
	}

}
