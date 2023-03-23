package interestGUI;

import java.text.NumberFormat;

public class Interest {
	
	public static String calculateSimpleInterest(double principal, double rate, double numYears) {
		String output = "";
		output += "Principal: " + NumberFormat.getCurrencyInstance().format(principal);// formats the principal correctly
		output += ", Rate: " + rate;
		output += "\nYear";
		output += ", Simple Interest Amount";
		for (int i = 1; i <= numYears; i++) {
			output += "\n" + i + "-->" + NumberFormat.getCurrencyInstance().format(simpleInterestHelper(principal, rate, i));// prints out the result of the helper method, in the correct format
		}
		return output;
	}

	private static double simpleInterestHelper(double principal, double rate, double year) {
		return principal + (principal * (rate / 100) * year);// calculates simple interest
	}
	
	public static String calculateCompoundInterest(double principal, double rate, double numYears) {
		String output = "";
		output += "Principal: " + NumberFormat.getCurrencyInstance().format(principal);// formats the principal correctly
		output += ", Rate: " + rate;
		output += "\nYear";
		output += ", Compound Interest Amount";
		for (int i = 1; i < numYears; i++) {
			output += "\n" + i + "-->" + NumberFormat.getCurrencyInstance().format(compoundInterestHelper(principal, rate, i));// prints out the result of the helper method, in the correct format
		}
		return output;
	}
	
	private static double compoundInterestHelper(double principal, double rate, double year) {
		return principal * Math.pow((1+(rate/100)), year);// calculates compound interest
	}
	
	public static String calculateBothInterests(double principal, double rate, double numYears) {
		String output = "";
		output += "Principal: " + NumberFormat.getCurrencyInstance().format(principal);// formats the principal correctly
		output += ", Rate: " + rate;
		output += "\nYear";
		output += ", Simple Interest Amount, Compound Interest Amount";
		for (int i = 1; i < numYears; i++) {
			output += "\n" + i + "-->" + NumberFormat.getCurrencyInstance().format(simpleInterestHelper(principal, rate, i));// prints out the result of the helper methods, in the correct format
			output += "-->" + NumberFormat.getCurrencyInstance().format(compoundInterestHelper(principal, rate, i));
		}
		return output;
	}
}
