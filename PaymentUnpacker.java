package paymentcalc;

import java.util.ArrayList;

public class PaymentUnpacker {
	
	private ArrayList<String> tempList = new ArrayList<String>();
	private String fName;
	private String lName;
	private int annualSal;
	private int superRate;
	private String initPayment;
	private PaymentProcessing processedItem;
	private ArrayList<String> processedItems = new ArrayList<String>();

	public PaymentUnpacker(ArrayList<ArrayList<String>> packagedData) {
		for (int j = 0; j < packagedData.size(); j++){
			tempList = packagedData.get(j);
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
					System.out.println("CSV file has incorrect values");
				}
			}
			processedItem = new PaymentProcessing(fName, lName, annualSal, superRate, initPayment);
			processedItems.add(processedItem.lineOutput);
		}
	}
	
	public ArrayList<String> ProcessedResults(){
		return processedItems;
	}
}