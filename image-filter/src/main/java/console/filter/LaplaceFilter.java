package console.filter;

import java.awt.image.BufferedImage;

import console.assembler.FilterAssembler;
import console.interfaces.IFilter;

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
