package business;

import java.sql.Timestamp;

public class Game {
	private int id = 0;
	private int userID;
	private String type;
	private Timestamp datePlayed;
	private long startTime;
	private long endTime;
	private double elapsedTime;
	private int numQuestions = 0;
	private int numRight = 0;
	private int numWrong = 0;
	
	public Game(int id, int userID, String type, Timestamp datePlayed, long startTime, long endTime, double elapsedTime,
			int numQuestions, int numRight, int numWrong) {
		super();
		this.id = id;
		this.userID = userID;
		this.type = type;
		this.datePlayed = datePlayed;
		this.startTime = startTime;
		this.endTime = endTime;
		this.elapsedTime = elapsedTime;
		this.numQuestions = numQuestions;
		this.numRight = numRight;
		this.numWrong = numWrong;
	}

	public Game(int uid, String t, int n) {
		userID = uid;
		type = t;
		numQuestions = n;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getUserID() {
		return userID;
	}

	public void setUserID(int userID) {
		this.userID = userID;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Timestamp getDatePlayed() {
		return datePlayed;
	}

	public void setDatePlayed(Timestamp datePlayed) {
		this.datePlayed = datePlayed;
	}

	public long getStartTime() {
		return startTime;
	}

	public void setStartTime(long startTime) {
		this.startTime = startTime;
	}

	public long getEndTime() {
		return endTime;
	}

	public void setEndTime(long endTime) {
		this.endTime = endTime;
	}

	public double getElapsedTime() {
		return elapsedTime;
	}

	public void setElapsedTime(double elapsedTime) {
		this.elapsedTime = elapsedTime;
	}

	public int getNumQuestions() {
		return numQuestions;
	}

	public void setNumQuestions(int numQuestions) {
		this.numQuestions = numQuestions;
	}

	public int getNumRight() {
		return numRight;
	}
	public void setNumRight(int numRight) {
		this.numRight = numRight;
	}
	public int getNumWrong() {
		return numWrong;
	}
	public void setNumWrong(int numWrong) {
		this.numWrong = numWrong;
	}

}
