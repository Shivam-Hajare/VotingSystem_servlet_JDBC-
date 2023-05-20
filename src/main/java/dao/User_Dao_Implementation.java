package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import pojo.User;
import utils.DbConnection;

public class User_Dao_Implementation implements User_Dao {
	private Connection cn;
	private PreparedStatement pst1,pst2;
	User user = null;

	public User_Dao_Implementation() {
		super();
		try {
			cn = DbConnection.openConnection();
			pst1 = cn.prepareStatement("select * from users2 where email=? and password=?");
			
			pst2=cn.prepareStatement("insert into users2 values(default,?,?,?,?,?,?,?)" );
			System.out.println("User doa is created");
			
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public User signIn(String email, String password) throws SQLException {
		pst1.setString(1, email);
		pst1.setString(2, password);

		try (ResultSet rst = pst1.executeQuery()) {
			// int id, String firstName, String lastName, String email, String password, int
			// status, Date dob,
			// String role
			if (rst.next()) {
				int id = rst.getInt("id");
				String firstName = rst.getString("first_name");
				String lastName = rst.getString("last_name");
				int status = rst.getInt("status");
				Date dob = rst.getDate("dob");
				String role = rst.getString("role");
				user = new User(id, firstName, lastName, email, password, dob, status, role);
				return user;
			}
		}
		return null;
	}
	public void clanUp() throws SQLException
	{
		try {
			if(pst1!=null)
			{
				pst1.close();
				DbConnection.closeConnection();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public User signUp(String firstName, String lastName, String email, String password, Date date, int status,
			String role) throws SQLException {
		User user;
		pst2.setString(1, firstName);
		
		pst2.setString(2, lastName);

		pst2.setString(3, email);

		pst2.setString(4, password);

		pst2.setDate(5, date);

		pst2.setInt(6, status);

		pst2.setString(7, role);

		int rst=pst2.executeUpdate();
		
		if(rst==1)
		{
			user = new User(firstName, lastName, email, password, date, status, role);
			return user;
			
		}
		
		return null;
	}

	
}
