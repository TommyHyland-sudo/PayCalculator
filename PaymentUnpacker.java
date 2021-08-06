package paymentcalc;

import java.util.ArrayList;

/*
 * This file is used to unpack a given record containing data gathered from an input CSV. 
 * For each row encountered this class will call the paymentprocessing class to produce a calculated CSV string.
 * All strings are combined into a new array list containing strings of each customers calculated values.
 */

public class PaymentUnpacker {
	
	private ArrayList<String> tempList = new ArrayList<String>();
	private String fName;
	private String lName;
	private int annualSal;
	private int superRate;
	private String initPayment;
	private PaymentProcessing processedItem;
	private ArrayList<String> processedItems = new ArrayList<String>();
	private boolean correctInput = true;

	public PaymentUnpacker(ArrayList<ArrayList<String>> packagedData) {
		//Loop for each customer
		for (int j = 0; j < packagedData.size(); j++){
			tempList = packagedData.get(j);
			//Loop for each value given from a customer e.g. name, lname, etc.
			for (int k = 0; k < tempList.size(); k++) {
				switch(k) {
				case 0:
					fName = tempList.get(k);
					break;
				case 1:
					lName = tempList.get(k);
					break;
				case 2:
					annualSal = Integer.parseInt(tempList.get(k));
					break;
				case 3:
					superRate = Integer.parseInt(tempList.get(k).replace("%", ""));
					break;
				case 4:
					initPayment = tempList.get(k);
					break;
				default:
					System.out.println("CSV file has an unexpected extra value: " + tempList.get(k));
				    correctInput = false;
				}
			}
			//Send data to other class to be processed, then add the result to an array list
			if (correctInput) {
				processedItem = new PaymentProcessing(fName, lName, annualSal, superRate, initPayment);
				processedItems.add(processedItem.LineOutput());
			} else {
				System.out.println("File input has an incorrect format, please fix the issue before attempting again");
			}
		}
	}
	
	//Get method for processed results
	public ArrayList<String> ProcessedResults(){
		return processedItems;
	}
	
	//Get method for valid data
	public boolean CorrectInput() {
		return correctInput;
	}
}