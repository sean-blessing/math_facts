package business;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;

import db.DBUtil;

public class GameDB {
	
	public GameDB() {
		
	}
	
	public ArrayList<Game> getAll() {
		ArrayList<Game>  all = new ArrayList<>();
		String sql = "select * from game";
		try (Connection conn = DBUtil.getConnection();
				Statement statement = conn.createStatement();
				ResultSet rs = statement.executeQuery(sql))
		{
			while (rs.next()) {
				Game u = getGameFromRS(rs);
				all.add(u);
			}
		}
		catch (SQLException sqle) {
			System.out.println("Error getting all game rows."); 
			sqle.printStackTrace();
		}
		
		return all;
	}

	public Game getGame(int uId) {
		Game u = null;
		String sql = "select * from game where id = ?";
		try (Connection conn = DBUtil.getConnection();
				PreparedStatement ps = conn.prepareStatement(sql))
		{	
			ps.setInt(1, uId);
			ResultSet rs = ps.executeQuery();
			
			while (rs.next()) {
				u = getGameFromRS(rs);
			}
			rs.close();
		}
		catch (SQLException sqle) {
			System.out.println("Error getting game for id:"+uId+"."); 
			sqle.printStackTrace();
		}
		
		return u;
	}

//	public Game getGame(String uName, String pwd) {
//		Game u = null;
//		String sql = "select * from game where GameName = ? and Password = ?";
//		try (Connection conn = DBUtil.getConnection();
//				PreparedStatement ps = conn.prepareStatement(sql))
//		{	
//			ps.setString(1, uName);
//			ps.setString(2, pwd);
//			ResultSet rs = ps.executeQuery();
//			
//			while (rs.next()) {
//				u = getGameFromRS(rs);
//			}
//			rs.close();
//		}
//		catch (SQLException sqle) {
//			System.out.println("Error getting game for gameName:"+uName+" and pwd:"+pwd+"."); 
//			sqle.printStackTrace();
//		}
//		
//		return u;
//	}

	private Game getGameFromRS(ResultSet rs) throws SQLException {
		int id = rs.getInt(1);
		int uid = rs.getInt(2);
		String type = rs.getString(3);
		Timestamp dtPlayed = rs.getTimestamp(4);
		long start = rs.getLong(5);
		long end = rs.getLong(6);
		double elapsed = rs.getDouble(7);
		int numQuestions = rs.getInt(8);
		int numRight = rs.getInt(9);
		int numWrong = rs.getInt(10);
		Game u = new Game(id,uid,type,dtPlayed,start,end,elapsed,numQuestions,numRight,numWrong);
		return u;
	}
}
