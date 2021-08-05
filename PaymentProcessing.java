package paymentcalc;

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
	public String lineOutput;
	
	public PaymentProcessing(String _fName, String _lName, int _annualSal, int _superRate, String _initPayment){
		fName = _fName;
		lName = _lName;
		annualSal = _annualSal;
		superRate = _superRate;
		initPayment = _initPayment;
		
		Calculation();
	}
	
	public void Calculation() {
		grossIncome = Math.round(annualSal/12);
		
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
		
		incomeOutput = (int) incomeTax;
		netIncome = grossIncome - incomeOutput;
		
		superIncome = Math.round(((grossIncome*superRate)/100));
		
		System.out.println(initPayment);
		lineOutput = fName + " " + lName + "," + initPayment + "," + grossIncome + "," + incomeOutput + "," + netIncome + "," + superIncome;
		System.out.println(lineOutput);
	}	
	
	public String LineOutput() {
		return lineOutput;
	}
}
