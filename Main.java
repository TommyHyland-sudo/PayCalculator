package paymentcalc;

import java.util.Scanner;

public class Main {
	
	public static Scanner sc = new Scanner(System.in);
	public static void main(String[] args) {
		
		//Get a users input for which file they want to write to
		System.out.println("Please enter a CSV file name to read from: ");
		String fileName = sc.nextLine() + ".csv";
		String inputFileName = "src/paymentcalc/" + fileName;
		String outputFileName = "Output" + fileName;
		
		//Calls a class which reads in a CSV file
		System.out.println("---Reading in CSV file " + fileName + " located in paymentcalc folder---");
		CSVReader inputData = new CSVReader(inputFileName);
		
		//Unpacks the information gathered from the CSV file and calculates all the necessary values. Values are output to console.
		System.out.println("---Unpacking and processing information obtained from CSV file---");
		PaymentUnpacker unpacker = new PaymentUnpacker(inputData.CsvData());
		
		//If the given information follows the correct format, proceed to the next step
		if (unpacker.CorrectInput()) {
			//Following section overwrites/creates a file based on the name given and if the user wants to continue
			System.out.println("Note: New information will overwrite previous data if the file given already exists, are you sure you want to continue for file name: Output" + fileName);
			if (pressAnyKeyToContinue()) {
				sc.close();
				//New calculated values are input into a new CSV file, if the file already exists, it is overwritten
				System.out.println("---Writing calculations to a new CSV file---");
				CSVWriter writer = new CSVWriter(outputFileName, unpacker.ProcessedResults());
				System.out.println("---CSV File named: Output" + fileName + " has been created with calculated results---");
			} else {
				sc.close();
			}
		}

		System.out.println("---Program finished---");
	}
	
	//Read any key input, requires enter to be pressed afterwards
	public static boolean pressAnyKeyToContinue() {
		System.out.println("Would you like to continue? Y/N");
		
		try {
			//Get the users input and loop until either a Y/y or N/n is input
			String val = sc.nextLine();
			//Check for Y or y
			while (!(val.contains("Y")) && !(val.contains("y"))) {
				//Check for N or n
				if (val.matches("N") || val.matches("n")) {
					System.out.println("Overwrite cancelled, output file has not been created");
					return false;
				} else {
					val = sc.nextLine();
				}
			}
			return true;
		}
		//If an invalid input is entered (not ASCII), throw an exception
		catch(Exception e) {
			e.printStackTrace();
			System.out.println("Invalid input value");
			return false;
		}
	}
}
