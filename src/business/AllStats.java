package business;

import java.util.ArrayList;

import util.TimeUtil;

public class AllStats {

	private User user = null;
	private String statsType = "";
	private ArrayList<Game> games = null;
	private int timesPlayed = 0;
	private int bestScore = 0;
	// times are stored in seconds
	private double bestTime = 0.0;
	private double totalTime = 0.0;
	private boolean bestTimeBeat = false;
	
	public AllStats(User user, String statsType) {
		this.user = user;
		this.statsType = statsType;
		games = new ArrayList<>();
	}
	
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public String getStatsType() {
		return statsType;
	}
	public void setStatsType(String statsType) {
		this.statsType = statsType;
	}
	public ArrayList<Game> getGames() {
		return games;
	}

	public void setGames(ArrayList<Game> games) {
		this.games = games;
	}

	public int getTimesPlayed() {
		return timesPlayed;
	}
	public void setTimesPlayed(int timesPlayed) {
		this.timesPlayed = timesPlayed;
	}
	public int getBestScore() {
		return bestScore;
	}

	public void setBestScore(int bestScore) {
		this.bestScore = bestScore;
	}

	public double getBestTime() {
		return bestTime;
	}
	public void setBestTime(double bestTime) {
		this.bestTime = bestTime;
	}
	public double getTotalTime() {
		return totalTime;
	}
	public void setTotalTime(double totalTime) {
		this.totalTime = totalTime;
	}
	
	public String getTotalTimeString() {
		return TimeUtil.getTimeString(totalTime);
	}

	public String getBestTimeString() {
		return TimeUtil.getTimeString(bestTime);
	}

	public boolean isBestTimeBeat() {
		return bestTimeBeat;
	}

	public void setBestTimeBeat(boolean bestTimeBeat) {
		this.bestTimeBeat = bestTimeBeat;
	}

	@Override
	public String toString() {
		return "AllStats [user=" + user + ", statsType=" + statsType + ", timesPlayed=" + timesPlayed + ", bestTime="
				+ bestTime + ", bestScore=" + bestScore + ", totalTime=" + totalTime + ", bestTimeBeat="+bestTimeBeat+"]";
	}
	
}
