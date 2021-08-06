package paymentcalc;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.io.BufferedReader;

/*
 * This class is used to read in data from a given file name, as long as it's in a CSV.
 */

public class CSVReader {
	
	public String[] csvData;
	//Data is stored in this 'output' variable
	public ArrayList<ArrayList<String>> output = new ArrayList<ArrayList<String>>();
	private ArrayList<String> tempList;
	
	public CSVReader(String fileName)  {
		
		try (FileReader reader = new FileReader(fileName);
			BufferedReader csvReader = new BufferedReader(reader);){
			
			String row;
			//Continue reading while there are more lines to read
			while ((row = csvReader.readLine()) != null) {
				tempList = new ArrayList<String>();
				
				//Split data based on commas and add it to a array list
			    csvData = row.split(",");
			    for (String arrayVal : csvData) {
			    	tempList.add(arrayVal);
			    }
			    
			    //Add array list to a record
			    output.add(tempList);
			}
			//Close both the FileReader and BufferedReader once they are finished reading
			csvReader.close();
			reader.close();
			
		} catch (IOException e) {}
	}
	
	//Get for the data produced
	public ArrayList<ArrayList<String>> CsvData() {
		return output;
	}
}
