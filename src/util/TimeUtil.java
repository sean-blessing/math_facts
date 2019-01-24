package util;

public class TimeUtil {
	public static String getTimeString(double time) {
		StringBuilder sb = new StringBuilder();
		// if time is greater than 60, convert to minutes, seconds
		if (time < 60) {
			sb.append(time + " seconds");
		}
		else {
			// cast to int to get minutes
			int min = (int)(time / 60);
			// remainder will be seconds
			double sec = time % 60;
			sb.append(min + ":"+sec+" (mm:ss)");
		}
		return sb.toString();
	}
	

}
