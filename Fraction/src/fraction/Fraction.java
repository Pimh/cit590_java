package fraction;

public class Fraction implements Comparable<Object> {
	private int numerator;
	private int denominator;
	
	
	public Fraction(int numerator, int denominator) {
		
		// Throw an exception if the denominator is zero
		if (denominator == 0) {
			throw new ArithmeticException("The denominator cannot be zero");
		}
		
		// Normalize the fraction and re-format the output 
		// if the denominator is a negative number
		int[] normFrac = normalize(numerator, denominator);
		
		// Assign the values to the instance numerator and denominator 
		this.numerator = normFrac[0];
		this.denominator = normFrac[1];
	}
	
	public Fraction(int wholeNumber) {
		this.numerator = wholeNumber;
		this.denominator = 1; 
	}
	
	public Fraction(String fraction) {
		
		// Split the fraction string around "/"
		String[] splitStr = fraction.split("\\/");
		int nNum = splitStr.length;	
		String num; String denom;
		
		// For a whole number string
		if (nNum == 1) {
			num = splitStr[0].trim();
			denom = "1";
			
		// For a fraction string
		} else if (nNum == 2) {
			num = splitStr[0].trim();
			denom = splitStr[1].trim();
			
		// For an incorrect fraction format
		} else {
			throw new NumberFormatException("Incorrect input format");
		}
		
		// Convert string to integer
		int intNum = Integer.parseInt(num);
		int intDenom = Integer.parseInt(denom);
		
		// Throw an exception if the denominator is zero
		if (intDenom == 0) {
			throw new ArithmeticException("The denominator cannot be zero");
		}

		// Normalize the fraction and re-format the output 
		// if the denominator is a negative number
		int[] normFrac = normalize(intNum, intDenom);
		
		// Assign the values to the instance numerator and denominator 
		this.numerator = normFrac[0];
		this.denominator = normFrac[1];
	}
	
	/** 
	 * Calculates summation of this Fraction and Fraction f.
	 * @param f The Fraction to be added to this Fraction.
	 * @return The sum of the two fractions.
	 */
	public Fraction add(Fraction f) {
		int calNum = (this.numerator * f.denominator) + 
				(this.denominator * f.numerator);
		int calDenom = this.denominator * f.denominator; 
		Fraction cal = new Fraction(calNum, calDenom); 
		return cal; 
	}
	
	/** 
	 * Subtracts this Fraction with Fraction f.
	 * @param f The Fraction to be subtracted off of this Fraction.
	 * @return The difference of the two fractions.
	 */
	public Fraction subtract(Fraction f) {
		int calNum = (this.numerator * f.denominator) - 
				(this.denominator * f.numerator);
		int calDenom = this.denominator * f.denominator; 
		Fraction cal = new Fraction(calNum, calDenom); 
		return cal;  
	}
	
	/** 
	 * Multiplies this Fraction by Fraction f.
	 * @param f The Fraction to be multiplied to this Fraction.
	 * @return The multiplication of the two fractions.
	 */
	public Fraction multiply(Fraction f) {
		int calNum = this.numerator * f.numerator;
		int calDenom = this.denominator * f.denominator;
		Fraction cal = new Fraction(calNum, calDenom); 
		return cal; 
	}
	
	/** 
	 * Divides this Fraction by Fraction f.
	 * @param f The Fraction to divide this Fraction. 
	 * @return The division of the two fractions.
	 */
	public Fraction divide(Fraction f) {
		int calNum = this.numerator * f.denominator; 
		int calDenom = this.denominator * f.numerator;
		Fraction cal = new Fraction(calNum, calDenom); 
		return cal; 
	}
	
	/** 
	 * Calculates the absolute value of this Fraction
	 * @return Returns a new Fraction that is 
	 * the absolute value of this fraction.
	 */
	public Fraction abs() {
		int multiplier;
		if ((this.numerator) < 0) {
			multiplier = -1;
		} else {
			multiplier = 1;
		}
		int calNum = this.numerator * multiplier; 
		Fraction cal = new Fraction(calNum, this.denominator); 
		return cal; 
	}
	
