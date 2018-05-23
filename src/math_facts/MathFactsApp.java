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
		System.out.println("Welcome to the math facts app!");
		displayMenu();
		System.out.println("Bye!");

	}
	
	private static void displayMenu() {
		System.out.println("Please select an option:");
		System.out.println("A - Addition");
		System.out.println("S - Subtraction");
		System.out.println("M - Multiplication");
		System.out.println("D - Division");
	}

}
