package console.service;

import console.filter.AverageFilter;
import console.filter.BlackWhiteFilter;
import console.filter.GaussFilter;
import console.filter.LaplaceFilter;
import console.filter.MeanRemovalFilter;
import console.filter.MedianFilter;
import console.filter.NegativeFilter;
import console.interfaces.IFilter;

public class FactoryFilter {
	public static IFilter createFilter(String name){
		switch (name){
		case "gauss":
			return new GaussFilter();
		case "laplace":
			return new LaplaceFilter();
		case "mean_removal":
			return new MeanRemovalFilter();
		case "negative":
			return new NegativeFilter();
		case "black_white":
			return new BlackWhiteFilter();
		case "average":
			return new AverageFilter();
		case "median":
			return new MedianFilter();
		default:
			System.out.println("Nie znaleziono filtru: " + name + ". Zastosowano domyślny filtr Gaussa");
			return new GaussFilter();
		}
	}
}
