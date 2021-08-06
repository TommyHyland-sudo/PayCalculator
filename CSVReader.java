package paymentcalc;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import java.io.BufferedReader;

/*
 * This class is used to read in data from a given file name, as long as it's in a CSV.
 */

public class CSVReader {
	
	private String[] csvData;
	//Data is stored in this 'output' variable
	private ArrayList<ArrayList<String>> output = new ArrayList<ArrayList<String>>();
	private ArrayList<String> tempList;
	
	public CSVReader(String fileName)  {
		
		try (FileReader reader = new FileReader(fileName);
			BufferedReader csvReader = new BufferedReader(reader);){
			
			String row;
			int columnCount = 0;
			int rowCount = 0;
			//Continue reading while there are more lines to read
			while ((row = csvReader.readLine()) != null) {
				tempList = new ArrayList<String>();
				
				//Split data based on commas and add it to a array list
			    csvData = row.split(",");
			    for (String arrayVal : csvData) {
			    	//Columns 2 and 3 need to be checked for integer inputs
			    	if (columnCount != 2 && columnCount != 3) {
			    		tempList.add(arrayVal);
			    	} else {
			    		//Column 2 should be an integer, check if it's numeric
			    		if (columnCount == 2) {
			    			if (isNumeric(arrayVal)) {
				    			tempList.add(arrayVal);
				    		}
			    		//Column 3 contains a % symbol, trim it then check if it's numeric
			    		} else if (columnCount == 3) {
			    			String temp = arrayVal.replace("%", "");
			    			if (isNumeric(temp)) {
			    				tempList.add(arrayVal);
			    			}
			    		}
			    	}
			    	columnCount++;
			    }
			    columnCount = 0;
			    
			    //Only if 5 values are detected will the row be added to the array list
			    if (tempList.size() == 5) {
			    	//Add array list to a record
				    output.add(tempList);
			    } else {
			    	System.out.println("Values are incorrect on row " + (rowCount+1) + " , skipping row");
			    }
			    rowCount++;
			}
			//Close both the FileReader and BufferedReader once they are finished reading
			csvReader.close();
			reader.close();
			
		} catch (IOException e) {
			output = null;
		}
	}
	
	//Get for the data produced
	public ArrayList<ArrayList<String>> CsvData() {
		return output;
	}
	
	//Used to validate integers
	//Grabbed from https://www.baeldung.com/java-check-string-number
	public static boolean isNumeric(String strNum) {
	    if (strNum == null) {
	        return false;
	    }
	    try {
	        int d = Integer.parseInt(strNum);
	    } catch (NumberFormatException nfe) {
	        return false;
	    }
	    return true;
	}
}
