package fraction;

import java.util.Scanner;

public class FractionCalculator {
	private Boolean cont = true; 
	
	public static void main(String[] args) {    
    	new FractionCalculator().main();
    }
	
	/**
	 * Performs calculations with fractions
	 */
	void main() {
		// Display instruction
		displayInstruction();
				
		// Print zero
		System.out.println("Total: " + Integer.toString(0));
		Fraction curTotal = new Fraction(0);
		Scanner sc = new Scanner(System.in);
		
		while (cont) {
			// Prompt user input
			System.out.print("Enter your command: ");

			// Accept command from the user
			String input = sc.nextLine(); 

			// trim
			String trimInput = input.trim();

			// Execute the user command
			char firstLetter = trimInput.charAt(0);
			try {
				if (trimInput.length() == 1) {
					curTotal = inputCommand(firstLetter, curTotal, null);
				}
				if (trimInput.length() > 1) {
					String num = trimInput.substring(1, trimInput.length());
					Fraction n = new Fraction(num);
					curTotal = inputCommand(firstLetter, curTotal, n);
				} 
				// Print result
				System.out.println("Total: " + curTotal);
			}
			catch (NumberFormatException e) {
				System.out.println("Incorrect input format! Please re-enter"
						+ "your command");
			}
			catch (ArithmeticException e) {
				System.out.println("Cannot be divided by zero! "
						+ "Please re-enter your command");
			}
		}
		sc.close();
	}
	
	/** 
	 * Displays instruction describing how to use this fraction calculator
	 */
	void displayInstruction() {
		String instruction = "Welcome to a fraction calculator! \n"
				+ "This calculator accepts the following commands: \n"				
				+ "	a To take the absolute value of the number currently displayed. \n"
				+ "	c To clear (reset to zero) the calculator. \n"
				+ "	i To invert the number currently displayed. \n"
				+ "	s n To discard the number currently displayed and replace it with n. \n"
				+ "	q To quit the program. \n"
				+ "	+ n To add n to the number currently displayed. \n"
				+ "	- n To subtract n from the number currently displayed. \n"
				+ "	* n To multiply the number currently displayed by n. \n"
				+ "	/ n To divide the number currently displayed by n. \n"
				+ "\nn can be either a whole number or a fraction with the following formats: \n"
				+ "	- Fractions may be written with or without spaces, as for example 27 / 99 or 27/99.\n"
				+ "	- There should be no space after a unary minus, as for example -3 is legal, but - 3 is not.\n"
				+ "	- It is not necessary to have a space after the initial. \n"
				+ "-------------------------------------------------------------------------------------------\n";
		System.out.println(instruction);
	}
	
	/**
	 * Identifies and executes the user's input command
	 * @param letter The first letter of the input command
	 * @param curTotal The number currently displayed
	 * @param n The input Fraction or integer
	 * @return the calculation after executing the input command
	 */
	public Fraction inputCommand(char letter, Fraction curTotal, Fraction n) {
		Fraction result = null;
		// Takes absolute value currently displayed
		if (letter == 'a') {
			result = curTotal.abs();
		// Clears the calculator
		} else if (letter == 'c') {
			result = new Fraction(0);
		// Inverts the number currently displayed
		} else if (letter == 'i') {
			result = curTotal.inverse();
		// Discards the number currently displayed and replace it with n.
		} else if (letter == 's') {
			result = n;
		// Quits the program.
		} else if (letter == 'q') {
			result = curTotal;
			System.out.print("Final answer: ");
			cont = false; 
		// Adds n to the number currently displayed.
		} else if (letter == '+') {
			result = curTotal.add(n);
		// Subtracts n from the number currently displayed.
		} else if (letter == '-') {
			result = curTotal.subtract(n);
		// Multiplies the number currently displayed by n.
		} else if (letter == '*') {
			result = curTotal.multiply(n);
		// Divides the number currently displayed by n.
		} else if (letter == '/') {
			result = curTotal.divide(n);
		// Throws some exceptions
		} else {
			throw new NumberFormatException();
		}
		return result; 
	}
}
