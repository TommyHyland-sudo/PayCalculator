package paymentcalc;
public class Main {
	private static String fileName;
	
	public static void main(String[] args) {
		fileName = "InputFile.csv";
		String inputFileName = fileName;
		String outputFileName = "Output" + fileName;
		
		//Calls a class which reads in a CSV file
		System.out.println(inputFileName);
		CSVReader inputData = new CSVReader(inputFileName);
		
		//Unpacks the information gathered from the CSV file and calculates all the necessary values. Values are output to console.
		PaymentUnpacker unpacker = new PaymentUnpacker(inputData.CsvData());
		
		//New calculated values are input into a new CSV file, if the file already exists, it is overwritten
		CSVWriter writer = new CSVWriter(outputFileName, unpacker.ProcessedResults());
	}
}