	/** 
	 * Calculates negation this Fraction.
	 * @return Returns a new Fraction that has the same 
	 * numeric value of this fraction, but the opposite sign.
	 */
	public Fraction negate() {
		Fraction cal = new Fraction(-this.numerator, this.denominator); 
		return cal; 
	}
	
	/** 
	 * Calculates the inverse of this Fraction.
	 * @return The inverse of of this Fraction.
	 */
	public Fraction inverse() {
		int calNum; int calDenom;
		if ((this.numerator) < 0) {
			calNum = -this.denominator;
			calDenom = -this.numerator;
		} else {
			calNum = this.denominator;
			calDenom = this.numerator;
		}
		Fraction cal = new Fraction(calNum, calDenom); 
		return cal;
	}
	
	/** 
	 * Determines if Object o is a Fraction equal to this Fraction.
	 * @return true if o is a Fraction equal to this, 
	 * and false in all other cases.
	 */
	@Override
	public boolean equals(Object o) {
		if (o instanceof Fraction) {
			Fraction f = (Fraction)o;
			if ((f.numerator == this.numerator) && 
					f.denominator == this.denominator) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}
	
	/** 
	 * Compares this Fraction to an object o
	 * @return 1 if this fraction is greater than o
	 * -1 if this fraction is smaller than o
	 * 0 if this fraction is equal to o
	 */
	@Override
	public int compareTo(Object o) {
		if ((o instanceof Fraction) || (o instanceof Integer)){
			Fraction f; 
			if (o instanceof Integer) {
				f = new Fraction((int)o, 1);
			} else {
				f = (Fraction)o;
			}
			
			if ((this.numerator*f.denominator) > 
				(this.denominator*f.numerator)) {
				return 1;
			} else if ((this.numerator*f.denominator) < 
			(this.denominator*f.numerator)) {
				return -1;
			} else {
				return 0; 
			}
			
		} else {
			throw new ClassCastException();
		}
	}
	
	/** 
	 * Converts this Fraction to string
	 * @return Returns a String of the form n/d, 
	 * where n is the numerator and d is the denominator.
	 */
	@Override
	public String toString() {
		//However, if d is 1, just return n (as a String). 
		//The returned String should not contain any blanks.
		//If the fraction represents a negative number, a minus sign should precede the n.
		String numStr; String denomStr;
		if (this.denominator == 1) {
			numStr = Integer.toString(this.numerator);
			return numStr;
		} else {
			numStr = Integer.toString(this.numerator);
			denomStr = Integer.toString(this.denominator);
			String fracStr = numStr + "/" + denomStr;
			return fracStr; 
		}
	}
	
	/** 
	 * Calculates GCD of the numerator and the denominator
	 * @param a The numerator of the fraction
	 * @param b The denominator of the fraction
	 * @return the GCD of the numerator and the denominator
	 */
	private int gcd(int a, int b) {
    	if (b == 0) {
    		return a; 
    	}else {
    		return gcd(b, a % b);
    	}
	}
	
	/** 
	 * Normalizes the fraction and re-format the output 
	 * if the denominator is a negative number
	 * @param numerator The numerator of the fraction
	 * @param denominator The denominator of the fraction
	 * @return the normalized and re-formatted fraction
	 */
	private int[] normalize(int numerator, int denominator) {
		int gcd = gcd(Math.abs(numerator), Math.abs(denominator)); 
		int normNum; int normDen;
		if (denominator < 0) {
			normNum = -numerator/gcd;
			normDen = -denominator/gcd;
		} else {
			normNum = numerator/gcd;
			normDen = denominator/gcd;
		}
		int[] fracArray = {normNum, normDen};
		return fracArray; 
	}
}
