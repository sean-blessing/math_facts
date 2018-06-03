package ui;

import java.awt.DisplayMode;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.function.IntBinaryOperator;

import business.Operation;
import util.Console;

/**
 * 
 * @author Sean Blessing
 *	This is a simple math facts app my kids can use through the summer
 *	to hone their math skills.  Starting out with this console app
 *	but will expand to a more sophisticated UI once base app is working.
 */

public class MathFactsApp {

	public static void main(String[] args) {
		StringBuilder msg = new StringBuilder("Welcome to the math facts app!\n");
		msg.append("Each option will present 10 problems.  Your stats will be recorded\n");
		msg.append("and a summary will be displayed upon completion.\n");
		msg.append("Good luck!!!\n");
		System.out.println(msg);

		String choice = "";
		while (!choice.equalsIgnoreCase("x")) {
			choice = Console.getString(displayMenu());
			long startTime = System.currentTimeMillis();
			playMathFacts(choice);
			long endTime = System.currentTimeMillis();
			long elapsedTime = endTime - startTime;
			//LocalDateTime startLT = LocalDateTime.(startTime);
			System.out.println("Start time = "+startTime);
			System.out.println("End time = "+endTime);
			System.out.println("elapsed time = "+elapsedTime);
			
		}
		System.out.println("Bye!");

	}

	// TODO - lambda works, but need to plan for the following:
	// 1) subtraction...  n1 must be greater than n2
	// 2) division...  result must be 0 remainder...  also n1 and n2 will need to be a larger range
	private static void playMathFacts(String choice) {
		Operation opr = null;
		switch (choice.toUpperCase()) {
			case "A":	opr = new Operation("Addition", "+");
						break;
			case "S":	opr = new Operation("Subtraction", "-");
						break;
			case "M":	opr = new Operation("Multiplication", "*");
						break;
			case "D":	opr = new Operation("Division", "/");
						break;
		}
		System.out.println(opr.getOperationString()+" Facts\n");
		int numRight = 0;
		int numWrong = 0;
		boolean correct = false;
		
		for (int i=1; i<=10; i++) {
			int num1 = getRandomNbr();
			int num2 = getRandomNbr();
			while (!correct) {
				System.out.println("Question #"+i+":");
				int ans = Console.getInt(num1 +" "+opr.getOperationSymbol()+" " + num2 + " = ");
				int expResult = getExpectedResult(num1,num2,opr);
				if  (ans == expResult) {
					numRight++;
					correct = true;
					System.out.println("Correct!");
				}
				else {
					numWrong++;
					System.out.println("Wrong!  Try again.");
				}
			}
			correct = false;
		}
		System.out.println("Stats:");
		System.out.println("# right = "+ numRight);
		System.out.println("# wrong = "+ numWrong);
	}
	
	private static int getExpectedResult(int n1, int n2, Operation opr) {
		int er = 0;
		IntBinaryOperator ibo = null;
		switch (opr.getOperationSymbol()) {
		case "+":	ibo = (a, b) -> a + b;
					break;
		case "-":	ibo = (a, b) -> a - b;
					break;
		case "*":	ibo = (a, b) -> a * b;
					break;
		case "/":	ibo = (a, b) -> a / b;
					break;
		}
		er = ibo.applyAsInt(n1, n2);
		return er;
	}
	
	private static void playMultiplicationFacts() {
		System.out.println("Multiplication Facts\n");
		int numRight = 0;
		int numWrong = 0;
		boolean correct = false;
		
		for (int i=1; i<=10; i++) {
			int num1 = getRandomNbr();
			int num2 = getRandomNbr();
			while (!correct) {
				System.out.println("Question #"+i+":");
				int ans = Console.getInt(num1 +" x " + num2 + " = ");
				if  (ans == num1 * num2) {
					numRight++;
					correct = true;
					System.out.println("Correct!");
				}
				else {
					numWrong++;
					System.out.println("Wrong!  Try again.");
				}
			}
			correct = false;
		}
		System.out.println("Stats:");
		System.out.println("# right = "+ numRight);
		System.out.println("# wrong = "+ numWrong);
	}
	
	private static void playDivisionFacts() {
		System.out.println("Function not yet implemented.");
	}

	private static void playAdditionFacts() {
		System.out.println("Function not yet implemented.");
	}

	private static void playSubtractionFacts() {
		System.out.println("Function not yet implemented.");
	}

	private static String displayMenu() {
		StringBuilder msg = new StringBuilder("Math Facts Menu:\n");
		msg.append("A - Addition\n");
		msg.append("S - Subtraction\n");
		msg.append("M - Multiplication\n");
		msg.append("D - Division\n");
		msg.append("X - Exit\n");
		msg.append("Please select an option:\n");
		return msg.toString();
	}
	
	private static int getRandomNbr() {
		int a = (int)(Math.random()*10);
		return a;
	}

}
