package ui;

import java.awt.DisplayMode;

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
			switch (choice.toUpperCase()) {
				case "A":	playAdditionFacts();
							break;
				case "S":	playSubtractionFacts();
							break;
				case "M":	playMultiplicationFacts();
							break;
				case "D":	playDivisionFacts();
							break;
			}
		}
		System.out.println("Bye!");

	}
	// TODO - Use a lambda to pass in the mathematical function??
	// May need to create a class representing the calculation...  
	// 3 ints:  n1, n2, and calculatedValue
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
		StringBuilder msg = new StringBuilder("Please select an option:\n");
		msg.append("A - Addition\n");
		msg.append("S - Subtraction\n");
		msg.append("M - Multiplication\n");
		msg.append("D - Division\n");
		msg.append("X - Exit\n");
		return msg.toString();
	}
	
	private static int getRandomNbr() {
		int a = (int)(Math.random()*10);
		return a;
	}

}
