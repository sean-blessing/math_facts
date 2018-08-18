package ui;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.function.IntBinaryOperator;

import business.AllStats;
import business.FactsNumbers;
import business.Game;
import business.GameDB;
import business.Operation;
import business.User;
import business.UserDB;
import util.Console;

/**
 * 
 * @author Sean Blessing
 *	This is a simple math facts app my kids can use through the summer
 *	to hone their math skills.  Starting out with this console app
 *	but will expand to a more sophisticated UI once base app is working.
 */

public class MathFactsApp {
	private static boolean loggedOn = false;
	private static User user = null;
	private static String userID = "";
	private static final int NUM_PROBLEMS = 20;

	public static void main(String[] args) {
		StringBuilder msg = new StringBuilder("Welcome to the math facts app!\n");
		msg.append("Each option will present "+NUM_PROBLEMS+" problems.  Your stats will be recorded\n");
		msg.append("and a summary will be displayed upon completion.\n");
		msg.append("Good luck!!!");
		System.out.println(msg);

		userLogin();
		String choice = Console.getString(displayMenu());
		while (!choice.equalsIgnoreCase("x")) {
			long startTime = System.currentTimeMillis();
			Timestamp datePlayed = new Timestamp(startTime);
			Game g = playMathFacts(choice);
			long endTime = System.currentTimeMillis();
			long elapsedTime = endTime - startTime;
			g.setDatePlayed(datePlayed);
			g.setStartTime(startTime);
			g.setEndTime(endTime);
			SimpleDateFormat sdf = new SimpleDateFormat("K:mm:ss' 'a");
			System.out.println("\n====================================");
			System.out.println("=            Game Stats            =");
			System.out.println("====================================");
			System.out.println("Thanks for playing, "+user.getFirstName()+"!!!");
			System.out.println("Start time = "+sdf.format(startTime));
			System.out.println("End time = "+sdf.format(endTime));
			BigDecimal secondsBD = new BigDecimal(elapsedTime);
			secondsBD = secondsBD.divide(new BigDecimal(1000)).setScale(1, RoundingMode.HALF_UP);
			System.out.println("Elapsed time (seconds) = "+secondsBD);
			g.setElapsedTime(secondsBD.doubleValue());
			System.out.println("# right: "+g.getNumRight());
			System.out.println("# wrong: "+g.getNumWrong());
			System.out.println("====================================/n");
			// Write game date to DB
			if (!saveGame(g)) {
				System.out.println("Error saving game.");
				System.out.println(g);
			}
			System.out.println("\n====================================");
			System.out.println("=          Overall Stats           =");
			System.out.println("====================================");
			AllStats stats = getStatsForUser(user, g.getType());
			if (stats.getBestTime() == g.getElapsedTime())
				stats.setBestTimeBeat(true);
			if (stats.getTimesPlayed() == 0)
				System.out.println("This was the first time played.  Come back for more!!!");
			else {
				System.out.println("Game type = "+g.getOperationString());
				System.out.println("Total times you've played this game:  "+stats.getTimesPlayed());
				System.out.println("Total time elapsed during game play:  "+stats.getTotalTime() + " seconds");
				System.out.println("Average time:  "+stats.getTotalTime() / stats.getTimesPlayed());
				if (stats.isBestTimeBeat()) 
					System.out.println("You beat your best time!");
				else {
					System.out.println("Your best time was "+stats.getBestTime()+".  This time:  "+ g.getElapsedTime() + ".");
					System.out.println("Try again to beat it!");
				}
			}
			System.out.println("====================================");
			choice = Console.getString(displayMenu());
		}
		System.out.println("Bye!");

	}

	// TODO - confirm division logic.  Should dividend be up to 100 or is that too difficult?
	private static Game playMathFacts(String choice) {
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

		Game g = new Game(user.getID(),choice, NUM_PROBLEMS);
		boolean correct = false;
		
		for (int i=1; i<=NUM_PROBLEMS; i++) {
			FactsNumbers fn = FactsNumbers.generateFactsNumbers(opr);
			int num1 = fn.getNumber1();
			int num2 = fn.getNumber2();
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
		g.setNumRight(numRight);
		g.setNumWrong(numWrong);
		return g;
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
	
//	private static void playMultiplicationFacts() {
//		System.out.println("Multiplication Facts\n");
//		int numRight = 0;
//		int numWrong = 0;
//		boolean correct = false;
//		
//		for (int i=1; i<=10; i++) {
//			int num1 = getRandomNbr();
//			int num2 = getRandomNbr();
//			while (!correct) {
//				System.out.println("Question #"+i+":");
//				int ans = Console.getInt(num1 +" x " + num2 + " = ");
//				if  (ans == num1 * num2) {
//					numRight++;
//					correct = true;
//					System.out.println("Correct!");
//				}
//				else {
//					numWrong++;
//					System.out.println("Wrong!  Try again.");
//				}
//			}
//			correct = false;
//		}
//		System.out.println("Stats:");
//		System.out.println("# right = "+ numRight);
//		System.out.println("# wrong = "+ numWrong);
//	}
//	
//	private static void playDivisionFacts() {
//		System.out.println("Function not yet implemented.");
//	}
//
//	private static void playAdditionFacts() {
//		System.out.println("Function not yet implemented.");
//	}
//
//	private static void playSubtractionFacts() {
//		System.out.println("Function not yet implemented.");
//	}

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

	private static void userLogin() {
		while (!loggedOn) {
			System.out.println("");
			String userName = Console.getString("Enter userName:  ");
			String pwd = Console.getString("Enter Password:  ");

			UserDB udb = new UserDB();
			User u = udb.getUser(userName, pwd);

			if (u!=null) {
				System.out.println("Welcome "+u.getFirstName()+"!!");
				user = u;
				loggedOn = true;
			}
			else {
				System.out.println("Invalid login.  Please try again.");
			}
		}
	}
	
	private static boolean saveGame(Game g) {
		Boolean success = false;
		GameDB gdb = new GameDB();
		success = gdb.addGame(g);
		return success;
	}
	
	private static AllStats getStatsForUser(User u, String type) {
		GameDB gdb = new GameDB();
		int count = 0;
		int bestScore = 0;
		double bestTime = 0.0;
		double totalTime = 0.0;
		ArrayList<Game> games = gdb.getAllForUserAndType(u, type);
		AllStats stats = new AllStats(u, type);
		for (Game g: games) {
			stats.getGames().add(g);
			count++;
			if (g.getNumRight()>bestScore) bestScore = g.getNumRight();
			if (bestTime == 0) {
				bestTime = g.getElapsedTime();
			}
			else if (g.getElapsedTime() < bestTime) {
				bestTime = g.getElapsedTime();
			}
			totalTime += g.getElapsedTime();
		}
		stats.setBestScore(bestScore);
		stats.setBestTime(bestTime);
		stats.setTimesPlayed(count);
		stats.setTotalTime(totalTime);
		return stats;
	}
}
