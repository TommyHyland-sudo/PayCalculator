package paymentcalc;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.Assert.assertNotEquals;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CSVReaderTest {

	@BeforeEach
	void setUp() throws Exception {
	}

	@Test
	void testCSVReader() {
		//Test the reader gathers specified information from a given file. CSV Rows 1, 2 and the last row are checked.
		System.out.println("---Testing to make sure data read in from CSV is accurate---");
		CSVReader CSVReader1 = new CSVReader("src/paymentcalc/Inputfile.csv");
		ArrayList<ArrayList<String>> comparativeArray = new ArrayList<ArrayList<String>>();
		ArrayList<String> tempArray = new ArrayList<String>();
		tempArray.add("Monica");
		tempArray.add("Tan");
		tempArray.add("60050");
		tempArray.add("9%");
		tempArray.add("01 March - 31 March");
		comparativeArray.add(tempArray);
		tempArray = new ArrayList<String>();
		tempArray.add("Brend");
		tempArray.add("Tulu");
		tempArray.add("120000");
		tempArray.add("10%");
		tempArray.add("01 March - 31 March");
		comparativeArray.add(tempArray);
		tempArray = new ArrayList<String>();
		tempArray.add("Kenneth");
		tempArray.add("Krook");
		tempArray.add("87000");
		tempArray.add("10%");
		tempArray.add("01 July - 31 July");
		comparativeArray.add(tempArray);
		
		assertEquals(comparativeArray.get(0), CSVReader1.CsvData().get(0));
		assertEquals(comparativeArray.get(1), CSVReader1.CsvData().get(1));
		assertEquals(comparativeArray.get(2), CSVReader1.CsvData().get(CSVReader1.CsvData().size() - 1));
		
		System.out.println("Success");
	}
	
	@Test
	void fileNotExist() {
		//Test makes sure the reader returns null if the file does not exist, rather than crashing
		System.out.println("---Testing CSVReader null return on file not existing---");
		CSVReader CSVReader2 = new CSVReader("src/paymentcalc/Fake.csv");
		assertEquals(null, CSVReader2.CsvData());
		System.out.println("Success");
	}
	
	@Test
	void testInvalidFile() {
		//Test should detect a missing row and remove it from the output
		System.out.println("---Testing missing row handling from CSVReader---");
		CSVReader CSVReader3 = new CSVReader("src/paymentcalc/MissingRow.csv");
		assertEquals(CSVReader3.CsvData().size()-1, 6);
		System.out.println("Success");
	}
	
	@Test
	void testNonIntegerInputs() {
		//Test should detect 2 incorrect integer inputs in the CSV file and remove 2 rows from the output
		System.out.println("---Testing non integer inputs in both salary and super rate from CSV file---");
		CSVReader CSVReader4 = new CSVReader("src/paymentcalc/InvalidData.csv");
		assertEquals(CSVReader4.CsvData().size()-1, 5);
		System.out.println("Success");
	}
	
	@Test
	void testPaymentUnpacker() {
		//An 8 row CSV file with correct values should output an array list with 8 rows
		System.out.println("---Testing a valid file reads the expected amount of data---");
		CSVReader CSVReader5 = new CSVReader("src/paymentcalc/InputFile.csv");
		PaymentUnpacker unpacker = new PaymentUnpacker(CSVReader5.CsvData());
		assertEquals(unpacker.CorrectInput(), true);
		assertEquals(unpacker.ProcessedResults().size()-1, 7);
		System.out.println("Success");
	}
	
	@Test
	void testCSVFileIncorrectValuesUnpacked() {
		//An 8 row CSV file with 2 rows of incorrect values should output an array list with 6 rows
		System.out.println("---Testing a file with 2 columns that are incorrect. Should ignore the 2 rows and process the rest of the data---");
		CSVReader CSVReader6 = new CSVReader("src/paymentcalc/InvalidData.csv");
		PaymentUnpacker unpacker = new PaymentUnpacker(CSVReader6.CsvData());
		assertEquals(unpacker.CorrectInput(), true);
		assertEquals(unpacker.ProcessedResults().size()-1, 5);
		System.out.println("Success");
	}
	
	@Test
	void testPaymentProcessingVaryingInputs() {
		//Input a series of values with an expected output, then compare the two. Another test is done for unexpected outputs.
		System.out.println("---Testing inputting expected inputs into the processing class, and grabbing expected results---");
		PaymentProcessing processor1 = new PaymentProcessing("John", "Doe", 32000, 12, "1 August - 31 August");
		PaymentProcessing processor2 = new PaymentProcessing("Frederick", "Fred", 180000, 11, "1 August - 31 Friday");
		String actualOutput1 = processor1.LineOutput();
		String actualOutput2 = processor2.LineOutput();
		String expectedOutput = ("John Doe" + "," + "1 August - 31 August" + "," + "2666" + "," + "218" + "," + "2448" + "," + "319");
		String unExpectedOutput = ("Frederick Fred" + "," + "1 August - 31 Friday" + "," + "2666" + "," + "218" + "," + "2448" + "," + "319");
		assertEquals(actualOutput1, expectedOutput);
		assertNotEquals(actualOutput2, unExpectedOutput);
		System.out.println("Success");
	}
	
	@Test
	void testCSVWriterWrites() {
		//Writer writes given a valid array of processed data
		System.out.println("---Testing the CSVWriter class will write to a given file with appropriate data---");
		ArrayList<String> arrayListToWrite = new ArrayList<String>();
		arrayListToWrite.add("John Doe" + "," + "01 August - 31 August" + "," + "2666" + "," + "218" + "," + "2448" + "," + "319");
		CSVWriter writer = new CSVWriter("ValidWriteTest.csv", arrayListToWrite);
		assertEquals(writer.NullTest(), false);
		System.out.println("Success");
	}
	
	@Test
	void testCSVWriterHandlesNull() {
		//Writer will handle a null
		System.out.println("---Testing null handling for CSVWriter---");
		ArrayList<String> arrayListToFail = null;
		CSVWriter writer = new CSVWriter("InvalidWriteTest.csv", arrayListToFail);
		assertEquals(writer.NullTest(), true);
		System.out.println("Success");
	}
}
