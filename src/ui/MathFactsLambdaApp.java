package ui;

import java.util.function.IntBinaryOperator;

public class MathFactsLambdaApp {

	public static void main(String[] args) {
		System.out.println("Addition test:");
		IntBinaryOperator plusOperation = (a, b) -> a + b;
        System.out.println("Sum of 10,34 : " + plusOperation.applyAsInt(10, 34));
		System.out.println("Subraction test:");
		IntBinaryOperator minusOperation = (a, b) -> a - b;
        System.out.println("Subraction of 22,5 : " + minusOperation.applyAsInt(22, 5));
		System.out.println("Multiplication test:");
		IntBinaryOperator multiplyOperation = (a, b) -> a * b;
        System.out.println("Product of 7,6 : " + multiplyOperation.applyAsInt(7, 6));
		System.out.println("Division test:");
		IntBinaryOperator divisionOperation = (a, b) -> a / b;
        System.out.println("Quotient of 72,9 : " + divisionOperation.applyAsInt(72, 9));
	}

}
