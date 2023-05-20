package dao;

import java.sql.Date;
import java.sql.SQLException;

import pojo.User;

public interface User_Dao {

	User signIn(String email,String password) throws SQLException;

	User signUp(String firstName, String lastName, String email, String password, Date date, int status,
			String role) throws SQLException;
	
	
}
