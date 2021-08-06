package paymentcalc;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/*
 * This file is used to either overwrite or create a new CSV file based on the name and information given.
 */

public class CSVWriter {
	
	public CSVWriter(String fileName, ArrayList<String> dataToWrite) {
		try {
			//Saves to the src/paymentcalc folder
			FileWriter writer = new FileWriter(new File("src/paymentcalc", fileName));
			for (String line : dataToWrite){
				writer.append(line + "\n");
			}
			//Close and flush to avoid data corruption if crash occurs.
			writer.flush();
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
