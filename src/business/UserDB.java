package business;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import db.DBUtil;

public class UserDB {
	
	public UserDB() {
		
	}
	
	public ArrayList<User> getAll() {
		ArrayList<User>  all = new ArrayList<>();
		String sql = "select * from user";
		try (Connection conn = DBUtil.getConnection();
				Statement statement = conn.createStatement();
				ResultSet rs = statement.executeQuery(sql))
		{
			while (rs.next()) {
				User u = getUserFromRS(rs);
				all.add(u);
			}
		}
		catch (SQLException sqle) {
			System.out.println("Error getting all user rows."); 
			sqle.printStackTrace();
		}
		
		return all;
	}

	public User getUser(int uId) {
		User u = null;
		String sql = "select * from user where id = ?";
		try (Connection conn = DBUtil.getConnection();
				PreparedStatement ps = conn.prepareStatement(sql))
		{	
			ps.setInt(1, uId);
			ResultSet rs = ps.executeQuery();
			
			while (rs.next()) {
				u = getUserFromRS(rs);
			}
			rs.close();
		}
		catch (SQLException sqle) {
			System.out.println("Error getting user for id:"+uId+"."); 
			sqle.printStackTrace();
		}
		
		return u;
	}

	public User getUser(String uName, String pwd) {
		User u = null;
		String sql = "select * from user where UserName = ? and Password = ?";
		try (Connection conn = DBUtil.getConnection();
				PreparedStatement ps = conn.prepareStatement(sql))
		{	
			ps.setString(1, uName);
			ps.setString(2, pwd);
			ResultSet rs = ps.executeQuery();
			
			while (rs.next()) {
				u = getUserFromRS(rs);
			}
			rs.close();
		}
		catch (SQLException sqle) {
			System.out.println("Error getting user for userName:"+uName+" and pwd:"+pwd+"."); 
			sqle.printStackTrace();
		}
		
		return u;
	}

	private User getUserFromRS(ResultSet rs) throws SQLException {
		int id = rs.getInt(1);
		String uName = rs.getString(2);
		String pwd = rs.getString(3);
		String fName = rs.getString(4);
		String lName = rs.getString(5);
		User u = new User(id,uName,pwd,fName,lName);
		return u;
	}
}
