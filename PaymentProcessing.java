package paymentcalc;

/*
 * This file is used to process gross income, income tax, net income, super income and produce a CSV formatted output name, data of payment and values mentioned
 */

public class PaymentProcessing {

	private String fName;
	private String lName;
	private int annualSal;
	private int superRate;
	private String initPayment;
	private int grossIncome;
	private int netIncome;
	private double incomeTax;
	private int incomeOutput;
	private int superIncome;
	private String lineOutput;
	
	public PaymentProcessing(String _fName, String _lName, int _annualSal, int _superRate, String _initPayment){
		//Store the input values
		fName = _fName;
		lName = _lName;
		annualSal = _annualSal;
		superRate = _superRate;
		initPayment = _initPayment;
		
		Calculation();
	}
	
	//Run the necessary calculations and output the needed values in a string with values separated by commas
	public void Calculation() {
		grossIncome = Math.round(annualSal/12);
		
		//Calculate tax based on different salary range rules
		incomeTax = 0;
		if (annualSal <= 18200) {
			incomeTax = 0;
		} else if (18200 < annualSal && annualSal <= 37000) {
			incomeTax = Math.round(((annualSal - 18201) * 0.19)/12);
		} else if (37000 < annualSal && annualSal <= 87000) {
			incomeTax = Math.round((3572 + (annualSal - 37001) * 0.325)/12);
		} else if (87000 < annualSal && annualSal <= 180000) {
			incomeTax = Math.round((19822 + (annualSal - 87001) * 0.37)/12);
		} else if (180000 < annualSal) {
			incomeTax = Math.round((54232 + (annualSal - 180001) * 0.45)/12);
		}
		
		//Used to convert the rounded value from a double back to an integer
		incomeOutput = (int) incomeTax;
		
		netIncome = grossIncome - incomeOutput;
		
		superIncome = Math.round(((grossIncome*superRate)/100));
		
		//Ready to be written into a CSV
		lineOutput = fName + " " + lName + "," + initPayment + "," + grossIncome + "," + incomeOutput + "," + netIncome + "," + superIncome;
	}	
	
	//Get for calculations
	public String LineOutput() {
		return lineOutput;
	}
}
