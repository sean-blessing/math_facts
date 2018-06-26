package business;

public class FactsNumbers {
	private int number1;
	private int number2;
	
	public FactsNumbers() {
		
	}

	public int getNumber1() {
		return number1;
	}

	public void setNumber1(int number1) {
		this.number1 = number1;
	}

	public int getNumber2() {
		return number2;
	}

	public void setNumber2(int number2) {
		this.number2 = number2;
	}
	
	/**
	 * generateFactsNumbers will generate a new instance
	 * of this class w/ 2 random numbers.  The #s will need
	 * to adhere to a few generation rules based on the 
	 * operation to be performed:
	 * - For subtraction, number1 must be >= number2
	 * - For division, the quotient must be a whole number
	 *   so the quotient can be a max of 100 and the
	 *   result of numerator % denominator must be zero. 
	 * @return
	 */
	public static FactsNumbers generateFactsNumbers(Operation opr) {
		FactsNumbers fn = new FactsNumbers();
		
		int number1 = getRandomNbr();
		int number2 = getRandomNbr();
		if (opr.getOperationString().equals("S")) {
			// ensure number 1 >= number2
			if (number2 > number1) {
				int hold = number1;
				number1 = number2;
				number2 = hold;
			}
		}
		else if (opr.getOperationString().equals("D")) {
			// ensure the quotient is a whole number???
		}
		fn.setNumber1(number1);
		fn.setNumber2(number2);
		return fn;
	}
	
	private static int getRandomNbr() {
		int a = (int)(Math.random()*10);
		return a;
	}
}
