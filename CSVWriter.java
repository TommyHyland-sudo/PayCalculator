package paymentcalc;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class CSVWriter {
	
	public CSVWriter(String fileName, ArrayList<String> dataToWrite) {
		try {
			FileWriter writer = new FileWriter(new File("src", fileName));
			for (String line : dataToWrite){
				writer.append(line + "\n");
			}
			writer.flush();
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
