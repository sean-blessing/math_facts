package math_facts;

import java.awt.DisplayMode;

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
		displayMenu();
		
		for (int i=0; i<10; i++) {
			System.out.println("Random # = "+getRandomNbr());
		}
		System.out.println("Bye!");

	}
	
	private static void displayMenu() {
		StringBuilder msg = new StringBuilder("Please select an option:\n");
		msg.append("A - Addition\n");
		msg.append("S - Subtraction\n");
		msg.append("M - Multiplication\n");
		msg.append("D - Division\n");
		System.out.println(msg);
	}
	
	private static int getRandomNbr() {
		int a = (int)(Math.random()*10);
		return a;
	}

}
