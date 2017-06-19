package console;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

import org.apache.commons.io.FilenameUtils;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.ParameterException;

import console.filter.IFilter;
import console.service.FactoryFilter;

public class Application {
	
	@Parameter(required = true, description = "file")
	List<String> files = new ArrayList<>();
    @Parameter(description = "Nazwa wybranego filtru. Dostępne: gauss, laplace, mean_removal, negative, black_white, average, median", names={"-t"})
    String filterType = "gauss";
    @Parameter(description = "Nazwa pliku wynikowego", names={"-o"})
    String output = "out.jpg";

	public static void main(String[] args) throws IOException {
        Application main = new Application();
        JCommander com = JCommander.newBuilder()
            .addObject(main)
            .build();
        try {
            com.parse(args);
          } catch (ParameterException e) {
            System.err.println(e.getMessage());
            com.usage();
            System.exit(1);
          }
        File input = new File(main.files.get(0));
        if(!input.exists()){
            System.err.println("Nie znaleziono pliku: "+ input.getAbsolutePath());
            com.usage();
            System.exit(1);
        }

        main.run();
	}
	
    public void run() throws IOException {
		BufferedImage image = ImageIO.read(new File(files.get(0)));
		if (image == null){
            System.err.println("Podany plik nie jest obrazem: " + files.get(0));
            System.exit(1);
		}
		IFilter f = FactoryFilter.createFilter(filterType);
		image = f.filter(image);
		File outputFile = new File(output);
		String ext = FilenameUtils.getExtension(output);
		if(!ImageIO.write(image, ext,outputFile)){
			System.err.println("Prawdopodobnie uzyles zlego rozszerzenia pliku wynikowego. Zmieniono na domyslny PNG");
			output = FilenameUtils.removeExtension(output);
			output += ".png";
			ImageIO.write(image, "png" ,new File(output));	
		}
    }

}
