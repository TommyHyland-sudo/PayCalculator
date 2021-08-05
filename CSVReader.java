package paymentcalc;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.io.BufferedReader;

public class CSVReader {
	
	public String[] csvData;
	public ArrayList<ArrayList<String>> output = new ArrayList<ArrayList<String>>();
	private ArrayList<String> tempList;
	
	public CSVReader(String fileName)  {
		
		try (FileReader reader = new FileReader(fileName);
			BufferedReader csvReader = new BufferedReader(reader);){
			
			String row;
			while ((row = csvReader.readLine()) != null) {
				tempList = new ArrayList<String>();
				
			    csvData = row.split(",");
			    //System.out.println(csvData.length);
			    for (String arrayVal : csvData) {
			    	//System.out.println(arrayVal);
			    	tempList.add(arrayVal);
			    }
			    
			    output.add(tempList);
			}
			csvReader.close();
			reader.close();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public ArrayList<ArrayList<String>> CsvData() {
		return output;
	}
}
